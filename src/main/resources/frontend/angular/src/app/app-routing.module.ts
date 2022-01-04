import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AssemblyComponent} from "./assembly/assembly.component";
import {RatingTableComponent} from "./rating-table/rating-table.component";
import {CreatingAssemblyComponent} from "./creating-assembly/creating-assembly.component";
import {HomePageComponent} from "./home-page/home-page.component";
//import {RegistrationComponent} from "./registration/registration.component";
//import {AuthorizationComponent} from "./authorization/authorization.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {MyAssembliesComponent} from "./my-assemblies/my-assemblies.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component:HomePageComponent},
  {path: 'assembly/:id', component: AssemblyComponent },
  {path: 'my_assemblies', component: MyAssembliesComponent},
  {path: 'my_assemblies/creating', component: CreatingAssemblyComponent},
  //{path: 'my_assemblies/overclocking', component: OverclockComponent },
  //{path: ' my_assemblies/testing', component: TestComponent },
  {path: 'rating', component: RatingTableComponent },
  //{path: 'assemblies', component: AssembliesComponent},
  //{path: 'registration', component: RegistrationComponent},
  //{path: 'authorization', component: AuthorizationComponent},
  {path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
