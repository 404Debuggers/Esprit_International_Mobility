package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Enum.Niveau;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.OfferRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.CandidacyService;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CandidacyServiceImpl implements CandidacyService {
    @Autowired
    public CandidacyRepository candidacyRepository;
    @Autowired
    public OfferRepository offerRepository;
    @Autowired
    public UserRepository userRepository;
    @Override
    public List<Candidacy> getAllCandidancy() {

        return candidacyRepository.findAll();
    }
    @Override
    public Candidacy getCandidancyById(Long id) {
        return candidacyRepository.findCandidaciesByCandidatureIdAndArchiveTrue(id);
    }
    @Override
    public Candidacy addCandidature(Candidacy c, Long userId, Long offerId,
                                    @RequestParam("attachments") MultipartFile attachment,
                                    @RequestParam("B2Fr") MultipartFile B2Fr,
                                    @RequestParam("B2Eng") MultipartFile B2Eng
    )throws IOException {

        String attachmentPath = "C:\\Users\\Marwen\\Desktop\\Projet Pi 5edma\\Esprit_International_Mobility\\upload\\documents\\ReleveDeNote" + attachment.getOriginalFilename();
        String B2FrPath = "C:\\Users\\Marwen\\Desktop\\Projet Pi 5edma\\Esprit_International_Mobility\\upload\\documents\\B2Francais" + B2Fr.getOriginalFilename();
        String B2EngPath = "C:\\Users\\Marwen\\Desktop\\Projet Pi 5edma\\Esprit_International_Mobility\\upload\\documents\\B2Anglais" + B2Eng.getOriginalFilename();


        FileOutputStream fileOutputStream = new FileOutputStream(attachmentPath);
        fileOutputStream.write(attachment.getBytes());
        fileOutputStream.close();
        c.setAttachements(attachmentPath);

        File file = new File(attachmentPath);
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();


        stripper.setSortByPosition(true);
        stripper.setStartPage(0);
        stripper.setEndPage(0);

        stripper.setStartPage(1);
        stripper.setEndPage(document.getNumberOfPages());
        String text = stripper.getText(document);
        int startIndex = text.indexOf("Moyenne générale :");
        int endIndex = text.indexOf("\n", startIndex);
        String moyenneGeneraleText = text.substring(startIndex + 19, endIndex).trim();
        double moyenne = Double.parseDouble(moyenneGeneraleText);
        c.setMarks(moyenne);

        FileOutputStream fileOutputStreamfr = new FileOutputStream(B2FrPath);
        fileOutputStreamfr.write(B2Fr.getBytes());
        fileOutputStreamfr.close();
        c.setB2Fr(B2FrPath);

        PDDocument b2fr = PDDocument.load(new File(B2FrPath));
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String txt = pdfStripper.getText(b2fr);


        if (txt.contains("Niveau d’orientation : B2") ) {
            c.setLevelFr(Niveau.Valid);
        } else {
            c.setLevelFr(Niveau.Invalid);
        }

        b2fr.close();


        FileOutputStream fileOutputStreamEng = new FileOutputStream(B2EngPath);
        fileOutputStreamEng.write(B2Eng.getBytes());
        fileOutputStreamEng.close();
        c.setB2Eng(B2EngPath);

        PDDocument b2Ang = PDDocument.load(new File(B2EngPath));
        PDFTextStripper pStripper = new PDFTextStripper();
        String t = pStripper.getText(b2Ang);


        if (t.contains("ORIENTATION LEVEL : B2") ) {
            c.setLevelEng(Niveau.Valid);
        } else {
            c.setLevelEng(Niveau.Invalid);
        }

        b2Ang.close();



        Offer of = offerRepository.findById(offerId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        List<Candidacy> candidacies = candidacyRepository.findCandidaciesByUserAndOfferAndArchive(user,of,true);
        LocalDate d = of.getDeadline();
        if (user !=null) {
            if (candidacies.isEmpty()) {
                if (LocalDate.now().isBefore(d)) {
                    c.setOffer(of);
                    c.setUser(user);
                    c.setStatus(Status.ON_HOLD);
                    c.setArchive(true);
                    System.out.println("candidature ajouté");
                    return candidacyRepository.save(c);
                }
                System.out.println("candidature fermé");
                return null;
            }
            System.out.println("candidature déja ajouté ou ut");
            return null;
        }
        System.out.println("utilisateur introuvable");
        return null;
    }


    @Override
    public Candidacy updateCandidancy(Candidacy c,Long idCandidacy ) {
        Candidacy candidacy = candidacyRepository.findById(idCandidacy).orElse(null);
        LocalDate d= candidacy.getOffer().getDeadline();
        if(LocalDate.now().isBefore(d)){
            candidacy.setCoverLettre(c.getCoverLettre());
            candidacy.setAttachements(c.getAttachements());
            candidacy.setOption(c.getOption());
            candidacy.setLevelEng(c.getLevelEng());
            candidacy.setLevelFr(c.getLevelFr());
            return candidacyRepository.save(candidacy);
        }
        return null;
    }
    @Override
    public void deleteCandidacy(Long id) {
        Optional<Candidacy> candidacyOptional = candidacyRepository.findById(id);
        if (candidacyOptional.isPresent()) {
            Candidacy candidacy = candidacyOptional.get();
            candidacy.setArchive(false);
            candidacyRepository.save(candidacy);
        } else {
            // handle candidacy not found error
        }
    }
    @Override
    public void RestoreCandidacy(Long id) {
        Optional<Candidacy> candidacyOptional = candidacyRepository.findById(id);
        if (candidacyOptional.isPresent()) {
            Candidacy candidacy = candidacyOptional.get();
            candidacy.setArchive(true);
            candidacyRepository.save(candidacy);
        } else {
            // handle candidacy not found error
        }
    }
    @Override
    public void deleteCandidacyFromDB(long candidatureId) {
        candidacyRepository.deleteById(candidatureId);
    }

    @Override
    public List<Candidacy> getCandidacyByOffer(Long idOffer) {
        Offer offer = offerRepository.findById(idOffer).orElse(null);
        return candidacyRepository.findCandidaciesByOfferAndArchive(offer,true);
    }

    @Override
    public List<Candidacy> getCandiacyByUser(Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        return candidacyRepository.findCandidaciesByUserAndArchive(user,true);
    }

    @Override
    public List<Candidacy> getCandiacyByUserAndOffer(Long idUser, Long idOffer) {
        User user = userRepository.findById(idUser).orElse(null);
        Offer offer = offerRepository.findById(idOffer).orElse(null);
        return candidacyRepository.findCandidaciesByUserAndOfferAndArchive(user,offer,true);
    }

    @Override
    public List<Candidacy> getCandidacyByStatus(Status status, Long userId, Long offerId) {

        return candidacyRepository.findCandidaciesByStatusAndUserAndOffer(status, userId, offerId);
    }

    @Override
    public List<Candidacy> getCandidacyByOfferOrderByMarks(Long offerId) {
        Offer o = offerRepository.findById(offerId).orElse(null);
        return candidacyRepository.findCandidaciesByOfferOrderedByMarksDescWhereArchiveIsTrue(o);
    }
    @Override
    public void acceptBestCandidatures(Long offerId) {
        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new RuntimeException("Offer not found"));

        if (offer.getNbPlace() > 0) {
            List<Candidacy> candidacies = getCandidacyByOfferOrderByMarks(offerId);

            int availablePlaces = offer.getNbPlace();

            for (Candidacy candidacy : candidacies) {if (availablePlaces > 0) {
                candidacy.setStatus(Status.ACCEPTED);
                availablePlaces--;
                candidacyRepository.save(candidacy);
            } else {
                candidacy.setStatus(Status.REJECTED);
                candidacyRepository.save(candidacy);
            }
            }
        }
    }
}
