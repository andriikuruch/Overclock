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
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user : any): Observable<any> {
    return this.http.post(this.apiServerUrl + '/registration', {
      username: user.username,
      email: user.email,
      password: user.password,
      frontUrl: this.getBaseUrl()
    }, httpOptions);
  }

  getBaseUrl() {
    if (Zone.current.get("originUrl")) {
      return Zone.current.get('originUrl');
    } else if (location) {
      return location.origin;
    } else {
      return 'something went wrong!';
    }
  }

  activateAccount(activatePasswordToken: any) {
    return this.http.get(this.apiServerUrl + '/registration/activate-account?token=' + activatePasswordToken);
  }
}
