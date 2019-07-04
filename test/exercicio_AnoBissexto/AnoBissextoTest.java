package exercicio_AnoBissexto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AnoBissextoTest {

	@Test
	public void deveRetornarAnoBissexto() {
		AnoBissexto ano = new AnoBissexto();
		
		assertEquals(true, ano.ehAnoBissexto(2020));
		assertEquals(true, ano.ehAnoBissexto(2016));
		assertEquals(true, ano.ehAnoBissexto(2012));
	}
	
	@Test
	public void naoDeveRetornarAnoBissexto() {
		AnoBissexto ano = new AnoBissexto();
		
		assertEquals(false, ano.ehAnoBissexto(2019));
		assertEquals(false, ano.ehAnoBissexto(2015));
		assertEquals(false, ano.ehAnoBissexto(2011));
	}
}
