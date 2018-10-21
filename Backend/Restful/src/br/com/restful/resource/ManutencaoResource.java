package br.com.restful.resource;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.restful.controller.ManutencaoController;
import br.com.restful.dao.AtivoDAO;
import br.com.restful.model.Manutencao;

@Path("/manutencao")
public class ManutencaoResource {
	
	@POST
	@Path("/cadastrarManutencao")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarManutencao(String manutencaoJson) throws ParseException{
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Manutencao manutencao = gson.fromJson(manutencaoJson, Manutencao.class);
		
		ManutencaoController manutencaoController = new ManutencaoController();
		if(manutencaoController.validador(manutencao)) {
			if(manutencaoController.cadastrarManutencao(manutencao)){
				AtivoDAO ativoDAO = new AtivoDAO();
				if(ativoDAO.setManutencao(manutencao)) {
					return Response.ok().build();
				}else {
					return Response.serverError().build();
				}
			}else{
				return Response.serverError().build();
			}
		}else {
			return Response.status(400).build();
		}
		
	}
}
