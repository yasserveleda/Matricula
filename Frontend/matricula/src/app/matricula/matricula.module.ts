import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatriculaComponent } from './matricula/matricula.component';
import { HttpClientModule } from '@angular/common/http';
import { MatriculaSerivce } from './matricula/matricula.service';

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule    
  ],
  providers: [MatriculaSerivce],
  declarations: [MatriculaComponent]
})
export class MatriculaModule { }
