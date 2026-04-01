package medicina;

import java.time.LocalDate;
import java.util.ArrayList;

public class Historial {

	private Paciente paciente;
	private ArrayList<String> enfermedades;
	private ArrayList<String> medicamentos;
	private ArrayList<Cita> citasAnteriores;
	private ArrayList<String> alergias;
	private LocalDate fechaInicial;
	private LocalDate fechaFinal;
	private ArrayList<Receta> listaRecetas;



	public Historial(Paciente paciente, ArrayList<String> enfermedades, ArrayList<String> medicamentos,
			ArrayList<Cita> citas_anteriores, ArrayList<String> alergias, LocalDate fecha_inicial,
			LocalDate fecha_final, ArrayList<Receta> recetas) {
		super();
		this.paciente = paciente;
		this.enfermedades = enfermedades;
		this.medicamentos = medicamentos;
		this.citasAnteriores = citas_anteriores;
		this.alergias = alergias;
		this.fechaInicial = fecha_inicial;
		this.fechaFinal = fecha_final;
		this.listaRecetas = recetas;
	}

	public Historial ( Paciente paciente) {
		this.paciente = paciente;
		this.enfermedades = new ArrayList<>();
		this.medicamentos = new ArrayList<>();
		this.citasAnteriores = new ArrayList<>();
		this.alergias = new ArrayList<>();
		this.fechaInicial = LocalDate.now();
		this.fechaFinal = null;
		this.listaRecetas  = new ArrayList<Receta>();
	}

	public void agregarReceta(Receta rec) {
		if (rec != null) {
			this.listaRecetas.add(rec);
			System.out.println("La receta ha sido añadida");
		}else {
			System.err.println("ERROR: La receta no puede ser nula");
		}
	}


	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public ArrayList<String> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(ArrayList<String> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public ArrayList<String> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(ArrayList<String> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public ArrayList<Cita> getCitasAnteriores() {
		return citasAnteriores;
	}

	public void setCitasAnteriores(ArrayList<Cita> citasAnteriores) {
		this.citasAnteriores = citasAnteriores;
	}

	public ArrayList<String> getAlergias() {
		return alergias;
	}

	public void setAlergias(ArrayList<String> alergias) {
		this.alergias = alergias;
	}

	public ArrayList<Receta> getListaRecetas() {
		return listaRecetas;
	}

	public void setListaRecetas(ArrayList<Receta> listaRecetas) {
		this.listaRecetas = listaRecetas;
	}

	public void agregarCita(Cita c) {
		this.citasAnteriores.add(c);
	}
	public LocalDate getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(LocalDate fecha_inicial) {
		this.fechaInicial = fecha_inicial;

	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}


	public void setFechaFinal(LocalDate fecha_final) {

		this.fechaFinal = fecha_final;
	}


	/**
	 * Quita una cita en caso de que se meta mal
	 * @param fechaFinal
	 */
	public void quitarCita(Cita c) {
		int indice = FuncionesAux.buscarCitas(citasAnteriores, c);
		if (indice != -1) {
			this.citasAnteriores.remove(indice);
			System.out.println("La cita ha sido eliminado");
		} else {
			System.err.println("ERROR: No se ha encontrado la cita.");
		}
	}


	//TODO quitar cita metodo 

	/**
	 * Agrega una enfermedad al historial de enfermedades
	 * @param enf
	 */
	public void agregarEnfermedad(String enf) {
		if (enf == null || enf.length() ==0) {
			System.err.println("ERROR: No se ha podido agregar la enfermedad");
		}else {
			this.enfermedades.add(enf);
			System.out.println("La ha enfermedad ha sido agregada");
		}

	}

	/**
	 * Agregar una alergia al historial de alergias
	 * @param alergia
	 */
	public void agregarAlergias(String alergia) {

		if (alergia == null || alergia.length() == 0) {
			System.err.println("ERROR: No se ha podido agregar la alergia");
		} else {
			this.alergias.add(alergia);
			System.out.println("La alergia ha sido agregada");
		}

	} 

	/**
	 * Agregar medicamentos al historial de medicamentos
	 * @param med
	 */
	public void agregarMedicamentos(String med) {
		if (med == null || med.length() == 0) { 
			System.err.println("ERROR: No se ha podido guardar la medicacion.");
		} else {
			this.medicamentos.add(med);
			System.out.println("La medicacion ha sido guardada");
		}
	}

	/**
	 * Quita los medicamentos del historial
	 * en caso de que te equivoques al meterla
	 * @param med
	 */
	public void quitarMedicamentos(String med) {
		int indice = FuncionesAux.buscarHistoriales(medicamentos, med);
		if (indice != -1) {
			this.medicamentos.remove(indice);
		} else {
			System.err.println("ERROR: No esta ese medicamento en el paciente");
		}
	}

	/**
	 * Quita una enfermedad en el historial
	 * en caso de que te equivoques al meterla
	 * @param enf
	 */
	public void quitarEnfermedad(String enf) {
		int indice = FuncionesAux.buscarHistoriales(enfermedades, enf);
		if (indice != -1) {
			this.enfermedades.remove(indice);
		} else {
			System.err.println("ERROR: No esta esa enfermedad en el paciente");
		}
	}



	//TODO arreglar el equals
	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null || !(obj instanceof Historial)) return false;
		Historial otro = (Historial) obj;
		return this.alergias.equals(otro.alergias) && this.enfermedades.equals(otro.enfermedades) && 
				this.medicamentos.equals(otro.medicamentos);

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.paciente+" tiene las siguientes condiciones: \n"+
		"Enfermedades: "+this.enfermedades.size()+"\n"+
		"Medicamentos: "+this.medicamentos.size()+"\n"+
		"Alergias: "+ this.alergias.size()+"\n";

	}



}
