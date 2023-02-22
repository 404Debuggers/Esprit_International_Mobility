package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.University;

import java.util.List;

public interface DormService {
    public List<Dormitories> getAllDorm();
    public Dormitories getDormById(long id);
    public Dormitories addDorm(Dormitories d,Long idUniversity);
    public Dormitories UpdateDorm(Dormitories d);
    public void deleteDorm(long id);

    public List<Dormitories> findDormitoriesByUniversity(Long universityId);
    /* public void decrementNbPlaces(Dormitories dormitory);*/
}
