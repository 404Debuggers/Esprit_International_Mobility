import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Offer } from '../class/offer';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OfferService {
  readonly API_URL = 'http://localhost:8085/api/test';
  constructor(private httpClient: HttpClient) { }
  getAllOffer() {
    return this.httpClient.get(`${this.API_URL}/getAlloffers`)
  }
  getOfferById(id: number): Observable<Offer> {
    return this.httpClient.get<Offer>(`${this.API_URL}/${id}`);
  }
}
