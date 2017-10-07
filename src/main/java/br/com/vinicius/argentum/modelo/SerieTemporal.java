package br.com.vinicius.argentum.modelo;

import java.util.List;

public class SerieTemporal {
	private final List<Candle> candles;

	public SerieTemporal(List<Candle> candles) {
		this.candles = candles;
	}

	public List<Candle> getCandles() {
		return candles;
	}

	public int getUltimaPosicao() {
		return this.candles.size() - 1;
	}

	public Candle getCandle(int index) {
		return candles.get(index);
	}

}
