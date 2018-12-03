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
  turmas: any[];
  numeroVagas: number;

  constructor(private matriculaservice: MatriculaSerivce) { 
    this.aluno = {};
    this.exibirErro = false;
  }

  ngOnInit() {
    this.getTurmas();
    this.getDisciplinas();
  }

  //Busca todas as disciplinas
  getDisciplinas() {
    this.matriculaservice.getDisciplinas().subscribe( data => {
      this.disciplinas = data['records'];
    });
  }

  
  //Atualiza o aluno 
  atualizaAluno() {
    if(this.aluno.id) {
      this.matriculaservice.atualizaAluno(this.aluno).subscribe( data => {
        if(data[`id`]) {
          this.aluno = data;
          this.disciplinasMatriculadasId = this.aluno.fields.DISCIPLINAS_MATRICULADO;
          this.listaDisciplinasMatriculadas();
          this.getHistorico();
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

  //Busca o historico do aluno
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

  //Busca todas as turmas
  getTurmas() {
    this.matriculaservice.getTurmas().subscribe( data => { 
      if(data[`records`].length > 0){ 
        this.turmas = data[`records`];
      }
    });
  }

  //Lista as Disciplinas Oferecidas para o aluno
  listarDisciplinasOferecidas(disciplinasHistorico) {
    let todasDisciplinas = [... this.disciplinas];

    for(let j = 0; j < disciplinasHistorico.length; j++) {
      for( var i = 0; i < todasDisciplinas.length; i++){ 
        if ( todasDisciplinas[i]['fields'].CODCRED == disciplinasHistorico[j]['fields'].CODCRED) {
          todasDisciplinas.splice(i, 1); 
        }
      }
    }
    
    this.disciplinasOferecidas = todasDisciplinas;
  }

  //Lista as disciplinas que o aluno esta matriculado
  listaDisciplinasMatriculadas() {
    let todasDisciplinas = [... this.disciplinas];
    let listaGuardaMatriculadas = [];
    if (this.disciplinasMatriculadasId) {
      for(let j = 0; j < this.disciplinasMatriculadasId.length; j++) {
        for( var i = 0; i < todasDisciplinas.length; i++){ 
          if ( todasDisciplinas[i]['id'] == this.disciplinasMatriculadasId[j]) {
            listaGuardaMatriculadas.push(todasDisciplinas[i]);
          }
        }
      }
    }
    this.disciplinasMatriculadas = listaGuardaMatriculadas;
  }
  
  //Matricula o aluno na disciplina
  matriculaDisciplinaAluno(dis) {
    this.matriculaservice.getDisciplinas().subscribe( data => {
      this.disciplinas = data['records'];
      let disciplina = this.disciplinas.find(x => x.id == dis.id);
      //Confere se a disciplina tem pre requisito
      if(disciplina.fields.PRE_REQUISITOS) {
        let idDisciplina = disciplina.fields.PRE_REQUISITOS[0];
        //Pre requisito aceito
        if(this.containsInArray(this.historicoAluno, idDisciplina)) {
          this.continuaMatricula(disciplina);
        } else {
          alert(`Pre requisito nao aceito`);
        }
      } else {
        this.continuaMatricula(disciplina);
      }
    });

    
  }

  //Matricula o aluno na turma
  matriculaTurmaAluno(turma) {
    if(this.aluno.fields.TURMAS) {
      this.aluno.fields.TURMAS.push(turma.id);
      this.atualizaAluno();
    } else {
      this.aluno.fields.TURMAS = [];
      this.aluno.fields.TURMAS.push(turma.id);
      this.atualizaAluno();
    }
  }

  //conclui a matricula
  continuaMatricula(disciplina) {
    this.numeroVagas = disciplina.fields.NUMERO_VAGAS;
    if(this.numeroVagas > 0){
      disciplina.fields.NUMERO_VAGAS = `${disciplina.fields.NUMERO_VAGAS - 1}`;
      this.atualizaDisciplina(disciplina);
      if(this.aluno.fields.DISCIPLINAS_MATRICULADO) {
        this.aluno.fields.DISCIPLINAS_MATRICULADO.push(disciplina.id);
        this.atualizaAluno();
      } else {
        this.aluno.fields.DISCIPLINAS_MATRICULADO = [];
        this.aluno.fields.DISCIPLINAS_MATRICULADO.push(disciplina.id);
        this.atualizaAluno();
      }
    } else {
      alert(`NAO TEM VAGA`);
    }    
  }

  atualizaDisciplina(disciplina) {
    if(disciplina.id) {
      this.matriculaservice.atualizaDisciplina(disciplina).subscribe( data => {
        if(data) {
          //console.log(data);
        }
      });
    }
  }

  //Remove disciplina matriculada do aluno
  desmatriculaDisciplinaAluno(disciplina) {
    if(this.aluno.fields.DISCIPLINAS_MATRICULADO) { 
      let index = this.aluno.fields.DISCIPLINAS_MATRICULADO.indexOf(disciplina.id);
      if (index !== -1) {
        this.aluno.fields.DISCIPLINAS_MATRICULADO.splice(index, 1);
        let numero = Number.parseInt(disciplina.fields.NUMERO_VAGAS);
        disciplina.fields.NUMERO_VAGAS = `${(numero+1)}`
        //Atualiza Numero Vagas
        this.atualizaDisciplina(disciplina);
        //Atualiza
        this.atualizaAluno();
        this.getHistorico();
      } 
    }         
  }

  containsInArray(lista, itemID) {
    let disciplinaCodCred = this.disciplinas.find(x => x.id == itemID);
    return lista.find(x => x.fields.CODCRED == disciplinaCodCred.fields.CODCRED);
  }

}
