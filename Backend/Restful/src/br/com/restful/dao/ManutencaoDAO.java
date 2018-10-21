package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Funcionario;
import br.com.restful.model.Manutencao;

public class ManutencaoDAO {

	private static ManutencaoDAO instance;

	public static ManutencaoDAO getInstance() {
		if (instance == null)
			instance = new ManutencaoDAO();
		return instance;
	}
	
	public Boolean cadastrarManutencao(Manutencao manutencao) {
		String sql = "INSERT INTO lu2cas01.MANUTENCAO(descricao,id_ativo,dt_conserto,dt_entrada,criado_por,dt_criacao)"
				+ " VALUES(?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean cadastrou = false;
		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, manutencao.getDescricao());
			pstm.setLong(2, manutencao.getAtivo().getId());
			pstm.setDate(3, new java.sql.Date(System.currentTimeMillis())); //dtConserto
			pstm.setDate(4, new java.sql.Date(System.currentTimeMillis())); //dtConserto
			pstm.setLong(5, manutencao.getCriadoPor());
			pstm.setDate(6, new java.sql.Date(System.currentTimeMillis())); //dtCriacao
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
}
