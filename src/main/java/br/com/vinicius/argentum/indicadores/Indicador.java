package br.com.vinicius.argentum.indicadores;

import br.com.vinicius.argentum.modelo.SerieTemporal;

public interface Indicador {

	public abstract double calcula(int posicao, SerieTemporal serie);

}