import { HistoricoService } from './historico.service';
import { Component, OnInit } from '@angular/core';

declare let $ :any;

@Component({
  selector: 'app-historico',
  templateUrl: './historico.component.html',
  styleUrls: ['./historico.component.scss']
})
export class HistoricoComponent implements OnInit {

  private historicos: any[];
  private disciplinas;
  private aluno;
  private matricula: string;
  private exibirErro: boolean;

  constructor(private historicoservice: HistoricoService) { 
    this.disciplinas = [];
    this.aluno = '';
    this.exibirErro = false;
  }

  ngOnInit() {
    this.historicoservice.getDisciplinas().subscribe( data => {
      this.disciplinas = data['records'];
    });
  }

  getDisciplina(CODCRED) {
    let lista = this.disciplinas;
    if(lista && CODCRED) {
      let found = lista.find( element => element.fields.CODCRED == CODCRED);
      if(found){
        return found.fields.DISCIPLINA;
      } else {
        return `Disciplina Não encontrada`;
      }
    } else {
      return `Disciplina Não encontrada`;
    }
  }

  getHistorico() {
    let matricula = this.matricula

    this.historicoservice.getHistorico(matricula).subscribe( data => {
      if(data[`records`].length > 0){
        this.exibirErro = false;
        this.historicos = data[`records`];
        this.historicos.forEach(element => {
          if(element.fields){
            element.fields.nome_disciplina = this.getDisciplina(element.fields.CODCRED);
          }
        });
      } else {
        this.exibirErro = true;
        this.historicos = [];
      }
    });

    this.historicoservice.getAluno(matricula).subscribe( data => {
      if(data[`records`].length > 0){
        this.aluno = data[`records`][0];
        this.aluno = this.aluno.fields;
      }
    });
  }
}
