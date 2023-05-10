package com.Debuggers.MobiliteInternational.Controllers;
import com.Debuggers.MobiliteInternational.Entity.Candidacy;
import com.Debuggers.MobiliteInternational.Entity.User;
import com.Debuggers.MobiliteInternational.Repository.ReportRepository;
import com.Debuggers.MobiliteInternational.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Report;
import com.Debuggers.MobiliteInternational.Services.Impl.ReportServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/test")
public class ReportController {
    @Autowired
    ReportServiceImpl iReportService;
    ReportRepository reportRepository;

    UserRepository userRepository;
    @PostMapping("/addReport")
    @ResponseBody
    public Report addReport(@RequestBody Report r, Principal p){
        return iReportService.addReport(r,p);
    }
    @GetMapping("/AllReport")
    @ResponseBody
    public List<Report> getAllReport(){
        return iReportService.getAllReport();
    }
    @GetMapping("/AllArchivedReport")
    @ResponseBody
    public List<Report> getAllArchivedCandidancy(){
    return iReportService.getAllArchivedReport();
  }
    @GetMapping("/getReportById/{id}")
    @ResponseBody
    public Report getReportById(@PathVariable("id")int id){
        return iReportService.getReportById(id);
    }
    @PutMapping("/updateReport/{userId}")
    @ResponseBody
    public Report updateReport(@RequestBody Report r , @PathVariable("userId") Long userId){
        return iReportService.updateReport(r,userId);
    }
    @DeleteMapping("/deleteReport/{id}")
    @ResponseBody
    public void deleteReport(@PathVariable("id")int id){
        iReportService.deleteReport(id);
    }


    @GetMapping("/reports")
    public List<Report> searchByType(@RequestParam(required = false) String type) {
        if (type != null) {
            return reportRepository.findByType(type);
        } else {
            return reportRepository.findAll();
        }
    }
    @GetMapping("/reports/chart")
    public ResponseEntity<Map<String, Double>> generateChart() {
        Map<String, Double> percentages = iReportService.generateCharts();
        return ResponseEntity.ok(percentages);
    }
  @GetMapping ("/restoreReport/{id}")
  @ResponseBody
  public ResponseEntity RestoreReport(@PathVariable("id")Long id){
    iReportService.RestoreReport(id);
    return ResponseEntity.ok("Votre Report est restauré");
  }
  @GetMapping  ("/archiveReport/{id}")
  @ResponseBody
  public ResponseEntity<String> ArchiveReport(@PathVariable("id")Long id){
    iReportService.ArchiveReport(id);
    return ResponseEntity.ok("Votre Candidature est archivé");
  }


  @GetMapping("/current-user/reports")
  @PreAuthorize("hasRole('STUDENT')")
  public ResponseEntity<List<Report>> getCurrentReports(Principal principal) {
    User user = userRepository.findByEmail(principal.getName());

    List<Report> reportList = reportRepository.findByUserAndArchiveTrue(user);
    return ResponseEntity.ok(reportList);

  }
}
