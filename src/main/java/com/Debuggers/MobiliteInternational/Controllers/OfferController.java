
package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Enum.StudyField;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Repository.OfferRepository;
import com.Debuggers.MobiliteInternational.Services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test/")
public class OfferController {

    @Autowired
    OfferService offerService ;
    @Autowired
    private OfferRepository offerRepository;


    @PostMapping("/add_Offer")
    public Offer addOffer (@RequestBody Offer offer) {
        return offerService.addOffer(offer);
    }

    @GetMapping("/getAlloffers")

    public List<Offer> getAllOffers( ) {
        return offerService.getAllOffers();
    }


    @DeleteMapping("/remove-offer/{offerId}")
    @ResponseBody
    public void suppOffer(@PathVariable("offerId") Long offerId) {
        offerService.suppOffer(offerId);
    }

    @PutMapping("/update_Offer")
    @ResponseBody
    public Offer updateOffer(@RequestBody Offer offer){
        return  offerService.updateOffer(offer);
    }

    @GetMapping("/addfavofferandAssignToUser/{offerId}")
    public void addFavandAssigntouser(Principal p ,@PathVariable Long offerId) {
        offerService.addFavandAssigntouser(p,offerId);

    }

    @GetMapping("/getfavoffer")
    public List<Offer> getFavOffers(Principal principal)
    {
        return offerService.getFavOffers(principal);
    }

   /*@GetMapping("/getSmilarFavOffers/{user_Id}")
    public List<Offer> findSimilarOffersForUser(@PathVariable Long userId){
        return offerService.findSimilarOffersForUser(userId);
    }*/

    @GetMapping("/getprop/")

    public List<StudyField> extractPropertiesFromOffers(Principal p)
    { return offerService.extractPropertiesFromOffers(p);}

    @GetMapping("/findOffersWithS")
    public List<Offer> findOffersWithSimilarProperties(Principal p){ return offerService.findOffersWithSimilarProperties(p);}


    @PostMapping("/offersaddwithdate")
    public ResponseEntity<Offer> addOfferAtSpecificTime(@RequestBody Offer offer, @RequestHeader("X-Offer-Time") String offerTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(offerTime, formatter);
        offerService.addOfferAtSpecificTime(offer, dateTime);
        return ResponseEntity.ok(offer);
    }


    @GetMapping("/offers/{id}/charts")
    public ResponseEntity<Map<String, Double>> generateChartsForOffer(@PathVariable Long id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();
            Map<String, Double> charts = offerService.generateCharts(offer);
            return ResponseEntity.ok(charts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
  @DeleteMapping("/users/favorites/{offerId}")
  public ResponseEntity<String> deleteFavorite(Principal p, @PathVariable("offerId") Long offerId) {
    try {
      offerService.deleteFavandRemoveFromUser(p, offerId);
      return ResponseEntity.ok("Favorite successfully deleted and removed from user");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting favorite: " + e.getMessage());
    }
  }

}
