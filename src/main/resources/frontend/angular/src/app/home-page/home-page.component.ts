import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(public router: Router) { }

  ngOnInit(): void {
  }

  openCreationAssembly(): void{
    this.router.navigate(['/my_assemblies/creating']);
  }

}
