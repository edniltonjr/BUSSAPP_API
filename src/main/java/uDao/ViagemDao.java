package uDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import JDBC.BaseDao;
import entities.Viagem;
import entities.ViagemConteudo;

public class ViagemDao extends BaseDao {

	private MotoristaDao motoristaDao;
	private VeiculoDao veiculoDao;
	private FuncionarioDao funcionarioDao;

	public ViagemDao() {
		super();
		motoristaDao = new MotoristaDao();
		veiculoDao = new VeiculoDao();
		funcionarioDao = new FuncionarioDao();
	}

	public Boolean insertOne(Viagem v) throws SQLException {
		String sql = "INSERT INTO viagens(id_motorista, id_veiculo, tipo_viagem, data_viagem) VALUE (?, ?, ?, ?);";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, v.getMotorista().getId_motorista());
		ps.setInt(2, v.getVeiculo().getId_veiculo());
		ps.setString(3, v.getTipo_viagem());
		ps.setTimestamp(4, getTimestamp(v.getData_viagem()));

		return ps.executeUpdate() > 0;
	}

	public Boolean updateOne(Viagem v) throws SQLException {
		String sql = "UPDATE viagens SET id_motorista = ?, id_veiculo = ?, tipo_viagem = ?, data_viagem = ? WHERE id_viagem = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, v.getMotorista().getId_motorista());
		ps.setInt(2, v.getVeiculo().getId_veiculo());
		ps.setString(3, v.getTipo_viagem());
		ps.setTimestamp(4, getTimestamp(v.getData_viagem()));
		ps.setInt(5, v.getId_viagem());

		return ps.executeUpdate() > 0;
	}

	public Viagem findOne(Integer id_viagem) throws SQLException {
		String sql = "SELECT * FROM viagens AS v " + "INNER JOIN motoristas AS m ON m.id_motorista = v.id_motorista "
				+ "INNER JOIN veiculos AS ve ON ve.id_veiculo = v.id_veiculo WHERE v.id_viagem = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, id_viagem);

		ResultSet rs = ps.executeQuery();

		Viagem v = null;

		if (rs.next()) {
			v = new Viagem();
			v.setId_viagem(rs.getInt("id_viagem"));
			v.setMotorista(motoristaDao.findOne(rs.getInt("id_motorista")));
			v.setVeiculo(veiculoDao.findOne(rs.getInt("id_veiculo")));
			v.setTipo_viagem(rs.getString("tipo_viagem"));
			v.setData_viagem(getCalendar(rs.getTimestamp("data_viagem")));

		}

		return v;
	}

	public List<Viagem> findAll() throws SQLException {
		String sql = "SELECT * FROM viagens AS v " + "INNER JOIN motoristas AS m ON m.id_motorista = v.id_motorista "
				+ "INNER JOIN veiculos AS ve ON ve.id_veiculo = v.id_veiculo ORDER BY v.id_viagem;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Viagem> viagens = new ArrayList<>();

		while (rs.next()) {
			Viagem v = new Viagem();

			v.setId_viagem(rs.getInt("id_viagem"));
			v.setMotorista(motoristaDao.findOne(rs.getInt("id_motorista")));
			v.setVeiculo(veiculoDao.findOne(rs.getInt("id_veiculo")));
			v.setTipo_viagem(rs.getString("tipo_viagem"));
			v.setData_viagem(getCalendar(rs.getTimestamp("data_viagem")));

			viagens.add(v);
		}

		return viagens;
	}

	public Boolean insertPeopleViagem(ViagemConteudo vc) throws SQLException {
		String sql = "INSERT INTO viagens_conteudo(id_viagem, id_funcionario) VALUE (?, ?);";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, vc.getViagem().getId_viagem());
		ps.setInt(2, vc.getFuncionario().getId_funcionario());

		return ps.executeUpdate() > 0;
	}

	public Boolean deletePeopleViagem(ViagemConteudo vc) throws SQLException {
		String sql = "DELETE FROM viagens_conteudo WHERE id_viagem = ? AND id_funcionario = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, vc.getViagem().getId_viagem());
		ps.setInt(2, vc.getFuncionario().getId_funcionario());

		return ps.executeUpdate() > 0;
	}

	public List<ViagemConteudo> findAllViagem(Viagem viagem) throws SQLException {
		String sql = "SELECT * FROM viagens_conteudo AS vc " + "INNER JOIN viagens AS v ON v.id_viagem = vc.id_viagem "
				+ "INNER JOIN funcionarios AS f ON f.id_funcionario = vc.id_funcionario "
				+ "INNER JOIN motoristas AS moto ON moto.id_motorista = v.id_motorista "
				+ "INNER JOIN veiculos AS vei ON vei.id_veiculo = v.id_veiculo " + "WHERE vc.id_viagem = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, viagem.getId_viagem());

		ResultSet rs = ps.executeQuery();

		List<ViagemConteudo> viagemConteudos = new ArrayList<>();

		while (rs.next()) {
			ViagemConteudo vc = new ViagemConteudo();
			vc.setFuncionario(funcionarioDao.findOne(rs.getInt("id_funcionario")));
			vc.setViagem(findOne(rs.getInt("id_viagem")));

			viagemConteudos.add(vc);

		}

		return viagemConteudos;
	}

	public Calendar getCalendar(Timestamp timestamp) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timestamp.getTime());
		return c;
	}

	public Timestamp getTimestamp(Calendar calendar) {
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		return timestamp;
	}

}
