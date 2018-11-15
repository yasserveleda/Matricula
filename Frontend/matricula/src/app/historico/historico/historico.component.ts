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

  constructor(private historicoservice: HistoricoService) { 
    this.disciplinas = [];
    this.aluno = '';
  }

  ngOnInit() {
    this.historicoservice.getDisciplinas().subscribe( data => {
      this.disciplinas = data['records'];
    });

    this.historicoservice.getData().subscribe( data => {
      this.historicos = data['records'];
      this.historicos.forEach(element => {
        if(element.fields){
          element.fields.nome_disciplina = this.getDisciplina(element.fields.CODCRED);
        }
      });
    });

    this.historicoservice.getAluno().subscribe( data => {
      this.aluno = data['records'][0];
      this.aluno = this.aluno.fields;
    });
    
  }

  getDisciplina(COD_CRED) {
    let lista = this.disciplinas;

    if(lista && COD_CRED) {
      let found = lista.find( element => element.fields.COD_CRED == COD_CRED);
      if(found){
        return found.fields.NOME;
      } else {
        return 'Disciplina Não encontrada';
      }
      
    } else {
      return 'Disciplina Não encontrada';
    }
  }
}
