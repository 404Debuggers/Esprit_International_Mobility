package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.University;

import java.util.List;

public interface UniversityService {

    public University addUni(University university);
    public List<University> getAllUnis();
    public University updateUni(University university);
    public void suppUni (Long universiteId);

    public University getUniversityById(Long universiteId);


}
