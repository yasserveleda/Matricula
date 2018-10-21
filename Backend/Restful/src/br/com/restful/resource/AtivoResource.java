package br.com.restful.resource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.restful.controller.AtivoController;
import br.com.restful.model.Ativo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Path("/ativo")
public class AtivoResource {

	@GET
	@Path("/listarAtivos")
	@Produces("application/json")
	public Response listarAtivos(){
		ArrayList<Ativo> ativos = new ArrayList<Ativo>();
		AtivoController ativoController = new AtivoController();
		ativos = ativoController.listarAtivos();
		String jsonAtivos = new Gson().toJson(ativos);
		
		return Response.status(200).entity(jsonAtivos).build();
	}
	
	/*@GET
	@Path("/listarAtivo")
	@Produces("application/json")
	public Response listarAtivo(@QueryParam("id") long id){
		Ativo ativo = null;
		AtivoController ativoController = new AtivoController();
		ativo = ativoController.listarAtivo(id);
		
		String jsonAtivo = new Gson().toJson(ativo);
		return Response.status(200).entity(jsonAtivo).build();
	}*/
	
	@GET
	@Path("/listarAtivo")
	@Produces("application/json")
	public Response listarAtivo(String ativoJson){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Ativo ativo = gson.fromJson(ativoJson, Ativo.class);
		
		ArrayList<Ativo> ativos = new ArrayList<Ativo>();
		AtivoController ativoController = new AtivoController();
		
		ativos = ativoController.listarAtivo(ativo);
		
		String jsonAtivo = new Gson().toJson(ativos);
		return Response.status(200).entity(jsonAtivo).build();
	}
	
	@POST
	@Path("/cadastrarAtivo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarAtivo(String ativoJson) throws ParseException{
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Ativo ativo = gson.fromJson(ativoJson, Ativo.class);
		
		AtivoController ativoController = new AtivoController();
		if(ativoController.validador(ativo)) {
			if(ativoController.cadastrarAtivo(ativo)){
				return Response.ok().build();
			}else{
				return Response.serverError().build();
			}
		}else {
			return Response.status(400).build();
		}
		
	}
	
	@POST
	@Path("/alterarAtivo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterarAtivo(String ativoJson) throws ParseException{
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Ativo ativo = gson.fromJson(ativoJson, Ativo.class);
		
		AtivoController ativoController = new AtivoController();
		if(ativoController.validador(ativo)) {
			if(ativoController.alterarAtivo(ativo)){
				return Response.ok().build();
			}else{
				return Response.serverError().build();
			}
		}else {
			return Response.status(400).build();
		}
	}
	
	@POST
	@Path("/excluirAtivo")
	@Consumes("application/json")
	public Response excluirAtivo(@QueryParam("id") long id){
		if(id > 0) {
			if(new AtivoController().excluirAtivo(id)){
				return Response.ok().build();
			}else{
				return Response.serverError().build();
			}
		}else {
			return Response.status(400).build();
		}
	}
}
