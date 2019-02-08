package services;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import entities.Viagem;
import uDao.ViagemDao;

@Path("/ws/viagem")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViagemService {
	private ViagemDao viagemDao;
	
	public ViagemService() {
		viagemDao = new ViagemDao();
	}
	
	@GET
	@Path("/todos")
	public List<Viagem> findAll() {
		try {
			return viagemDao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
