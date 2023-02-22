package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Entity.Offer;

import java.util.List;

public interface CandidacyService {
    List<Candidacy> getAllCandidancy();
    public Candidacy getCandidancyById(Long id);
    public Candidacy addCandidature(Candidacy c,Long offerId,Long userId);
    public Candidacy updateCandidancy(Candidacy c,Long idCandidacy);
    public void deleteCandidancy(Long id);
    public List<Candidacy> getCandidacyByOffer(Long idOffer);
    public List<Candidacy> getCandiacyByUser(Long idUser);
    public List<Candidacy> getCandiacyByUserAndOffer(Long idUser,Long idOffer);
    public List<Candidacy> getCandidacyByStatus(Status status, Long userId, Long offerId);


}
