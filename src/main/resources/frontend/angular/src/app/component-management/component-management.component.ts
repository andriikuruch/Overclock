import { Component, OnInit } from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {ComponentManagementService} from "../service/component-management.service";
import {Motherboard} from "../entities/motherboard";
import {AppearanceService} from "../service/appearance.service";
import {CPU} from "../entities/cpu";
import {GPU} from "../entities/gpu";
import {RAM} from "../entities/ram";

@Component({
  selector: 'app-component-management',
  templateUrl: './component-management.component.html',
  styleUrls: ['./component-management.component.css']
})
export class ComponentManagementComponent implements OnInit {

  public motherboard: Motherboard = {
    id: 1,
    name: '',
    socket: '',
    chipsetManufacturer: '',
    chipset: ''
  };

  public cpu: CPU = {
    id: 1,
    name: '',
    socket: '',
    manufacturer: '',
    generation: '',
    family: '',
    voltage: 0.5,
    frequency: 1,
    threadsNumber: 0,
    coresNumber: 1
  };

  public gpu: GPU = {
    id: 1,
    name: '',
    chipManufacturer: '',
    chip: '',
    memoryCapacity: 1,
    coreFrequency: 1000,
    memoryFrequency: 1000,
    voltage: 0.5
  };

  public ram: RAM = {
    id: 1,
    name: '',
    capacity: 1,
    frequency: 2133,
    voltage: 0.5,
    timings: '40-40-40-60'
  };

  public motherboardChipsetManufacturers: string[] = [];
  public motherboardSockets: string[] = [];
  public motherboardChipsets: string[] = [];

  public cpuManufacturers: string[] = [];
  public cpuSockets: string[] = [];
  public cpuGenerations: string[] = [];
  public cpuFamilies: string[] = [];

  public gpuChipManufacturers: string[] = [];
  public gpuChips: string[] = [];

  public currentForm: number;
  public patternName = /\S{4,100}/;
  public patternTimings = /(([1-3]\d-)|(40-)){3}(([2-5]\d)|(60))/;

  constructor(private componentManagementService: ComponentManagementService,
              private appearanceService: AppearanceService) {
    this.currentForm = 0;
  }

  public onSelectType(type: any): void {
    switch (type.target.value) {
      case 'Материнская плата':
        this.currentForm = 0;
        break;
      case 'Процессор':
        this.currentForm = 1;
        break;
      case 'Видеокарта':
        this.currentForm = 2;
        break;
      case 'Оперативная память':
        this.currentForm = 3;
        break;
    }
  }

  // Motherboard
  public addMotherboard(): void {
    this.componentManagementService.addMotherboard(this.motherboard).subscribe(
      (response: void) => {
        this.appearanceService.customAlert('Материнская плата ' + this.motherboard.name + ' добавлена!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getMotherboardChipsetManufacturers(): void {
    this.componentManagementService.getMotherboardChipsetManufacturers().subscribe(
      (response: string[]) => {
        this.motherboardChipsetManufacturers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getMotherboardSockets(): void {
    this.componentManagementService.getMotherboardSockets(this.motherboard.chipsetManufacturer!).subscribe(
      (response: string[]) => {
        this.motherboardSockets = response;
        this.motherboard.socket = response[0];
        this.getMotherboardChipsets();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getMotherboardChipsets(): void {
    this.componentManagementService.getMotherboardChipsets(this.motherboard.socket!).subscribe(
      (response: string[]) => {
        this.motherboardChipsets = response;
        this.motherboard.chipset = response[0];
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onChangeMotherboardChipsetManufacturer(chipsetManufacturer: string): void {
    this.getMotherboardSockets();
  }

  public onChangeMotherboardSocket(socket: string): void {
    this.getMotherboardChipsets();
  }

  public onSubmitMotherboard(): void {
    this.addMotherboard();
  }

  //CPU
  public addCPU(): void {
    this.componentManagementService.addCPU(this.cpu).subscribe(
      (response: void) => {
        this.appearanceService.customAlert('Процессор ' + this.cpu.name + ' добавлен!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getCPUManufacturers(): void {
    this.componentManagementService.getCPUManufacturers().subscribe(
      (response: string[]) => {
        this.cpuManufacturers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getCPUSockets(): void {
    this.componentManagementService.getCPUSockets(this.cpu.manufacturer!).subscribe(
      (response: string[]) => {
        this.cpuSockets = response;
        this.cpu.socket = response[0];
        this.getCPUGenerations();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getCPUGenerations(): void {
    this.componentManagementService.getCPUGenerations(this.cpu.socket!).subscribe(
      (response: string[]) => {
        this.cpuGenerations = response;
        this.cpu.generation = response[0];
        this.getCPUFamilies();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getCPUFamilies(): void {
    this.componentManagementService.getCPUFamilies(this.cpu.generation!).subscribe(
      (response: string[]) => {
        this.cpuFamilies = response;
        this.cpu.family = response[0];
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onChangeCPUManufacturer(manufacturer: string): void {
    this.getCPUSockets();
  }

  public onChangeCPUSocket(socket: string): void {
    this.getCPUGenerations();
  }

  public onChangeCPUGeneration(generation: string): void {
    this.getCPUFamilies();
  }

  public onSubmitCPU(): void {
    this.addCPU();
  }

  // GPU
  public addGPU(): void {
    this.componentManagementService.addGPU(this.gpu).subscribe(
      (response: void) => {
        this.appearanceService.customAlert('Видеокарта ' + this.gpu.name + ' добавлена!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getGPUChipManufacturers(): void {
    this.componentManagementService.getGPUChipManufacturers().subscribe(
      (response: string[]) => {
        this.gpuChipManufacturers = response;
        this.gpu.chipManufacturer = response[0];

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getGPUChips(): void {
    this.componentManagementService.getGPUChips(this.gpu.chipManufacturer!).subscribe(
      (response: string[]) => {
        this.gpuChips = response;
        this.gpu.chip = response[0];

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onChangeGPUChipManufacturer(manufacturer: string): void {
    this.getGPUChips();
  }

  public onSubmitGPU(): void {
    this.addGPU();
  }

  // RAM
  public addRAM(): void {
    this.componentManagementService.addRAM(this.ram).subscribe(
      (response: void) => {
        this.appearanceService.customAlert('Оперативная память ' + this.ram.name + ' добавлена!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onSubmitRAM(): void {
    this.addRAM();
  }

  ngOnInit(): void {
    // Motherboard
    this.componentManagementService.getMotherboardChipsetManufacturers().subscribe(
      (response: string[]) => {
        this.motherboardChipsetManufacturers = response;
        this.motherboard.chipsetManufacturer = response[0];
        this.componentManagementService.getMotherboardSockets(this.motherboard.chipsetManufacturer).subscribe(
          (response: string[]) => {
            this.motherboardSockets = response;
            this.motherboard.socket = response[0];
            this.componentManagementService.getMotherboardChipsets(this.motherboard.socket).subscribe(
              (response: string[]) => {
                this.motherboardChipsets = response;
                this.motherboard.chipset = response[0];
              },
              (error: HttpErrorResponse) => {
                alert(error.message);
              }
            );
          },
          (error: HttpErrorResponse) => {
            alert(error.message);
          }
        );
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

    // CPU
    this.componentManagementService.getCPUManufacturers().subscribe(
      (response: string[]) => {
        this.cpuManufacturers = response;
        this.cpu.manufacturer = response[0];
        this.componentManagementService.getCPUSockets(this.cpu.manufacturer).subscribe(
          (response: string[]) => {
            this.cpuSockets = response;
            this.cpu.socket = response[0];
            this.componentManagementService.getCPUGenerations(this.cpu.socket).subscribe(
              (response: string[]) => {
                this.cpuGenerations = response;
                this.cpu.generation = response[0];
                this.componentManagementService.getCPUFamilies(this.cpu.generation).subscribe(
                  (response: string[]) => {
                    this.cpuFamilies = response;
                    this.cpu.family = response[0];
                  },
                  (error: HttpErrorResponse) => {
                    alert(error.message);
                  }
                );
              },
              (error: HttpErrorResponse) => {
                alert(error.message);
              }
            );
          },
          (error: HttpErrorResponse) => {
            alert(error.message);
          }
        );
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

    // GPU
    this.componentManagementService.getGPUChipManufacturers().subscribe(
      (response: string[]) => {
        this.gpuChipManufacturers = response;
        this.gpu.chipManufacturer = response[0];
        this.componentManagementService.getGPUChips(this.gpu.chipManufacturer).subscribe(
          (response: string[]) => {
            this.gpuChips = response;
            this.gpu.chip = response[0];
          },
          (error: HttpErrorResponse) => {
            alert(error.message);
          }
        );
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
