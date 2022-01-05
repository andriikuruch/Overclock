import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Assembly} from "../entities/assembly";
import {Comment} from "../entities/comment"
import {environment} from "../../environments/environment";
import {CPU} from "../entities/cpu";
import {RAM} from "../entities/ram";
import {GPU} from "../entities/gpu";
import {Motherboard} from "../entities/motherboard";
import {Overclock} from "../entities/overclock";

@Injectable({
  providedIn: 'root'
})
export class AssemblyService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getAssembly(assemblyId: number): Observable<Assembly> {
    return this.http.get<Assembly>(`${this.apiServerUrl}/assembly/${assemblyId}`);
  }

  public getAllAssemblies(): Observable<Assembly[]> {
    return this.http.get<Assembly[]>(`${this.apiServerUrl}/assembly/all`);
  }

  public addComment(assemblyId: number, comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(`${this.apiServerUrl}/assembly/${assemblyId}/comment`, comment);
  }

  public createAssembly(assembly: Assembly): Observable<Assembly> {
    return this.http.post<Assembly>(`${this.apiServerUrl}/assembly`, assembly);
  }

  public getAllMotherboards(): Observable<Motherboard[]> {
    return this.http.get<Motherboard[]>(`${this.apiServerUrl}/motherboard`);
  }

  public getAllCPUs(): Observable<CPU[]> {
    return this.http.get<CPU[]>(`${this.apiServerUrl}/cpu`);
  }

  public getAllGPUs(): Observable<GPU[]> {
    return this.http.get<GPU[]>(`${this.apiServerUrl}/gpu`);
  }

  public getAllRAMs(): Observable<RAM[]> {
    return this.http.get<RAM[]>(`${this.apiServerUrl}/ram`);
  }

  public updateScore(assembly_id: number, score: number): Observable<Assembly> {
    return this.http.post<Assembly>(`${this.apiServerUrl}/assembly/${assembly_id}/score`, score);
  }

  public getOverclock(overclockId: number): Observable<Overclock> {
    return this.http.get<Overclock>(`${this.apiServerUrl}/assembly/overclock/${overclockId}`)
  }
}
