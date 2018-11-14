import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatriculaModule } from './matricula/matricula.module';
import { HistoricoModule } from './historico/historico.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatriculaModule,
    HistoricoModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
