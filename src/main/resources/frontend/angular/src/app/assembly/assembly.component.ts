import { Component, OnInit } from '@angular/core';
import {Assembly} from "../entities/assembly";
import {AssemblyService} from "../service/assembly.service";
import {Comment} from "../entities/comment";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {UserService} from "../service/user.service";
import {User} from "../entities/user";
import {AppearanceService} from "../service/appearance.service";
import {DataSharingService} from "../service/datasharing.service";

@Component({
  selector: 'app-assembly-component',
  templateUrl: './assembly.component.html',
  styleUrls: ['./assembly.component.css']
})
export class AssemblyComponent implements OnInit {

  public assembly: Assembly = { id: 0, name: "default", motherboard: {}, ram: {}, gpu: {}, cpu: {}, author: 0, overclock: 0, comments: [], score: 0};
  public user: User = {};
  private subscription: Subscription;

  config = {
    itemsPerPage: 5,
    currentPage: 1
  };

  isLoggedIn : boolean = false;

  constructor(private assemblyService: AssemblyService, activateRoute: ActivatedRoute, private router: Router,
              private userService: UserService, private appearanceService: AppearanceService, private dataSharingService: DataSharingService) {
    this.subscription = activateRoute.params.subscribe(params=>this.assembly.id=params['id']);
    this.dataSharingService.isLoggedIn.subscribe( value => {
      this.isLoggedIn = value;
    });
  }

  public getAssembly(): void {
    this.assemblyService.getAssembly(this.assembly.id).subscribe(
      (response: Assembly) => {
        if (response === null) {
          this.router.navigate([`/assembly/${this.assembly.id}/not_found`]);
        } else {
          this.assembly = response;
          this.assembly.comments.sort(function (a, b) {
            let aDate = new Date(a.dateOfComment), bDate = new Date(b.dateOfComment);
            return aDate > bDate ? -1 : aDate < bDate ? 1 : 0;
          })
          this.getUserById(this.assembly.author);
        }
      }
    );
  }

  public onAddComment(assemblyId: number): void {
    const input = (<HTMLInputElement>document.getElementById('commentMessage'));
    const inputValue = input.value;
    let date = new Date();
    let dd = String(date.getDate()).padStart(2, '0');
    let mm = String(date.getMonth() + 1).padStart(2, '0');
    let yyyy = date.getFullYear();
    const today =  yyyy+ '-' + mm + '-' + dd;
    if (inputValue == null || inputValue == '') {
      this.appearanceService.customAlert("Сообщение не может быть пустым!");
    } else if (inputValue.length > 222) {
      this.appearanceService.customAlert("Слишком большое сообщение!");
    } else {
      this.assemblyService.addComment(assemblyId, {
        commentMessage: inputValue.toString(),
        dateOfComment: today.toString()
      }).subscribe(
        (response: Comment) => {
          this.getAssembly();
        }
      );
      input.value = "";
    }
  }

  public getUserById(userId: number): void {
    this.userService.getUserById(userId).subscribe(
      (response: User) => {
        this.user = response;
      }
    );
  }

  pageChanged(event : any) : any {
    this.config.currentPage = event;
  }

  resetPage() : void {
    this.config.currentPage = 1;
  }

  redirectToNotFound() : void {
    this.router.navigate([`/assembly_not_fount`]);
  }

  ngOnInit(): void {
    this.getAssembly();
  }
}
