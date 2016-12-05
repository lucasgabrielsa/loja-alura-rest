package br.com.alura.loja.resource;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;
import br.com.alura.loja.util.XStreamUtil;

@Path("projetos")
public class ProjetoResource {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca() {		
		Map<Long, Projeto> projetos = new ProjetoDAO().getProjetos();
		XStream xstream = XStreamUtil.getXStream();
		return xstream.toXML(projetos);
	}
	
	@Path("{idProjeto}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("idProjeto") long idProjeto) {		
		Projeto projeto =  new ProjetoDAO().busca(idProjeto);
		System.out.println(projeto.getNome());
		return projeto.toXML();		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {
		XStream xstream = XStreamUtil.getXStream();
		Projeto projeto = (Projeto) xstream.fromXML(conteudo);
		new ProjetoDAO().adiciona(projeto);
		URI uri = URI.create("/carrinhos/"+ projeto.getId());
		return Response.created(uri).build();		
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}")
	public Response remove(@PathParam("id") long id) {
		new ProjetoDAO().remove(id);
		return Response.ok().build();
	}

}
