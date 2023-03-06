package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Services.Impl.CandidacyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
@RestController
public class CandidacyController {
    @Autowired
    CandidacyServiceImpl icandidacyService;
    @PostMapping(value = "/addCandidancy/{offerId}/{userId}")
    @ResponseBody
    public Candidacy addCandidancy(@ModelAttribute Candidacy c,
                                   @RequestParam("attachments") MultipartFile attachment,
                                   @RequestParam("B2Francais") MultipartFile B2Fr,
                                   @RequestParam("B2Anglais") MultipartFile B2Eng,
                                   @PathVariable("offerId") Long offerId,
                                   @PathVariable("userId")Long userId ) throws IOException {
        String attachmentPath = "C:\\Users\\Marwen\\Desktop\\Projet Pi push\\Esprit_International_Mobility\\upload\\documents\\ReleveDeNote" + attachment.getOriginalFilename();
        Path path = Paths.get(attachmentPath);
        Files.copy(attachment.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        String B2FrPath = "C:\\Users\\Marwen\\Desktop\\Projet Pi push\\Esprit_International_Mobility\\upload\\documents\\B2Francais" + B2Fr.getOriginalFilename();
        Path p = Paths.get(B2FrPath);
        Files.copy(attachment.getInputStream(), p, StandardCopyOption.REPLACE_EXISTING);

        String B2EngPath = "C:\\Users\\Marwen\\Desktop\\Projet Pi push\\Esprit_International_Mobility\\upload\\documents\\B2Anglais" + B2Eng.getOriginalFilename();
        Path pp = Paths.get(B2EngPath);
        Files.copy(attachment.getInputStream(), pp, StandardCopyOption.REPLACE_EXISTING);


        return icandidacyService.addCandidature(c,offerId,userId,attachment,B2Fr,B2Eng);
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
    @PutMapping ("/deleteCandidancy/{id}")
    @ResponseBody
    public void deleteCandidancy(@PathVariable("id")Long id){
        icandidacyService.deleteCandidacy(id);
    }

    @PutMapping ("/restoreCandidancy/{id}")
    @ResponseBody
    public void RestoreCandidancy(@PathVariable("id")Long id){

        icandidacyService.RestoreCandidacy(id);
    }

    @DeleteMapping("/deleteCandidacyFromDb/{id}")
    public void deleteCandidacyFromDb(@PathVariable("id")Long candidatureId){
        icandidacyService.deleteCandidacyFromDB(candidatureId);
    }

    @GetMapping("/getstatus/{status}/{userId}/{offerId}")
    @ResponseBody
    List<Candidacy> getCandidaciesByStatusAndUserAndOffer(@PathVariable("status") Status status,
                                                          @PathVariable("userId") Long userId,
                                                          @PathVariable("offerId") Long offerId){
        return icandidacyService.getCandidacyByStatus(status, userId, offerId);
    }
    @GetMapping("/offers/{offerId}/candidacies")
    @ResponseBody
    public List<Candidacy> getCandidacyByOfferIdOrderByMarks(@PathVariable Long offerId) {
        return icandidacyService.getCandidacyByOfferOrderByMarks(offerId);
    }
    @PutMapping("/{offerId}/accept-best-candidatures")
    public ResponseEntity<?> acceptBestCandidatures(@PathVariable Long offerId) {
        icandidacyService.acceptBestCandidatures(offerId);
        return ResponseEntity.ok("Best candidatures accepted");
    }

}
