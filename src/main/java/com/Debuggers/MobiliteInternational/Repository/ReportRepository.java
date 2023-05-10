package com.Debuggers.MobiliteInternational.Repository;

import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.Enum.StatusReport;
import com.Debuggers.MobiliteInternational.Entity.Report;
import com.Debuggers.MobiliteInternational.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByType(String type);
  @Query("SELECT r FROM Report r WHERE r.archive = false")
  List<Report> findAllArchived();
    List<Report> findByUserAndArchiveTrue(User u);

    @Query("SELECT r.status, COUNT(r) FROM Report r GROUP BY r.status")
    Map<StatusReport, Long> countByStatus();
  @Query("SELECT r FROM Report r WHERE r.archive = true")
  List<Report> findAll();


}
