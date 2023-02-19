package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Interview;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.InterviewRepository;
import com.Debuggers.MobiliteInternational.Repository.UniversityRepository;
import com.Debuggers.MobiliteInternational.Services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {
    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    CandidacyRepository candidacyRepository;
    @Autowired
    UniversityRepository universityRepository;
    public void createEntretien(Interview interview, Long candidatureId) {
        Candidacy candidacy = candidacyRepository.findById(candidatureId).orElse(null);
        interview.setCandidacy(candidacy);
        interviewRepository.save(interview);
    }

    @Override
    public void deleteEntretien(Long interviewId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElse(null);

        interviewRepository.delete(interview);
    }

    @Override
    public List<Interview> getAllEntretiens() {
        return interviewRepository.findAll();
    }

    @Override
    public Interview getEntretienById(Long interviewId) {
        return interviewRepository.findById(interviewId)
                .orElse(null);
    }

    @Override
    public Interview updateEntretien(Long interviewId, Interview interview) {
        Interview existingInterview = interviewRepository.findById(interviewId)
                .orElse(null);


        return interviewRepository.save(existingInterview);
    }

    @Override
    public Interview updateEntretienne(Interview interview) {
        return interviewRepository.save(interview);
    }

    @Override
    public List<Interview> getEntretienBycandidature(Long candidatureId) {
        return  interviewRepository.findInterviewByCandidacy_CandidatureId(candidatureId);
    }
}
