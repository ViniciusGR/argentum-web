package br.com.vinicius.argentum.bean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.ChartModel;

import br.com.vinicius.argentum.grafico.GeradorModeloGrafico;
import br.com.vinicius.argentum.indicadores.Indicador;
import br.com.vinicius.argentum.indicadores.IndicadorFechamento;
import br.com.vinicius.argentum.indicadores.MediaMovelSimples;
import br.com.vinicius.argentum.modelo.Candle;
import br.com.vinicius.argentum.modelo.CandleFactory;
import br.com.vinicius.argentum.modelo.Negociacao;
import br.com.vinicius.argentum.modelo.SerieTemporal;
import br.com.vinicius.argentum.ws.ClienteWebService;

@Named
@ViewScoped
public class ArgentumBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	private String nomeMedia;
	private String nomeIndicadorBase;

	public ArgentumBean() {
		/* Recebe uma lista de negociações através do WebService */
		negociacoes = new ClienteWebService().getNegociacoes();
		
		geraGrafico();
	}

	public void geraGrafico() {
		/* Cria uma lista de candles com as negociacoes */
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);

		/* Dada uma lista de Candle, cria uma Série Temporal */
		SerieTemporal serie = new SerieTemporal(candles);

		/* Gera gráfico usando Série Temporal. Parametros: Serie Temporal, Começo, Fim, Modelo Gráfico */
		GeradorModeloGrafico geradorGrafico = new GeradorModeloGrafico(serie,
				2, serie.getUltimaPosicao(), modeloGrafico);
		
		/* Plota gráfico */
		geradorGrafico.plotaIndicador(defineIndicador());
		this.modeloGrafico = geradorGrafico.getModeloGrafico();
	}

	public Indicador defineIndicador() {
		if (nomeIndicadorBase == null || nomeMedia == null) {
			return new MediaMovelSimples(new IndicadorFechamento());
		}
		try {
			String pacote = "br.com.vinicius.argentum.indicadores.";
			Class<?> classeIndicadorBase = Class.forName(pacote
					+ nomeIndicadorBase);
			Indicador indicadorBase = (Indicador) classeIndicadorBase
					.newInstance();

			Class<?> classeMedia = Class.forName(pacote + nomeMedia);
			Constructor<?> construtorMedia = classeMedia
					.getConstructor(Indicador.class);
			Indicador indicador = (Indicador) construtorMedia
					.newInstance(indicadorBase);
			return indicador;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

}
