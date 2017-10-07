package br.com.vinicius.argentum.indicadores;

import br.com.vinicius.argentum.modelo.SerieTemporal;

public class MediaMovelSimples implements Indicador {

	Indicador outroIndicador;

	public MediaMovelSimples(Indicador outroIndicador) {
		this.outroIndicador = outroIndicador;
	}

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		double soma = 0.0;
		for (int i = posicao; i > posicao - 3; i--) {
			soma += outroIndicador.calcula(i, serie);
		}
		return soma / 3;
	}

	@Override
	public String toString() {
		return "MMS de " + outroIndicador;
	}

}
