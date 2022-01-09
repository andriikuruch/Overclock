import {User} from "../model/user";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";
import {AppearanceService} from "../service/appearance.service";

@Component({
    selector: 'app-registration',
    templateUrl: 'registration.component.html',
    styleUrls: ['./registration.component.css']
  })


  export class RegistrationComponent {
  data: User | undefined;
  form: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(4)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
  });

  get username() {
    return this.form.get('username');
  }

  get email() {
    return this.form.get('email');
  }

  get password() {
    return this.form.get('password');
  }

  constructor( private authService: AuthService, public router: Router, public appearanceService: AppearanceService) {
  }

  user: User = new User('', '', '', '');

  errorMessage = 'error';

  ngOnInit() {
  }

  onSubmit() {
    if (this.form.valid) {
      this.authService.register(this.user).subscribe(
        data => {
          console.log(data);
        },
        err => {
          this.errorMessage = err.error.message;
          console.log(this.errorMessage);
          this.appearanceService.customAlert("Пользователь с данным почтовым аккаунтом уже зарегистрирован или такого почтового адресса не существует");
        }
      );
    }else {
      console.log("NO")
    }
  }

  openAuthorization(): void {
    this.router.navigate(['/authorization']);
  }

}
