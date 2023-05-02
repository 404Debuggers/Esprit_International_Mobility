import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Candidacy } from '../class/candidacy';
import { Offer } from '../class/offer';
import { idText } from 'typescript';


@Injectable({
  providedIn: 'root'
})
export class CandidacyService implements OnInit {
   candidacyURL='http://localhost:8085/api/test'
   constructor(private httpClient: HttpClient) { }
  ngOnInit(): void {

  }

getAllCandidacy(): Observable<Candidacy[]> {
  return this.httpClient.get<Candidacy[]>(`${this.candidacyURL}/AllCandidancy`);
}
getAllArchivedCandidancy(): Observable<Candidacy[]> {
  return this.httpClient.get<Candidacy[]>(`${this.candidacyURL}/AllArchivedCandidancy`);
}
 addCandidacy( id:Number,attachments: any, B2Fr: any, B2Eng: any) : Observable<any>{
   const formData = new FormData();
   formData.append('attachments', attachments);
   formData.append('B2Francais', B2Fr);
   formData.append('B2Anglais', B2Eng);
   //formData.append('candidacy', JSON.stringify(c));

   const token = sessionStorage.getItem('Token');
   const headers = { 'Authorization': `Bearer ${token}` };
    console.log(token)
   return this.httpClient.post(`${this.candidacyURL}/addCandidancy/${id}`, formData, { headers: headers });
 }
 getCandidacyByOfferid(id:any):Observable<Object>{
  return this.httpClient.get(`${this.candidacyURL}/getCandidancyByOfferid/${id}`)
 }
 deleteCandidacyFromDb(id:any):Observable<Object>{
  return this.httpClient.delete(`${this.candidacyURL}/deleteCandidacyFromDb/${id}`)
 }
 deleteCandidancy(id:any):Observable<Object>{
  return this.httpClient.get(`${this.candidacyURL}/deleteCandidancy/${id}`)
 }
 RestoreCandidancy(id:any):Observable<Object>{
  return this.httpClient.get(`${this.candidacyURL}/restoreCandidancy/${id}`)
 }
}
