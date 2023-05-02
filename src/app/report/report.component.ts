import { Component, OnInit } from '@angular/core';
import { Report } from '../class/report';
import { ReportService } from '../shared/report.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  listReport: any;
  form : boolean = true;
  report! : Report;
  constructor(private reportService:ReportService) { }

  ngOnInit(): void {
    this.getAllReport();
  }
  getAllReport() {
    this.reportService.getAllReport().subscribe((res:any) => this.listReport = res)
  }

}
