import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReportService implements OnInit {
  readonly reportURL='http://localhost:8085'
  constructor(private httpClient:HttpClient) { }
  ngOnInit(): void {
  }
  getAllReport() {
    return this.httpClient.get(`${this.reportURL}/AllReport`)
  }
}
