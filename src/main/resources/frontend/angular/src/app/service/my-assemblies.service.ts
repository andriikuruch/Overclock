import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Assembly} from "../entities/assembly";
import {RAM} from "../entities/ram";

@Injectable({
  providedIn: 'root'
})
export class MyAssembliesService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getAll(): Observable<Assembly[]> {
    return this.http.get<Assembly[]>(`${this.apiServerUrl}/assembly/my_assemblies`);
  }

  public delete(assemblyId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/assembly/${assemblyId}`);
  }
}
