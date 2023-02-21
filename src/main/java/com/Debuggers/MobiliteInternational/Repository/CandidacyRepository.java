package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidacyRepository extends JpaRepository<Candidacy ,Long> {
    List<Candidacy> findCandidaciesByOffer(Offer offer);
    List<Candidacy> findCandidaciesByUser(User user);
    List<Candidacy> findCandidaciesByUserAndOffer(User user, Offer offer);
}