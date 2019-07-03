package br.com.caelum.leilao.teste;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	// deve ser obrigatoriamente public void e não receber parâmetros
	// também deve set anotado com 'test'
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenário
		Usuario joao = new Usuario("João"); 
		Usuario adebaior = new Usuario("Adebaior"); 
		Usuario maria = new Usuario("Maria"); 

		Leilao leilao = new Leilao("Playstation 4 Novo");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(adebaior, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// validação, comparando resultados
		Assert.assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescenteComOutrosValores() {
		// parte 1: cenário
		Usuario joao = new Usuario("João"); 
		Usuario adebaior = new Usuario("Adebaior"); 
		Usuario maria = new Usuario("Maria"); 

		Leilao leilao = new Leilao("Playstation 4 Novo");
		
		leilao.propoe(new Lance(joao, 1000.0));
		leilao.propoe(new Lance(adebaior, 2000.0));
		leilao.propoe(new Lance(maria, 3000.0));
		
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// validação, comparando resultados
		Assert.assertEquals(3000.0, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		// parte 1: cenário
		Usuario joao = new Usuario("João"); 
		Usuario adebaior = new Usuario("Adebaior"); 
		Usuario maria = new Usuario("Maria"); 

		Leilao leilao = new Leilao("Playstation 4 Novo");
		
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(adebaior, 300.0));
		leilao.propoe(new Lance(maria, 250.0));
		
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// validação, comparando resultados
		Assert.assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveCalcularAMediaDosLances() {
		// parte 1: cenário
		Usuario joao = new Usuario("João"); 
		Usuario adebaior = new Usuario("Adebaior"); 
		Usuario maria = new Usuario("Maria"); 

		Leilao leilao = new Leilao("Playstation 4 Novo");
		
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(adebaior, 400.0));
		leilao.propoe(new Lance(maria, 500.0));
		
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// validação, comparando resultados
		Assert.assertEquals(400.0, leiloeiro.getMediaDosLances(), 0.00001);
	}
	
	@Test
	public void deveCalcularAMediaDeZeroLance() {
		// parte 1: cenário
		Usuario adebaior = new Usuario("Adebaior"); 

		Leilao leilao = new Leilao("Playstation 4 Novo");
		// nenhum lance
		
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// validação sem nenhum lance
		Assert.assertEquals(0.0, leiloeiro.getMediaDosLances(), 0.00001);
	}

}
