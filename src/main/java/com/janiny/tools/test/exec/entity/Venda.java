package com.janiny.tools.test.exec.entity;

public class Venda {

	public Venda(Integer id, Integer idItem, Integer qtdeItem, Double precoItem, String nomeVendedor) {
		super();
		this.id = id;
		this.idItem = idItem;
		this.qtdeItem = qtdeItem;
		this.precoItem = precoItem;
		this.nomeVendedor = nomeVendedor;
	}
	private Integer id;
	private Integer idItem;
	private Integer qtdeItem;
	private Double precoItem;
	private String nomeVendedor;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdItem() {
		return idItem;
	}
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	public Integer getQtdeItem() {
		return qtdeItem;
	}
	public void setQtdeItem(Integer qtdeItem) {
		this.qtdeItem = qtdeItem;
	}
	public Double getPrecoItem() {
		return precoItem;
	}
	public void setPrecoItem(Double precoItem) {
		this.precoItem = precoItem;
	}
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
}
