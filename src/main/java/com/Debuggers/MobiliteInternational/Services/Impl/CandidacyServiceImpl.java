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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Candidacy addCandidature(Candidacy c, Long userId,Long offerId) {
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




}
