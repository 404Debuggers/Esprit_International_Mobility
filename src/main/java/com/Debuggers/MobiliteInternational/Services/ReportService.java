package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Report;

import java.util.List;

public interface ReportService {
    List<Report> getAllReport();

    public Report getReportById(long id);

    public Report addReport(Report r);

    public Report updateReport(Report r);

    public void deleteReport(long id);
}
