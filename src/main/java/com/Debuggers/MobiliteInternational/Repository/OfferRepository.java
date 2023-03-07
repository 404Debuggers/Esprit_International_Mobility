package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Enum.StudyField;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface OfferRepository extends JpaRepository<Offer,Long> {

    @Query("SELECT o FROM Offer o WHERE o.fieldOfStudy = :fieldOfStudy")
    List<Offer> findOffersByStudyField(@Param("fieldOfStudy") StudyField fieldOfStudy);

    @Query("SELECT o FROM Offer o GROUP BY o.fieldOfStudy")
    Map<StudyField, List<Offer>> findOffersGroupedByFieldOfStudy();

   // List<Offer> findOffersByPropertyContaining(StudyField fieldOfStudy);
}
