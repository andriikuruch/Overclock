import { Component, OnInit } from '@angular/core';
import {MyAssembliesService} from "../service/my-assemblies.service";
import {Assembly} from "../entities/assembly";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import { DataSharingService } from '../service/datasharing.service';
import {AppearanceService} from "../service/appearance.service";

@Component({
  selector: 'app-my-assemblies',
  templateUrl: './my-assemblies.component.html',
  styleUrls: ['./my-assemblies.component.css']
})
export class MyAssembliesComponent implements OnInit {

  myAssemblies: Assembly[] = [];

  private deletedAssemblyId: number = 0;

  constructor(private myAssembliesService: MyAssembliesService,
              private router: Router,
              private dataSharingService: DataSharingService,
              private appearanceService: AppearanceService) {
    this.dataSharingService.isLoggedIn.subscribe(value => {
      this.isLoggedIn = value;
    });
    this.dataSharingService.isAdmin.subscribe(value => {
      this.isAdmin = value;
    })
  }

  isLoggedIn: boolean = false;
  isAdmin: boolean = false;

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
    this.deletedAssemblyId = assemblyId;
    let assemblyName;
    for (let i = 0; i < this.myAssemblies.length; i++) {
      if (this.myAssemblies[i].id == assemblyId) {
        assemblyName = this.myAssemblies[i].name;
        break;
      }
    }

    let allowButton = <HTMLButtonElement>document.getElementById('allowButton');
    let denyButton = <HTMLButtonElement>document.getElementById('denyButton');
    allowButton.addEventListener('click', () => this.allowDeleteAssembly());
    denyButton.addEventListener('click', this.denyDeleteAssembly);

    this.appearanceService.customQuestion('Вы действительно хотите удалить сборку ' + assemblyName + '?');
  }

  allowDeleteAssembly(): void {
    let question = document.getElementById("question");
    question!.style.display = "none";

    let allowButton = <HTMLButtonElement>document.getElementById('allowButton');
    allowButton.removeEventListener('click', allowButton.eventListeners!('click').pop()!);

    this.myAssembliesService.delete(this.deletedAssemblyId).subscribe(
      (response: void) => {
        this.getAssemblies();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  denyDeleteAssembly(): void {
    let question = document.getElementById("question");
    question!.style.display = "none";

    let denyButton = <HTMLButtonElement>document.getElementById('denyButton');
    denyButton.removeEventListener('click', denyButton.eventListeners!('click').pop()!);
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
    if (!this.isLoggedIn)
      this.openAuthorization();
    else if (this.isAdmin)
      this.openHomePage();
    else
      this.getAssemblies();
    let li = (<HTMLLIElement>document.getElementById("my_assemblies"));
    li.style.textDecoration = "underline";
  }

  ngOnDestroy(): void {
    let li = (<HTMLLIElement>document.getElementById("my_assemblies"));
    li.style.textDecoration = "none";
  }
}
