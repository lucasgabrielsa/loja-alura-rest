package br.com.alura.loja.resource;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.XStreamUtil;


@Path("carrinhos")
public class CarrinhoResource {
	
//	@GET
//	@Produces(MediaType.APPLICATION_XML)
//	public Map<Long, Carrinho> busca(){		
//		//XStream xstream = XStreamUtil.getXStream();					
//		//return xstream.toXML(new CarrinhoDAO().getCarrinhos());
//		return new CarrinhoDAO().getCarrinhos();
//	}
	
	@Path("{idCarrinho}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Carrinho busca(@PathParam("idCarrinho") long idCarrinho){		
		Carrinho carrinho = new CarrinhoDAO().busca(idCarrinho);
		return carrinho;	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Carrinho carrinho) {
		//XStream xstream = XStreamUtil.getXStream();					
		//Carrinho carrinho = (Carrinho) xstream.fromXML(conteudo);		
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/carrinhos/"+ carrinho.getId());
		return Response.created(uri).build();		
	}
	
	@Path("{id}/produtos/{produtoId}")
	@PUT
	public Response atualizaProduto(Produto produto, @PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		//XStream xstream = XStreamUtil.getXStream();
		//Produto produto = (Produto) xstream.fromXML(conteudo);
		carrinho.troca(produto);
		return Response.ok().build();
	}
	
	@Path("{id}/produtos/{produtoId}/quantidade")
	@PUT
	public Response atualizaQuantidade(Produto produto, @PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);		
		carrinho.trocaQuantidade(produto);
		return Response.ok().build();
	}
	
	
	@Path("{id}/produtos/{produtoId}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String verProduto(@PathParam("id") long id, @PathParam("produtoId") int produtoId) {
		System.out.println("entrou aqui");
		Carrinho carrinho = new CarrinhoDAO().busca(id);		
		Produto produto = carrinho.getProdutoById(produtoId);	
		return produto.toXML();
	}
	
	
	
	@Path("{id}/produtos/{produtoId}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		new CarrinhoDAO().busca(id).remove(produtoId);
		return Response.ok().build();
	}
	
	@Path("{id}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id) {
		new CarrinhoDAO().remove(id);
		return Response.ok().build();
	}

}
