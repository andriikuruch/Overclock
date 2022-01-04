import { Component, OnInit } from '@angular/core';
import {MyAssembliesService} from "../service/my-assemblies.service";
import {Assembly} from "../entities/assembly";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-my-assemblies',
  templateUrl: './my-assemblies.component.html',
  styleUrls: ['./my-assemblies.component.css']
})
export class MyAssembliesComponent implements OnInit {

  myAssemblies: Assembly[] = [];

  constructor(private myAssembliesService: MyAssembliesService, private router: Router) {
  }

  public getAssemblies(): void {
    this.myAssembliesService.getAll().subscribe(
      (response: Assembly[]) => {
        this.myAssemblies = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onTestAssembly(assemblyId: number): void {
    this.router.navigate([`my_assemblies/${assemblyId}/testing`]);
  }

  public onOverclockAssembly(assemblyId: number): void {
    this.router.navigate([`my_assemblies/${assemblyId}/overclocking`]);
  }

  public onDeleteAssembly(assemblyId: number): void {
    this.myAssembliesService.delete(assemblyId).subscribe(
      (response: void) => {
        this.getAssemblies();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddAssembly() {
    this.router.navigate(['my_assemblies/creating']);
  }

  ngOnInit() {
    this.getAssemblies();
  }
}
