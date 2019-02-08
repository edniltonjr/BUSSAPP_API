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

import entities.Viagem;
import entities.ViagemConteudo;
import uDao.ViagemDao;

@Path("/ws/viagem_conteudo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViagemConteudoService {

	private ViagemDao viagemDao;

	public ViagemConteudoService() {
		viagemDao = new ViagemDao();
	}

	@POST
	@Path("/{id_viagem}/inserir")
	public Boolean inserirFuncionariosViagem(List<Integer> pessoas, @PathParam("id_viagem") Integer idViagem) {
		for (Integer p : pessoas) {
			try {

				ViagemConteudo vc = new ViagemConteudo();
				vc.getFuncionario().setId_funcionario(p);
				vc.getViagem().setId_viagem(idViagem);

				viagemDao.insertPeopleViagem(vc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	@GET
	@Path("/{id_viagem}/todos")
	public List<ViagemConteudo> buscarFuncionariosViagem(@PathParam("id_viagem") Integer idViagem) {
		try {
			Viagem viagem = new Viagem();
			viagem.setId_viagem(idViagem);
			return viagemDao.findAllViagem(viagem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
