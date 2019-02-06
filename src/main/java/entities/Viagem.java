package entities;

import java.util.Calendar;

public class Viagem {

	private Integer id_viagem;
	private ClasseGenerica motorista;
	private ClasseGenerica veiculo;
	private String tipo_viagem;
	private Calendar data_viagem;

	public Viagem() {
		motorista = new ClasseGenerica();
		veiculo = new ClasseGenerica();
		data_viagem = Calendar.getInstance();
	}

	public Integer getId_viagem() {
		return id_viagem;
	}

	public void setId_viagem(Integer id_viagem) {
		this.id_viagem = id_viagem;
	}

	public ClasseGenerica getMotorista() {
		return motorista;
	}

	public void setMotorista(ClasseGenerica motorista) {
		this.motorista = motorista;
	}

	public ClasseGenerica getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(ClasseGenerica veiculo) {
		this.veiculo = veiculo;
	}

	public Calendar getData_viagem() {
		return data_viagem;
	}

	public void setData_viagem(Calendar data_viagem) {
		this.data_viagem = data_viagem;
	}

	public String getTipo_viagem() {
		return tipo_viagem;
	}

	public void setTipo_viagem(String tipo_viagem) {
		this.tipo_viagem = tipo_viagem;
	}

}
