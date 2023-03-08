package com.Debuggers.MobiliteInternational.Controllers;
import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.Enum.DormStatus;
import com.Debuggers.MobiliteInternational.Entity.Enum.PaiementStatus;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.DormRepository;
import com.Debuggers.MobiliteInternational.Services.DormService;
import com.Debuggers.MobiliteInternational.Services.Impl.ReservationServiceImpl;
import com.Debuggers.MobiliteInternational.Services.Impl.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Reservation;
import com.Debuggers.MobiliteInternational.Services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    ReservationServiceImpl reservationService;
    DormService dormService;
    CandidacyRepository candidacyRepository;
    DormRepository dormRepository;
    StripeService stripeService;

    public ReservationController(ReservationServiceImpl reservationService, DormService dormService, CandidacyRepository candidacyRepository, DormRepository dormRepository, StripeService stripeService) {
        this.reservationService = reservationService;
        this.dormService = dormService;
        this.candidacyRepository = candidacyRepository;
        this.dormRepository = dormRepository;
        this.stripeService = stripeService;
    }

    @PostMapping("/addReservation/{candidacyId}/{dormId}")
    @ResponseBody
    public Reservation addReservation(@RequestBody Reservation r,@PathVariable("candidacyId")Long candidacyId ,@PathVariable("dormId")Long dormId)
    {return reservationService.addReservation(r,candidacyId,dormId);}



    @PutMapping("/UpdateReservation/{reservationId}/{newDormitoriesId}")
    @ResponseBody
    public Reservation UpdateReservation(@PathVariable Long reservationId,@PathVariable  Long newDormitoriesId)
    {return reservationService.UpdateReservation(reservationId,newDormitoriesId);}

    @DeleteMapping("/deleteReservation/{id}")
    public void deleteReservation(@PathVariable("id")Long id )
    {reservationService.deleteReservation(id);}

    @GetMapping("/getAllReservation")
    public List<Reservation> getAllReservation()
    {return reservationService.getAllReservation();}

    @GetMapping("/getReservationById/{id}")
    public Reservation getReservationById(@PathVariable("id")long id)
    {return reservationService.getReservationById(id);}



    @GetMapping("/getAvailableDormitories")
    public List<Dormitories> getAvailableDormitories() {
        List<Dormitories> dormitories = reservationService.getReservationAvailable();

        return dormitories;
    }
    @PostMapping("/dorm-payment")
    public String payDormFees(@RequestParam("cardNumber") String cardNumber,
                              @RequestParam("expMonth") String expMonth,
                              @RequestParam("expYear") String expYear,
                              @RequestParam("cvc") String cvc,
                              @RequestParam("amount") int amount,
                              @RequestParam("currency") String currency,
                              @RequestParam("reservationId") Long reservationId,
                              @RequestParam("recipientEmail") String recipientEmail,
                              Reservation reservation) {
        try {
            reservationService.payDormFees(cardNumber, expMonth, expYear, cvc, amount, currency ,reservationId,recipientEmail);
            ResponseEntity.ok( "Payment successful!");
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return "Payment successful!";
    }


}




