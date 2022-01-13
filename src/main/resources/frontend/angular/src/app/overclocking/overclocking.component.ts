import { Component, OnInit } from '@angular/core';
import {OverclockingService} from "../service/overclocking.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Overclock} from "../entities/overclock";
import {Subscription} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";
import {AssemblyService} from "../service/assembly.service";
import {Assembly} from "../entities/assembly";
import {Motherboard} from "../entities/motherboard";
import {CPU} from "../entities/cpu";
import {GPU} from "../entities/gpu";
import {RAM} from "../entities/ram";
import {Comment} from "../entities/comment";
import {AppearanceService} from "../service/appearance.service";
import {DataSharingService} from '../service/datasharing.service';

@Component({
  selector: 'app-overclocking',
  templateUrl: './overclocking.component.html',
  styleUrls: ['./overclocking.component.css']
})
export class OverclockingComponent implements OnInit {

  public overclocking: Overclock = {
    id: 0,
    cpufrequency: '',
    cpuvoltage: '',
    gpucoreFrequency: '',
    gpumemoryFrequency: '',
    gpuvoltage: '',
    ramvoltage: '',
    ramfrequency: '',
    ramtimings: ''
  };
  public assembly: Assembly = {
    id: 0,
    name: '',
    motherboard: {},
    cpu: {},
    gpu: {},
    ram: {},
    comments: [],
    author: 0,
    overclock: 0,
    score: 0
  };
  private assemblyId: number = 0;
  private subscription: Subscription;

  public patternTimings = /(([1-3]\d-)|(40-)){3}(([2-5]\d)|(60))/;

  isLoggedIn : boolean = false;
  isAdmin : boolean = false;

  constructor(
    private overclockingService: OverclockingService,
    private assemblyService: AssemblyService,
    private appearanceService: AppearanceService,
    private router: Router, activateRoute: ActivatedRoute,
    private dataSharingService: DataSharingService) {

    this.dataSharingService.isLoggedIn.subscribe( value => {
      this.isLoggedIn = value;
    });
    this.dataSharingService.isAdmin.subscribe(value => {
      this.isAdmin = value;
    })

    this.subscription = activateRoute.params.subscribe(params => this.assemblyId = params['id']);
  }

  public saveOverclock(overclocking: Overclock): void {
    this.overclockingService.saveOverclock(overclocking).subscribe(
      (response: void) => {
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getOverclock(): void {
    this.overclockingService.getOverclock(this.assembly.overclock).subscribe(
      (response: Overclock) => {
        this.overclocking = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getDefaultValues(): void {
    this.overclockingService.getDefaultValues().subscribe(
      (response: Overclock) => {
        const overclockingId = this.overclocking.id;
        this.overclocking = response;
        this.overclocking.id = overclockingId;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public updateOverclock(newOverclock: Overclock): void {
    this.overclockingService.updateOverclock(newOverclock).subscribe(
      (response: void) => {
        this.overclocking = newOverclock;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onSubmit(): void {
    if (this.assembly.overclock == null) {
      this.saveOverclock(this.overclocking);
    } else {
      this.updateOverclock(this.overclocking);
    }
    this.appearanceService.customAlert('Разгон для сборки ' + this.assembly.name + ' успешно сохранен!');
  }

  public onReset(): void {
    this.getDefaultValues();
  }

  public onTest(): void {
    if (this.assembly.overclock == null) {
      this.saveOverclock(this.overclocking);
    } else {
      this.updateOverclock(this.overclocking);
    }
    this.router.navigate([`my_assemblies/${this.assemblyId}/testing`]);
  }

  openAuthorization(): void {
    this.router.navigate(['/authorization']);
  }

  openHomePage(): void {
    this.router.navigate(['/home']);
  }

  ngOnInit(): void {
    if (this.isLoggedIn === false)
      this.openAuthorization();
    else if (this.isAdmin === true)
      this.openHomePage();
    else {
      this.overclockingService.setAssemblyId(this.assemblyId);

      this.assemblyService.getAssembly(this.assemblyId).subscribe(
        (response: Assembly) => {
          this.assembly = response;
          if (this.assembly.overclock == null) {
            this.getDefaultValues();
          } else {
            this.getOverclock();
          }
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }
}
