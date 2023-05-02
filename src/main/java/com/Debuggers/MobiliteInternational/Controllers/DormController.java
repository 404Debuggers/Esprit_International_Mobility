package com.Debuggers.MobiliteInternational.Controllers;
import com.Debuggers.MobiliteInternational.Entity.Enum.PaiementStatus;
import com.Debuggers.MobiliteInternational.Entity.Reservation;
import com.Debuggers.MobiliteInternational.Repository.DormRepository;
import com.Debuggers.MobiliteInternational.Services.Impl.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Services.DormService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class DormController {

    DormService dormitoriesService;
    DormRepository dormRepository;


    @PostMapping("/addDorm/{universiteId}")
    @ResponseBody
    public Dormitories addDorm(@RequestBody Dormitories d,@PathVariable("universiteId")long universiteId)
    { return dormitoriesService.addDorm(d,universiteId); }

    @PutMapping("/UpdateDorm/{DormId}")
    @ResponseBody
    public Dormitories UpdateDorm(@RequestBody Dormitories d , @PathVariable Long DormId)
    {return dormitoriesService.UpdateDorm(d,DormId);}

    @DeleteMapping("/deleteDorm/{id}")
    public void deleteDorm(@PathVariable("id")long id)
    { dormitoriesService.deleteDorm(id);}

    @GetMapping("/getAllDorm")
    public List<Dormitories> getAllDorm()
    {return dormitoriesService.getAllDorm();}

    @GetMapping("/getDormById/{id}")
    public Dormitories getDormById(@PathVariable("id")long id)
    {return dormitoriesService.getDormById(id);}



    @GetMapping("/findDormitoriesByUniversity/{universityId}")
    public List<Dormitories> findDormitoriesByUniversity(@PathVariable Long universityId) {
        return dormitoriesService.findDormitoriesByUniversity(universityId);
    }

    @GetMapping("/LessExpensiveDorm")
    public List<Dormitories> LessExpensiveDorm(){
        List<Dormitories> dorm= dormitoriesService.LessExpensiveDorm();
        return dorm;
    }


    @PutMapping("/{id}/rating")
    public ResponseEntity<Dormitories> rateDormitory(
            @PathVariable(value = "id") Long dormitoryId,
            @RequestParam(value = "rating") Double rating) {

        Optional<Dormitories> dormitoryOptional = dormRepository.findById(dormitoryId);
        if (!dormitoryOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Dormitories dormitory = dormitoryOptional.get();
        double currentRating = dormitory.getRating() != null ? dormitory.getRating() : 0;
        int totalRatings = dormitory.getReservationSet().size();

        double newRating = (currentRating * totalRatings + rating) / (totalRatings + 1);
        dormitory.setRating(newRating);
        dormRepository.save(dormitory);

        return ResponseEntity.ok(dormitory);
    }


}