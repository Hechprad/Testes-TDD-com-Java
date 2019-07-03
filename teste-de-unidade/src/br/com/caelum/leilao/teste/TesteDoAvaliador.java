package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	public static void main(String[] args) {
		// parte 1: cen�rio
		Usuario joao = new Usuario("Jo�o"); 
		Usuario adebaior = new Usuario("Adebaior"); 
		Usuario maria = new Usuario("Maria"); 

		Leilao leilao = new Leilao("Playstation 4 Novo");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(adebaior, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		// parte 2: a��o
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// parte 3: valida��o
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		System.out.println(maiorEsperado == leiloeiro.getMaiorLance());
		System.out.println(menorEsperado == leiloeiro.getMenorLance());
	}

}
