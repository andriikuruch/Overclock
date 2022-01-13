import { Component, OnInit } from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {ComponentManagementService} from "../service/component-management.service";
import {Motherboard} from "../entities/motherboard";
import {AppearanceService} from "../service/appearance.service";
import {CPU} from "../entities/cpu";
import {GPU} from "../entities/gpu";
import {RAM} from "../entities/ram";
import {Router} from "@angular/router";
import { DataSharingService } from '../service/datasharing.service';

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
  public currentAction: number;
  public patternName = /\S{4,100}/;
  public patternSearch = /\S{3,100}/;
  public patternTimings = /(([1-3]\d-)|(40-)){3}(([2-5]\d)|(60))/;

  public showObject: number = 1;

  public findMotherboards: Motherboard[] = [];
  public findCPUs: CPU[] = [];
  public findGPUs: GPU[] = [];
  public findRAMs: RAM[] = [];

  public searchWord: string = '';

  isLoggedIn : boolean = false;
  isAdmin : boolean = false;

  constructor(private componentManagementService: ComponentManagementService, private router: Router,
              private appearanceService: AppearanceService, private dataSharingService: DataSharingService) {
    this.dataSharingService.isLoggedIn.subscribe( value => {
      this.isLoggedIn = value;
    });
    this.dataSharingService.isAdmin.subscribe(value => {
      this.isAdmin = value;
    })
    this.currentForm = 0;
    this.currentAction = 0;
  }

  public onSelectAction(type: any): void {
    switch (type.target.value) {
      case 'Добавление':
        this.ngOnInit();
        this.showObject = 1;
        this.currentAction = 0;
        break;
      case 'Редактирование/Удаление':
        this.showObject = 0;
        this.currentAction = 1;
        break;
    }
  }

  public onSelectType(type: any): void {
    if (this.currentAction == 1) {
      this.showObject = 0;
    }
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

  public getMotherboardsByName(name: string): void {
    this.searchWord = name;
    this.componentManagementService.getMotherboardsByName(name).subscribe(
      (response: Motherboard[]) => {
        this.findMotherboards = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onEditingMotherboard(id: number): void {
    this.showObject = 1;
    this.componentManagementService.getMotherboardById(id).subscribe(
      (response: Motherboard) => {
        this.motherboard = response;

        this.motherboardChipsetManufacturers = [this.motherboard.chipsetManufacturer!];
        this.motherboardSockets = [this.motherboard.socket!];
        this.motherboardChipsets = [this.motherboard.chipset!];

        this.componentManagementService.getMotherboardChipsetManufacturers().subscribe(
          (response: string[]) => {
            this.motherboardChipsetManufacturers = response;
            this.componentManagementService.getMotherboardSockets(this.motherboard.chipsetManufacturer!).subscribe(
              (response: string[]) => {
                this.motherboardSockets = response;
                this.componentManagementService.getMotherboardChipsets(this.motherboard.socket!).subscribe(
                  (response: string[]) => {
                    this.motherboardChipsets = response;
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
  }

  public onRemoveMotherboard(id: number): void {
    this.componentManagementService.deleteMotherboard(id).subscribe(
      (response: void) => {
        this.getMotherboardsByName(this.searchWord);
        this.showObject = 0;
        this.appearanceService.customAlert('Успешно удалено!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onSaveChangingMotherboard(id: number): void {
    this.componentManagementService.updateMotherboard(this.motherboard).subscribe(
      (response: void) => {
        this.appearanceService.customAlert('Изменения сохранены!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
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

  public getCPUsByName(name: string): void {
    this.searchWord = name;
    this.componentManagementService.getCPUsByName(name).subscribe(
      (response: CPU[]) => {
        this.findCPUs = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onEditingCPU(id: number): void {
    this.showObject = 1;
    this.componentManagementService.getCPUById(id).subscribe(
      (response: CPU) => {
        this.cpu = response;

        this.cpuManufacturers = [this.cpu.manufacturer!];
        this.cpuSockets = [this.cpu.socket!];
        this.cpuGenerations = [this.cpu.generation!];
        this.cpuFamilies = [this.cpu.family!];

        this.componentManagementService.getCPUManufacturers().subscribe(
          (response: string[]) => {
            this.cpuManufacturers = response;
            this.componentManagementService.getCPUSockets(this.cpu.manufacturer!).subscribe(
              (response: string[]) => {
                this.cpuSockets = response;
                this.componentManagementService.getCPUGenerations(this.cpu.socket!).subscribe(
                  (response: string[]) => {
                    this.cpuGenerations = response;
                    this.componentManagementService.getCPUFamilies(this.cpu.generation!).subscribe(
                      (response: string[]) => {
                        this.cpuFamilies = response;
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

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onRemoveCPU(id: number): void {
    this.componentManagementService.deleteCPU(id).subscribe(
      (response: void) => {
        this.getCPUsByName(this.searchWord);
        this.showObject = 0;
        this.appearanceService.customAlert('Успешно удалено!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onSaveChangingCPU(id: number): void {
    this.componentManagementService.updateCPU(this.cpu).subscribe(
      (response: void) => {
        this.appearanceService.customAlert('Изменения сохранены!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
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

  public getGPUsByName(name: string): void {
    this.searchWord = name;
    this.componentManagementService.getGPUsByName(name).subscribe(
      (response: GPU[]) => {
        this.findGPUs = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onEditingGPU(id: number): void {
    this.showObject = 1;
    this.componentManagementService.getGPUById(id).subscribe(
      (response: GPU) => {
        this.gpu = response;

        this.gpuChipManufacturers = [this.gpu.chipManufacturer!];
        this.gpuChips = [this.gpu.chip!];

        this.componentManagementService.getGPUChipManufacturers().subscribe(
          (response: string[]) => {
            this.gpuChipManufacturers = response;
            this.componentManagementService.getGPUChips(this.gpu.chipManufacturer!).subscribe(
              (response: string[]) => {
                this.gpuChips = response;
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
  }

  public onRemoveGPU(id: number): void {
    this.componentManagementService.deleteGPU(id).subscribe(
      (response: void) => {
        this.getGPUsByName(this.searchWord);
        this.showObject = 0;
        this.appearanceService.customAlert('Успешно удалено!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onSaveChangingGPU(id: number): void {
    this.componentManagementService.updateGPU(this.gpu).subscribe(
      (response: void) => {
        this.appearanceService.customAlert('Изменения сохранены!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
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

  public getRAMsByName(name: string): void {
    this.searchWord = name;
    this.componentManagementService.getRAMsByName(name).subscribe(
      (response: RAM[]) => {
        this.findRAMs = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onEditingRAM(id: number): void {
    this.showObject = 1;
    this.componentManagementService.getRAMById(id).subscribe(
      (response: RAM) => {
        this.ram = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onRemoveRAM(id: number): void {
    this.componentManagementService.deleteRAM(id).subscribe(
      (response: void) => {
        this.getRAMsByName(this.searchWord);
        this.showObject = 0;
        this.appearanceService.customAlert('Успешно удалено!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onSaveChangingRAM(id: number): void {
    this.componentManagementService.updateRAM(this.ram).subscribe(
      (response: void) => {
        this.appearanceService.customAlert('Изменения сохранены!');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  // Removing/editing

  public onSearchComponent(name: string): void {
    switch (this.currentForm) {
      case 0:
        this.getMotherboardsByName(name);
        this.showObject = 2;
        break;
      case 1:
        this.getCPUsByName(name);
        this.showObject = 3;
        break;
      case 2:
        this.getGPUsByName(name);
        this.showObject = 4;
        break;
      case 3:
        this.getRAMsByName(name);
        this.showObject = 5;
        break;
    }
  }

  ngOnInit(): void {
    if (this.isLoggedIn === false)
      this.openAuthorization();
    else if (this.isAdmin === false)
      this.openHomePage();
    else {
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
    this.motherboard.name = '';

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
    this.cpu.name = '';
    this.cpu.voltage = 0.5;
    this.cpu.frequency = 1;
    this.cpu.threadsNumber = 0;
    this.cpu.coresNumber = 1;

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
    this.gpu.name = '';
    this.gpu.memoryCapacity = 1;
    this.gpu.coreFrequency = 1000;
    this.gpu.memoryFrequency = 1000;
    this.gpu.voltage = 0.5;

    // RAM
    this.ram.name = '';
    this.ram.capacity = 1;
    this.ram.frequency = 2133;
    this.ram.voltage = 0.5;
    this.ram.timings = '40-40-40-60';
    }
  }

  openAuthorization(): void {
    this.router.navigate(['/authorization']);
  }

  openHomePage(): void{
    this.router.navigate(['/home']);
  }

}
