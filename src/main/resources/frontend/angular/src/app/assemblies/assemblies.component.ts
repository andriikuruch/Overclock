import { Component, OnInit } from '@angular/core';
import { Assembly } from "../entities/assembly";
import { Motherboard } from "../entities/motherboard";
import { CPU } from "../entities/cpu";
import { GPU } from "../entities/gpu";
import { RAM } from "../entities/ram";
import {AssemblyService} from "../service/assembly.service";
import {HttpErrorResponse} from "@angular/common/http";
import {UserService} from "../service/user.service";
import {User} from "../entities/user";

@Component({
  selector: 'app-assemblies',
  templateUrl: './assemblies.component.html',
  styleUrls: ['./assemblies.component.css']
})
export class AssembliesComponent implements OnInit {

  public assemblies: Assembly[] = [];
  public motherboards: Motherboard[] = [];
  public cpus: CPU[] = [];
  public gpus: GPU[] = [];
  public rams: RAM[] = [];
  public users: User[] = [];
  public searchText = '';

  public motherboardCheckboxes: Array<any>;
  public cpuCheckboxes: Array<any>;
  public gpuCheckboxes: Array<any>;
  public ramCheckboxes: Array<any>;

  constructor(private assemblyService: AssemblyService, private userService: UserService) {
    this.motherboardCheckboxes = [];
    this.cpuCheckboxes = [];
    this.gpuCheckboxes = [];
    this.ramCheckboxes = [];
  }

  motherboardChecked() {
    return this.motherboardCheckboxes.filter(item => { return item.checked; });
  }

  cpuChecked() {
    return this.cpuCheckboxes.filter(item => { return item.checked; });
  }

  gpuChecked() {
    return this.gpuCheckboxes.filter(item => { return item.checked; });
  }

  ramChecked() {
    return this.ramCheckboxes.filter(item => { return item.checked; });
  }

  public createItems() : void {
  }

  public getAll(): void {
    this.assemblyService.getAllAssemblies().subscribe(
      (response: Assembly[]) => {
        this.assemblies = response;
        this.initializeUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public reset(): void {
    this.motherboardCheckboxes = this.motherboardCheckboxes.map(o => ({ ...o, checked: o.checked = false }));
    this.cpuCheckboxes = this.cpuCheckboxes.map(o => ({ ...o, checked: o.checked = false }));
    this.gpuCheckboxes = this.gpuCheckboxes.map(o => ({ ...o, checked: o.checked = false }));
    this.ramCheckboxes = this.ramCheckboxes.map(o => ({ ...o, checked: o.checked = false }));
    this.searchText = '';
  }

  public getAllMotherboards(): void {
    this.assemblyService.getAllMotherboards().subscribe(
      (response: Motherboard[]) => {
        this.motherboards = response;
        for (var i = 0; i < this.motherboards.length; i++)
           this.motherboardCheckboxes.push({'value': this.motherboards[i].name, 'checked': false});
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
        for (var i = 0; i < this.cpus.length; i++)
           this.cpuCheckboxes.push({'value': this.cpus[i].name, 'checked': false});
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
        for (var i = 0; i < this.gpus.length; i++)
           this.gpuCheckboxes.push({'value': this.gpus[i].name, 'checked': false});
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
        for (var i = 0; i < this.rams.length; i++)
           this.ramCheckboxes.push({'value': this.rams[i].name, 'checked': false});
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getUserById(userId: number, i: number): void {
    this.userService.getUserById(userId).subscribe(
      (response: User) => {
        this.users[i] = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public initializeUsers(): void {
    this.users = []
    for (let i = 0; i < this.assemblies.length; i++) {
      this.getUserById(this.assemblies[i].author, i);
    }
  }

  ngOnInit(): void {
    this.getAllMotherboards();
    this.getAllCPUs();
    this.getAllGPUs();
    this.getAllRAMs();
    this.getAll();
  }
}
