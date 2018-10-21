package br.com.restful.model;

import java.sql.Date;

public class Manutencao {
	private long id;
	private Ativo ativo;
	private String descricao;
	private Date dtConserto;
	private long criadoPor;
	private long modificadoPor;
	private Date dtCriacao;
	private Date dtModificacao;
	
	public Manutencao() {
		super();
	}

	public Manutencao(long id, Ativo ativo, String descricao, Date dtConserto, long criadoPor, long modificadoPor,
			Date dtCriacao, Date dtModificacao) {
		super();
		this.id = id;
		this.ativo = ativo;
		this.descricao = descricao;
		this.dtConserto = dtConserto;
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

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDtConserto() {
		return dtConserto;
	}

	public void setDtConserto(Date dtConserto) {
		this.dtConserto = dtConserto;
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
	
	
}
