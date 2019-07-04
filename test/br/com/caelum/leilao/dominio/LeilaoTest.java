package br.com.caelum.leilao.dominio;

import static br.com.caelum.leilao.matcher.LeilaoMatcher.temUmLance;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class LeilaoTest {

	private Usuario steveJobs;
	private Usuario steveWozniak;
	private Usuario billGates;

	@Before
	public void setUp() {
		this.steveJobs = new Usuario("Steve Jobs");
		this.steveWozniak = new Usuario("Steve Wozniak");
		this.billGates = new Usuario("Bill Gates");
	}
	@Test
	public void deveReceberUmLance() {
		
		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").constroi();
        assertEquals(0, leilao.getLances().size());

        Lance lance = new Lance(billGates, 500.0);
        leilao.propoe(lance);

		assertThat(leilao.getLances().size(), equalTo(1));
		assertThat(leilao.getLances().get(0).getValor(), equalTo(500.0));
		assertThat(leilao, temUmLance(lance));
	}
	
	@Test
	public void deveReceberVariosLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Monitor 40 pol")
				.lance(steveJobs, 2000.0)
				.lance(steveWozniak, 3000.0)
				.lance(billGates, 500.0)
				.constroi();
		
		assertEquals(3, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
		assertEquals(500.0, leilao.getLances().get(2).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		
		Leilao leilao = new CriadorDeLeilao().para("Monitor 52 pol")
				.lance(steveJobs, 2000.0)
				.lance(steveJobs, 3000.0)
				.constroi();
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		
		Leilao leilao = new CriadorDeLeilao().para("Monitor 40 pol")
				.lance(steveJobs, 2000.0)
				.lance(billGates, 3000.0)
				.lance(steveJobs, 4000.0)
				.lance(billGates, 5000.0)
				.lance(steveJobs, 6000.0)
				.lance(billGates, 7000.0)
				.lance(steveJobs, 8000.0)
				.lance(billGates, 9000.0)
				.lance(steveJobs, 10000.0)
				.lance(billGates, 11000.0)
				.constroi();
		
		// deve ser ignorado
		leilao.propoe(new Lance(steveJobs, 12000.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
	@Test
	public void deveDobrarOUltimoLanceDado() {
		
		Leilao leilao = new CriadorDeLeilao().para("Monitor 52 pol")
				.lance(steveJobs, 2000.0)
				.lance(billGates, 3000.0)
				.constroi();
		
		leilao.dobraLance(steveJobs);
		
		assertEquals(3, leilao.getLances().size());
		assertEquals(4000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		
		Leilao leilao = new CriadorDeLeilao().para("Monitor 52 pol")
				.lance(billGates, 3000.0)
				.constroi();
		
		leilao.dobraLance(steveJobs);
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(3000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test(expected=IllegalArgumentException.class)
	public void lanceNaoPodeSerZero() {
		new CriadorDeLeilao().para("Bota marrom semi-nova")
				.lance(billGates, 0)
				.constroi();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void lanceNaoPodeSerMenorQueZero() {
		new CriadorDeLeilao().para("Bota marrom semi-nova")
				.lance(billGates, -100)
				.constroi();
	}
}
