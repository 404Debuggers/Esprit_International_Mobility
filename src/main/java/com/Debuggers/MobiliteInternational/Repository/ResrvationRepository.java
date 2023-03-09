package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResrvationRepository extends JpaRepository <Reservation,Long> {


  // @Query("select d from Dormitories  d where d.archive=true ")
    //List<Dormitories> findAll();

    @Query("select r from Reservation r where r.archive=true ")
    List<Reservation> findAll();

}
