package br.com.caelum.leilao.teste;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	// deve ser obrigatoriamente public void e n�o receber par�metros
	// tamb�m deve set anotado com 'test'
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
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
		double mediaDosLances = 316.66666;
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		Assert.assertEquals(mediaDosLances, leiloeiro.getMediaDosLances(), 0.00001);
	}

}