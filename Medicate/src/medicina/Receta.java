package medicina;

import java.time.LocalDate;

public class Receta {
	private Medico medico;
	private Paciente paciente;
	private String medicamento;
	private String dosis;
	private int duracionDias;
	private LocalDate fechaEntrega;
	private boolean recogida;


	public Receta(Medico medico, Paciente paciente, String medicamento, String dosis, int duracionDias,
			LocalDate fechaEntrega, boolean recogida) {

		this.medico = medico;
		this.paciente = paciente;
		setMedicamento(medicamento);
		setDosis(dosis);
		setDuracionDias(duracionDias);
		this.fechaEntrega = fechaEntrega;
		this.recogida = recogida;
		
	}


	public Medico getMedico() {
		return medico;
	}


	public void setMedico(Medico medico) {
		this.medico = medico;
	}


	public Paciente getPaciente() {
		return paciente;
	}


	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


	public String getMedicamento() {
		return medicamento;
	}


	public void setMedicamento(String medicamento) {
		this.medicamento = FuncionesAux.ayudaSetter(medicamento, "medicamento por validar");
	}


	public String getDosis() {
		return dosis;
	}


	public void setDosis(String dosis) {
		this.dosis = FuncionesAux.ayudaSetter(dosis, "dosis");
	}


	public int getDuracionDias() {
		return duracionDias;
	}


	public void setDuracionDias(int duracionDias) {
		if (duracionDias<0) {
			System.err.println("ERROR: La duracion de dias no puede ser negativa");
			this.duracionDias=0; 
		} else {
			this.duracionDias = duracionDias;
		}
	}


	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}


	public boolean isRecogida() {
		return recogida;
	}


	public void setRecogida(boolean recogida) {
		this.recogida = recogida;
	}


	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Receta)) return false;
		if (this == obj) return true;

		Receta otraReceta = (Receta) obj;
		if (otraReceta.getMedicamento().equals(this.getMedicamento())      
				&& 
				otraReceta.getDosis().equals(this.getDosis())
				&&
				otraReceta.getPaciente().equals(this.getPaciente())
				) {
			return true;
		}


		return false;
	}


	@Override
	public String toString() {

		return "Medicamento: "+getMedicamento()+"\n"+
				"Dosis: "+getDosis()+"\n"+
				"Duracion(dias): "+getDuracionDias()+"\n"+
				"Recogida: "+ (recogida ? "Si": "No")
				;
	}


}
