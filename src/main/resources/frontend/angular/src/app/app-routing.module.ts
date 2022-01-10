import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AssemblyComponent} from "./assembly/assembly.component";
import {AssembliesComponent} from "./assemblies/assemblies.component";
import {RatingTableComponent} from "./rating-table/rating-table.component";
import {CreatingAssemblyComponent} from "./creating-assembly/creating-assembly.component";
import {HomePageComponent} from "./home-page/home-page.component";
import {RegistrationComponent} from "./registration/registration.component";
import {AuthorizationComponent} from "./authorization/authorization.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {MyAssembliesComponent} from "./my-assemblies/my-assemblies.component";
import {AssemblyTestComponent} from "./assembly-test/assembly-test.component";
import {MainTestResultComponent} from "./main-test-result/main-test-result.component";
import {TestOnProgramResultComponent} from "./test-on-program-result/test-on-program-result.component";
import {PartialTestComponentResult} from "./partial-test-result/partial-test.component-result";
import {OverclockingComponent} from "./overclocking/overclocking.component";
import {ActivatingAccountComponent} from "./activating-account/activating-account.component";
import {ComponentManagementComponent} from "./component-management/component-management.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component:HomePageComponent},
  {path: 'assembly/:id', component: AssemblyComponent },
  {path: 'my_assemblies', component: MyAssembliesComponent},
  {path: 'my_assemblies/creating', component: CreatingAssemblyComponent},
  {path: 'my_assemblies/:id/overclocking', component: OverclockingComponent },
  {path: 'my_assemblies/:assemblyId/testing', component: AssemblyTestComponent},
  {path: 'my_assemblies/:assemblyId/main_test_result', component: MainTestResultComponent},
  {path: 'my_assemblies/:assemblyId/test_on_program/:program', component: TestOnProgramResultComponent},
  {path: 'my_assemblies/:assemblyId/partial_test/:component', component: PartialTestComponentResult},
  {path: 'components', component: ComponentManagementComponent},
  {path: 'rating', component: RatingTableComponent },
  {path: 'assemblies', component: AssembliesComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'api/registration/activate-account', component:ActivatingAccountComponent, pathMatch: 'prefix'},
  {path: 'authorization', component: AuthorizationComponent},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
