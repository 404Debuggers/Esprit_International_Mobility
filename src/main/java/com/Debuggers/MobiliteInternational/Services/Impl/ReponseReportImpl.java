package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Enum.StatusReport;
import com.Debuggers.MobiliteInternational.Entity.ReponseReport;
import com.Debuggers.MobiliteInternational.Entity.Report;
import com.Debuggers.MobiliteInternational.Repository.ReponseReportRepository;
import com.Debuggers.MobiliteInternational.Repository.ReportRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.NotificationService;
import com.Debuggers.MobiliteInternational.Services.ReponseReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
@AllArgsConstructor
public class ReponseReportImpl implements ReponseReportService {
    @Autowired
    public NotificationService notificationService;
    public ReponseReportRepository ReponsereportRepository;
    public ReportRepository reportRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TwiliosmsServiceImpl twiliosmsService;

    @Override
    public List<ReponseReport> getAllReponse() {
        return ReponsereportRepository.findAll();
    }

    @Override
    public ReponseReport getReponseById(long id) {
        return ReponsereportRepository.findById(id).orElse(null);
    }
    @Override
    public ReponseReport updateReponse(ReponseReport r , Long reportId) {

        Report report = reportRepository.findById(reportId).orElse(null);

        r.setReport(report);
        return ReponsereportRepository.save(r);
    }


    @Override
    public ReponseReport addReponse(ReponseReport r, long reportId) throws MessagingException {
        Report report = reportRepository.findById(reportId).orElse(null);


        r.setReport(report);
        report.getReponseReports().add(r);
        report.setStatus(StatusReport.TREATED);

        notificationService.sendNotification(r);


        //String tophone="+21696456266";
        //String smsmessage="consulter notre site la reponse";
        //twiliosmsService.sendSms(tophone,smsmessage);
        return ReponsereportRepository.save(r);

    }



}

