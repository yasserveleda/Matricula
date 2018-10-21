package br.com.restful.controller;

import java.util.ArrayList;

import br.com.restful.dao.FuncionarioDAO;
import br.com.restful.model.Funcionario;

public class FuncionarioController {
	
	public ArrayList<Funcionario> listarFuncionarios(){
		return FuncionarioDAO.getInstance().listarFuncionarios();
	}
	
	public Funcionario listarFuncionario(long id){
		return FuncionarioDAO.getInstance().listarFuncionario(id);
	}
	
	public Boolean cadastrarFuncionario(Funcionario funcionario){
		return FuncionarioDAO.getInstance().cadastrarFuncionario(funcionario);
	}
	
	public Boolean alterarFuncionario(Funcionario funcionario){
		return FuncionarioDAO.getInstance().alterarFuncionario(funcionario);
	}
	
	public boolean excluirFuncionario(long id){
		boolean sucesso = false;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		sucesso = funcionarioDAO.excluirFuncionario(id);
		return sucesso;
	}
	
}
