import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Overclock} from "../entities/overclock";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class OverclockingService {

  private overclockingUrl: string;
  private assemblyId: number = 0;

  constructor(private http: HttpClient) {
    this.overclockingUrl = environment.apiBaseUrl + '/assembly';
  }

  public saveOverclock(overclocking: Overclock): Observable<void> {
    return this.http.post<void>(`${this.overclockingUrl}/${this.assemblyId}/overclock`, overclocking);
  }

  public getDefaultValues(): Observable<Overclock> {
    return this.http.get<Overclock>(`${this.overclockingUrl}/${this.assemblyId}/overclock/default`);
  }

  public getOverclock(overclockId: number): Observable<Overclock> {
    return this.http.get<Overclock>(`${this.overclockingUrl}/overclock/${overclockId}`);
  }

  public updateOverclock(newOverclock: Overclock): Observable<void> {
    return this.http.put<void>(`${this.overclockingUrl}/overclock/${newOverclock.id}`, newOverclock);
  }

  public deleteOverclockByAssemblyId(assemblyId: number): Observable<void> {
    return this.http.delete<void>(`${this.overclockingUrl}/overclock/${assemblyId}`);
  }

  public setAssemblyId(assemblyId: number) {
    this.assemblyId = assemblyId;
  }
}
