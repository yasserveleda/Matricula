package br.com.restful.controller;

import java.util.ArrayList;

import br.com.restful.dao.FuncionarioDAO;
import br.com.restful.dao.UsuarioDAO;
import br.com.restful.dao.UsuarioDAO;
import br.com.restful.model.Funcionario;
import br.com.restful.model.Usuario;
import br.com.restful.model.Usuario;

public class UsuarioController {
	
	public ArrayList<Usuario> listarUsuarios(){
		return UsuarioDAO.getInstance().listarUsuarios();
	}
	
	public Usuario listarUsuario(long id){
		return UsuarioDAO.getInstance().listarUsuario(id);
	}
	
	public Boolean cadastrarUsuario(Usuario usuario){
		return UsuarioDAO.getInstance().cadastrarUsuario(usuario);
	}
	
	public Boolean alterarUsuario(Usuario usuario){
		return UsuarioDAO.getInstance().alterarUsuario(usuario);
	}
	
	public boolean excluirUsuario(long id){
		boolean sucesso = false;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		sucesso = usuarioDAO.excluirUsuario(id);
		return sucesso;
	}
	
}