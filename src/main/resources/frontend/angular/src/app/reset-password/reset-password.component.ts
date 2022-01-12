import {ResetPasswordRequest} from "../model/resetPassword";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";
import {AppearanceService} from "../service/appearance.service";
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-resetPassword',
    templateUrl: 'reset-password.component.html',
    styleUrls: ['./reset-password.component.css']
  })

  export class ResetPasswordComponent {
  data: ResetPasswordRequest | undefined;
  form: FormGroup = new FormGroup({
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
  });

  get password() {
    return this.form.get('password');
  }

  constructor( private authService: AuthService, public router: Router, public appearanceService: AppearanceService,
               private route: ActivatedRoute) {
  }

  request : ResetPasswordRequest = new ResetPasswordRequest('', '');

  errorMessage = 'error';

  isSuccessful = false;

  ngOnInit() {
    this.disableSubmit();
    this.getToken();
    this.enableSubmit();
  }

  onSubmit() {
    if (this.form.valid) {
      this.authService.resetPassword(this.request).subscribe(
        data => {
          console.log(data);
        },
        err => {
          this.errorMessage = err.error.message;
          console.log(this.errorMessage);
          this.appearanceService.customAlert("Не удалось сбросить пароль");
        },
        () => {
          this.cleanUp();
          this.isSuccessful = true;
        }
      );
    }else {
      console.log("NO")
    }
  }

  getToken() : void {
    this.route.queryParams.subscribe(params => {
      this.request.resetPasswordToken = params['token'];
    });
  }

  enableSubmit() : void {
    (<HTMLInputElement>document.getElementById("register")).disabled = false;
  }

  disableSubmit() : void {
    (<HTMLInputElement>document.getElementById("register")).disabled = true;
  }

  cleanUp() : void {
    (<HTMLInputElement>document.getElementById("signup")).remove();
    (<HTMLInputElement>document.getElementById("password")).remove();
    (<HTMLInputElement>document.getElementById("register")).remove();
  }

  openAuthorization(): void {
    this.router.navigate(['/authorization']);
  }

}
