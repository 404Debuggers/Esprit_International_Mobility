package com.Debuggers.MobiliteInternational.Services.Impl;
import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.Enum.DormStatus;
import com.Debuggers.MobiliteInternational.Entity.Enum.PaiementStatus;
import com.Debuggers.MobiliteInternational.Entity.Enum.Status;
import com.Debuggers.MobiliteInternational.Entity.Reservation;
import com.Debuggers.MobiliteInternational.Repository.CandidacyRepository;
import com.Debuggers.MobiliteInternational.Repository.DormRepository;
import com.Debuggers.MobiliteInternational.Repository.ResrvationRepository;
import com.Debuggers.MobiliteInternational.Services.ReservationService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

import com.stripe.model.Token;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Reservation addReservation(Reservation r, Long candidacyId, Long dormId) {
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

                if(dorm.getNbPlaces()==0){
                    dorm.setStatus(DormStatus.valueOf("Not_Available"));
                }

                return  reservationRepository.save(r);
            }
        }
        return null;
    }

    @Override
    public List<Dormitories> getReservationAvailable() {
        List<Dormitories> dormitories = dormRepository.findAll();
        List<Dormitories> availableDormitories = new ArrayList<>();

        for (Dormitories dormitory : dormitories) {
            if (dormitory.getStatus() == DormStatus.Available) {
                availableDormitories.add(dormitory);
            }
        }

        return availableDormitories;
    }




    @Override
    public Reservation UpdateReservation(Long reservationId, Long newDormitoriesId) {

        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            return null;
        }

        Dormitories newDormitory = dormRepository.findById(newDormitoriesId).orElse(null);
        if (newDormitory == null) {
            return null;
        }
        if (newDormitory.getStatus() != DormStatus.Available || newDormitory.getNbPlaces() == 0) {
            return null;
        }
        reservation.getDorm().setNbPlaces(reservation.getDorm().getNbPlaces()+1);
        if (reservation.getDorm().getNbPlaces() != 0) {
            reservation.getDorm().setStatus(DormStatus.valueOf("Available"));
        }
        reservation.setDorm(newDormitory);
        newDormitory.getReservationSet().add(reservation);
        newDormitory.setNbPlaces(newDormitory.getNbPlaces() - 1);
        if (newDormitory.getNbPlaces() == 0) {
            newDormitory.setStatus(DormStatus.Not_Available);

        }

        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(long id) {

     Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            Dormitories dorm = reservation.getDorm();
            dorm.setNbPlaces(dorm.getNbPlaces() + 1);
            reservation.setArchive(true);
            dorm.setStatus(DormStatus.valueOf("Available"));
            reservationRepository.save(reservation);
        }

    }

}