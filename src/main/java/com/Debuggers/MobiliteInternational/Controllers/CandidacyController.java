package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Services.Impl.CandidacyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CandidacyController {
    @Autowired
    CandidacyServiceImpl icandidacyService;
    @PostMapping("/addCandidancy")
    @ResponseBody
    public Candidacy addCandidancy(@RequestBody Candidacy c){
        return icandidacyService.addCandidature(c);
    }
    @GetMapping("/AllCandidancy")
    @ResponseBody
    public List<Candidacy> getAllCandidancy(){
        return icandidacyService.getAllCandidancy();
    }
    @GetMapping("/getCandidancyById/{id}")
    @ResponseBody
    public Candidacy getDepartementById(@PathVariable("id")int id){
        return icandidacyService.getCandidancyById(id);
    }
    @PutMapping("/updateCandidancy")
    @ResponseBody
    public Candidacy updateCandidancy(@RequestBody Candidacy c){
        return icandidacyService.updateCandidancy(c);
    }
    @DeleteMapping("/deleteCandidancy/{id}")
    @ResponseBody
    public void deleteCandidancy(@PathVariable("id")int id){
        icandidacyService.deleteCandidancy(id);
    }
}
