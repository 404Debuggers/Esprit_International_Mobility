package com.Debuggers.MobiliteInternational.Services.Impl;
import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.Enum.DormStatus;
import com.Debuggers.MobiliteInternational.Entity.Enum.DormType;
import com.Debuggers.MobiliteInternational.Entity.Enum.PaiementStatus;
import com.Debuggers.MobiliteInternational.Entity.University;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.DormRepository;
import com.Debuggers.MobiliteInternational.Repository.UniversityRepository;
import com.Debuggers.MobiliteInternational.Services.DormService;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
@AllArgsConstructor
public class DormServiceImpl implements DormService {

    DormRepository dormitoriesRepository;
    UniversityRepository universityRepository;
    CandidacyRepository candidacyRepository;
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
        d.setArchive(true);
        d.setDormstatus(DormStatus.Available);
        return dormitoriesRepository.save(d);
    }

    @Override
    public Dormitories UpdateDorm(Dormitories d, Long DormId) {
        Dormitories dorm = dormRepository.findById(DormId).orElse(null);
        dorm.setDormType(d.getDormType());
        dorm.setDormstatus(d.getDormstatus());
        dorm.setNbPlaces(d.getNbPlaces());
        dorm.setDormType(d.getDormType());
        dorm.setLocation(d.getLocation());
        dorm.setPrix(d.getPrix());
        dorm.setRent(d.getRent());
        return dormitoriesRepository.save(dorm);
    }

    @Override
    public void deleteDorm(long id) {
        Optional<Dormitories> dormOptional = dormRepository.findById(id);
        if (dormOptional.isPresent()){
            Dormitories dormitories= dormOptional.get();
            dormitories.setArchive(false);
            dormRepository.save(dormitories);
        }else {

        }


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
             if (dorm.getDormstatus() == DormStatus.Available && dorm.getPrix() != null && dorm.getPrix() < 100){
                 LessExpensive.add(dorm);
             }


         }
         return LessExpensive;
    }

    /**
     * @param dormitoryId
     * @param rating
     * @return
     */
    @Override
    public Dormitories updateDormitoryRating(Long dormitoryId, Double rating) {


        Optional<Dormitories> dormitoryOptional = dormitoriesRepository.findById(dormitoryId);

        if (dormitoryOptional.isPresent()) {
            Dormitories dormitory = dormitoryOptional.get();
            double currentRating = dormitory.getRating() != null ? dormitory.getRating() : 0;
            int totalRatings = dormitory.getReservationSet().size();

            double newRating = (currentRating * totalRatings + rating) / (totalRatings + 1);
            dormitory.setRating(newRating);
            dormitoriesRepository.save(dormitory);

            return dormitory;
        } else {
            return null;
        }
    }
    }



