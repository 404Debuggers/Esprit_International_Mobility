package com.Debuggers.MobiliteInternational.Controllers;
import com.Debuggers.MobiliteInternational.Entity.Interview;
import com.Debuggers.MobiliteInternational.Entity.Interview_Event;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Services.InterviewService;
import com.Debuggers.MobiliteInternational.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("/api/test")
public class InterviewController {
    @Autowired
    InterviewService interviewService;

    @Autowired
    UserService userService;

    @PostMapping("/ajouterentretien/{candidatureId}/{universityId}")
    @ResponseBody
   // @PreAuthorize("hasRole('ADMIN')")
    public Interview createEntretien(@PathVariable("candidatureId")Long candidatureId, @PathVariable("universityId")Long universityId, @RequestBody Interview_Event interview , Principal principal) {
        String username = principal.getName();
        User user = userService.findOne(username);

        return interviewService.createEntretien(interview, candidatureId, universityId);

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