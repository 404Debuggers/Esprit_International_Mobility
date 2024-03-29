package com.Debuggers.MobiliteInternational.Services.Impl;
import com.Debuggers.MobiliteInternational.Entity.Enum.StatusReport;
import com.Debuggers.MobiliteInternational.Entity.Report;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.ReportRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
    @Autowired
    public ReportRepository reportRepository;
    @Autowired
    public UserRepository userRepository;


    @Override
    public List<Report> getAllReport() {
        return reportRepository.findAll();
    }
    @Override
    public List<Report> getAllArchivedReport() {

    return reportRepository.findAllArchived();
  }
    @Override
    public Report getReportById(long id) {
        return reportRepository.findById(id).orElse(null);
    }

    @Override
    public Report addReport(Report r, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());

        r.setUser(user);
        r.setStatus(StatusReport.NOT_TREATED);
        user.getReportSet().add(r);

        return reportRepository.save(r);
    }

    @Override
    public Report updateReport(Report r , Long userId) {

        User user = userRepository.findById(userId).orElse(null);

        r.setUser(user);

        return reportRepository.save(r);
    }

    @Override
    public void deleteReport(long id) {
        reportRepository.deleteById(id);
    }
    public List<Report> searchByType(String type) {
        if (type == null || type.isEmpty()) {
            return reportRepository.findAll();
        } else {
            return reportRepository.findByType(type);
        }
    }

    @Override
    public Map<String, Double> generateCharts() {
        List<Report> reports = reportRepository.findAll();
        int total = reports.size();
        Map<String, Integer> counts = new HashMap<>();
        for (Report report:reports) {
            String category = report.getStatus().toString();
            counts.put(category, counts.getOrDefault(category, 0) + 1);

        }
        Map<String, Double> percentages = new HashMap<>();
        for (String category : counts.keySet()) {
            int count = counts.get(category);
            double percentage = ((double) count / total) * 100.0;
            percentages.put(category, percentage);
        }
        return percentages;
    }
  @Override
  public void ArchiveReport(Long id) {
    Optional<Report> ReportOptional = reportRepository.findById(id);
    if (ReportOptional.isPresent()) {
      Report r = ReportOptional.get();
      r.setArchive(false);
      reportRepository.save(r);
    } else {
      // handle candidacy not found error
    }
  }
  @Override
  public void RestoreReport(Long id) {
    Optional<Report> reportOptional = reportRepository.findById(id);
    if (reportOptional.isPresent()) {
      Report r = reportOptional.get();
      r.setArchive(true);
      reportRepository.save(r);
    } else {
      // handle candidacy not found error
    }
  }



}


