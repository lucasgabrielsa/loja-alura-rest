package br.com.alura.loja;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {
	
	private static HttpServer server;
	
	public static void paraServidor() {      
	        server.stop();
	}

	public static void inicializaServidor() {
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		URI uri = URI.create("http://localhost:8080/");
		server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		System.out.println("Servidor rodando");		
	}

}
