package uDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.BaseDao;
import entities.Veiculo;

public class VeiculoDao extends BaseDao {

	public VeiculoDao() {
		super();
	}

	public Veiculo buscaVeiculo(Integer id_veiculo) throws SQLException {

		String sql = "SELECT * FROM veiculos WHERE id_veiculo = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, id_veiculo);

		ResultSet rs = ps.executeQuery();

		Veiculo v = null;

		if (rs.next()) {

			v = new Veiculo();
			v.setId_veiculo(rs.getInt("id_veiculo"));
			v.setModelo(rs.getString("modelo"));
			v.setPlaca(rs.getString("placa"));
		}

		return v;

	}

	public Boolean updateOne(Veiculo v) throws SQLException {
		String sql = "UPDATE veiculos SET modelo = ?, placa = ? WHERE id_veiculo = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setString(1, v.getModelo());
		ps.setString(2, v.getPlaca());
		ps.setInt(3, v.getId_veiculo());

		return ps.executeUpdate() > 0;
	}

	public Boolean insertOne(Veiculo v) throws SQLException {
		String sql = "INSERT INTO veiculos(modelo, placa) VALUE (?, ?);";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setString(1, v.getModelo());
		ps.setString(2, v.getPlaca());

		return ps.executeUpdate() > 0;
	}

	public List<Veiculo> findAll() throws SQLException {
		String sql = "SELECT * FROM veiculos;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Veiculo> veiculos = new ArrayList<>();

		while (rs.next()) {
			Veiculo v = new Veiculo();
			v.setId_veiculo(rs.getInt("id_veiculo"));
			v.setModelo(rs.getString("modelo"));
			v.setPlaca(rs.getString("placa"));
			veiculos.add(v);
		}

		return veiculos;
	}

}
