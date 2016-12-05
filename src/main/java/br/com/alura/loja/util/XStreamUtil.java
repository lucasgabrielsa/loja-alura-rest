package br.com.alura.loja.util;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;

public abstract class XStreamUtil {
	
	private static XStream xstream;
	
	public static XStream getXStream() {
				
				
		if(xstream == null) {
			System.out.println("Criado um Xstream");
			xstream = new XStream(); 
		}
		
		xstream.alias("carrinho", Carrinho.class);
		xstream.alias("produto", Produto.class);
		xstream.alias("projeto", Projeto.class);
		
		return xstream;		
	}
	
}
