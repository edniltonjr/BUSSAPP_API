package services;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.Motorista;
import uDao.MotoristaDao;

@Path("/ws/motorista")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MotoristaService {
	private MotoristaDao motoristaDao;

	public MotoristaService() {
		motoristaDao = new MotoristaDao();
	}

	@GET
	@Path("/{cpf}/busca")
	public Motorista findOne(@PathParam("cpf") String cpf) {
		try {
			return motoristaDao.findOne(cpf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@GET
	@Path("/todos")
	public List<Motorista> findAll() {
		try {
			return motoristaDao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@POST
	@Path("/inserir")
	public Boolean insertOne(Motorista motorista) { // você só consegue recuperar classes, se for um Integer, String, qualqeur tipo primitivo
		//vai dar erro, se for algumm tipo primitivo faz pela URL
		try {
			return motoristaDao.insertOne(motorista);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
