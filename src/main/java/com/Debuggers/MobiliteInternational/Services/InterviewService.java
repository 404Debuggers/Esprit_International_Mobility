package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Interview;
import com.Debuggers.MobiliteInternational.Entity.Interview_Event;

import java.util.List;

public interface InterviewService {
    Interview createEntretien(Interview_Event interview, Long candidatureId, Long universityId);

    void deleteEntretien(Long interviewId);

    List<Interview> getAllEntretiens();

    Interview getEntretienById(Long interviewId);

    Interview updateEntretien(Long interviewId, Interview_Event interview);


    Interview updateEntretienne(Interview interview);

    List<Interview> getEntretienBycandidature(Long candidatureId);
}
