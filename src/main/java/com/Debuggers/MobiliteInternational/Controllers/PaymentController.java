package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Services.Impl.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private StripeService stripeService;

    @PostMapping("/charge")
    public ResponseEntity<String> chargeCard(@RequestParam("amount") int amount,
                                             @RequestParam("currency") String currency) {
        try {
            stripeService.chargeCreditCard(amount, currency);
            return ResponseEntity.ok("Payment successful");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}