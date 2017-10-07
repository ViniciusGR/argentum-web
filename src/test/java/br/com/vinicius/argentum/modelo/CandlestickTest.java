package br.com.vinicius.argentum.modelo;

import java.util.Calendar;

import org.junit.Test;

public class CandlestickTest {

	@Test(expected = IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		new Candle(100, 100, 100, 50, 100, Calendar.getInstance());
	}

	@Test(expected = IllegalArgumentException.class)
	public void dataNaoPodeSerNula() {
		new Candle(100, 100, 100, 100, 100, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void valoresNaoPodemSerNegativos() {
		new Candle(-100, -100, -100, -100, -100, Calendar.getInstance());
	}

}
