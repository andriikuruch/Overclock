import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Assembly} from "../entities/assembly";

@Injectable({
  providedIn: 'root'
})
export class RatingService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getTopByOverclock(): Observable<Assembly[]> {
    return this.http.get<Assembly[]>(`${this.apiServerUrl}/rating`)
  }

  public getTopByDefault(): Observable<Assembly[]> {
    return this.http.get<Assembly[]>(`${this.apiServerUrl}/rating/default`)
  }
}
