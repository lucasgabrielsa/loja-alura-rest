package br.com.alura.loja.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.alura.loja.modelo.Projeto;

public class ProjetoDAO {
	
	private static Map<Long, Projeto> banco = new HashMap();
	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		banco.put(1l, new Projeto("Minha loja", 1l, 2014));
        banco.put(2l, new Projeto("Alura", 2l, 2012));        
	}
	
	public void adiciona(Projeto projeto) {
		long id = contador.incrementAndGet();
		projeto.setId(id);
		banco.put(id, projeto);
	}
	
	public Projeto busca(long l) {
		return banco.get(l);
	}
	
	public void remove(long id) {
		banco.remove(id);
	}
	
	public Map<Long, Projeto> getProjetos() {
		return banco;
	}
	

}
