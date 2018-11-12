import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";

interface myData {
    obj: Object
}

@Injectable()
export class MatriculaSerivce {

    constructor(private http: HttpClient) {
        this.matricularAlunoDisciplina();
     }

    getDatda() {
        return this.http.get<myData>('https://api.airtable.com/v0/appM4HMJqyZHY4gBR/DISCIPLINA?api_key=key7EJ7UZ3gFw0FLc');
    }

    matricularAlunoDisciplina() {
        const httpOptions = {
            headers: new HttpHeaders({
                'Authorization': 'Bearer key7EJ7UZ3gFw0FLc',
                'Content-Type': 'application/json'
            })
        };

        const data = {
            "id": "recUQfECdkWXFE8zy",
            "fields": {
                "ID": "1",
                "ALUNO": "101101",
                "SENHA": "101101@psa.br",
                "DISCIPLINAS_MATRICULADO": [
                    "recnfsgdoOeuFC4RA",
                    "recakbctFXdaIYu6B",
                    "recXcUbsSubyoqweA"
                ],
                "COD_CRED": [
                    "4637N-02",
                    "4637C-04",
                    "4636C-04"
                ]
            }
        }

        return this.http.post('https://api.airtable.com/v0/appM4HMJqyZHY4gBR/ALUNO/recUQfECdkWXFE8zy', data, httpOptions);
    }
}