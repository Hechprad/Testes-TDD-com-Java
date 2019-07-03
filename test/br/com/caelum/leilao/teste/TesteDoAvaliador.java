package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		// parte 1: cenário
		Usuario joao = new Usuario("João"); 
		Leilao leilao = new Leilao("Carro Zero");
		
		leilao.propoe(new Lance(joao, 1000.0));
		
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// validação, comparando resultados
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances () {
		// parte 1: cenário
		Usuario joao = new Usuario("João"); 
		Usuario adebaior = new Usuario("Adebaior"); 

		Leilao leilao = new Leilao("Celular tópi");
		
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(adebaior, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(adebaior, 400.0));
		
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		// validação, comparando resultados
		assertEquals(3, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
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
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
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
		assertEquals(400.0, leiloeiro.getMediaDosLances(), 0.00001);
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
		assertEquals(0, leiloeiro.getMediaDosLances(), 0.00001);
	}

}
