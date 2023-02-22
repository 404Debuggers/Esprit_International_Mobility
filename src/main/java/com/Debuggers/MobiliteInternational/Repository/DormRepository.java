package com.Debuggers.MobiliteInternational.Repository;


import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DormRepository extends JpaRepository <Dormitories,Long> {


    List<Dormitories> findByUniversityUniversiteId(Long universityId);
}
