package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.OfferRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.CandidacyService;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


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
        return candidacyRepository.findById(id).orElse(null);
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

        FileOutputStream fileOutputStreamEng = new FileOutputStream(B2EngPath);
        fileOutputStreamEng.write(B2EngPath.getBytes());
        fileOutputStreamEng.close();
        c.setB2Eng(B2EngPath);




        Offer of = offerRepository.findById(offerId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        List<Candidacy> candidacies = candidacyRepository.findCandidaciesByUserAndOffer(user,of);
        LocalDate d = of.getDeadline();
        if(candidacies.isEmpty()) {
            if( LocalDate.now().isBefore(d)){
                c.setOffer(of);
                c.setUser(user);
                c.setStatus(Status.ON_HOLD);
                of.getCandidacySet().add(c);
                user.getCandidacySet().add(c);
                System.out.println("candidature ajouté");
                return candidacyRepository.save(c);
            }
            System.out.println("candidature fermé");
            return null;
        }
        System.out.println("candidature déja ajouté");
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
    public void deleteCandidancy(Long id) {
        candidacyRepository.deleteById(id);
    }

    @Override
    public List<Candidacy> getCandidacyByOffer(Long idOffer) {
        Offer offer = offerRepository.findById(idOffer).orElse(null);
        return candidacyRepository.findCandidaciesByOffer(offer);
    }

    @Override
    public List<Candidacy> getCandiacyByUser(Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        return candidacyRepository.findCandidaciesByUser(user);
    }

    @Override
    public List<Candidacy> getCandiacyByUserAndOffer(Long idUser, Long idOffer) {
        User user = userRepository.findById(idUser).orElse(null);
        Offer offer = offerRepository.findById(idOffer).orElse(null);
        return candidacyRepository.findCandidaciesByUserAndOffer(user,offer);
    }

    @Override
    public List<Candidacy> getCandidacyByStatus(Status status, Long userId, Long offerId) {

        return candidacyRepository.findCandidaciesByStatusAndUserAndOffer(status, userId, offerId);
    }

    @Override
    public List<Candidacy> getCandidacyByOfferOrderByMarks(Long offerId) {
        Offer o = offerRepository.findById(offerId).orElse(null);
        return candidacyRepository.findCandidaciesByOfferOrderByMarksDesc(o);
    }




}
