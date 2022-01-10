import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import { TokenStorageService } from '../service/token-storage.service';
import { DataSharingService } from '../service/datasharing.service';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})

export class TopBarComponent implements OnInit {

  constructor(public router: Router, private tokenStorage: TokenStorageService, private dataSharingService: DataSharingService) {
    this.dataSharingService.isLoggedIn.subscribe( value => {
      this.isLoggedIn = value;
    });
    this.dataSharingService.curUser.subscribe( value => {
      this.curUser = value;
    });
    this.dataSharingService.isAdmin.subscribe(value => {
      this.isAdmin = value;
    })
  }

  isLoggedIn : boolean = false;
  curUser : string = '';
  role : string = '';
  isAdmin : boolean = false;

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.dataSharingService.isLoggedIn.next(true);
      this.getCurrentUser();
    } else
      this.dataSharingService.isLoggedIn.next(false);
    if (this.tokenStorage.getUser().role  === "ADMIN"){
      this.dataSharingService.isAdmin.next(true);
    } else
      this.dataSharingService.isAdmin.next(false);
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

  openComponents(): void{
    this.router.navigate(['/components']);
  }

  getCurrentUser(): void {
    this.curUser = this.tokenStorage.getUser().userName;
  }

  logOut() {
    this.tokenStorage.signOut();
    this.dataSharingService.isLoggedIn.next(false);
    this.dataSharingService.isAdmin.next(false);
    this.openHomePage();
  }
}
