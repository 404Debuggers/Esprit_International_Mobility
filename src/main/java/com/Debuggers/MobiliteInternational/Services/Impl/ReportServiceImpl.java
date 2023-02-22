package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Report;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.ReportRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Report getReportById(long id) {
        return reportRepository.findById(id).orElse(null);
    }

    @Override
    public Report addReport(Report r,Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        r.setUser(user);
        user.getReportSet().add(r);

        return reportRepository.save(r);

    }

    @Override
    public Report updateReport(Report r) {
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

}
