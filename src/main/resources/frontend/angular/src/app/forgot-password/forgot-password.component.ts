import {ForgotPasswordRequest} from "../model/forgotPassword";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";
import {AppearanceService} from "../service/appearance.service";

@Component({
    selector: 'app-forgotpassword',
    templateUrl: 'forgot-password.component.html',
    styleUrls: ['./forgot-password.component.css']
  })

  export class ForgotPasswordComponent {
  data: ForgotPasswordRequest | undefined;
  form: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
  });

  get email() {
    return this.form.get('email');
  }

  constructor( private authService: AuthService, public router: Router, public appearanceService: AppearanceService) {
  }

  request: ForgotPasswordRequest = new ForgotPasswordRequest('');

  errorMessage = 'error';

  ngOnInit() {
  }

  onSubmit() {
    if (this.form.valid) {
      this.authService.forgotPassword(this.request).subscribe(
        data => {
          console.log(data);
        },
        err => {
          this.errorMessage = err.error.message;
          console.log(this.errorMessage);
          this.appearanceService.customAlert("Такого адреса не существует в системе");
          this.changeTextOnFail();
        },
        () => {
          this.appearanceService.customAlert("Письмо с инструкциями по восстановлению отправлено на указанный e-mail");
          this.changeTextOnSuccess();
        }
      );
    }else {
      console.log("Form invalid")
    }
  }

  changeTextOnFail() : void {
    (<HTMLInputElement>document.getElementById("register")).textContent="Восстановить";
  }

  changeTextOnSuccess() : void {
    (<HTMLInputElement>document.getElementById("register")).textContent="Отправить снова";
  }

  openAuthorization(): void {
    this.router.navigate(['/authorization']);
  }

}
