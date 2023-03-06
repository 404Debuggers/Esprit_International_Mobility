package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidacyRepository extends JpaRepository<Candidacy ,Long> {

    List<Candidacy> findByOffer(Offer offer);
}
