package medicina;

import java.time.LocalDate;
import java.time.LocalTime;

public class Urgencia extends Cita implements Mostrable {
	private NivelImportancia nivelGravedad;
	private boolean ingesoNecesario;
	
	public Urgencia(Paciente p, Medico m ,LocalDate fecha,LocalTime hora,  String motivo, boolean atendida, NivelImportancia nivel, 
			boolean ingresoNecesario) {
		super(p, m, fecha, hora,motivo, atendida);
		this.nivelGravedad = nivel;
		setIngesoNecesario();
		
	}
	
	
	private boolean importancia () {
		return (this.nivelGravedad == NivelImportancia.URGENTE || this.nivelGravedad == NivelImportancia.GRAVE);
	}


	public NivelImportancia getNivelGravedad() {
		return nivelGravedad;
	}


	public void setNivelGravedad(NivelImportancia nivelGravedad) {
		this.nivelGravedad = nivelGravedad;
	}


	public boolean isIngesoNecesario() {
		return ingesoNecesario;
	}


	public void setIngesoNecesario() {
		this.ingesoNecesario = importancia();
	}


	@Override
	public String toString() {
		return super.toString()+
				"URGENCIA - NIVEL:"+ nivelGravedad + "\n"+
				"Requiere ingreso:"+ (importancia() ? "Si": "No");
	}


	@Override
	public void mostrarResumen() {
		
		super.mostrarResumen();
		System.out.println("Nivel de urgencia : "+ this.nivelGravedad);
	}
	
	
}
