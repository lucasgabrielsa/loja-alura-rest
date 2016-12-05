package br.com.alura.loja;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Carrinho;
import junit.framework.Assert;

public class ClientTest {
	
	HttpServer server;
	private Client client;
	private WebTarget target;
	
	@Before
	public void before() throws IOException {
		server = Servidor.inicializaServidor();
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		this.client = ClientBuilder.newClient(config);
		this.target = client.target("http://localhost:8080");
		
	}
	
	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);		
		//Assert.assertTrue(carrinho.getP.contains("Vergueiro"));				
	}
	
	@After
	public void after() {
		server.stop();
	}

}
