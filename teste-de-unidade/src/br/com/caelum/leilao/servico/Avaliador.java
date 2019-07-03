package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	// constante que guarda o menor n�mero double
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	// constante que guarda o maior n�mero double
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	// constante que guarda a m�dia dos lances inicializada com o valor 0
	private double mediaDosLances = 0;

	public void avalia(Leilao leilao) {
		
		double valorTotalDosLances = 0;
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorDeTodos) {maiorDeTodos = lance.getValor();}
			if(lance.getValor() < menorDeTodos) {menorDeTodos = lance.getValor();}
			valorTotalDosLances += lance.getValor();
		}
		if(valorTotalDosLances == 0) {
			mediaDosLances = 0;
			return;
		}
		mediaDosLances = valorTotalDosLances / leilao.getLances().size();
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
