import { Component, OnInit } from '@angular/core';
import {AssemblyService} from "../service/assembly.service";
import {Assembly} from "../entities/assembly";
import {GPU} from "../entities/gpu";
import {CPU} from "../entities/cpu";
import {RAM} from "../entities/ram";
import {Motherboard} from "../entities/motherboard";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {AppearanceService} from "../service/appearance.service";
import {Router} from "@angular/router";
import { DataSharingService } from '../service/datasharing.service';

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
  private notificationMessage: string = "";

  private subscription: Subscription;

  isLoggedIn : boolean = false;
  isAdmin : boolean = false;

  constructor(private assemblyService: AssemblyService, private appearanceService: AppearanceService, activateRoute: ActivatedRoute,
              private router: Router, private dataSharingService: DataSharingService) {
    this.subscription = activateRoute.params.subscribe(params=>this.assembly.id=params['id']);

    this.dataSharingService.isLoggedIn.subscribe( value => {
      this.isLoggedIn = value;
    });
    this.dataSharingService.isAdmin.subscribe(value => {
      this.isAdmin = value;
    })
  }

  public createAssembly(): void {
    this.assembly = { id: 0, name: "default", motherboard: {}, ram: {}, gpu: {}, cpu: {}, author: 0, overclock: 0, comments: [], score: 0};
    let nameInput = (<HTMLInputElement>document.getElementById("name"));
    let name: string = nameInput.value;
    if (name == '') {
      this.appearanceService.customAlert("Имя не введено");
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
      (response: string) => {

        switch (response) {
          case "Motherboard and CPU are not compatible":
            this.notificationMessage = "Материнская плата и процессор несовместимы";
            break;
          case "Motherboard is not valid":
            this.notificationMessage = "Материнская плата имеет ошибочные характеристики. " +
              "\nПожалуйста выберите другую из предложенного списка. Приносим свои извинения.";
            break;
          case "CPU is not valid":
            this.notificationMessage = "Процессор имеет ошибочные характеристики. " +
              "\nПожалуйста выберите другой из предложенного списка. Приносим свои извинения.";
            break;
          case "GPU is not valid":
            this.notificationMessage = "Видеокарта имеет ошибочные характеристики. " +
              "\nПожалуйста выберите другую из предложенного списка. Приносим свои извинения.";
            break;
          default:
            this.notificationMessage = response;
        }
        this.appearanceService.customAlert(this.notificationMessage);
      }
    );
    nameInput.value = '';
  }

  public getAllMotherboards(): void {
    this.assemblyService.getAllMotherboards().subscribe(
      (response: Motherboard[]) => {
        this.mbs = response;
      }
    );
  }

  public getAllCPUs(): void {
    this.assemblyService.getAllCPUs().subscribe(
      (response: CPU[]) => {
        this.cpus = response;
      }
    );
  }

  public getAllGPUs(): void {
    this.assemblyService.getAllGPUs().subscribe(
      (response: GPU[]) => {
        this.gpus = response;
      }
    );
  }

  public getAllRAMs(): void {
    this.assemblyService.getAllRAMs().subscribe(
      (response: RAM[]) => {
        this.rams = response;
      }
    );
  }

  openAuthorization(): void {
    this.router.navigate(['/authorization']);
  }

  openHomePage(): void{
    this.router.navigate(['/home']);
  }

  ngOnInit(): void {
    if (this.isLoggedIn === false)
      this.openAuthorization();
    else if (this.isAdmin === true)
      this.openHomePage();
    else {
      this.getAllMotherboards();
      this.getAllCPUs();
      this.getAllGPUs();
      this.getAllRAMs();
    }
  }
}
