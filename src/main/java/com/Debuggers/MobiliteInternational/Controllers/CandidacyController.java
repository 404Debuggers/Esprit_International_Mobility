package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.OfferRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.Impl.CandidacyServiceImpl;
import com.Debuggers.MobiliteInternational.Services.UserService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
@AllArgsConstructor
public class CandidacyController {



    @Autowired
    public OfferRepository offerRepository;
    @Autowired
    public UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    CandidacyServiceImpl icandidacyService;
    @Autowired
    private CandidacyRepository candidacyRepository;

    @PostMapping(value = "/addCandidancy/{offerId}")
    @PreAuthorize("hasRole('STUDENT') ")
    @ResponseBody
    public Candidacy addCandidancy(@ModelAttribute Candidacy c,
                                   @RequestParam("attachments") MultipartFile attachment,
                                   @RequestParam("B2Francais") MultipartFile B2Fr,
                                   @RequestParam("B2Anglais") MultipartFile B2Eng,
                                   @PathVariable("offerId") Long offerId,
                                   Principal principal) throws IOException {
        System.out.println("ridha ya miboun");
        String attachmentPath = "C:\\Users\\Marwen\\Desktop\\projet PI push\\Esprit_International_Mobility\\upload\\documents" + attachment.getName();
        Path path = Paths.get(attachmentPath);
        System.out.println("ridha ya miboun");
        System.out.println(path);
        Files.copy(attachment.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        String B2FrPath = "C:\\Users\\Marwen\\Desktop\\projet PI push\\Esprit_International_Mobility\\upload\\documents" + B2Fr.getName();
        Path p = Paths.get(B2FrPath);
        Files.copy(B2Fr.getInputStream(), p, StandardCopyOption.REPLACE_EXISTING);

        String B2EngPath = "C:\\Users\\Marwen\\Desktop\\projet PI push\\Esprit_International_Mobility\\upload\\documents" + B2Eng.getName();
        Path pp = Paths.get(B2EngPath);
        Files.copy(B2Eng.getInputStream(), pp, StandardCopyOption.REPLACE_EXISTING);


        return icandidacyService.addCandidature(c,offerId,attachment,B2Fr,B2Eng,principal);
    }
//@PostMapping(value = "/addCandidancy")
//   public Candidacy addCandidancy(@ModelAttribute Candidacy c){
//    System.out.println("hhhhhhh");
//    return c;
//}

    @GetMapping("/AllCandidancy")
    @ResponseBody
    public List<Candidacy> getAllCandidancy(){
        return icandidacyService.getAllCandidancy();
    }
    @GetMapping("/AllArchivedCandidancy")
    @ResponseBody
    public List<Candidacy> getAllArchivedCandidancy(){
        return icandidacyService.getAllArchivedCandidancy();
    }

    @GetMapping("/getCandidancyById/{id}")
    @ResponseBody
    public Candidacy getCandidacyById(@PathVariable("id")Long id){
        return icandidacyService.getCandidancyById(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
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
    @GetMapping  ("/deleteCandidancy/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteCandidancy(@PathVariable("id")Long id){
        icandidacyService.deleteCandidacy(id);
        return ResponseEntity.ok("Votre Candidature est archivé");
    }

    @GetMapping ("/restoreCandidancy/{id}")
    @ResponseBody
    public ResponseEntity RestoreCandidancy(@PathVariable("id")Long id){
        icandidacyService.RestoreCandidacy(id);
        return ResponseEntity.ok("Votre candidature est restauré");
    }

    @DeleteMapping("/deleteCandidacyFromDb/{id}")
    @ResponseBody
    public void deleteCandidacyFromDb(@PathVariable("id")Long candidatureId){
        icandidacyService.deleteCandidacyFromDB(candidatureId);
        System.out.println("Votre candidature est supprimé définitivement");
        //return ResponseEntity.ok("Votre candidature est supprimé définitivement");
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

    @GetMapping("/current-user/candidacies")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<Candidacy>> getCurrentUserCandidacy(Principal principal) {
        String email = principal.getName();

        List<Candidacy> candidacies = candidacyRepository.findByUserEmailAndArchiveTrue(email);
        return ResponseEntity.ok(candidacies);



    }
}
