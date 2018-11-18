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
    //Carrega Todas disciplinas do Curso
    this.matriculaservice.getDisciplinas().subscribe( data => {
      this.disciplinas = data['records'];
      this.disciplinasOferecidas = this.disciplinas;
    });
  }
  

  //Faz a busca do Aluno atraves da matricula
  getAlunoHistorico() {
    let matricula = this.matricula;
    this.matriculaservice.getHistorico(matricula).subscribe( data => {
      if(data[`records`].length > 0){
        this.exibirErro = false;
        this.historicoAluno = data[`records`];
        this.listarDisciplinasOferecidas(this.historicoAluno);
      } else {
        this.exibirErro = true;
        this.historicoAluno = [];
        this.disciplinasOferecidas = [];
      }
    });
  }

  //Lista as Disciplinas Oferecidas para o Aluno Buscado
  listarDisciplinasOferecidas(disciplinasHistorico) {
    let todasDisciplinas = this.disciplinas;
    for(let j = 0; j < disciplinasHistorico.length; j++) {
      for( var i = 0; i < todasDisciplinas.length; i++){ 
        if ( todasDisciplinas[i]['fields'].CODCRED == disciplinasHistorico[j]['fields'].CODCRED) {
          todasDisciplinas.splice(i, 1); 
        }
      }
    }
    this.disciplinasOferecidas = todasDisciplinas;
  }

}
