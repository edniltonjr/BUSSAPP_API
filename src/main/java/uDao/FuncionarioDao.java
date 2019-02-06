package uDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.BaseDao;
import entities.ClasseGenerica;
import entities.Funcionario;

public class FuncionarioDao extends BaseDao {

	public FuncionarioDao() {
		super();
	}

	public Boolean deleteOne(Funcionario f) throws SQLException {
		String sql = "DELETE FROM funcionarios WHERE id_funcionario = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, f.getId_funcionario());

		return ps.executeUpdate() > 0;
	}

	public Boolean insertOne(Funcionario f) throws SQLException {
		String sql = "INSERT INTO funcionarios(nome, cpf, id_cargo, id_filial) VALUE (?, ?, ?, ?);";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setString(1, f.getNome());
		ps.setString(2, f.getCpf());
		ps.setInt(3, f.getCargo().getId());
		ps.setInt(4, f.getFilial().getId());

		return ps.executeUpdate() > 0;
	}

	public Boolean updateOne(Funcionario f) throws SQLException {
		String sql = "UPDATE funcionarios SET nome = ?, cpf = ?, id_cargo = ?, id_filial = ? WHERE id_funcionario = ?;";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setString(1, f.getNome());
		ps.setString(2, f.getCpf());
		ps.setInt(3, f.getCargo().getId());
		ps.setInt(4, f.getFilial().getId());
		ps.setInt(5, f.getId_funcionario());

		return ps.executeUpdate() > 0;
	}

	public Funcionario findOne(Integer id_funcionario) throws SQLException {
		String sql = "SELECT * FROM funcionarios AS f " + "INNER JOIN cargos AS c ON c.id_cargo = f.id_cargo "
				+ "INNER JOIN filiais AS fi ON fi.id_filial = f.id_filial " + "WHERE id_funcionario = ?";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ps.setInt(1, id_funcionario);

		ResultSet rs = ps.executeQuery();

		Funcionario f = null;

		if (rs.next()) {
			f = new Funcionario();

			f.setId_funcionario(rs.getInt("id_funcionario"));
			f.setNome(rs.getString("nome"));
			f.setCpf(rs.getString("cpf"));
			f.getCargo().setId(rs.getInt("id_cargo"));
			f.getCargo().setNome(rs.getString("c.nome"));
			f.getFilial().setId(rs.getInt("id_filial"));
			f.getFilial().setNome(rs.getString("fi.nome"));

		}

		return f;
	}

	public List<Funcionario> findAll() throws SQLException {
		String sql = "SELECT * FROM funcionarios AS f " + "INNER JOIN cargos AS c ON c.id_cargo = f.id_cargo "
				+ "INNER JOIN filiais AS fi ON fi.id_filial = f.id_filial";

		PreparedStatement ps = getConnection().prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Funcionario> funcionarios = new ArrayList<>();

		while (rs.next()) {
			Funcionario f = new Funcionario();

			f.setId_funcionario(rs.getInt("id_funcionario"));
			f.setNome(rs.getString("nome"));
			f.setCpf(rs.getString("cpf"));
			f.getCargo().setId(rs.getInt("id_cargo"));
			f.getCargo().setNome(rs.getString("c.nome"));
			f.getFilial().setId(rs.getInt("id_filial"));
			f.getFilial().setNome(rs.getString("fi.nome"));

			funcionarios.add(f);
		}

		return funcionarios;
	}

	public class FilialDao extends BaseDao {
		public FilialDao() {
			super();
		}

		public List<ClasseGenerica> findAll() throws SQLException {
			String sql = "SELECT * FROM filiais;";

			PreparedStatement ps = getConnection().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<ClasseGenerica> filiais = new ArrayList<>();

			while (rs.next()) {
				ClasseGenerica filial = new ClasseGenerica();

				filial.setId(rs.getInt("id_filial"));
				filial.setNome(rs.getString("nome"));

				filiais.add(filial);
			}

			return filiais;
		}

		public Boolean insertOne(ClasseGenerica classeGenerica) throws SQLException {
			String sql = "INSERT INTO filiais(nome) VALUE (?);";

			PreparedStatement ps = getConnection().prepareStatement(sql);

			ps.setString(1, classeGenerica.getNome());

			return ps.executeUpdate() > 0;
		}

		public Boolean updateOne(ClasseGenerica classeGenerica) throws SQLException {
			String sql = "UPDATE filiais SET nome = ? WHERE id_filial = ?;";

			PreparedStatement ps = getConnection().prepareStatement(sql);

			ps.setString(1, classeGenerica.getNome());
			ps.setInt(2, classeGenerica.getId());

			return ps.executeUpdate() > 0;
		}

	}

	public class CargoDao extends BaseDao {
		public CargoDao() {
			super();
		}

		public List<ClasseGenerica> findAll() throws SQLException {
			String sql = "SELECT * FROM cargos;";

			PreparedStatement ps = getConnection().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<ClasseGenerica> cargos = new ArrayList<>();

			while (rs.next()) {
				ClasseGenerica cargo = new ClasseGenerica();

				cargo.setId(rs.getInt("id_cargo"));
				cargo.setNome(rs.getString("nome"));

				cargos.add(cargo);
			}

			return cargos;
		}

		public Boolean insertOne(ClasseGenerica classeGenerica) throws SQLException {
			String sql = "INSERT INTO cargos(nome) VALUE (?);";

			PreparedStatement ps = getConnection().prepareStatement(sql);

			ps.setString(1, classeGenerica.getNome());

			return ps.executeUpdate() > 0;
		}

		public Boolean updateOne(ClasseGenerica classeGenerica) throws SQLException {
			String sql = "UPDATE cargos SET nome = ? WHERE id_cargo = ?;";

			PreparedStatement ps = getConnection().prepareStatement(sql);

			ps.setString(1, classeGenerica.getNome());
			ps.setInt(2, classeGenerica.getId());

			return ps.executeUpdate() > 0;
		}
	}
}
