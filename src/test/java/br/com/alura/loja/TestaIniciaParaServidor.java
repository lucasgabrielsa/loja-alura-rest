package br.com.alura.loja;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;


public class TestaIniciaParaServidor {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("inicializa servidor");
		HttpServer server = Servidor.inicializaServidor();
				
		System.out.println("Parando servidor");
		server.stop();
		
	}

}
