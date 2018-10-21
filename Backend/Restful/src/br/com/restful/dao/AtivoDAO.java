package br.com.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.corba.se.impl.encoding.IDLJavaSerializationInputStream;

import java.sql.Date;

import br.com.restful.factory.ConnectionFactory;
import br.com.restful.model.Ativo;
import br.com.restful.model.Manutencao;

public class AtivoDAO {

	private static AtivoDAO instance;

	public static AtivoDAO getInstance() {
		if (instance == null)
			instance = new AtivoDAO();
		return instance;
	}

	public ArrayList<Ativo> listarAtivos() {
		String sql = "SELECT * FROM lu2cas01.ATIVO";
		 
		ArrayList<Ativo> ativos = new ArrayList<Ativo>();
		 
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		 
		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
		 
			while(rs.next()){
				Ativo ativo = new Ativo();
				 
				ativo.setId(rs.getLong("id"));
				ativo.setDescricao(rs.getString("descricao")); 
				ativo.setDtCompra(rs.getDate("dt_compra"));
				ativo.setFabricante(rs.getString("fabricante"));
				ativo.setVlCompra(rs.getDouble("vl_compra"));
				ativo.setVlDepreciado(rs.getDouble("vl_depreciado"));
				ativo.setCriadoPor(rs.getLong("criado_por"));
				ativo.setDtCriacao(rs.getDate("dt_criacao"));
				ativo.setIdStatus(rs.getLong("id_status"));
				ativo.setModificadoPor(rs.getLong("modificado_por"));
				ativo.setDtModificacao(rs.getDate("dt_modificacao"));
				 
				ativos.add(ativo);
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
		return ativos;
	}

	public ArrayList<Ativo> listarAtivo(Ativo ativo) {
		ArrayList<Ativo> ativos = new ArrayList<Ativo>();
		int i = 0;
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT * FROM lu2cas01.ATIVO WHERE 1=1 ";
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);
			
			if(ativo.getId() > 0) {
				sql += " and id = ? ";
				i++;
				pstm.setLong(i, ativo.getId());
			}else if(!ativo.getDescricao().isEmpty()) {
				sql += " and descricao = ? ";
				i++;
				pstm.setString(i, ativo.getDescricao());
			}else if(ativo.getDtCompra() != null && !ativo.getDtCompra().equals("")) {
				sql += " and dt_compra = ? ";
				i++;
				pstm.setDate(i, new java.sql.Date(ativo.getDtCompra().getTime()));
			}else if(!ativo.getFabricante().isEmpty()) {
				sql += " and fabricante = ? ";
				i++;
				pstm.setString(i, ativo.getFabricante());
			}else if(ativo.getVlCompra() > 0) {
				sql += " and vl_compra = ? ";
				i++;
				pstm.setDouble(i, ativo.getVlCompra());
			}else if(ativo.getVlDepreciado() > 0) {
				sql += " and vl_depreciado = ? ";
				i++;
				pstm.setDouble(i, ativo.getVlDepreciado());
			}else if(ativo.getIdStatus() > 0) {
				sql += " and id_status = ? ";
				i++;
				pstm.setLong(i, ativo.getIdStatus());
			}else if(!ativo.getObservacao().isEmpty()) {
				sql += " and observacao = ? ";
				i++;
				pstm.setString(i, ativo.getObservacao()                                                                                                              );
			}
			
			rs = pstm.executeQuery();
		 
			
			while(rs.next()){
				Ativo ati = new Ativo();
				 
				ativo.setId(rs.getLong("id"));
				ativo.setDescricao(rs.getString("descricao")); 
				ativo.setDtCompra(rs.getDate("dt_compra"));
				ativo.setFabricante(rs.getString("fabricante"));
				ativo.setVlCompra(rs.getDouble("vl_compra"));
				ativo.setVlDepreciado(rs.getDouble("vl_depreciado"));
				ativo.setCriadoPor(rs.getLong("criado_por"));
				ativo.setDtCriacao(rs.getDate("dt_criacao"));
				ativo.setIdStatus(rs.getLong("id_status"));
				ativo.setModificadoPor(rs.getLong("modificado_por"));
				ativo.setDtModificacao(rs.getDate("dt_modificacao"));
				 
				ativos.add(ati);
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

		return ativos;
	}

	public Boolean cadastrarAtivo(Ativo ativo) {
		String sql = "INSERT INTO lu2cas01.ATIVO(descricao,fabricante,dt_compra,vl_compra,vl_depreciado,id_status,observacao,criado_por,dt_criacao)"//,dt_modificacao)"
				+ " VALUES(?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;
		boolean cadastrou = false;
		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, ativo.getDescricao());
			pstm.setString(2, ativo.getFabricante());
			pstm.setDate(3, new java.sql.Date(ativo.getDtCompra().getTime()));
			pstm.setDouble(4, ativo.getVlCompra());
			pstm.setDouble(5, ativo.getVlDepreciado());
			pstm.setLong(6, ativo.getIdStatus());
			pstm.setString(7, ativo.getObservacao());
			pstm.setLong(8, ativo.getCriadoPor());
			//pstm.setLong(9, ativo.getModificadoPor());
			pstm.setDate(9, new java.sql.Date(System.currentTimeMillis()));
			//pstm.setDate(11, new java.sql.Date(ativo.getDtModificacao().getTime()));
			
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

	public Boolean alterarAtivo(Ativo ativo) {
		String sql = "UPDATE lu2cas01.ATIVO SET descricao = ?,fabricante = ?,dt_compra = ?,vl_compra = ?,vl_depreciado = ?,id_status = ?, observacao = ?, modificado_por = ?, dt_modificacao = ? WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;
		boolean alterou = false;
		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, ativo.getDescricao());
			pstm.setString(2, ativo.getFabricante());
			pstm.setDate(3, new Date(ativo.getDtCompra().getTime()));
			pstm.setDouble(4, ativo.getVlCompra());
			pstm.setDouble(5, ativo.getVlDepreciado());
			pstm.setLong(6, ativo.getIdStatus());
			pstm.setString(7, ativo.getObservacao());
			pstm.setLong(8, ativo.getModificadoPor());
			pstm.setDate(9, new java.sql.Date(System.currentTimeMillis()));
			pstm.setLong(10, ativo.getId());

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

	public boolean excluirAtivo(long id) {
		boolean sucesso = false;
		String sql = "DELETE FROM lu2cas01.ATIVO WHERE id = ?";

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
	
	public Boolean setManutencao(Manutencao manutencao) {
		String sql = "UPDATE lu2cas01.ATIVO SET id_status = 2, modificado_por = ?, dt_modificacao = ? WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;
		boolean alterou = false;
		try {
			conn = ConnectionFactory.criarConexao();
			pstm = conn.prepareStatement(sql);

			pstm.setLong(1, manutencao.getCriadoPor());
			pstm.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			pstm.setLong(3, manutencao.getAtivo().getId());

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
}
