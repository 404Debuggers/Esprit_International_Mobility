package com.Debuggers.MobiliteInternational.Controllers;
import com.Debuggers.MobiliteInternational.Repository.ReportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Report;
import com.Debuggers.MobiliteInternational.Services.Impl.ReportServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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
}
