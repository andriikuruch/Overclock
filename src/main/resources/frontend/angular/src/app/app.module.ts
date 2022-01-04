import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { HttpClient, HttpClientModule} from "@angular/common/http";
import { AssemblyComponent } from './assembly/assembly.component';
import { AssembliesComponent} from './assemblies/assemblies.component';
import { RatingTableComponent } from './rating-table/rating-table.component';
import { CreatingAssemblyComponent } from './creating-assembly/creating-assembly.component';
import { SearchFilterPipe } from './pipes/searchfilter.pipe';
import { PartsFilterPipe } from './pipes/partsfilter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    AssemblyComponent,
    AssembliesComponent,
    RatingTableComponent,
    CreatingAssemblyComponent,
    SearchFilterPipe,
    PartsFilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
