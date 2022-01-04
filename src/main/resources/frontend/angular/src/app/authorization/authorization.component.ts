import { Component, OnInit } from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {UserService} from "../service/user.service";
import {User} from "../entities/user";
import {Router} from "@angular/router";
import { AuthService } from '../service/auth.service';
import { TokenStorageService } from '../service/token-storage.service';
import { DataSharingService } from '../service/datasharing.service';

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  user : any = {
                 name: '',
                 password: ''
                }

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router,
              private dataSharingService: DataSharingService) {
    this.dataSharingService.isLoggedIn.subscribe( value => {
      this.isLoggedIn = value;
    });
  }

  public onSubmit() : void {
    let nameInput = (<HTMLInputElement>document.getElementById("userName"));
    let name: string = nameInput.value;
    let passInput = (<HTMLInputElement>document.getElementById("password"));
    let password: string = passInput.value;

    this.user = {
                  name : name,
                  password : password
                }


    this.authService.login(this.user).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data.user);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.dataSharingService.isLoggedIn.next(true);
        this.dataSharingService.curUser.next(this.getCurrentUser());
        this.router.navigate(['/']);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
        alert('error:' + err.error.message);
      }
    );
  }

  reloadPage() : void {
    window.location.reload();
  }

  getCurrentUser(): string {
    return this.tokenStorage.getUser().userName;
  }

  ngOnInit() : void {
    }
}
