package com.Debuggers.MobiliteInternational.Controllers;
<<<<<<< Updated upstream

=======
import com.Debuggers.MobiliteInternational.Entity.Enum.StatusReport;
import com.Debuggers.MobiliteInternational.Repository.ReportRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> Stashed changes
import com.Debuggers.MobiliteInternational.Entity.Report;
import com.Debuggers.MobiliteInternational.Services.Impl.ReportServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<<<<<<< Updated upstream
=======
import java.util.Date;
>>>>>>> Stashed changes
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class ReportController {
    @Autowired
    ReportServiceImpl iReportService;
    ReportRepository reportRepository;
    @PostMapping("/addReport/{userId}")
    @ResponseBody
    public Report addReport(@RequestBody Report r,@PathVariable("userId")Long userId){
        return iReportService.addReport(r,userId);
    }
    @GetMapping("/AllReport")
    @ResponseBody
    public List<Report> getAllReport(){
        return iReportService.getAllReport();
    }
    @GetMapping("/getReportById/{id}")
    @ResponseBody
    public Report getReportById(@PathVariable("id")int id){
        return iReportService.getReportById(id);
    }
    @PutMapping("/updateReport")
    @ResponseBody
    public Report updateReport(@RequestBody Report r){
        return iReportService.updateReport(r);
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

}
