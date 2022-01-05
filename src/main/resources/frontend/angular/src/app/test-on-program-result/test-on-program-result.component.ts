import { Component, OnInit } from '@angular/core';
import {Assembly} from "../entities/assembly";
import {Overclock} from "../entities/overclock";
import {Subscription} from "rxjs";
import {AssemblyService} from "../service/assembly.service";
import {TestService} from "../service/test.service";
import {ActivatedRoute} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-test-on-program-result',
  templateUrl: './test-on-program.component-result.html',
  styleUrls: ['./test-on-program.component-result.css']
})
export class TestOnProgramResultComponent implements OnInit {

  public assembly: Assembly = { id: 0, name: "default", motherboard: {}, ram: {}, gpu: {}, cpu: {}, author: 0, overclock: 0, comments: [], score: 0};
  public overclock: Overclock = {};
  public program: string = "";
  public score: number = 0;
  private subscriptionAssemblyId: Subscription;
  private subscriptionProgram: Subscription;

  constructor(private assemblyService: AssemblyService, private testService: TestService, activateRoute: ActivatedRoute) {
    this.subscriptionAssemblyId = activateRoute.params.subscribe(params=>this.assembly.id=params['assemblyId']);
    this.subscriptionProgram = activateRoute.params.subscribe(params=>this.program=params['program']);
  }

  public getAssembly(): void {
    this.assemblyService.getAssembly(this.assembly.id).subscribe(
      (response: Assembly) => {
        this.assembly = response;
        if (this.assembly.overclock != null && this.assembly.overclock != 0) {
          this.getOverclock(this.assembly.overclock);
        }
        this.testAssemblyOnProgram();
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

  public testAssemblyOnProgram(): void  {
    this.testService.testAssemblyOnProgram(this.assembly.id, this.program).subscribe(
      (response: number) => {
        this.score = response;
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
