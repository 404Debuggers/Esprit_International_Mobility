package com.Debuggers.MobiliteInternational.Controllers;
import org.springframework.web.bind.annotation.RestController;
import com.Debuggers.MobiliteInternational.Entity.Report;
import com.Debuggers.MobiliteInternational.Services.Impl.ReportServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class ReportController {
    @Autowired
    ReportServiceImpl iReportService;
    @PostMapping("/addReport")
    @ResponseBody
    public Report addReport(@RequestBody Report r){
        return iReportService.addReport(r);
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
}
