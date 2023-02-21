package com.Debuggers.MobiliteInternational.Services.Impl;
import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.Enum.DormStatus;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Entity.Reservation;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.DormRepository;
import com.Debuggers.MobiliteInternational.Repository.ResrvationRepository;
import com.Debuggers.MobiliteInternational.Services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    ResrvationRepository reservationRepository;
    CandidacyRepository candidacyRepository;

    DormRepository dormRepository;

    @Override
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation addReservation(Reservation r , Long candidacyId , Long dormId) {
        Candidacy c = candidacyRepository.findById(candidacyId).orElse(null);
        Dormitories dorm = dormRepository.findById(dormId).orElse(null);
        if(c.getStatus() == Status.ACCEPTED)
        {
            if(dorm.getStatus() == DormStatus.Available && dorm.getNbPlaces()!=0)
            {
                r.setDorm(dorm);
                r.setCandidacy(c);
                r.setReservationDate(new Date());
                r.setArchive(false);

                dorm.getReservationSet().add(r);

                dorm.setNbPlaces(dorm.getNbPlaces() -1);

                return  reservationRepository.save(r);
            }
        }
        return null;
    }

    @Override
    public Reservation UpdateReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public void deleteReservation(long id) {
        reservationRepository.deleteById(id);
    }

}