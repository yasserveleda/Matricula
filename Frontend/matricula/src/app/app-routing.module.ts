import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MatriculaComponent } from './matricula/matricula/matricula.component';
import { HistoricoComponent } from './historico/historico/historico.component';

const routes: Routes = [
  { path: '', redirectTo: '/matricula', pathMatch: 'full' },
  { path: 'matricula', component: MatriculaComponent },
  { path: 'historico', component: HistoricoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
