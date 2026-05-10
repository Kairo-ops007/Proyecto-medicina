package medicina;

public class Habitacion {
	private Paciente paciente;
	private Medico medico;
	private Persona acompanyante;
	public Habitacion(Paciente paciente, Medico medico) {
		this.paciente = paciente;
		this.medico = medico;
	}
	public Habitacion(Paciente paciente, Medico medico, Persona acompanyante) {
		this.paciente = paciente;
		this.medico = medico;
		this.acompanyante = acompanyante;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Persona getAcompanyante() {
		return acompanyante;
	}
	public void setAcompanyante(Persona acompanyante) {
		this.acompanyante = acompanyante;
	}
	
}	
