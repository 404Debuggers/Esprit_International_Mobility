package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidacyRepository extends JpaRepository<Candidacy ,Long> {


    @Query("SELECT c FROM Candidacy c WHERE c.archive = true")
    List<Candidacy> findAll();
    @Query("SELECT c from Candidacy c WHERE c.candidatureId = ?1 AND c.archive=true ")
    Candidacy findCandidaciesByCandidatureIdAndArchiveTrue(long id);
    List<Candidacy> findCandidaciesByOfferAndArchive(Offer offer,boolean archive);
    @Query("SELECT c FROM Candidacy c WHERE c.offer = ?1 AND c.archive = true ORDER BY c.marks DESC")
    List<Candidacy> findCandidaciesByOfferOrderedByMarksDescWhereArchiveIsTrue(Offer o);
    List<Candidacy> findCandidaciesByUserAndArchive(User user,boolean archive);
    List<Candidacy> findCandidaciesByUserAndOfferAndArchive(User user, Offer offer,boolean archive);
    @Query("SELECT c FROM Candidacy c WHERE c.status = :status AND c.user.user_Id = :userId AND c.offer.offerId = :offerId AND c.archive = true ")
    List<Candidacy> findCandidaciesByStatusAndUserAndOffer(@Param("status") Status status,
                                                           @Param("userId") Long userId,
                                                           @Param("offerId") Long offerId);


    List<Candidacy> findByOffer(Offer offer);



}