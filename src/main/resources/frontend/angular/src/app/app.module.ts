import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
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
import { RegistrationComponent } from "./registration/registration.component";
import { NotFoundComponent } from "./not-found/not-found.component";
import { FooterComponent } from './footer/footer.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MyAssembliesComponent} from "./my-assemblies/my-assemblies.component";
import { authInterceptorProviders } from './helpers/auth.interceptor';
import { DataSharingService } from './service/datasharing.service';
import { AssemblyTestComponent } from './assembly-test/assembly-test.component';
import { MainTestResultComponent } from './main-test-result/main-test-result.component';
import { TestOnProgramResultComponent } from './test-on-program-result/test-on-program-result.component';
import { PartialTestComponentResult } from './partial-test-result/partial-test.component-result';
import {OverclockingComponent} from "./overclocking/overclocking.component";
import {ActivatingAccountComponent} from "./activating-account/activating-account.component";
import { ComponentManagementComponent } from './component-management/component-management.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    AssemblyComponent,
    MyAssembliesComponent,
    AssembliesComponent,
    OverclockingComponent,
    RatingTableComponent,
    CreatingAssemblyComponent,
    HomePageComponent,
    AuthorizationComponent,
    RegistrationComponent,
    NotFoundComponent,
    FooterComponent,
    SearchFilterPipe,
    PartsFilterPipe,
    AssemblyTestComponent,
    MainTestResultComponent,
    TestOnProgramResultComponent,
    PartialTestComponentResult,
    ActivatingAccountComponent,
    ComponentManagementComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  providers: [authInterceptorProviders, DataSharingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
