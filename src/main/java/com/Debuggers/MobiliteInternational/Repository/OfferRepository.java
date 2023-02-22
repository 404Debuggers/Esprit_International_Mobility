package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Enum.StudyField;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer,Long> {

    @Query("SELECT o FROM Offer o WHERE o.fieldOfStudy = :fieldOfStudy")
    List<Offer> findOffersByStudyField(@Param("fieldOfStudy") StudyField fieldOfStudy);

   // List<Offer> findOffersByPropertyContaining(StudyField fieldOfStudy);
}
