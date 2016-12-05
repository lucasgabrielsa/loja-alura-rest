package br.com.alura.loja;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ProjetoTest {

	HttpServer server;
	
	@Before
	public void inicia() throws IOException {
		server = Servidor.inicializaServidor();
	}
	
	@Test
	public void testaProjeto1() {
		Client client = ClientBuilder.newClient();
				
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/projetos/2").request().get(String.class);
		
		System.out.println(conteudo);
		Assert.assertTrue(conteudo.contains("Alura"));
	}
	
	@After
	public void termino() {
		server.stop();
	}
}
