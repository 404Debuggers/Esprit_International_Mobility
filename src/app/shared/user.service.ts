import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthLoginInfo} from "../auth/login-info";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  PATH_OF_API = "http://localhost:8085";

  requestHeader = new HttpHeaders(
    {
      "No-Auth": "True"
    }
  );
  constructor(private httpclient: HttpClient) { }



  login(user: AuthLoginInfo): Observable<any> {
    console.info(user);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.httpclient.post<AuthLoginInfo>(this.PATH_OF_API + "/api/auth/signin", user, {headers: headers});
  }
}
