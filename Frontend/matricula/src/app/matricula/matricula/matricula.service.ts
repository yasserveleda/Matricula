import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";

interface myData {
    status: string
    obj: Object
}

@Injectable()
export class MatriculaSerivce {

    private httpOptions;

    constructor(private http: HttpClient) {
        this.httpOptions = {
            headers: new HttpHeaders({
                'Authorization': 'Bearer key7EJ7UZ3gFw0FLc',
                'Content-Type': 'application/json'
            })
        };
    }

    getDisciplinas() {
        let urlAsc = `https://api.airtable.com/v0/appM4HMJqyZHY4gBR/DISCIPLINA?sort%5B0%5D%5Bfield%5D=SEMESTRE&sort%5B0%5D%5Bdirection%5D=asc`;
        return this.http.get<myData>(urlAsc, this.httpOptions);
    }

    getHistorico(matricula) {
        let url = `https://api.airtable.com/v0/appM4HMJqyZHY4gBR/HISTORICO?filterByFormula=({MATRICULA}='${matricula}')`;
        return this.http.get<myData>(url, this.httpOptions);
    }

    getAluno(matricula) {
        let url = `https://api.airtable.com/v0/appM4HMJqyZHY4gBR/ALUNO?filterByFormula=({ALUNO}='${matricula}')`;
        return this.http.get<myData>(url, this.httpOptions);
    }

    atualizaAluno(aluno) {
        let url = `https://api.airtable.com/v0/appM4HMJqyZHY4gBR/ALUNO/${aluno.id}`;
        let disciplinas = aluno.fields.DISCIPLINAS_MATRICULADO;
        let turmas = aluno.fields.TURMAS;
        let alunoAux = {
            "fields": {
                "DISCIPLINAS_MATRICULADO": disciplinas,
                "TURMAS": turmas
            }
        };
        return this.http.patch<myData>(url, alunoAux, this.httpOptions);
    }

    atualizaDisciplina(disciplina) {
        let url = `https://api.airtable.com/v0/appM4HMJqyZHY4gBR/DISCIPLINA/${disciplina.id}`;
        let disciplinaAux = {
            "fields": {
                "NUMERO_VAGAS": disciplina.fields.NUMERO_VAGAS
            }
        };
        return this.http.patch<myData>(url, disciplinaAux, this.httpOptions);
    }

    getTurmas() {
        let url = `https://api.airtable.com/v0/appM4HMJqyZHY4gBR/TURMA`;
        let urlAsc  = `https://api.airtable.com/v0/appM4HMJqyZHY4gBR/TURMA?sort%5B0%5D%5Bfield%5D=SEMESTRE&sort%5B0%5D%5Bdirection%5D=asc`;
        return this.http.get<myData>(urlAsc, this.httpOptions);
    }

}