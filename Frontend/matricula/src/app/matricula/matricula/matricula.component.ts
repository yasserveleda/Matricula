import { MatriculaSerivce } from './matricula.service';
import { Component, OnInit } from '@angular/core';

declare let $ :any;

@Component({
  selector: 'app-matricula',
  templateUrl: './matricula.component.html',
  styleUrls: ['./matricula.component.scss']
})
export class MatriculaComponent implements OnInit {

  disciplinas: any[];
  historicoAluno: any[];
  disciplinasOferecidas: any[];
  disciplinasMatriculadas: any[];
  disciplinasMatriculadasId: any[];
  exibirErro: boolean;
  aluno;
  matricula: string;

  constructor(private matriculaservice: MatriculaSerivce) { 
    this.aluno = {};
    this.exibirErro = false;
  }

  ngOnInit() {
    //Carrega Todas disciplinas do Curso
    this.matriculaservice.getDisciplinas().subscribe( data => {
      this.disciplinas = data['records'];
    });
  }
  
  matriculaAluno() {
    if(this.aluno.id) {
      this.matriculaservice.matriculaAluno(this.aluno).subscribe( data => {
        
        if(data[`id`]) {
          this.aluno = data;
          this.disciplinasMatriculadasId = this.aluno.fields.DISCIPLINAS_MATRICULADO;
          this.listaDisciplinasMatriculadas();
        }

      });
    }
  }

  //Faz a busca do Aluno atraves da matricula
  getAluno() {
    let matricula = this.matricula;

    this.matriculaservice.getAluno(matricula).subscribe( data => {
      if(data[`records`].length > 0){
        this.aluno = data[`records`][0];
        this.disciplinasMatriculadasId = this.aluno.fields.DISCIPLINAS_MATRICULADO;
        this.listaDisciplinasMatriculadas();

        this.getHistorico();
      }
    });
  }

  getHistorico() {
    let matricula = this.matricula;

    this.matriculaservice.getHistorico(matricula).subscribe( data => {
      if(data[`records`].length > 0){
        this.exibirErro = false;
        this.historicoAluno = data[`records`];
        //copia das disciplinas que o aluno está matriculado
        let auxDisciplinasMatriculadas = this.disciplinasMatriculadas;
        //disciplinas que serao removidas das oferecidas
        let disciplinasRemoviveis = [];
        disciplinasRemoviveis = auxDisciplinasMatriculadas.concat(this.historicoAluno);
        this.listarDisciplinasOferecidas(disciplinasRemoviveis);
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

  listaDisciplinasMatriculadas() {
    let todasDisciplinas = this.disciplinas;
    let listaGuardaMatriculadas = [];

    for(let j = 0; j < this.disciplinasMatriculadasId.length; j++) {
      for( var i = 0; i < todasDisciplinas.length; i++){ 
        if ( todasDisciplinas[i]['id'] == this.disciplinasMatriculadasId[j]) {
          listaGuardaMatriculadas.push(todasDisciplinas[i]);
        }
      }
    }

    this.disciplinasMatriculadas = listaGuardaMatriculadas;
  }
  
  matriculaDisciplinaAluno(disciplina) {
    if(this.aluno.fields.DISCIPLINAS_MATRICULADO) {
      this.aluno.fields.DISCIPLINAS_MATRICULADO.push(disciplina.id);
      this.matriculaAluno();
    } else {
      this.aluno.fields.DISCIPLINAS_MATRICULADO = [];
      this.aluno.fields.DISCIPLINAS_MATRICULADO.push(disciplina.id);
      this.matriculaAluno();
    }
  }

}
