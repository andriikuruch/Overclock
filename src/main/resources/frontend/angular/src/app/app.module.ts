import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { HttpClientModule} from "@angular/common/http";
import { AssemblyComponent } from './assembly/assembly.component';
import { AssembliesComponent} from './assemblies/assemblies.component';
import { RatingTableComponent } from './rating-table/rating-table.component';
import { CreatingAssemblyComponent } from './creating-assembly/creating-assembly.component';
import { SearchFilterPipe } from './pipes/searchfilter.pipe';
import { PartsFilterPipe } from './pipes/partsfilter.pipe';
import { HomePageComponent } from './home-page/home-page.component';
import { AuthorizationComponent } from "./authorization/authorization.component";
//import { RegistrationComponent } from "./registration/registration.component";
import { NotFoundComponent } from "./not-found/not-found.component";
import { FooterComponent } from './footer/footer.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MyAssembliesComponent} from "./my-assemblies/my-assemblies.component";
import { authInterceptorProviders } from './helpers/auth.interceptor';
import { DataSharingService } from './service/datasharing.service';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    AssemblyComponent,
    MyAssembliesComponent,
    AssembliesComponent,
    RatingTableComponent,
    CreatingAssemblyComponent,
    HomePageComponent,
    AuthorizationComponent,
    //RegistrationComponent,
    NotFoundComponent,
    FooterComponent,
    SearchFilterPipe,
    PartsFilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [authInterceptorProviders, DataSharingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
