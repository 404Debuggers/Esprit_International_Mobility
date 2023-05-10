package com.Debuggers.MobiliteInternational.Controllers;


import com.Debuggers.MobiliteInternational.Entity.Enum.StatusReport;
import com.Debuggers.MobiliteInternational.Entity.ReponseReport;
import com.Debuggers.MobiliteInternational.Entity.Report;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.ReponseReportRepository;
import com.Debuggers.MobiliteInternational.Repository.ReportRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import com.Debuggers.MobiliteInternational.Services.Impl.ReponseReportImpl;
import com.Debuggers.MobiliteInternational.Services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/test")
@RestController
@AllArgsConstructor
public class ReponseReportController {

  UserRepository userRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ReponseReportRepository responseRepository;

    ReponseReportImpl reponseReport;
    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/AllReponse")
    @ResponseBody
    public List<ReponseReport> getAllReponse(){
        return reponseReport.getAllReponse();
    }
    @GetMapping("/getReponseById/{id}")
    @ResponseBody public ReponseReport getReponseById(@PathVariable("id")int id){
        return reponseReport.getReponseById(id);
    }
    @PutMapping("/updateReponse/{reportId}")
    @ResponseBody
    public  ReponseReport updateReport(@RequestBody ReponseReport r , @PathVariable("reportId") Long reportId){
        return reponseReport.updateReponse(r,reportId);
    }
    @PostMapping("/addReponse/{reponseId}")
    @ResponseBody
    public ReponseReport addReponse(@RequestBody ReponseReport r,@PathVariable("reponseId")Long reportId) throws MessagingException {


        Report report= reportRepository.findById(reportId).orElse(null);

        report.setStatus(StatusReport.TREATED);


        r.setReport(report);


        return reponseReport.addReponse(r,reportId);

    }






}
