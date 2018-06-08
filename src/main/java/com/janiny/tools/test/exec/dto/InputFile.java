package com.janiny.tools.test.exec.dto;

import java.util.ArrayList;
import java.util.List;

import com.janiny.tools.test.exec.entity.Cliente;
import com.janiny.tools.test.exec.entity.Venda;
import com.janiny.tools.test.exec.entity.Vendedor;

public class InputFile {

	private String fileName;
	
	private List<Cliente> clientes;
	private List<Venda> vendas;
	private List<Vendedor> vendedores;
	
	private String qtdeClientes;
	private String qtdeVendedores;
	private String idVendaValorMaisAlto;
	private String nomeVendedorMenosVendeu;
	
	public InputFile (String fileName){
		this.fileName = fileName;
	}
	
	public List<Cliente> getClientes() {
		if(clientes==null){
			clientes = new ArrayList<Cliente>();
		}
		return clientes;
	}
	public List<Venda> getVendas() {
		if(vendas==null){
			vendas = new ArrayList<Venda>();
		}
		return vendas;
	}
	public List<Vendedor> getVendedores() {
		if(vendedores==null){
			vendedores = new ArrayList<Vendedor>();
		}
		return vendedores;
	}
	public String getQtdeClientes() {
		return qtdeClientes;
	}
	public String getQtdeVendedores() {
		return qtdeVendedores;
	}
	public String getIdVendaValorMaisAlto() {
		return idVendaValorMaisAlto;
	}
	public String getNomeVendedorMenosVendeu() {
		return nomeVendedorMenosVendeu;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String gerarOutputString() {
		qtdeClientes = String.valueOf(clientes.size());
		qtdeVendedores = String.valueOf(vendedores.size());
		idVendaValorMaisAlto = calcularVendaValorMaisAlto();
		nomeVendedorMenosVendeu = calcularNomeVendedorMenosVendeu();
		
		StringBuilder sb = new StringBuilder();
		sb.append("1. Quantidade de Clientes: ");
		sb.append(qtdeClientes);
		sb.append("\n");
		sb.append("2. Quantidade de Vendedores: ");
		sb.append(qtdeVendedores);
		sb.append("\n");
		sb.append("3. ID da Venda de valor mais alto: ");
		sb.append(idVendaValorMaisAlto);
		sb.append("\n");
		sb.append("4. Nome do Vendedor que menos vendeu: ");
		sb.append(nomeVendedorMenosVendeu);
		sb.append("\n");
		
		return sb.toString();
	}

	private String calcularNomeVendedorMenosVendeu() {
		String nomeVendedorMenosVendeu ="";
		Double menorTotalVendas = 0d;
		
		for(Vendedor vendedor : vendedores){
			Double totalVendas = buscarTotalVendaVendedor(vendedor.getNome());
			if(menorTotalVendas.equals(0d) || totalVendas < menorTotalVendas){
				menorTotalVendas = totalVendas;
				nomeVendedorMenosVendeu = vendedor.getNome();
			}
		}
		
		return nomeVendedorMenosVendeu;
	}

	private Double buscarTotalVendaVendedor(String nome) {
		Double totalVendas = 0d;
		for(Venda venda : vendas){
			if(venda.getNomeVendedor().equals(nome)){
				totalVendas += venda.getPrecoItem()*venda.getQtdeItem();
			}
		}
		return totalVendas;
	}

	private String calcularVendaValorMaisAlto() {
		Integer idMaisAlto = null;
		Double valorMaisAlto = 0d;
		
		for(Venda venda : vendas){
			Double valorTotalVenda = venda.getPrecoItem()*venda.getQtdeItem();
			if(valorMaisAlto < valorTotalVenda){
				valorMaisAlto = valorTotalVenda;
				idMaisAlto = venda.getId();
			}
		}
		return String.valueOf(idMaisAlto);
	}
}
