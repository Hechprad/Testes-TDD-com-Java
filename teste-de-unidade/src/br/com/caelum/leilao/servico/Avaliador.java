package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	// constante que guarda o menor número double
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	// constante que guarda o maior número double
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	// constante que guarda a média dos lances inicializada com o valor 0
	private double mediaDosLances = 0;
	private List<Lance> maiores;

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
		
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>() {
			@Override
			public int compare(Lance o1, Lance o2) {
				if(o1.getValor() < o2.getValor()) return 1;
				if(o1.getValor() > o2.getValor()) return -1;
				return 0;
			}
		});
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}
	
	public List<Lance> getTresMaiores() {
		return maiores;
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
