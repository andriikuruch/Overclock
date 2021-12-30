import { Component, OnInit } from '@angular/core';
import {AssemblyService} from "../service/assembly.service";
import {Assembly} from "../entities/assembly";
import {HttpErrorResponse} from "@angular/common/http";
import {GPU} from "../entities/gpu";
import {CPU} from "../entities/cpu";
import {RAM} from "../entities/ram";
import {Motherboard} from "../entities/motherboard";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-creating-assembly',
  templateUrl: './creating-assembly.component.html',
  styleUrls: ['./creating-assembly.component.css']
})
export class CreatingAssemblyComponent implements OnInit {

  public assembly: Assembly = { id: 0, name: "default", motherboard: {}, ram: {}, gpu: {}, cpu: {}, author: 0, overclock: 0, comments: [], score: 0};
  public mbs: Motherboard[] = [];
  public cpus: CPU[] = [];
  public gpus: GPU[] = [];
  public rams: RAM[] = [];

  private subscription: Subscription;

  constructor(private assemblyService: AssemblyService, activateRoute: ActivatedRoute) {
  this.subscription = activateRoute.params.subscribe(params=>this.assembly.id=params['id']);
}
  public createAssembly(): void {
    this.assembly = { id: 0, name: "default", motherboard: {}, ram: {}, gpu: {}, cpu: {}, author: 0, overclock: 0, comments: [], score: 0};
    let nameInput = (<HTMLInputElement>document.getElementById("name"));
    let name: string = nameInput.value;
    if (name == '') {
      alert('Имя не введено!')
      return;
    }

    this.assembly.name = name.toString();

    let mb = (<HTMLSelectElement>document.getElementById("mb"));
    let mbId: number = Number(mb.options[mb.selectedIndex].id);
    let cpu = (<HTMLSelectElement>document.getElementById("cpu"));
    let cpuId: number = Number(cpu.options[cpu.selectedIndex].id);
    let gpu = (<HTMLSelectElement>document.getElementById("gpu"));
    let gpuId: number = Number(gpu.options[gpu.selectedIndex].id);
    let ram = (<HTMLSelectElement>document.getElementById("ram"));
    let ramId: number = Number(ram.options[ram.selectedIndex].id);


    for (let i = 0; i < this.mbs.length; i++) {
      if (this.mbs[i].id == mbId) {
        this.assembly.motherboard = this.mbs[i];
      }
    }
    for (let i = 0; i < this.cpus.length; i++) {
      if (this.cpus[i].id == cpuId) {
        this.assembly.cpu = this.cpus[i];
      }
    }
    for (let i = 0; i < this.gpus.length; i++) {
      if (this.gpus[i].id == gpuId) {
        this.assembly.gpu = this.gpus[i];
      }
    }
    for (let i = 0; i < this.rams.length; i++) {
      if (this.rams[i].id == ramId) {
        this.assembly.ram = this.rams[i];
      }
    }

    this.assemblyService.createAssembly(this.assembly).subscribe(
      (response: Assembly) => {
        this.assembly = response;
        alert('Сборка '+  name +' успешно создана');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    nameInput.value = '';
  }

  public getAllMotherboards(): void {
    this.assemblyService.getAllMotherboards().subscribe(
      (response: Motherboard[]) => {
        this.mbs = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getAllCPUs(): void {
    this.assemblyService.getAllCPUs().subscribe(
      (response: CPU[]) => {
        this.cpus = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getAllGPUs(): void {
    this.assemblyService.getAllGPUs().subscribe(
      (response: GPU[]) => {
        this.gpus = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getAllRAMs(): void {
    this.assemblyService.getAllRAMs().subscribe(
      (response: RAM[]) => {
        this.rams = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  ngOnInit(): void {
    this.getAllMotherboards();
    this.getAllCPUs();
    this.getAllGPUs();
    this.getAllRAMs();
  }
}
