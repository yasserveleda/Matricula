package br.com.restful.resource;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.restful.controller.DashboardController;

@Path("/dashboard")
public class DashboardResource {
	
	@GET
	@Path("/contadorAtivos")
	@Produces("application/json")
	public Response contadorAtivos(){
		ArrayList<HashMap> contadorAtivos = new ArrayList<>();
		DashboardController dashboardController = new DashboardController();
		contadorAtivos = dashboardController.contadorAtivos();
		String jsonContadorAtivos = new Gson().toJson(contadorAtivos);
		
		return Response.status(200).entity(jsonContadorAtivos).build();
	}

}
