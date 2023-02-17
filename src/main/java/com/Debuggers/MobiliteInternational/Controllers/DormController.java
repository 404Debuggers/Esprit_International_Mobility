package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Services.DormService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DormController {

    DormService dormitoriesService;

    @PostMapping("/addDorm")
    @ResponseBody
    public Dormitories addDorm(@RequestBody Dormitories d)
    { return dormitoriesService.addDorm(d); }

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


}
