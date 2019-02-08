package uDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.BaseDao;
import entities.ClasseGenerica;
import entities.Motorista;

public class MotoristaDao extends BaseDao {

	public MotoristaDao() {
		super();
	}

	public Boolean deleteOne(Motorista m) throws SQLException {
		String sql = "DELETE FROM motoristas WHERE id_motorista = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, m.getId_motorista());

		return ps.executeUpdate() > 0;
	}

	public Boolean updateOne(Motorista m) throws SQLException {
		String sql = "UPDATE motoristas SET nome = ?, cpf = ?, rg = ?, id_tipo_cnh = ? WHERE id_motorista = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setString(1, m.getNome());
		ps.setString(2, m.getCpf());
		ps.setString(3, m.getRg());
		ps.setInt(4, m.getTipo_cnh().getId());
		ps.setInt(5, m.getId_motorista());

		return ps.executeUpdate() > 0;
	}

	public Boolean insertOne(Motorista m) throws SQLException {
		String sql = "INSERT INTO motoristas(nome, cpf, rg, id_tipo_cnh) VALUE (?, ?, ?, ?);";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setString(1, m.getNome());
		ps.setString(2, m.getCpf());
		ps.setString(3, m.getRg());
		ps.setInt(4, m.getTipo_cnh().getId());

		return ps.executeUpdate() > 0;
	}

	public Motorista findOne(Integer id_motorista) throws SQLException {
		String sql = "SELECT * FROM motoristas AS m " + "INNER JOIN tipos_cnh AS ti ON ti.id_tipo_cnh = m.id_tipo_cnh "
				+ "WHERE m.id_motorista = ?;"; // String a ser executada

		PreparedStatement ps = getConnection().prepareStatement(sql); // Preparar a string pra ligar os parametros

		ps.setInt(1, id_motorista); // Escolher qual index de parametro, e depois qual o parametro em si

		ResultSet rs = ps.executeQuery(); // Executar e receber o resultado da string query

		Motorista m = null; // declarar o corpo como null, a fim de se testar se houve resultados

		if (rs.next()) {
			m = new Motorista(); // se houve, dar um corpo, a fim de se evitar null pointer exception
			m.setId_motorista(rs.getInt("id_motorista")); // preencher todos os atributos utilizando getter and setter
			m.setNome(rs.getString("nome"));
			m.setCpf(rs.getString("cpf"));
			m.setRg(rs.getString("rg"));
			m.getTipo_cnh().setNome(rs.getString("ti.nome")); // se for informado a coluna incorreta ou inexistente,
																// ocorrerá
			m.getTipo_cnh().setId(rs.getInt("id_tipo_cnh")); // uma exceção
		}

		return m;
	}

	public List<Motorista> findAll() throws SQLException {
		String sql = "SELECT * FROM motoristas AS m " + "INNER JOIN tipos_cnh AS ti ON ti.id_tipo_cnh = m.id_tipo_cnh;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Motorista> motoristas = new ArrayList<>();

		while (rs.next()) {
			Motorista m = new Motorista();
			m.setId_motorista(rs.getInt("id_motorista"));
			m.setNome(rs.getString("nome"));
			m.setCpf(rs.getString("cpf"));
			m.setRg(rs.getString("rg"));
			m.getTipo_cnh().setNome(rs.getString("ti.nome"));
			m.getTipo_cnh().setId(rs.getInt("id_tipo_cnh"));

			motoristas.add(m);
		}

		return motoristas;
	}

	public class CnhDao extends BaseDao {
		public CnhDao() {
			super();
		}

		public List<ClasseGenerica> findAll() throws SQLException {
			String sql = "SELECT * FROM tipos_cnh;";

			PreparedStatement ps = getConnection().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<ClasseGenerica> cnhs = new ArrayList<>();

			while (rs.next()) {
				ClasseGenerica cnh = new ClasseGenerica();

				cnh.setId(rs.getInt("id_tipo_cnh"));
				cnh.setNome(rs.getString("nome"));

				cnhs.add(cnh);
			}

			return cnhs;
		}

		public Boolean insertOne(ClasseGenerica cnh) throws SQLException {
			String sql = "INSERT INTO tipos_cnh(nome) VALUE (?);";

			PreparedStatement ps = getConnection().prepareStatement(sql);

			ps.setString(1, cnh.getNome());

			return ps.executeUpdate() > 0;
		}

		public Boolean updateOne(ClasseGenerica cnh) throws SQLException {
			String sql = "UPDATE tipos_cnh SET nome = ? WHERE id_tipo_cnh = ?;";

			PreparedStatement ps = getConnection().prepareStatement(sql);

			ps.setString(1, cnh.getNome());
			ps.setInt(2, cnh.getId());

			return ps.executeUpdate() > 0;
		}
	}
}
