import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { AssemblyComponent } from './assembly/assembly.component';
import { RatingTableComponent } from './rating-table/rating-table.component';
//import { CreatingAssemblyComponent } from './creating-assembly/creating-assembly.component';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    AssemblyComponent,
    RatingTableComponent,
    //CreatingAssemblyComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
