package br.com.restful.controller;

import br.com.restful.dao.ManutencaoDAO;
import br.com.restful.model.Manutencao;

public class ManutencaoController {
	
	public Boolean cadastrarManutencao(Manutencao manutencao){
		return ManutencaoDAO.getInstance().cadastrarManutencao(manutencao);
	}
	
	public boolean validador(Manutencao manutencao) {
		boolean valido = true;
		return valido;
	}
}
