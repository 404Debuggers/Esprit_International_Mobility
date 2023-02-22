package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidacyRepository extends JpaRepository<Candidacy ,Long> {
    List<Candidacy> findCandidaciesByOffer(Offer offer);
    List<Candidacy> findCandidaciesByOfferOrderByMarksDesc(Offer o);
    List<Candidacy> findCandidaciesByUser(User user);
    List<Candidacy> findCandidaciesByUserAndOffer(User user, Offer offer);
    @Query("SELECT c FROM Candidacy c WHERE c.status = :status AND c.user.user_Id = :userId AND c.offer.offerId = :offerId")
    List<Candidacy> findCandidaciesByStatusAndUserAndOffer(@Param("status") Status status, @Param("userId") Long userId, @Param("offerId") Long offerId);
}