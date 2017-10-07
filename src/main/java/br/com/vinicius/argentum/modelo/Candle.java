package br.com.vinicius.argentum.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Candle {
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;

	public Candle(double abertura, double fechamento, double minimo,
			double maximo, double volume, Calendar data) {
		if (maximo < minimo) {
			throw new IllegalArgumentException(
					"O preco máximo não pode ser menor que o preço mínimo");
		}
		if (data == null) {
			throw new IllegalArgumentException("A data não pode ser nula.");
		}
		if (abertura < 0 || fechamento < 0 || minimo < 0 || maximo < 0
				|| volume < 0) {
			throw new IllegalArgumentException(
					"Nenhum valor pode ser negativo.");
		}

		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}

	public boolean isAlta() {
		return this.abertura < this.fechamento;
	}

	public boolean isBaixa() {
		return this.abertura > this.fechamento;
	}

	@Override
	public String toString() {
		return "[Abertura " + getAbertura() + ", Fechamento " + getFechamento()
				+ ", Mínima " + getMinimo() + ", Máxima " + getMaximo()
				+ ", Volume " + getVolume() + ", Data "
				+ new SimpleDateFormat("dd/MM/yyy").format(getData().getTime())
				+ "]";
	}

}
