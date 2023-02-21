package com.Debuggers.MobiliteInternational.Services.Impl;
import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.Reservation;
import com.Debuggers.MobiliteInternational.Entity.University;
import com.Debuggers.MobiliteInternational.Repository.DormRepository;
import com.Debuggers.MobiliteInternational.Repository.UniversityRepository;
import com.Debuggers.MobiliteInternational.Services.DormService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DormServiceImpl implements DormService {

    DormRepository dormitoriesRepository;
    UniversityRepository universityRepository;

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


    /*@Transactional
    @Override
    public void decrementNbPlaces(Dormitories dormitory) {


            Set<Reservation> reservations = dormitory.getReservationSet();
            int nbPlaces = dormitory.getNbPlaces();
            if (reservations.size() >= nbPlaces) {
                throw new RuntimeException("Dormitory is  full");
            }
            dormitory.setNbPlaces(nbPlaces - 1);
            for (Reservation reservation : reservations) {
                reservation.getDorm().setNbPlaces(nbPlaces - 1);
            }
            dormitoriesRepository.save(dormitory);
        }*/
}