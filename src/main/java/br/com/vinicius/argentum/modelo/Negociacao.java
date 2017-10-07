package br.com.vinicius.argentum.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Negociacao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final double preco;
	private final int quantidade;
	private final Calendar data;

	public Negociacao(double preco, int quantidade, Calendar data) {
		if (data == null) {
			throw new IllegalArgumentException("A data não pode ser nula.");
		}

		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) data.clone();
	}

	public double getVolume() {
		return preco * quantidade;
	}

	public boolean isMesmoDia(Calendar outraData) {
		if (data.get(Calendar.DAY_OF_MONTH) == outraData
				.get(Calendar.DAY_OF_MONTH)
				&& data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH)
				&& data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "[Preço " + this.preco + ", Quantidade " + this.quantidade + ", Data "
				+ new SimpleDateFormat("dd/MM/yyyy").format(this.data.getTime()) + "]";
	}
}
