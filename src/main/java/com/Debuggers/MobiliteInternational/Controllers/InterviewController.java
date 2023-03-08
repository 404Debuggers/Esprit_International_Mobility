package com.Debuggers.MobiliteInternational.Controllers;
import com.Debuggers.MobiliteInternational.Entity.Interview;
import com.Debuggers.MobiliteInternational.Entity.Interview_Event;
import com.Debuggers.MobiliteInternational.Services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class InterviewController {
    @Autowired
    InterviewService interviewService;

    @PostMapping("/ajouterentretien/{candidatureId}/{universityId}")
    @ResponseBody
    public Interview createEntretien(@PathVariable("candidatureId")Long candidatureId, @PathVariable("universityId")Long universityId, @RequestBody Interview_Event interview) {

        return interviewService.createEntretien(interview, candidatureId,universityId);

    }
    @DeleteMapping("/supprimerentretien/{interviewId}")
    public void deleteEntretien(@PathVariable("interviewId") Long interviewId){
        interviewService.deleteEntretien(interviewId);
    }
    @GetMapping("/affichertoutentretien")
    public List<Interview> getAllEntretiens(){return interviewService.getAllEntretiens();}
    @PutMapping("/modifierrentretien/{interviewId}")
    @ResponseBody
    public Interview updateEntretien(@PathVariable ("interviewId")Long interviewId,@RequestBody Interview_Event interview) {
        interviewService.updateEntretien(interviewId,interview);
        return interview;
    }
    @GetMapping("/getEntretienbyCandidature/{candidatureId}")
    public List<Interview> getEntretienBycandidature(@PathVariable("candidatureId")Long candidatureId)
    {return interviewService.getEntretienBycandidature(candidatureId);}
}