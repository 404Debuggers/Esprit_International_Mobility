package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Report;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Report> getAllReport();

  List<Report> getAllArchivedReport();

  public Report getReportById(long id);

    public Report addReport(Report r , Principal principal);
    public Report updateReport(Report r , Long userId);

    public void deleteReport(long id);
    List<Report> searchByType(String type);
    public Map<String, Double> generateCharts();


  void ArchiveReport(Long id);

  void RestoreReport(Long id);
}
