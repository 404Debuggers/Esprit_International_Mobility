import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DormitoriesService {
  private listDormURL="http://localhost:8085/getAllDorm";
 
  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
    })
  }
  constructor(private http:HttpClient) { }

  GetAllDorm():Observable<any[]>{
    const token = sessionStorage.getItem('Token');
    console.info(token);
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
    return this.http.get<any[]>(this.listDormURL , {headers})
  }
}
