import { Component, OnInit } from '@angular/core';
import { Assembly } from "../entities/assembly";
import {RatingService} from "../service/rating.service";
import {HttpErrorResponse} from "@angular/common/http";
import {UserService} from "../service/user.service";
import {User} from "../entities/user";

@Component({
  selector: 'app-rating-table',
  templateUrl: './rating-table.component.html',
  styleUrls: ['./rating-table.component.css']
})
export class RatingTableComponent implements OnInit {

  public assemblies: Assembly[] = [];
  public users: User[] = [];

  constructor(private ratingService: RatingService, private userService: UserService) { }

  public getTopByOverclock(): void {
    this.ratingService.getTopByOverclock().subscribe(
      (response: Assembly[]) => {
        this.assemblies = response;
        this.initializeUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    let buttonOverclock = <HTMLButtonElement>document.getElementById("overclockTop");
    let buttonDefault = <HTMLButtonElement>document.getElementById("defaultTop");
    buttonOverclock.disabled = true;
    buttonDefault.disabled = false;
    buttonOverclock.style.background = "#111111";
    buttonDefault.style.background = "#2c283b";
  }

  public getTopByDefault(): void {
    this.ratingService.getTopByDefault().subscribe(
      (response: Assembly[]) => {
        this.assemblies = response;
        this.initializeUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    let buttonOverclock = <HTMLButtonElement>document.getElementById("overclockTop");
    let buttonDefault = <HTMLButtonElement>document.getElementById("defaultTop");
    buttonOverclock.disabled = false;
    buttonDefault.disabled = true;
    buttonOverclock.style.background = "#2c283b";
    buttonDefault.style.background = "#111111";
  }

  public getUserById(userId: number, i: number): void {
    this.userService.getUserById(userId).subscribe(
      (response: User) => {
        this.users[i] = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public initializeUsers(): void {
    this.users = []
    for (let i = 0; i < this.assemblies.length; i++) {
      this.getUserById(this.assemblies[i].author, i);
    }
  }

  ngOnInit(): void {
    this.getTopByDefault()
  }
}
