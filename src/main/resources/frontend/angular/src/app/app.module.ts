import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { HttpClientModule} from "@angular/common/http";
import { AssemblyComponent } from './assembly/assembly.component';
import { RatingTableComponent } from './rating-table/rating-table.component';
import { CreatingAssemblyComponent } from './creating-assembly/creating-assembly.component';
import { HomePageComponent } from './home-page/home-page.component';
//import { AuthorizationComponent } from "./authorization/authorization.component";
//import { RegistrationComponent } from "./registration/registration.component";
import { NotFoundComponent } from "./not-found/not-found.component";
import { FooterComponent } from './footer/footer.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MyAssembliesComponent} from "./my-assemblies/my-assemblies.component";

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    AssemblyComponent,
    MyAssembliesComponent,
    RatingTableComponent,
    CreatingAssemblyComponent,
    HomePageComponent,
    //AuthorizationComponent,
    //RegistrationComponent,
    NotFoundComponent,
    FooterComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        ReactiveFormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
