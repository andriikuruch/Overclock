import { Component, OnInit } from '@angular/core';
import {Assembly} from "../entities/assembly";
import {Subscription} from "rxjs";
import {AssemblyService} from "../service/assembly.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {TestService} from "../service/test.service";
import {Overclock} from "../entities/overclock";

@Component({
  selector: 'app-main-test-result',
  templateUrl: './main-test-result.component.html',
  styleUrls: ['./main-test-result.component.css']
})
export class MainTestResultComponent implements OnInit {

  public assembly: Assembly = { id: 0, name: "default", motherboard: {}, ram: {}, gpu: {}, cpu: {}, author: 0, overclock: 0, comments: [], score: 0};
  public overclock: Overclock = {};
  public score: number = 0;
  private subscription: Subscription;

  constructor(private assemblyService: AssemblyService, private testService: TestService, activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe(params=>this.assembly.id=params['assemblyId']);
  }

  public getAssembly(): void {
    this.assemblyService.getAssembly(this.assembly.id).subscribe(
      (response: Assembly) => {
        this.assembly = response;
        if (this.assembly.overclock != null && this.assembly.overclock != 0) {
          this.getOverclock(this.assembly.overclock);
        }
        this.testAssembly();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public updateScore(): void  {
    this.assemblyService.updateScore(this.assembly.id, this.score).subscribe(
      (response: Assembly) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public testAssembly(): void  {
    this.testService.testAssembly(this.assembly.id).subscribe(
      (response: number) => {
        this.score = response;
        this.updateScore();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getOverclock(overclockId: number): void {
    this.assemblyService.getOverclock(overclockId).subscribe(
      (response: Overclock) => {
        this.overclock = response;
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
