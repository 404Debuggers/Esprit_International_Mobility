package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.OfferRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.CandidacyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Set<Candidacy> vd2 = new HashSet<>();
        user.setCandidacySet(vd2);
        Set<Candidacy> vd1 = new HashSet<>();
        of.setCandidacySet(vd1);
        if(candidacies.isEmpty() ) {
            c.setOffer(of);
            c.setUser(user);
            of.getCandidacySet().add(c);
            user.getCandidacySet().add(c);
        return candidacyRepository.save(c);
        }
        else return null;

    }

    @Override
    public Candidacy updateCandidancy(Candidacy c) {
        return candidacyRepository.save(c);
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
