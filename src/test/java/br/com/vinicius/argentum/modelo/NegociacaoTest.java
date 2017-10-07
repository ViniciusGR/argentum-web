package br.com.vinicius.argentum.modelo;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import br.com.vinicius.argentum.modelo.Negociacao;

public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEImutavel() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao negociacao = new Negociacao(10, 5, c);
		negociacao.getData().set(Calendar.DAY_OF_MONTH, 20);
		assertEquals(15, negociacao.getData().get(Calendar.DAY_OF_MONTH));
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(10, 5, null);
	}

	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();
		Negociacao n1 = new Negociacao(40.0, 100, agora);
		assertTrue(n1.isMesmoDia(mesmoMomento));
	}

	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {
		//GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);

		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		assertTrue(negociacao.isMesmoDia(tarde));
	}

	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {
		Calendar data1 = new GregorianCalendar(2011, 1, 20, 8, 30);
		Calendar data2 = new GregorianCalendar(2011, 2, 20, 8, 30);
		Negociacao negociacao = new Negociacao(40.0, 100, data1);
		assertFalse(negociacao.isMesmoDia(data2));
	}

	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia() {
		Calendar data1 = new GregorianCalendar(2011, 1, 20, 8, 30);
		Calendar data2 = new GregorianCalendar(2012, 1, 20, 8, 30);
		Negociacao negociacao = new Negociacao(40.0, 100, data1);
		assertFalse(negociacao.isMesmoDia(data2));
	}

}
