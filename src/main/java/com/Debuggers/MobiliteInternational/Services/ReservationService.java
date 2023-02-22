package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Entity.Reservation;

import java.util.List;

public interface ReservationService {
    public List<Reservation> getAllReservation();
    public Reservation getReservationById(long id);
    public Reservation addReservation(Reservation r , Long candidacyId , Long dormId );
    public Reservation UpdateReservation(Long reservationId, Long newDormitoriesId);
    public void deleteReservation(long id);
    public List<Dormitories> getReservationAvailable();
}
