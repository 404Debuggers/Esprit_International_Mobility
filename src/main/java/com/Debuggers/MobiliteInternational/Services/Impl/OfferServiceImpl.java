package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Enum.StudyField;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Entity.UserOfferFav;
import com.Debuggers.MobiliteInternational.Repository.OfferRepository;
import com.Debuggers.MobiliteInternational.Repository.UserOfferFavRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public void addFavandAssigntouser(Long user_Id, Long offerId) {

        User user = userRepository.findById(user_Id).orElseThrow(null);
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
    public List<Offer> getFavOffers(Long user_Id) {

        User user = userRepository.findById(user_Id).orElseThrow(null);
        List<UserOfferFav> listfav = user.getUserFavOffers();
        List<Offer> favoriteOffers = new ArrayList<>();
        for (UserOfferFav uof: listfav
             ) {
            favoriteOffers.add(uof.getOffer());

        }


        return favoriteOffers ;
    }

  public List<StudyField> extractPropertiesFromOffers(Long user_Id) {
      List<Offer> offer = (List<Offer>) getFavOffers(user_Id);
    List<StudyField> properties = new ArrayList<>();
    for (Offer offers : offer) {

        properties.add(offers.getFieldOfStudy());
        //properties.add(offer.getFieldOfStudy());

        // Add more properties as needed
    }
    return properties;
}


  public List<Offer> findOffersWithSimilarProperties(Long user_Id) {
        List<Offer> similarOffers = new ArrayList<>();
        List<StudyField> properties = (List<StudyField>) extractPropertiesFromOffers(user_Id);
        for (StudyField property : properties) {
            List<Offer> offers = offerRepository.findOffersByStudyField(property);
            similarOffers.addAll(offers);
        }
        return similarOffers;
    }
    /*
    @Override
    public List<Offer> findSimilarOffersForUser(Long userId) {
        List<Offer> favoriteOffers = getFavOffers(userId);
        List<String> favoriteProperties = extractPropertiesFromOffers(favoriteOffers);
        List<Offer> similarOffers = findOffersWithSimilarProperties(favoriteProperties);
        return similarOffers;
    }*/



}
