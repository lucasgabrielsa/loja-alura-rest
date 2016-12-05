package br.com.alura.loja;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import junit.framework.Assert;

public class TestaConverteCarrinhoXML {
	
	HttpServer server;
	private Client client;
	
	@Before
	public void antes() throws IOException {
		server = Servidor.inicializaServidor();
	}
	
	
	@Test
	public void testaConverteCarrinhoXML() {
		Carrinho carrinho = criaCarrinho();		
		client = ClientBuilder.newClient();			
		WebTarget target = client.target("http://localhost:8080");
		
		Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);
		Response response = target.path("/carrinhos/1").request().post(entity);
		Assert.assertEquals(201, response.getStatus());	
		
		String location = response.getHeaderString("Location");
		Carrinho carrinhoCarregado = client.target(location).request().get(Carrinho.class);
		Assert.assertTrue(carrinhoCarregado.getProdutos().get(0).equals("Tablet"));		
	}


	private Carrinho criaCarrinho() {
		Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");        
		return carrinho;
	}
	
	@After
	public void depois() {
		server.stop();
	}
	
	
	

}
