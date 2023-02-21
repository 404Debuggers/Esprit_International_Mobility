package com.Debuggers.MobiliteInternational.Services.Impl;
import com.Debuggers.MobiliteInternational.Entity.University;
import com.Debuggers.MobiliteInternational.Repository.UniversityRepository;
import com.Debuggers.MobiliteInternational.Services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {


    @Autowired
    UniversityRepository universityRepository ;

    @Override
    public University addUni(University university) {
        return universityRepository.save(university);
    }

    @Override
    public List<University> getAllUnis() {
        List<University> unis = (List<University>) universityRepository.findAll();
        return unis;
    }

    @Override
    public University updateUni(University university) {
        return universityRepository.save(university);
    }

    @Override
    public void suppUni(Long universiteId) {

        universityRepository.deleteById(universiteId);
    }



}
