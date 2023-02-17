package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Offer;
import com.Debuggers.MobiliteInternational.Services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {

    @Autowired
    OfferService offerService ;



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
}
