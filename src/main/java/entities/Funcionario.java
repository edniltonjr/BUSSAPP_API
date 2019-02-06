package entities;

public class Funcionario {

	private Integer id_funcionario;
	private String nome;
	private String cpf;
	private ClasseGenerica cargo;
	private ClasseGenerica filial;

	public Funcionario() {
		cargo = new ClasseGenerica();
		filial = new ClasseGenerica();
	}

	public Integer getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ClasseGenerica getCargo() {
		return cargo;
	}

	public void setCargo(ClasseGenerica cargo) {
		this.cargo = cargo;
	}

	public ClasseGenerica getFilial() {
		return filial;
	}

	public void setFilial(ClasseGenerica filial) {
		this.filial = filial;
	}

}
