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

  constructor(private historicoservice: HistoricoService) { }

  ngOnInit() {
    this.historicoservice.getData().subscribe( data => {
      this.historicos = data['records'];
      console.log(this.historicos);
    });

  }
}
