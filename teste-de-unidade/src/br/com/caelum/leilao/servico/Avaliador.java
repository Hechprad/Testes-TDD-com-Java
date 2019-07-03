package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	// constante que guarda o menor número double
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	// constante que guarda o maior número double
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	// constante que guarda o menor número double
	private double mediaDosLances = 0;

	public void avalia(Leilao leilao) {
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorDeTodos) {maiorDeTodos = lance.getValor();}
			if(lance.getValor() < menorDeTodos) {menorDeTodos = lance.getValor();}
			mediaDosLances += lance.getValor();
		}
		mediaDosLances = mediaDosLances / leilao.getLances().size();
	}
	
	public double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public double getMenorLance() {
		return menorDeTodos;
	}
	
	public double getMediaDosLances() {
		return mediaDosLances;
	}
}
