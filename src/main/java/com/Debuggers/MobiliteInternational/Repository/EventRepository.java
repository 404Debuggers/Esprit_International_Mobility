package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {


}
