package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Dormitories;



import java.util.List;

public interface DormService {
    public List<Dormitories> getAllDorm();
    public Dormitories getDormById(long id);

    public Dormitories addDorm(Dormitories d,Long idUniversity);

    Dormitories UpdateDorm(Dormitories d, Long DormId);

    public void deleteDorm(long id);

    public List<Dormitories> findDormitoriesByUniversity(Long universityId);
    /* public void decrementNbPlaces(Dormitories dormitory);*/

    public List<Dormitories> LessExpensiveDorm();

    public Dormitories updateDormitoryRating(Long dormitoryId, Double rating);

}
