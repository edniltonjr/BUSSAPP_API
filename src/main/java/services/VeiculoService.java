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
import entities.Veiculo;
import uDao.VeiculoDao;

@Path("/ws/veiculo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VeiculoService {
	private VeiculoDao veiculoDao;
	
	public VeiculoService() {
		veiculoDao = new VeiculoDao();
	}
	
	@GET
	@Path("/{id}/busca")
	public Veiculo findOne(@PathParam("id") Integer id) {
		try {
			return veiculoDao.findOne(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@GET
	@Path("/todos")
	public List<Veiculo> findAll() {
		try {
			return veiculoDao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	@POST
	@Path("/inserir")
	public Boolean insertOne(Veiculo veiculo) {
		try {
			return veiculoDao.insertOne(veiculo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
