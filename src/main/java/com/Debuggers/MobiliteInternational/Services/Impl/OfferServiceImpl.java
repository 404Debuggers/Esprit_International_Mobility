package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Enum.StudyField;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Entity.UserOfferFav;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.OfferRepository;
import com.Debuggers.MobiliteInternational.Repository.UserOfferFavRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    OfferRepository offerRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserOfferFavRepository userOfferFavRepository;




    private final JavaMailSender javaMailSender;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    private CandidacyRepository candidacyRepository;


    public OfferServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Offer addOffer(Offer offer) {

        List<String> userEmails = userService.getAllUserEmails();
        String subject = "New offer available";
        String body =  "Dear user,\n\n" +
                "A new offer has just been added to our website!\n\n" +
                "Title: " + offer.getTitle() + "\n" +
                "Description: " + offer.getDescription() + "\n" +
                "Link: https://www.example.com/offers/" + offer.getOfferId() + "\n\n" +
                "Thank you for using our service.\n" +
                "Best regards,\n" +
                "The Example Team";
        sendEmail(userEmails, subject, body);
        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> getAllOffers() {
        List<Offer> offer = (List<Offer>) offerRepository.findAll();
        return offer;
    }

    @Override
    public Offer updateOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public void suppOffer(Long offerId) {
        offerRepository.deleteById(offerId);
    }

    @Override
    public void sendEmail(List<String> to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to.toArray(new String[to.size()]));
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);

    }

    @Override
    public void addFavandAssigntouser(Principal p , Long offerId) {

        User user = userRepository.findUserByEmail(p.getName()).orElseThrow(null);
        Offer offer = offerRepository.findById(offerId).orElseThrow(null);
        UserOfferFav favorite = new UserOfferFav();
        favorite.setIsFavorite(Boolean.TRUE);
        userOfferFavRepository.save(favorite);

        user.getUserFavOffers().add(favorite);
        userRepository.save(user);

        favorite.setOffer(offer);
        userOfferFavRepository.save(favorite);


    }

    @Override
    public List<Offer> getFavOffers(Principal principal) {
        String username= principal.getName();
        System.out.println(username);
        User user = userRepository.findUserByEmail(username).orElseThrow(null);
        List<UserOfferFav> listfav = user.getUserFavOffers();
        List<Offer> favoriteOffers = new ArrayList<>();
        for (UserOfferFav uof: listfav
        ) {
            favoriteOffers.add(uof.getOffer());

        }


        return favoriteOffers ;
    }

        public List<StudyField> extractPropertiesFromOffers(Principal p) {
            List<Offer> offer = (List<Offer>) getFavOffers(p);
            List<StudyField> properties = new ArrayList<>();
            for (Offer offers : offer) {
                properties.add(offers.getFieldOfStudy());
                //properties.add(offer.getFieldOfStudy());

                // Add more properties as needed
            }
            return properties;
        }


  /*public List<Offer> findOffersWithSimilarProperties(Long user_Id) {
        List<Offer> similarOffers = new ArrayList<>();
        List<StudyField> properties = (List<StudyField>) extractPropertiesFromOffers(user_Id);
        for (StudyField property : properties) {
            List<Offer> offers = offerRepository.findOffersByStudyField(property);
            similarOffers.addAll(offers);
        }
        return similarOffers;
    }*/

   /* public List<Offer> findOffersWithSimilarProperties(Long user_Id) {
        List<Offer> similarOffers = new ArrayList<>();
        List<Offer> dissimilarOffers = new ArrayList<>();
        List<StudyField> properties = (List<StudyField>) extractPropertiesFromOffers(user_Id);
        for (StudyField property : properties) {
            List<Offer> offers = offerRepository.findOffersByStudyField(property);
            for (Offer offer : offers) {
                if (offer.getFieldOfStudy().equals(property)) {
                    similarOffers.add(offer);
                } else if (offer.getFieldOfStudy().equals(property) == false ) {
                    dissimilarOffers.add(offer);
                }
            }
        }
        List<Offer> finalOffers = similarOffers;
        for (Offer o :dissimilarOffers
             ) {
            finalOffers.add(o);
        }
        return dissimilarOffers;
    }
*/

    public List<Offer>  findOffersWithSimilarProperties(Principal principal) {

        List<Offer> similarOffers = new ArrayList<>();
        List<Offer> dissimilarOffers = new ArrayList<>();
        int var = 0 ;
        List<StudyField> properties = (List<StudyField>) extractPropertiesFromOffers(principal);
        List<Offer> offers = offerRepository.findAll();
        for (Offer offer : offers) {
            var = 0 ;
            for (StudyField property : properties) {

                if (offer.getFieldOfStudy().equals(property))  {
                    similarOffers.add(offer);
                } else  {

                    var = var+1 ;
                }
            }
            if (var == properties.size())
            {  dissimilarOffers.add(offer) ;}
        }

        List<Offer> finalOffers = new ArrayList<>(similarOffers);
        finalOffers.addAll(dissimilarOffers);
        return finalOffers ;
    }


    public Offer addOfferAtSpecificTime(Offer offer, LocalDateTime time) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        long delay = Duration.between(LocalDateTime.now(), time).toMillis();
        executor.schedule(() -> {
            List<String> userEmails = userService.getAllUserEmails();
            String subject = "New offer available";
            String body =  "Dear user,\n\n" +
                    "A new offer has just been added to our website!\n\n" +
                    "Title: " + offer.getTitle() + "\n" +
                    "Description: " + offer.getDescription() + "\n" +
                    "Link: https://www.example.com/offers/" + offer.getOfferId() + "\n\n" +
                    "Thank you for using our service.\n" +
                    "Best regards,\n" +
                    "The Example Team";
            sendEmail(userEmails, subject, body);
            offerRepository.save(offer);
        }, delay, TimeUnit.MILLISECONDS);
        executor.shutdown();
        return offer;}
    /*
    @Override
    public List<Offer> findSimilarOffersForUser(Long userId) {
        List<Offer> favoriteOffers = getFavOffers(userId);
        List<String> favoriteProperties = extractPropertiesFromOffers(favoriteOffers);
        List<Offer> similarOffers = findOffersWithSimilarProperties(favoriteProperties);
        return similarOffers;
    }*/






    public Map<String, Double> generateCharts(Offer offer) {

        List<Candidacy> candidacies = candidacyRepository.findByOffer(offer);
        int total = candidacies.size();
        Map<String, Integer> counts = new HashMap<>();
        for (Candidacy candidacy : candidacies) {
            User user = candidacy.getUser();
            String category = user.getSexe() + "," + getAgeCategory(calculateAge(user.getDateNaissance()));
            counts.put(category, counts.getOrDefault(category, 0) + 1);
        }
        Map<String, Double> percentages = new HashMap<>();
        for (String category : counts.keySet()) {
            int count = counts.get(category);
            double percentage = ((double) count / total) * 100.0;
            percentages.put(category, percentage);
        }
        return percentages;
    }
    public static int calculateAge(Date birthDate) {
        if (birthDate == null) {
            return 0;
        }
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthDate);
        Calendar nowCal = Calendar.getInstance();
        int age = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
        if (nowCal.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    // Helper method to categorize age into age groups
    private String getAgeCategory(int age) {
        if (age < 18) {
            return "Under 18";
        } else if (age < 25) {
            return "18-24";
        } else if (age < 35) {
            return "25-34";
        } else if (age < 45) {
            return "35-44";
        } else if (age < 55) {
            return "45-54";
        } else if (age < 65) {
            return "55-64";
        } else {
            return "65 and over";
        }
    }



    @Scheduled(cron ="0 * * * * ?" ) // Run at midnight every day
    public void updateArchivedOffers() {
        List<Offer> offers = offerRepository.findAll();
        for (Offer offer : offers) {
            if (offer.getDateFin() != null && offer.getDateFin().before(new Date()) && offer.getArchive()==true) {
                offer.setArchive(false);
                offerRepository.save(offer);
            }
        }
    }
  @Override
  public void deleteFavandRemoveFromUser(Principal p, Long offerId) {

    User user = userRepository.findUserByEmail(p.getName()).orElseThrow(null);
    Offer offer = offerRepository.findById(offerId).orElseThrow(null);

    // find the favorite and remove it from the user's favorites
    Optional<UserOfferFav> favoriteOptional = user.getUserFavOffers().stream()
      .filter(fav -> fav.getOffer().getOfferId().equals(offerId))
      .findFirst();
    favoriteOptional.ifPresent(favorite -> {
      user.getUserFavOffers().remove(favorite);
      userRepository.save(user);
        System.out.println(user);
    });

    // delete the favorite
    favoriteOptional.ifPresent(userOfferFavRepository::delete);
  }


}
