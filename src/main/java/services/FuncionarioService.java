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

import entities.Funcionario;
import uDao.FuncionarioDao;

@Path("/ws/funcionario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionarioService {
	private FuncionarioDao funcionarioDao;

	public FuncionarioService() {
		funcionarioDao = new FuncionarioDao();
	}

	@GET
	@Path("/{id_funcionario}/busca")
	public Funcionario findOne(@PathParam("id_funcionario") Integer id_funcionario) {
		try {
			return funcionarioDao.findOne(id_funcionario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@GET
	@Path("/todos")
	public List<Funcionario> findAll() {
		try {
			return funcionarioDao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@POST
	@Path("/inserir")
	public Boolean insertOne(Funcionario funcionario) { // você só consegue recuperar classes, se for um Integer, String,
													// qualqeur tipo primitivo
		// vai dar erro, se for algumm tipo primitivo faz pela URL
		try {
			return funcionarioDao.insertOne(funcionario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
