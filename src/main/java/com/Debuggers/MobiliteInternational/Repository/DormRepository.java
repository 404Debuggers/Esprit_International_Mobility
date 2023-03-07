package com.Debuggers.MobiliteInternational.Repository;


import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DormRepository extends JpaRepository <Dormitories,Long> {
    @Query("select d from Dormitories  d where d.archive=true ")
    List<Dormitories> findAll();
    List<Dormitories> findByUniversityUniversiteId(Long universityId);
}
