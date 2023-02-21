package com.Debuggers.MobiliteInternational.Controllers;
import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Reservation;
import com.Debuggers.MobiliteInternational.Services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReservationController {
    ReservationService reservationService;

    @PostMapping("/addReservation/{candidacyId}/{dormId}")
    @ResponseBody
    public Reservation addReservation(@RequestBody Reservation r,@PathVariable("candidacyId")Long candidacyId ,@PathVariable("dormId")Long dormId)
    {return reservationService.addReservation(r,candidacyId,dormId);}

    @PutMapping("/UpdateReservation")
    @ResponseBody
    public Reservation UpdateReservation(@RequestBody Reservation r)
    {return reservationService.UpdateReservation(r);}

    @DeleteMapping("/deleteReservation/{id}")
    public void deleteReservation(@PathVariable("id")long id )
    {reservationService.deleteReservation(id);}

    @GetMapping("/getAllReservation")
    public List<Reservation> getAllReservation()
    {return reservationService.getAllReservation();}

    @GetMapping("/getReservationById/{id}")
    public Reservation getReservationById(@PathVariable("id")long id)
    {return reservationService.getReservationById(id);}
}
