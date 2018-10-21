package br.com.restful.resource;

import java.text.ParseException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.restful.controller.AtivoController;
import br.com.restful.controller.FuncionarioController;
import br.com.restful.model.Funcionario;

@Path("/funcionario")
public class FuncionarioResource {

	@GET
	@Path("/listarFuncionarios")
	@Produces("application/json")
	public Response listarFuncionarios(){
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		FuncionarioController funcionarioController = new FuncionarioController();
		funcionarios = funcionarioController.listarFuncionarios();
		String jsonFuncionarios = new Gson().toJson(funcionarios);
		
		return Response.status(200).entity(jsonFuncionarios).build();
	}
	
	@GET
	@Path("/listarFuncionario")
	@Produces("application/json")
	public Response listarFuncionario(@QueryParam("id") long id){
		Funcionario funcionario = null;
		FuncionarioController funcionarioController = new FuncionarioController();
		funcionario = funcionarioController.listarFuncionario(id);
		
		String jsonFuncionario = new Gson().toJson(funcionario);
		return Response.status(200).entity(jsonFuncionario).build();
	}
	
	@POST
	@Path("/cadastrarFuncionario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarFuncionario(String funcionarioJson) throws ParseException{
		
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
		Funcionario funcionario = gson.fromJson(funcionarioJson, Funcionario.class);
		
		FuncionarioController funcionarioController = new FuncionarioController();
		if(funcionarioController.cadastrarFuncionario(funcionario)){
			return Response.ok().build();
		}else{
			return Response.serverError().build();
		}
		
	}
	
	@POST
	@Path("/alterarFuncionario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterarFuncionario(String funcionarioJson) throws ParseException{
		
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
		Funcionario funcionario = gson.fromJson(funcionarioJson, Funcionario.class);
		
		FuncionarioController funcionarioController = new FuncionarioController();
		if(funcionarioController.alterarFuncionario(funcionario)){
			return Response.ok().build();
		}else{
			return Response.serverError().build();
		}
	}
	
	@POST
	@Path("/excluirFuncionario")
	@Consumes("application/json")
	public Response excluirFuncionario(@QueryParam("id") long id){
		if(id > 0) {
			if(new FuncionarioController().excluirFuncionario(id)){
				return Response.ok().build();
			}else{
				return Response.serverError().build();
			}
		}else {
			return Response.status(400).build();
		}
	}
}
