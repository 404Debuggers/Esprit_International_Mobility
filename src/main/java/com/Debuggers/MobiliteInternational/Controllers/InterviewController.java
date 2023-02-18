package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Interview;
import com.Debuggers.MobiliteInternational.Services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InterviewController {
    @Autowired
    InterviewService interviewService;

    @PostMapping("/ajouterentretien/{candidatureId}")
    public void createEntretien(@PathVariable("candidatureId")Long candidatureId, @RequestBody Interview interview) {

        interviewService.createEntretien(interview, candidatureId);
    }


    @DeleteMapping("/supprimerentretien/{interviewId}")
    public void deleteEntretien(@PathVariable("interviewId") Long interviewId){
        interviewService.deleteEntretien(interviewId);
    }

    @GetMapping("/affichertoutentretien")
    public List<Interview> getAllEntretiens(){return interviewService.getAllEntretiens();}

    @PutMapping("/modifierrentretien/{interviewId}")
    public Interview updateEntretien(@PathVariable ("interviewId")Long interviewId,@RequestBody Interview interview) {
        interviewService.updateEntretien(interviewId,interview);
        return interview;
    }

    @PutMapping("/modifierrentretienne")
    public Interview updateEntretienne(@RequestBody Interview interview) {
        interviewService.updateEntretienne(interview);
        return interview;
    }

    @GetMapping("/getEntretienbyCandidature/{candidatureId}")
    public List<Interview> getEntretienBycandidature(@PathVariable("candidatureId")Long candidatureId)
    {return interviewService.getEntretienBycandidature(candidatureId);}
}
