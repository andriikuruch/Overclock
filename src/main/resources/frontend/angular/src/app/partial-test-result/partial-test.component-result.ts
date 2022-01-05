import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {AssemblyService} from "../service/assembly.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Assembly} from "../entities/assembly";
import {HttpErrorResponse} from "@angular/common/http";
import {Overclock} from "../entities/overclock";
import {TestService} from "../service/test.service";

@Component({
  selector: 'app-partial-test-result',
  templateUrl: './partial-test.component-result.html',
  styleUrls: ['./partial-test.component-result.css']
})
export class PartialTestComponentResult implements OnInit {

  public assembly: Assembly = { id: 0, name: "default", motherboard: {}, ram: {}, gpu: {}, cpu: {}, author: 0, overclock: 0, comments: [], score: 0};
  public overclock: Overclock = {};
  public score: number = 0;
  public component: string = "";
  private subscriptionAssemblyId: Subscription;
  private subscriptionComponent: Subscription;

  constructor(private assemblyService: AssemblyService,private testService: TestService, activateRoute: ActivatedRoute) {
    this.subscriptionAssemblyId = activateRoute.params.subscribe(params=>this.assembly.id=params['assemblyId']);
    this.subscriptionComponent = activateRoute.params.subscribe(params=>this.component=params['component']);
  }

  public getAssembly(): void {
    this.assemblyService.getAssembly(this.assembly.id).subscribe(
      (response: Assembly) => {
        this.assembly = response;
        if (this.assembly.overclock != null && this.assembly.overclock != 0) {
          this.getOverclock(this.assembly.overclock);
        }
        switch (this.component) {
          case "cpu":
            this.testCPU();
            break;
          case "gpu":
            this.testGPU();
            break;
          case "ram":
            this.testRAM();
            break;
        }
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

  public testCPU(): void {
    this.testService.testCPU(this.assembly.id).subscribe(
      (response: number) => {
        this.score = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public testGPU(): void {
    this.testService.testGPU(this.assembly.id).subscribe(
      (response: number) => {
        this.score = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public testRAM(): void {
    this.testService.testRAM(this.assembly.id).subscribe(
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
