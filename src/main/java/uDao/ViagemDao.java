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

	public ViagemDao() {
		super();
	}

	public Boolean insertOne(Viagem v) throws SQLException {
		String sql = "INSERT INTO viagens(id_motorista, id_veiculo, tipo_viagem, data_viagem) VALUE (?, ?, ?, ?);";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, v.getMotorista().getId());
		ps.setInt(2, v.getVeiculo().getId());
		ps.setString(3, v.getTipo_viagem());
		errorCalendar(v.getData_viagem());
		Timestamp timestamp = new Timestamp(v.getData_viagem().getTimeInMillis());
		ps.setTimestamp(4, timestamp);

		return ps.executeUpdate() > 0;
	}

	public Boolean updateOne(Viagem v) throws SQLException {
		String sql = "UPDATE viagens SET id_motorista = ?, id_veiculo = ?, tipo_viagem = ?, data_viagem = ? WHERE id_viagem = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, v.getMotorista().getId());
		ps.setInt(2, v.getVeiculo().getId());
		ps.setString(3, v.getTipo_viagem());
		errorCalendar(v.getData_viagem());
		Timestamp timestamp = new Timestamp(v.getData_viagem().getTimeInMillis());
		ps.setTimestamp(4, timestamp);
		ps.setInt(5, v.getId_viagem());

		return ps.executeUpdate() > 0;
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
			v.getMotorista().setNome(rs.getString("m.nome"));
			v.getMotorista().setId(rs.getInt("id_motorista"));
			v.getVeiculo().setNome(rs.getString("ve.modelo"));
			v.getVeiculo().setId(rs.getInt("id_veiculo"));
			v.setTipo_viagem(rs.getString("tipo_viagem"));

			Timestamp t = rs.getTimestamp("data_viagem");
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(t.getTime());
			v.setData_viagem(c);

			viagens.add(v);
		}

		return viagens;
	}

	private void errorCalendar(Calendar c) {
		// c.set(Calendar.HOUR, c.get(Calendar.HOUR) - 2);
	}

	public Boolean insertPeopleViagem(ViagemConteudo vc) throws SQLException {
		String sql = "INSERT INTO viagens_conteudo(id_viagem, id_funcionario) VALUE (?, ?);";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, vc.getViagem().getId_viagem());
		ps.setInt(2, vc.getFuncionario().getId());

		return ps.executeUpdate() > 0;
	}

	public Boolean deletePeopleViagem(ViagemConteudo vc) throws SQLException {
		String sql = "DELETE FROM viagens_conteudo WHERE id_viagem = ? AND id_pessoa = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, vc.getViagem().getId_viagem());
		ps.setInt(2, vc.getFuncionario().getId());

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
			vc.getFuncionario().setId(rs.getInt("id_funcionario"));
			vc.getFuncionario().setNome(rs.getString("f.nome"));

			vc.getViagem().setId_viagem(rs.getInt("id_viagem"));
			vc.getViagem().getVeiculo().setId(rs.getInt("id_veiculo"));
			vc.getViagem().getVeiculo().setNome(rs.getString("vei.modelo"));
			vc.getViagem().getMotorista().setId(rs.getInt("id_motorista"));
			vc.getViagem().getMotorista().setNome(rs.getString("moto.nome"));
			vc.getViagem().setTipo_viagem(rs.getString("tipo_viagem"));
			Timestamp t = rs.getTimestamp("data_viagem");
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(t.getTime());
			vc.getViagem().setData_viagem(c);

			viagemConteudos.add(vc);

		}

		return viagemConteudos;
	}

}
