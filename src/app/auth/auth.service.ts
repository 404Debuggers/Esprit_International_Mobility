import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {BehaviorSubject, map, Observable} from 'rxjs';

import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from './login-info';
import { SignUpInfo } from './signup-info';
import {JwtHelperService} from "@auth0/angular-jwt";
import {TokenStorageService} from "./token-storage.service";
import {Router} from "@angular/router";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const TOKEN_KEY = 'AuthToken';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private currentUserSubject : BehaviorSubject<any>;

  public currentUser : Observable<any>;

  private loginUrl = 'http://localhost:8085/api/auth/signin';
  private signupUrl = 'http://localhost:8085/api/auth/signup';

  constructor(private http: HttpClient , private jwtHelper: JwtHelperService , private tokenStorage: TokenStorageService , private router: Router ) {
    this.currentUserSubject = new BehaviorSubject<any>(sessionStorage.getItem(TOKEN_KEY));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue() : any {
  return this.currentUserSubject.value;
  }


  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions).pipe(
      map(data =>{
        this.saveUserData(data);
        return data;
      })
    );
  }

  private saveUserData(data:any){
    this.tokenStorage.saveToken(data.accessToken);
    this.tokenStorage.saveUsername(data.email);
    this.tokenStorage.saveAuthorities(data.authorities);
    this.currentUserSubject.next(data.accessToken)
  }
}
