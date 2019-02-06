package entities;

public class ViagemConteudo {

	private Viagem viagem;
	private ClasseGenerica funcionario;

	public ViagemConteudo() {
		viagem = new Viagem();
		funcionario = new ClasseGenerica();
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public ClasseGenerica getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(ClasseGenerica funcionario) {
		this.funcionario = funcionario;
	}

}
