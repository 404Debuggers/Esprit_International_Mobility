package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview,Long> {

    public List<Interview> findInterviewByCandidacy_CandidatureId(Long candidatureId);
}
