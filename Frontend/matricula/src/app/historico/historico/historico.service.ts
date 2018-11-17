import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';

interface myData {
  obj: Object
}

@Injectable({
  providedIn: 'root'
})
export class HistoricoService {

  private httpOptions;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
          'Authorization': 'Bearer key7EJ7UZ3gFw0FLc',
          'Content-Type': 'application/json'
      })
    };
 }

  getData() {
    let url = "https://api.airtable.com/v0/appM4HMJqyZHY4gBR/HISTORICO?filterByFormula=({MATRICULA}='101101')";
    return this.http.get<myData>(url, this.httpOptions);
  }

  getDisciplinas() {
    let url = "https://api.airtable.com/v0/appM4HMJqyZHY4gBR/DISCIPLINA2";
    return this.http.get<myData>(url, this.httpOptions);
  }

  getAluno() {
    let url = "https://api.airtable.com/v0/appM4HMJqyZHY4gBR/ALUNO?filterByFormula=({ALUNO}='101101')";
    return this.http.get<myData>(url, this.httpOptions);
  }
}
