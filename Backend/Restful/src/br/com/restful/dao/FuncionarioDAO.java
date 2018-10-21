package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Funcionario;

public class FuncionarioDAO {

	private static FuncionarioDAO instance;

	public static FuncionarioDAO getInstance() {
		if (instance == null)
			instance = new FuncionarioDAO();
		return instance;
	}

	public ArrayList<Funcionario> listarFuncionarios() {
		String sql = "SELECT * FROM lu2cas01.FUNCIONARIO";
		 
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		 
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		 
		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
		 
			while(rs.next()){
				Funcionario funcionario = new Funcionario();

				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setCargo(rs.getString("cargo"));
				funcionario.setCnpj(rs.getString("cnpj"));
				funcionario.setCriadoPor(rs.getLong("criado_por"));
				funcionario.setDtCriacao(rs.getDate("dt_criacao"));
				funcionario.setModificadoPor(rs.getLong("modificado_por"));
				funcionario.setDtModificacao(rs.getDate("dt_modificacao"));
				
				funcionarios.add(funcionario);
			}
		 } catch (Exception e) {
			 e.printStackTrace();
		 }finally{
			 try{
				 if(rs != null){
					 rs.close();
				 }
				 if(pstm != null){
					 pstm.close();
				 }
				 if(conn != null){
					 conn.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		return funcionarios;
	}

	public Funcionario listarFuncionario(long id) {
		Funcionario funcionario = new Funcionario();
		
		String sql = "SELECT * FROM lu2cas01.ATIVO WHERE id = ?";
		 
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		 
		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);
			
			pstm.setLong(1, id);
			
			rs = pstm.executeQuery();
		 
			if(rs.next()){
				 
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setCargo(rs.getString("cargo"));
				funcionario.setCnpj(rs.getString("cnpj"));
				funcionario.setCriadoPor(rs.getLong("criado_por"));
				funcionario.setDtCriacao(rs.getDate("dt_criacao"));
				funcionario.setModificadoPor(rs.getLong("modificado_por"));
				funcionario.setDtModificacao(rs.getDate("dt_modificacao"));
				
			}
		 } catch (Exception e) {
			 e.printStackTrace();
		 }finally{
			 try{
				 if(rs != null){
					 rs.close();
				 }
				 if(pstm != null){
					 pstm.close();
				 }
				 if(conn != null){
					 conn.close();
				 }
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		 }

		return funcionario;
	}

	public Boolean cadastrarFuncionario(Funcionario funcionario) {
		String sql = "INSERT INTO lu2cas01.FUNCIONARIO(nome,cpf,cnpj,cargo,criado_por,dt_criacao)"
				+ " VALUES(?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean cadastrou = false;
		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, funcionario.getNome());
			pstm.setString(2, funcionario.getCpf());
			pstm.setString(3, funcionario.getCnpj());
			pstm.setString(4, funcionario.getCargo());
			pstm.setLong(5, funcionario.getCriadoPor());
			pstm.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			
			// Executa a sql para inser��o dos dados
			if(pstm.executeUpdate() > 0){
				cadastrou = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fecha as conex�es
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cadastrou;
	}

	public Boolean alterarFuncionario(Funcionario funcionario) {
		String sql = "UPDATE lu2cas01.FUNCIONARIO SET nome = ?,cpf = ?,cnpj = ?,cargo = ?,modificado_por = ?,dt_modificacao = ? WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;
		boolean alterou = false;
		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, funcionario.getNome());
			pstm.setString(2, funcionario.getCpf());
			pstm.setString(3, funcionario.getCnpj());
			pstm.setString(4, funcionario.getCargo());
			pstm.setLong(5, funcionario.getModificadoPor());
			pstm.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			pstm.setLong(7, funcionario.getId());

			// Executa a sql para inser��o dos dados
			if(pstm.executeUpdate() > 0){
				alterou = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fecha as conex�es
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return alterou;
	}

	public boolean excluirFuncionario(long id) {
		boolean sucesso = false;
		String sql = "DELETE FROM lu2cas01.FUNCIONARIO WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);

			pstm.setLong(1, id);

			// Executa a sql para inser��o dos dados
			if(pstm.executeUpdate() > 0){
				sucesso = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fecha as conex�es
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sucesso;
	}

}
