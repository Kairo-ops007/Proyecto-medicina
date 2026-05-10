package medicina;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Medico extends Persona implements Mostrable {
	//RESEÑAS
	protected ArrayList<TipoMedico> especialidades;
	protected int numeroMedico;
	protected int añosExp;
	protected HashMap <LocalDate,Cita> citasMedico;
	private static int cont=1;
	public Medico(String nombre, String apellido1, String apellido2, int x, int y, String domicilio, String dni,
			int edad, String genero, ArrayList<TipoMedico> especialidades, int añosExp, HashMap<LocalDate, Cita> citasMedico) {
	
		super(nombre, apellido1, apellido2, x, y, domicilio, dni, edad, genero);
		
		setEspecialidades(especialidades);
		setNumeroMedico(cont++);
		setAñosExp(añosExp);
		this.citasMedico = citasMedico;
		
	}

	public int getAñosExp() {
		return añosExp;
	}


	public void setAñosExp(int añosExp) {
		if (añosExp < 0) {
			System.err.println("ERROR: Los años de experiencia no pueden ser negativos");
			this.añosExp = 0;
		} else {
			this.añosExp = añosExp;
		}
	}


	public ArrayList<TipoMedico> getEspecialidades() {
		return especialidades;
	}

	
	public void setEspecialidades(ArrayList<TipoMedico> especialidad) {
		this.especialidades= especialidad;
	}

	
	public int getNumeroMedico() {
	
		return numeroMedico;
	
	}


	public void setNumeroMedico(int numero_medico) {

		if (numero_medico < 0) {
			System.err.println("ERROR: El número del medico "+getNombre()+" ha fallado");
			this.numeroMedico = 0;
		} else {
			this.numeroMedico = numero_medico;
		}
	}

	
	/**
	 * Visualiza las especialidades del medico
	 */
	public void visualizarEspecialidades() {
		for(TipoMedico s : this.especialidades) {
			System.out.println(s);
		}
	}
	@Override
	public String toString() {
		return super.toString()+"Número de medico: "+ getNumeroMedico()+"\n" +"Con la especialidad: "+ getEspecialidades()+"\n";
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null || !(obj instanceof Medico)) return false;
		
		if (this == obj) return true;
		
		Medico otroMedico = (Medico) obj;
		return this.getDni().equals(otroMedico.getDni());
		
	}

	public HashMap<LocalDate, Cita> getCitasMedico() {
		return citasMedico;
	}

	public void setCitasMedico(HashMap<LocalDate, Cita> citasMedico) {
		this.citasMedico = citasMedico;
	}

	@Override
	public String obtenerRol() {
		
		return "Medico";
	}

	@Override
	public void mostrarResumen() {
		System.out.println("El medico "+getNombre()+" "+ getApellido1()+ " "+ getApellido2());		
		System.out.println("Numero: "+ getNumeroMedico());
		System.out.println("Años experiencia: "+ getAñosExp());
		System.out.println("Edad: "+getEdad());
		System.out.println("Especialidades: "+ getEspecialidades());
	}
	
	
	

}