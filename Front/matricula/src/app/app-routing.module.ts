import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MatriculaComponent } from './matricula/matricula/matricula.component';

const routes: Routes = [
  { path: '', redirectTo: '/matricula', pathMatch: 'full' },
  { path: 'matricula', component: MatriculaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
