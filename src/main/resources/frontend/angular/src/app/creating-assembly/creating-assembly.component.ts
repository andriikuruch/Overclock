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

    let mb = (<HTMLInputElement>document.getElementById("_mb"));
    let mbName: string = mb.value;
    let cpu = (<HTMLInputElement>document.getElementById("_cpu"));
    let cpuName: string = cpu.value;
    let gpu = (<HTMLInputElement>document.getElementById("_gpu"));
    let gpuName: string = gpu.value;
    let ram = (<HTMLInputElement>document.getElementById("_ram"));
    let ramName: string = ram.value;

    if (mbName === "") {
      this.appearanceService.customAlert("Материнская плата не была выбрана!");
      return;
    }
    if (cpuName === "") {
      this.appearanceService.customAlert("Процессор не был выбран!");
      return;
    }
    if (gpuName === "") {
      this.appearanceService.customAlert("Видеокарта не была выбрана!");
      return;
    }
    if (ramName === "") {
      this.appearanceService.customAlert("Оперативная память не была выбрана!");
      return;
    }

    for (let i = 0; i < this.mbs.length; i++) {
      if (this.mbs[i].name == mbName) {
        this.assembly.motherboard = this.mbs[i];
      }
    }
    for (let i = 0; i < this.cpus.length; i++) {
      if (this.cpus[i].name == cpuName) {
        this.assembly.cpu = this.cpus[i];
      }
    }
    for (let i = 0; i < this.gpus.length; i++) {
      if (this.gpus[i].name == gpuName) {
        this.assembly.gpu = this.gpus[i];
      }
    }
    for (let i = 0; i < this.rams.length; i++) {
      if (this.rams[i].name == ramName) {
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
            nameInput.value = '';
            mb.value = "";
            cpu.value = "";
            gpu.value = "";
            ram.value = "";
            this.notificationMessage = response;
        }
        this.appearanceService.customAlert(this.notificationMessage);
      }
    );
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

  openHomePage(): void {
    this.router.navigate(['/home']);
  }

  clear(id: string): void {
    (<HTMLInputElement>document.getElementById(id)).value = "";
  }

  ngOnInit(): void {
    if (!this.isLoggedIn)
      this.openAuthorization();
    else if (this.isAdmin)
      this.openHomePage();
    else {
      this.getAllMotherboards();
      this.getAllCPUs();
      this.getAllGPUs();
      this.getAllRAMs();
    }
  }
}
