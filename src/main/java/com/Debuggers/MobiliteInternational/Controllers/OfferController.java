
package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Enum.StudyField;
import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Repository.OfferRepository;
import com.Debuggers.MobiliteInternational.Services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/addfavofferandAssignToUser/{user_Id}/{offerId}")
    public void addFavandAssigntouser(@PathVariable Long user_Id,@PathVariable Long offerId) {
        offerService.addFavandAssigntouser(user_Id,offerId);

    }

    @GetMapping("/getfavoffer/{user_Id}")
    public List<Offer> getFavOffers(@PathVariable Long user_Id)
    {
        return offerService.getFavOffers(user_Id);
    }

   /*@GetMapping("/getSmilarFavOffers/{user_Id}")
    public List<Offer> findSimilarOffersForUser(@PathVariable Long userId){
        return offerService.findSimilarOffersForUser(userId);
    }*/

    @GetMapping("/getprop/{user_Id}")

    public List<StudyField> extractPropertiesFromOffers(@PathVariable Long user_Id )
    { return offerService.extractPropertiesFromOffers(user_Id);}

    @GetMapping("/findOffersWithS/{user_Id}")
    public List<Offer> findOffersWithSimilarProperties(@PathVariable Long user_Id){ return offerService.findOffersWithSimilarProperties(user_Id);}


    @PostMapping("/offersaddwithdate")
    public ResponseEntity<Offer> addOfferAtSpecificTime(@RequestBody Offer offer, @RequestHeader("X-Offer-Time") String offerTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
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

}