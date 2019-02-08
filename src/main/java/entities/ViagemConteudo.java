package entities;

public class ViagemConteudo {

	private Viagem viagem;
	private Funcionario funcionario;

	public ViagemConteudo() {
		viagem = new Viagem();
		funcionario = new Funcionario();
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
