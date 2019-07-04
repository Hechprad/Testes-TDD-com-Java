package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario adebaior;
	private Usuario maria;

	// diz para o JUnit executar este método UMA vez antes de rodar cada teste
	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		
		this.joao = new Usuario("João"); 
		this.adebaior = new Usuario("Adebaior"); 
		this.maria = new Usuario("Maria");
		
		System.out.println("@Before - Inicializando o teste!");
	}
	
	// diz para o JUnit executar este método UMA vez após rodar cada teste
	@After
	public void finaliza() {
	  System.out.println("@After - Fim do teste!");
	}
	
	// diz para o JUnit executar este método UMA vez antes de rodar os métodos da classe 'AvaliadorTest'
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("@BeforeClass - before class");
	}

	// diz para o JUnit executar este método UMA vez após rodar os métodos da classe 'AvaliadorTest'
	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("@AfterClass - after class");
	}
	
	// deve ser obrigatoriamente public void e não receber parâmetros
	// também deve set anotado com 'test'
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenário
		Leilao leilao = new CriadorDeLeilao().para("Monitor novo")
				.lance(joao, 250.0)
				.lance(adebaior, 300.0)
				.lance(maria, 400.0)
				.constroi();
		
		// parte 2: ação
		leiloeiro.avalia(leilao);
		
		// validação, comparando resultados
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		// parte 1: cenário
		Leilao leilao = new CriadorDeLeilao().para("Carro Zero")
				.lance(joao, 1000.0)
				.constroi();
		
		// parte 2: ação
		leiloeiro.avalia(leilao);
		
		// validação, comparando resultados
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances () {
		// parte 1: cenário
		Leilao leilao = new CriadorDeLeilao().para("")
				.lance(joao, 100.0)
				.lance(adebaior, 200.0)
				.lance(joao, 300.0)
				.lance(adebaior, 400.0)
				.constroi();
		
		// parte 2: ação
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		// validação, comparando resultados
		assertEquals(3, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
		// parte 1: cenário
		Leilao leilao = new CriadorDeLeilao().para("Celular tópi")
				.lance(joao, 100.0)
				.lance(adebaior, 200.0)
				.constroi();
		
		// parte 2: ação
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		// validação, comparando resultados
		assertEquals(2, maiores.size());
		assertEquals(200.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(100.0, maiores.get(1).getValor(), 0.00001);
	}
	
	@Test
	public void deveDevolverListaVaziaCasoNaoHajaLances() {
		// parte 1: cenário
		Leilao leilao = new CriadorDeLeilao().para("Xbox")
				.constroi();
		
		// parte 2: ação
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		// validação, comparando resultados
		assertEquals(0, maiores.size());
	}
	
	@Test
	public void deveEntenderLancesRandomicos() {
		// parte 1: cenário
		Leilao leilao = new CriadorDeLeilao().para("Televisão 50 pol")
				.lance(joao, 200.0)
				.lance(adebaior, 450.0)
				.lance(joao, 120.0)
				.lance(adebaior, 700.0)
				.lance(joao, 630.0)
				.lance(adebaior, 230.0)
				.constroi();
		// parte 2: ação
		leiloeiro.avalia(leilao);
		
		// validação, comparando resultados
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.00001);
		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		// parte 1: cenário
		Leilao leilao = new CriadorDeLeilao().para("Radio am")
				.lance(joao, 400.0)
				.lance(adebaior, 300.0)
				.lance(maria, 250.0)
				.lance(joao, 100.0)
				.constroi();
		
		// parte 2: ação
		leiloeiro.avalia(leilao);
		
		// validação, comparando resultados
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(100.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveCalcularAMediaDosLances() {
		// parte 1: cenário
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
				.lance(joao, 300.0)
				.lance(adebaior, 400.0)
				.lance(maria, 500.0)
				.constroi();
		
		// parte 2: ação
		leiloeiro.avalia(leilao);
		
		// validação, comparando resultados
		assertEquals(400.0, leiloeiro.getMediaDosLances(), 0.00001);
	}

	@Test
	public void deveCalcularAMediaDeZeroLance() {
		// parte 1: cenário
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
				.constroi();
		// nenhum lance
		
		// parte 2: ação
		leiloeiro.avalia(leilao);
		
		// validação sem nenhum lance
		assertEquals(0, leiloeiro.getMediaDosLances(), 0.00001);
	}
	
}
