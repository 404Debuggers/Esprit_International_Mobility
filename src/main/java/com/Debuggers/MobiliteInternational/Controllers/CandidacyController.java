package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Services.Impl.CandidacyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CandidacyController {
    @Autowired
    CandidacyServiceImpl icandidacyService;
    @PostMapping("/addCandidancy/{offerId}/{userId}")
    @ResponseBody
    public Candidacy addCandidancy(@RequestBody Candidacy c , @PathVariable("offerId") Long offerId,@PathVariable("userId")Long userId ){
        return icandidacyService.addCandidature(c,offerId,userId);
    }
    @GetMapping("/AllCandidancy")
    @ResponseBody
    public List<Candidacy> getAllCandidancy(){
        return icandidacyService.getAllCandidancy();
    }
    @GetMapping("/getCandidancyById/{id}")
    @ResponseBody
    public Candidacy getCandidacyById(@PathVariable("id")Long id){
        return icandidacyService.getCandidancyById(id);
    }
    @GetMapping("/getCandidancyByOfferid/{idOffer}")
    @ResponseBody
    public List<Candidacy> getCandidacyByOfferid(@PathVariable("idOffer")Long idOffer){
        return icandidacyService.getCandidacyByOffer(idOffer);
    }
    @GetMapping("/getCandidancyByUserid/{idUser}")
    @ResponseBody
    public List<Candidacy> getCandidacyByUserid(@PathVariable("idUser")Long idUser){
        return icandidacyService.getCandiacyByUser(idUser);
    }
    @GetMapping("/getCandidancyByUseridAndOfferid/{idUser}/{idOffer}")
    @ResponseBody
    public List<Candidacy> getCandidacyByUseridAndOfferid(@PathVariable("idUser")Long idUser,@PathVariable("idOffer")Long idOffer){
        return icandidacyService.getCandiacyByUserAndOffer(idUser,idOffer);
    }
    @PutMapping("/updateCandidancy/{idCandidacy}")
    @ResponseBody
    public Candidacy  updateCandidancy(@RequestBody Candidacy c,@PathVariable Long idCandidacy){
        return icandidacyService.updateCandidancy(c,idCandidacy);


    }
    @DeleteMapping("/deleteCandidancy/{id}")
    @ResponseBody
    public void deleteCandidancy(@PathVariable("id")Long id){
        icandidacyService.deleteCandidancy(id);
    }
}
