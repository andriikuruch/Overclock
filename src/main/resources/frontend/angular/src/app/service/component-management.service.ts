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

  public getMotherboardById(motherboardId: number): Observable<Motherboard> {
    return this.http.get<Motherboard>(`${this.apiServerUrl}/motherboard/${motherboardId}`);
  }

  public getMotherboardsByName(name: string): Observable<Motherboard[]> {
    return this.http.get<Motherboard[]>(`${this.apiServerUrl}/motherboard/search/${name}`);
  }

  public updateMotherboard(motherboard: Motherboard): Observable<void> {
    return this.http.put<void>(`${this.apiServerUrl}/motherboard/${motherboard.id}`, motherboard);
  }

  public deleteMotherboard(motherboardId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/motherboard/${motherboardId}`);
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

  public getCPUById(cpuId: number): Observable<CPU> {
    return this.http.get<CPU>(`${this.apiServerUrl}/cpu/${cpuId}`);
  }

  public getCPUsByName(name: string): Observable<CPU[]> {
    return this.http.get<CPU[]>(`${this.apiServerUrl}/cpu/search/${name}`);
  }

  public updateCPU(cpu: CPU): Observable<void> {
    return this.http.put<void>(`${this.apiServerUrl}/cpu/${cpu.id}`, cpu);
  }

  public deleteCPU(cpuId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/cpu/${cpuId}`);
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

  public getGPUById(gpuId: number): Observable<GPU> {
    return this.http.get<GPU>(`${this.apiServerUrl}/gpu/${gpuId}`);
  }

  public getGPUsByName(name: string): Observable<GPU[]> {
    return this.http.get<GPU[]>(`${this.apiServerUrl}/gpu/search/${name}`);
  }

  public updateGPU(gpu: GPU): Observable<void> {
    return this.http.put<void>(`${this.apiServerUrl}/gpu/${gpu.id}`, gpu);
  }

  public deleteGPU(gpuId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/gpu/${gpuId}`);
  }

  // RAM
  public addRAM(ram: RAM): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}/ram`, ram);
  }

  public getRAMById(ramId: number): Observable<RAM> {
    return this.http.get<RAM>(`${this.apiServerUrl}/ram/${ramId}`);
  }

  public getRAMsByName(name: string): Observable<RAM[]> {
    return this.http.get<RAM[]>(`${this.apiServerUrl}/ram/search/${name}`);
  }

  public updateRAM(ram: RAM): Observable<void> {
    return this.http.put<void>(`${this.apiServerUrl}/ram/${ram.id}`, ram);
  }

  public deleteRAM(ramId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/ram/${ramId}`);
  }
}
