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
  private historicoAluno: any[];
  disciplinasOferecidas: any[];

  private exibirErro: boolean;
  private aluno;
  private matricula: string;

  constructor(private matriculaservice: MatriculaSerivce) { 
    this.aluno = {};
    this.aluno.ALUNO = 'YASSER';
    this.exibirErro = false;
  }

  ngOnInit() {
    this.matriculaservice.getDisciplinas().subscribe( data => {
      this.disciplinas = data['records'];
      this.disciplinasOferecidas = this.disciplinas;
      //console.log(this.disciplinas);
    });
  }
  

  getAluno() {
    let matricula = this.matricula;
    // this.matriculaservice.getAluno(matricula).subscribe( data => {
    //   if(data[`records`].length > 0){
    //     this.exibirErro = false;
    //     this.aluno = data[`records`][0];
    //     this.aluno = this.aluno.fields;
    //     console.log(this.aluno);
    //   } else {
    //     this.exibirErro = true;
    //   }
    // });

    this.matriculaservice.getHistorico(matricula).subscribe( data => {
      if(data[`records`].length > 0){
        this.exibirErro = false;
        this.historicoAluno = data[`records`];
      } else {
        this.exibirErro = true;
        this.historicoAluno = [];
        this.disciplinasOferecidas = [];
      }
    });
  }

}
