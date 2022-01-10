import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Motherboard} from "../entities/motherboard";
import {CPU} from "../entities/cpu";
import {GPU} from "../entities/gpu";
import {RAM} from "../entities/ram";

@Injectable({
  providedIn: 'root'
})
export class ComponentManagementService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  // Motherboard
  public getMotherboardChipsetManufacturers(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiServerUrl}/motherboard/chipset_manufacturers`);
  }

  public getMotherboardSockets(chipsetManufacturer: string): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiServerUrl}/motherboard/sockets/${chipsetManufacturer}`);
  }

  public getMotherboardChipsets(socket: string): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiServerUrl}/motherboard/chipsets/${socket}`);
  }

  public addMotherboard(motherboard: Motherboard): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}/motherboard`, motherboard);
  }

  // CPU
  public getCPUManufacturers(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiServerUrl}/cpu/manufacturers`);
  }

  public getCPUSockets(manufacturer: string): Observable<string[]> {
    return this.http.post<string[]>(`${this.apiServerUrl}/cpu/sockets`, manufacturer);
  }

  public getCPUGenerations(socket: string): Observable<string[]> {
    return this.http.post<string[]>(`${this.apiServerUrl}/cpu/generations`, socket);
  }

  public getCPUFamilies(generation: string): Observable<string[]> {
    return this.http.post<string[]>(`${this.apiServerUrl}/cpu/families`, generation);
  }

  public addCPU(cpu: CPU): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}/cpu`, cpu);
  }

  // GPU
  public getGPUChipManufacturers(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiServerUrl}/gpu/chip_manufacturers`);
  }

  public getGPUChips(chipManufacturer: string): Observable<string[]> {
    return this.http.post<string[]>(`${this.apiServerUrl}/gpu/chips`, chipManufacturer);
  }

  public addGPU(gpu: GPU): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}/gpu`, gpu);
  }

  // RAM
  public addRAM(ram: RAM): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}/ram`, ram);
  }
}
