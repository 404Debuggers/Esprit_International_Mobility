package com.Debuggers.MobiliteInternational.Services.Impl;
import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.Enum.DormStatus;
import com.Debuggers.MobiliteInternational.Entity.Enum.DormType;
import com.Debuggers.MobiliteInternational.Entity.Reservation;
import com.Debuggers.MobiliteInternational.Entity.University;
import com.Debuggers.MobiliteInternational.Repository.DormRepository;
import com.Debuggers.MobiliteInternational.Repository.UniversityRepository;
import com.Debuggers.MobiliteInternational.Services.DormService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class DormServiceImpl implements DormService {

    DormRepository dormitoriesRepository;
    UniversityRepository universityRepository;
    DormRepository dormRepository;

    @Override
    public List<Dormitories> getAllDorm() {
        return dormitoriesRepository.findAll();
    }

    @Override
    public Dormitories getDormById(long id) {
        return dormitoriesRepository.findById(id).orElse(null);
    }

    @Override
    public Dormitories addDorm(Dormitories d,Long universiteId) {
        University u = universityRepository.findById(universiteId).orElse(null);
        if(d.getDormType()== DormType.Single){
           d.setNbPlaces(1);
        }
        if(d.getDormType()== DormType.Double){
            d.setNbPlaces(2);
        }
        if(d.getDormType()== DormType.ThreeBedRoom){
            d.setNbPlaces(3);
        }
        if(d.getDormType()== DormType.FourBedRoom){
            d.setNbPlaces(4);
        }
        d.setUniversity(u);
        return dormitoriesRepository.save(d);
    }

    @Override
    public Dormitories UpdateDorm(Dormitories d) {
        return dormitoriesRepository.save(d);
    }

    @Override
    public void deleteDorm(long id) {
        dormitoriesRepository.deleteById(id);
    }

    @Override
    public List<Dormitories> findDormitoriesByUniversity(Long universityId) {
        return dormitoriesRepository.findByUniversityUniversiteId(universityId);
    }

    @Override
    public List<Dormitories> LessExpensiveDorm() {
        List<Dormitories> dormitories= dormRepository.findAll();
        List<Dormitories> LessExpensive = new ArrayList<>();

         for (Dormitories dorm : dormitories){
             if (dorm.getStatus() == DormStatus.Available && dorm.getPrix() != null && dorm.getPrix() < 100){
                 LessExpensive.add(dorm);
             }


         }
         return LessExpensive;
    }


}