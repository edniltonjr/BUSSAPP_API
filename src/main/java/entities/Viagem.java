package entities;

import java.util.Calendar;

public class Viagem {

	private Integer id_viagem;
	private Motorista motorista;
	private Veiculo veiculo;
	private String tipo_viagem;
	private Calendar data_viagem;

	public Viagem() {
		motorista = new Motorista();
		veiculo = new Veiculo();
		data_viagem = Calendar.getInstance();
	}

	public Integer getId_viagem() {
		return id_viagem;
	}

	public void setId_viagem(Integer id_viagem) {
		this.id_viagem = id_viagem;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getTipo_viagem() {
		return tipo_viagem;
	}

	public void setTipo_viagem(String tipo_viagem) {
		this.tipo_viagem = tipo_viagem;
	}

	public Calendar getData_viagem() {
		return data_viagem;
	}

	public void setData_viagem(Calendar data_viagem) {
		this.data_viagem = data_viagem;
	}

}
