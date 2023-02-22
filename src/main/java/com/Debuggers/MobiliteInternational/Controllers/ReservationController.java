package com.Debuggers.MobiliteInternational.Controllers;
import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.Enum.DormStatus;
import com.Debuggers.MobiliteInternational.Services.DormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    DormService dormService;

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



}
