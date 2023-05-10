package com.Debuggers.MobiliteInternational.Controllers;

import com.Debuggers.MobiliteInternational.Entity.University;
import com.Debuggers.MobiliteInternational.Services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class UniversityController {



    @Autowired
    UniversityService universityService;

    @PostMapping("/add_Uni")
    public University addUni (@RequestBody University university) {
        return universityService.addUni(university);
    }

    @GetMapping("/getAllunis")

    public List<University> getAllUnis( ) {
        return universityService.getAllUnis();
    }


    @DeleteMapping("/remove-uni/{universiteId}")
    @ResponseBody
    public void suppUni(@PathVariable("universiteId") Long universiteId) {
        universityService.suppUni(universiteId);
    }


    @PutMapping("/update_Uni")
    @ResponseBody
    public University updateUni(@RequestBody University university){
        return  universityService.updateUni(university);
    }




    @GetMapping("/getunibyid/{universiteId}")
    public University getUniversityById( @PathVariable Long universiteId) {

        return universityService.getUniversityById(universiteId);
    }
}


