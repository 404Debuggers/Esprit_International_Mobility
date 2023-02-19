package com.Debuggers.MobiliteInternational.Repository;


import com.Debuggers.MobiliteInternational.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResrvationRepository extends JpaRepository <Reservation,Long> {

}
