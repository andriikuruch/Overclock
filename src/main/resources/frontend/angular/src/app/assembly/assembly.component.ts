import { Component, OnInit } from '@angular/core';
import {Assembly} from "../entities/assembly";
import {AssemblyService} from "../service/assembly.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Comment} from "../entities/comment";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {UserService} from "../service/user.service";
import {User} from "../entities/user";

@Component({
  selector: 'app-assembly-component',
  templateUrl: './assembly.component.html',
  styleUrls: ['./assembly.component.css']
})
export class AssemblyComponent implements OnInit {

  public assembly: Assembly = { id: 0, name: "default", motherboard: {}, ram: {}, gpu: {}, cpu: {}, author: 0, overclock: 0, comments: [], score: 0};
  public user: User = {};
  private subscription: Subscription;

  constructor(private assemblyService: AssemblyService, activateRoute: ActivatedRoute, private userService: UserService) {
    this.subscription = activateRoute.params.subscribe(params=>this.assembly.id=params['id']);
  }

  public getAssembly(): void {
    this.assemblyService.getAssembly(this.assembly.id).subscribe(
      (response: Assembly) => {
        this.assembly = response;
        this.getUserById(this.assembly.author);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddComment(assemblyId: number): void {
    const inputValue = (<HTMLInputElement>document.getElementById('commentMessage')).value;
    let date = new Date();
    let dd = String(date.getDate()).padStart(2, '0');
    let mm = String(date.getMonth() + 1).padStart(2, '0');
    let yyyy = date.getFullYear();
    const today =  yyyy+ '-' + mm + '-' + dd;
    if (inputValue == null || inputValue == '') {
      alert("Message can't be blank");
    } else {
      this.assemblyService.addComment(assemblyId, {
        commentMessage: inputValue.toString(),
        dateOfComment: today.toString()
      }).subscribe(
        (response: Comment) => {
          this.getAssembly();
        },
        (error: HttpErrorResponse) => {
          alert(error.message)
        }
      );
    }
  }

  public getUserById(userId: number): void {
    this.userService.getUserById(userId).subscribe(
      (response: User) => {
        this.user = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  ngOnInit(): void {
    this.getAssembly();
  }
}
