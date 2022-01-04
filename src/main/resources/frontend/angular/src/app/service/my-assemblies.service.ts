import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Assembly} from "../entities/assembly";
import {RAM} from "../entities/ram";

@Injectable({
  providedIn: 'root'
})
export class MyAssembliesService {

  private assembliesUrl: string;

  constructor(private http: HttpClient) {
    this.assembliesUrl = 'http://localhost:8000/api/assembly';
  }

  public getAll(): Observable<Assembly[]> {
    return this.http.get<Assembly[]>(`${this.assembliesUrl}/my_assemblies`);
  }

  public delete(assemblyId: number): Observable<void> {
    return this.http.delete<void>(`${this.assembliesUrl}/${assemblyId}`);
  }
}
