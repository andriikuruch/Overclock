import { Component, OnInit } from '@angular/core';
import {MyAssembliesService} from "../service/my-assemblies.service";
import {Assembly} from "../entities/assembly";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import { DataSharingService } from '../service/datasharing.service';

@Component({
  selector: 'app-my-assemblies',
  templateUrl: './my-assemblies.component.html',
  styleUrls: ['./my-assemblies.component.css']
})
export class MyAssembliesComponent implements OnInit {

  myAssemblies: Assembly[] = [];

  constructor(private myAssembliesService: MyAssembliesService, private router: Router, private dataSharingService: DataSharingService) {
    this.dataSharingService.isLoggedIn.subscribe( value => {
      this.isLoggedIn = value;
    });
    this.dataSharingService.isAdmin.subscribe(value => {
      this.isAdmin = value;
    })
  }

  isLoggedIn : boolean = false;
  isAdmin : boolean = false;

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

  openAuthorization(): void {
    this.router.navigate(['/authorization']);
  }

  openHomePage(): void {
    this.router.navigate(['/home']);
  }

  ngOnInit() {
    if (this.isLoggedIn === false)
      this.openAuthorization();
    else if (this.isAdmin === true)
      this.openHomePage();
    else
      this.getAssemblies();
  }
}
