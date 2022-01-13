import { Component, OnInit } from '@angular/core';
import {Assembly} from "../entities/assembly";
import {Subscription} from "rxjs";
import {AssemblyService} from "../service/assembly.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import { DataSharingService } from '../service/datasharing.service';

@Component({
  selector: 'app-assembly-test',
  templateUrl: './assembly-test.component.html',
  styleUrls: ['./assembly-test.component.css']
})
export class AssemblyTestComponent implements OnInit {

  public assembly: Assembly = { id: 0, name: "default", motherboard: {}, ram: {}, gpu: {}, cpu: {}, author: 0, overclock: 0, comments: [], score: 0};
  public programs: string[] = ["Blander", "Adobe Premiere", "CS GO", "Metro Exodus"];
  public selectedProgram: string = this.programs[0];
  private subscription: Subscription;

  isLoggedIn : boolean = false;
  isAdmin : boolean = false;

  constructor(private assemblyService: AssemblyService, activateRoute: ActivatedRoute, private router: Router,
              private dataSharingService: DataSharingService) {
    this.dataSharingService.isLoggedIn.subscribe( value => {
      this.isLoggedIn = value;
    });
    this.dataSharingService.isAdmin.subscribe(value => {
      this.isAdmin = value;
    })
    this.subscription = activateRoute.params.subscribe(params=>this.assembly.id=params['assemblyId']);
  }

  public getAssembly(): void {
    this.assemblyService.getAssembly(this.assembly.id).subscribe(
      (response: Assembly) => {
        this.assembly = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public switchLeft(): void {
    if (this.selectedProgram == this.programs[0]) {
      this.selectedProgram = this.programs[this.programs.length-1];
    } else {
      this.selectedProgram = this.programs[this.programs.indexOf(this.selectedProgram)-1];
    }
    this.setImage();
  }

  public switchRight(): void {
    if (this.selectedProgram == this.programs[this.programs.length-1]) {
      this.selectedProgram = this.programs[0];
    } else {
      this.selectedProgram = this.programs[this.programs.indexOf(this.selectedProgram)+1];
    }
    this.setImage();
  }

  public setImage(): void {
    let img = <HTMLImageElement>document.getElementById("program-img");
    let imgName = "";
    if (this.selectedProgram == this.programs[0]) {
      imgName = this.selectedProgram;
    } else {
      let array = this.selectedProgram.split(' ');
      imgName = array[0] + "%20" + array[1];
    }
    img.src = "assets/"+ imgName +".jpg";
  }

  public navigateToMainTest(): void {
    this.router.navigate([`/my_assemblies/${this.assembly.id}/main_test_result`]);
  }

  public navigateToTestOnProgram(): void {
    let program: string;
    if (this.selectedProgram == this.programs[0]) {
      program = this.selectedProgram;
    } else {
      let array = this.selectedProgram.split(' ');
      program = array[0] + '_' + array[1];
    }
    this.router.navigate([`/my_assemblies/${this.assembly.id}/test_on_program/${program}`]);
  }

  public navigateToPartialTest(component: string): void {
    this.router.navigate([`/my_assemblies/${this.assembly.id}/partial_test/${component}`]);
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
    else
      this.getAssembly();
  }

}
