package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Interview;

import java.util.List;

public interface InterviewService {
    void createEntretien(Interview interview, Long candidatureId, Long universityId);

    void deleteEntretien(Long interviewId);

    List<Interview> getAllEntretiens();

    Interview getEntretienById(Long interviewId);

    Interview updateEntretien(Long interviewId, Interview interview);


    Interview updateEntretienne(Interview interview);

    List<Interview> getEntretienBycandidature(Long candidatureId);
}
