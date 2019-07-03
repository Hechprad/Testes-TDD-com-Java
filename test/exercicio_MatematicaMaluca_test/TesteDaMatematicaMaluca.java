package exercicio_MatematicaMaluca_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exercicio_MatematicaMaluca.MatematicaMaluca;

public class TesteDaMatematicaMaluca {

	@Test
	public void deveMultiplicarNumerosMaioresQue30() {
		MatematicaMaluca doida = new MatematicaMaluca();
		assertEquals(100 * 4, doida.contaMaluca(100));
	}
	
	@Test
	public void deveMultiplicarNumerosMaioresQue10EMenoresQue30() {
		MatematicaMaluca doida = new MatematicaMaluca();
		assertEquals(20 * 3, doida.contaMaluca(20));
	}
	
	@Test
	public void deveMultiplicarNumerosMenoresQue10() {
		MatematicaMaluca doida = new MatematicaMaluca();
		assertEquals(5 * 2, doida.contaMaluca(5));
	}
}
