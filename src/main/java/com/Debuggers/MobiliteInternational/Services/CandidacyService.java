package com.Debuggers.MobiliteInternational.Services;
import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;

import java.util.List;

public interface CandidacyService {
    List<Candidacy> getAllCandidancy();
    public Candidacy getCandidancyById(long id);
    public Candidacy addCandidature(Candidacy c);
    public Candidacy updateCandidancy(Candidacy c);
    public void deleteCandidancy(long id);
}
