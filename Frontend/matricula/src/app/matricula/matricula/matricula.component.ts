import { MatriculaSerivce } from './matricula.service';
import { Component, OnInit } from '@angular/core';

declare let $ :any;

@Component({
  selector: 'app-matricula',
  templateUrl: './matricula.component.html',
  styleUrls: ['./matricula.component.scss']
})
export class MatriculaComponent implements OnInit {

  private disciplinas: any[];

  constructor(private matriculaservice: MatriculaSerivce) { 
    //console.log($);
  }

  ngOnInit() {
    this.matriculaservice.getDatda().subscribe( data => {
      this.disciplinas = data['records'];
      console.log(this.disciplinas);
    });

    //this.matriculaservice.matricularAlunoDisciplina();
   }

}
