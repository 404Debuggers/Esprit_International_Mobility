package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Services.CandidacyService;
import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CandidacyServiceImpl implements CandidacyService {
    @Autowired
    public CandidacyRepository candidacyRepository;

    @Override
    public List<Candidacy> getAllCandidancy() {
        return candidacyRepository.findAll();
    }

    @Override
    public Candidacy getCandidancyById(long id) {
        return candidacyRepository.findById(id).orElse(null);
    }

    @Override
    public Candidacy addCandidature(Candidacy c) {
        return candidacyRepository.save(c);
    }

    @Override
    public Candidacy updateCandidancy(Candidacy c) {
        return candidacyRepository.save(c);
    }

    @Override
    public void deleteCandidancy(long id) {
        candidacyRepository.deleteById(id);
    }


}
