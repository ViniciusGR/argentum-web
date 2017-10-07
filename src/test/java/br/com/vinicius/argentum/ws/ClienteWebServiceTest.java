package br.com.vinicius.argentum.ws;

import org.junit.Test;

import br.com.vinicius.argentum.modelo.Negociacao;

public class ClienteWebServiceTest {

	@Test
	public void testaCliente() {
		ClienteWebService cliente = new ClienteWebService();
		for (Negociacao negociacao : cliente.getNegociacoes()) {
			System.out.println(negociacao);
		}
	}

}
