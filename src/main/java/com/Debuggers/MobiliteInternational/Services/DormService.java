package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Dormitories;

import java.util.List;

public interface DormService {
    public List<Dormitories> getAllDorm();
    public Dormitories getDormById(long id);
    public Dormitories addDorm(Dormitories d,Long idUniversity);
    public Dormitories UpdateDorm(Dormitories d);
    public void deleteDorm(long id);
    /* public void decrementNbPlaces(Dormitories dormitory);*/
}
