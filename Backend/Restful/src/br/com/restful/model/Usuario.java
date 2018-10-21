package br.com.restful.model;

import java.util.Date;

import sun.security.util.Password;

public class Usuario {

	private long id;
	private long idPerfil;
	private String senha;
	private long criadoPor;
	private long modificadoPor;
	private Date dtCriacao;
	private Date dtModificacao;
	
	public Usuario() {
		super();
	}

	public Usuario(long id, long idPerfil, String senha, long criadoPor, long modificadoPor, Date dtCriacao,
			Date dtModificacao) {
		super();
		this.id = id;
		this.idPerfil = idPerfil;
		this.senha = senha;
		this.criadoPor = criadoPor;
		this.modificadoPor = modificadoPor;
		this.dtCriacao = dtCriacao;
		this.dtModificacao = dtModificacao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(long criadoPor) {
		this.criadoPor = criadoPor;
	}

	public long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtModificacao() {
		return dtModificacao;
	}

	public void setDtModificacao(Date dtModificacao) {
		this.dtModificacao = dtModificacao;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", idPerfil=" + idPerfil + ", senha=" + senha + ", criadoPor=" + criadoPor
				+ ", modificadoPor=" + modificadoPor + ", dtCriacao=" + dtCriacao + ", dtModificacao=" + dtModificacao
				+ "]";
	}


}