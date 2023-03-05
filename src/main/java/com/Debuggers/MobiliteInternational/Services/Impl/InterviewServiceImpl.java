package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.*;
import com.Debuggers.MobiliteInternational.Repository.EventRepository;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.InterviewRepository;
import com.Debuggers.MobiliteInternational.Repository.UniversityRepository;
import com.Debuggers.MobiliteInternational.Services.EmailSenderService;
import com.Debuggers.MobiliteInternational.Services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    CandidacyRepository candidacyRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    ReminderSchedulerImpl reminderScheduler;
    public Interview createEntretien(Interview_Event interview, Long candidatureId, Long universityId) {
        Interview newInteriew = new Interview();
        Event newEvent = new Event();

        Candidacy candidacy = candidacyRepository.findById(candidatureId).orElse(null);
        newInteriew.setCandidacy(candidacy);
        University university = universityRepository.findById(universityId).orElse(null);
        newInteriew.setUniversity(university);

        newInteriew.setLink(interview.getLink());
        newInteriew.setArchive(interview.getArchive());
        Interview interview1= interviewRepository.save(newInteriew);


        newEvent.setTitle(newInteriew.getCandidacy().getUser().getFirstName());
        newEvent.setStart(interview.getEventDate());
        newEvent.setEnd(interview.getEventDate());
        newEvent.setInterview(newInteriew);
        eventRepository.save(newEvent);
        System.out.println(interview1);

        emailSenderService.sendEmail(interview1);
        reminderScheduler.scheduleReminder(newEvent,interview1, 1);
        return interview1;





    }

    @Override
    public void deleteEntretien(Long interviewId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElse(null);

        Event existingEvent = eventRepository.findById(interviewId).orElse(null);


        interviewRepository.delete(interview);
        eventRepository.delete(existingEvent);

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
    public Interview updateEntretien(Long interviewId, Interview_Event interview) {
        Interview existingInterview = interviewRepository.findById(interviewId)
                .orElse(null);
        if (existingInterview != null) {
            if (interview.getArchive() != null) {
                existingInterview.setArchive(interview.getArchive());
            }

            if (interview.getLink() != null) {
                existingInterview.setLink(interview.getLink());
            }
            if (interview.getEventDate() != null) {
                //interviewId laazem tetbadel il eventId t3daha hata heya parametre fel url il fok
               Event existingEvent = eventRepository.findById(interviewId).orElse(null);
               existingEvent.setStart(interview.getEventDate());
                existingEvent.setEnd(interview.getEventDate());
                eventRepository.save(existingEvent);


            }
            return interviewRepository.save(existingInterview);

        } else {
            System.out.println("not foiund");
            return null;
        }
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
