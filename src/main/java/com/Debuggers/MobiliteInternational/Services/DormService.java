package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.University;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import java.util.List;

public interface DormService {
    public List<Dormitories> getAllDorm();
    public Dormitories getDormById(long id);
    public Dormitories addDorm(Dormitories d,Long idUniversity);
    public Dormitories UpdateDorm(Dormitories d);
    public void deleteDorm(long id);

    public List<Dormitories> findDormitoriesByUniversity(Long universityId);
    /* public void decrementNbPlaces(Dormitories dormitory);*/

    public List<Dormitories> LessExpensiveDorm();
}
