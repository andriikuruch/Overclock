import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})

export class TopBarComponent implements OnInit {

  constructor(public router: Router) {}

  ngOnInit() {
  }

  openMyAssemblies(): void {
    this.router.navigate(['/my_assemblies']);
  }

  openAssemblies(): void {
    this.router.navigate(['/assemblies']);
  }

  openRating(): void {
    this.router.navigate(['/rating']);
  }

  openRegistration(): void {
    this.router.navigate(['/registration']);
  }

  openAuthorization(): void {
    this.router.navigate(['/authorization']);
  }

  openHomePage(): void{
    this.router.navigate(['/home']);
  }

}
