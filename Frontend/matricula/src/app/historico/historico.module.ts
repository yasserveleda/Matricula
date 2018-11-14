import { HistoricoComponent } from './historico/historico.component';
import { HistoricoService } from './historico/historico.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule
  ],
  providers: [HistoricoService],
  declarations: [HistoricoComponent]
})
export class HistoricoModule { }