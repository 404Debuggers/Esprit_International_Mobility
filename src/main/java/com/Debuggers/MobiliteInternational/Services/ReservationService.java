package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Reservation;

import java.util.List;

public interface ReservationService {
    public List<Reservation> getAllReservation();
    public Reservation getReservationById(long id);
    public Reservation addReservation(Reservation r);
    public Reservation UpdateReservation(Reservation r);
    public void deleteReservation(long id);
}
