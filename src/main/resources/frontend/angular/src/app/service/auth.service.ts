import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  login(credentials : any): Observable<any> {
    return this.http.post(this.apiServerUrl + '/authorization', {
      name: credentials.name,
      password: credentials.password
    }, httpOptions);
  }

  register(user : any): Observable<any> {
    return this.http.post(this.apiServerUrl + '/registration', {
      userName: user.userName,
      email: user.email,
      password: user.password
    }, httpOptions);
  }
}
