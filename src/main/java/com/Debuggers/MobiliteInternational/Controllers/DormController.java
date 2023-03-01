package com.Debuggers.MobiliteInternational.Controllers;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Services.DormService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class DormController {

    DormService dormitoriesService;

    @PostMapping("/addDorm/{universiteId}")
    @ResponseBody
    public Dormitories addDorm(@RequestBody Dormitories d,@PathVariable("universiteId")long universiteId)
    { return dormitoriesService.addDorm(d,universiteId); }

    @PutMapping("/UpdateDorm")
    @ResponseBody
    public Dormitories UpdateDorm(@RequestBody Dormitories d)
    {return dormitoriesService.UpdateDorm(d);}

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


}
