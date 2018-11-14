import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';

interface myData {
  obj: Object
}

@Injectable({
  providedIn: 'root'
})
export class HistoricoService {

  constructor(private http: HttpClient) {
    this.getData();
 }

  getData() {
    return this.http.get<myData>('https://api.airtable.com/v0/appM4HMJqyZHY4gBR/HISTORICO?api_key=key7EJ7UZ3gFw0FLc');
  }
}
