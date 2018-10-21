package br.com.restful.controller;

import java.util.ArrayList;

import br.com.restful.dao.AtivoDAO;
import br.com.restful.model.Ativo;
import br.com.restful.model.Manutencao;

public class AtivoController {
	
	public ArrayList<Ativo> listarAtivos(){
		return AtivoDAO.getInstance().listarAtivos();
	}
	
	public ArrayList<Ativo> listarAtivo(Ativo ativo){
		return AtivoDAO.getInstance().listarAtivo(ativo);
	}
	
	public Boolean cadastrarAtivo(Ativo ativo){
		return AtivoDAO.getInstance().cadastrarAtivo(ativo);
	}
	
	public Boolean alterarAtivo(Ativo ativo){
		return AtivoDAO.getInstance().alterarAtivo(ativo);
	}
	
	public boolean excluirAtivo(long id){
		boolean sucesso = false;
		AtivoDAO ativoDAO = new AtivoDAO();
		sucesso = ativoDAO.excluirAtivo(id);
		return sucesso;
	}
	
	public boolean setManutencao(Manutencao manutencao) {
		return AtivoDAO.getInstance().setManutencao(manutencao);
	}
	
	
	public boolean validador(Ativo ativo) {
		boolean valido = false;
		if(ativo.getDescricao().isEmpty() ||
				(ativo.getDtCompra() == null ||ativo.getDtCompra().equals("")) ||
				ativo.getFabricante().isEmpty() ||
				ativo.getIdStatus() <= 0 ||
				ativo.getVlCompra() <= 0 ||
				ativo.getVlDepreciado() <= 0) {
			return valido;
		}else {
			valido = true;
			return valido;
		}
	}
	
}
