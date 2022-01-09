import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-activating-account',
  templateUrl: './activating-account.component.html',
  styleUrls: ['./activating-account.component.css']
})
export class ActivatingAccountComponent implements OnInit {

  isActivated : boolean = false;
  flag: boolean = true;
  errorMessage = 'error';

  constructor(public router: Router, private route: ActivatedRoute, public authService: AuthService) {}

  ngOnInit() {
    let token = this.route.snapshot.queryParamMap.get('token');
      if (this.flag) {
        this.authService.activateAccount(token).subscribe(
          data => {
            this.isActivated = true;
            this.flag = false;
            console.log(data);
          },
          err => {
            this.errorMessage = err.error.message;
            console.log(this.errorMessage);
          }
        )
      }
  }

  openCreationAssembly(): void{
    this.router.navigate(['/my_assemblies/creating']);
  }



}
