import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TestService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public testAssembly(assemblyId: number): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/test/assembly/${assemblyId}`);
  }

  public testAssemblyOnProgram(assemblyId: number, program: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/test/assembly/${assemblyId}/${program}`);
  }

  public testCPU(assemblyId: number): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/test/assembly/${assemblyId}/component/cpu`);
  }

  public testGPU(assemblyId: number): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/test/assembly/${assemblyId}/component/gpu`);
  }

  public testRAM(assemblyId: number): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/test/assembly/${assemblyId}/component/ram`);
  }
}
