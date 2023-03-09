package com.Debuggers.MobiliteInternational.Services;
import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CandidacyService {
    List<Candidacy> getAllCandidancy();
    public Candidacy getCandidancyById(Long id);


    Candidacy addCandidature(Candidacy c, Long offerId,
                             @RequestParam("attachments") MultipartFile attachment,
                             @RequestParam("B2Fr") MultipartFile B2Fr,
                             @RequestParam("B2Eng") MultipartFile B2Eng
            , Principal principal)throws IOException;

    public Candidacy updateCandidancy(Candidacy c, Long idCandidacy);
    void deleteCandidacy(Long id);
    void RestoreCandidacy(Long id);
    void deleteCandidacyFromDB(long candidatureId);
    public List<Candidacy> getCandidacyByOffer(Long idOffer);
    public List<Candidacy> getCandiacyByUser(Long idUser);
    public List<Candidacy> getCandiacyByUserAndOffer(Long idUser,Long idOffer);
    public List<Candidacy> getCandidacyByStatus(Status status, Long userId, Long offerId);
    public List<Candidacy> getCandidacyByOfferOrderByMarks(Long offerId);
    void acceptBestCandidatures(Long offerId);


}

