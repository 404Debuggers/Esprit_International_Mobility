package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview,Long> {
    @Transactional
    @Modifying
    @Query("update Interview i set i.link = ?1 where i.calendar = ?2")
    int updateLinkByCalendar(String link, Date calendar);
    public List<Interview> findInterviewByCandidacy_CandidatureId(Long candidatureId);
}