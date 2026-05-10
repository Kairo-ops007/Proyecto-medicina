package medicina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class GestionClinica implements Serializable{
	private static final long serialVersionUID = 1L;
	private Habitacion [][][] edificio;
	private int totalPisos;
	private int totalHabitaciones;

	public GestionClinica (int totalPisos, int totalHabitaciones) {
		this.totalPisos = totalPisos;
		this.totalHabitaciones = totalHabitaciones;
		this.edificio = new Habitacion[totalPisos][2][totalHabitaciones];
		for (int i = 0; i < totalPisos; i++) {
			for (int j=0; j<2 ;j++) {
				for (int k=0; k < totalHabitaciones;k++) {
					edificio [i][j][k] = new Habitacion(null, null);
				}
			}
		}
	}

	private int indice (String lado) {
		return lado.equalsIgnoreCase("Izquierda") ? 0:1;
	}
	private Habitacion hab ( int piso, String lado, int habitacion ) {
		return edificio[piso][indice(lado)][habitacion];
	}

	public void ocuparHabitacionPacienteTotal (Paciente p , Persona acompanyante, int piso, String lado, int habitacion) {
		if (p.getEdad()< 18 || p.getEdad()> 65 || acompanyante != null) {
			ocuparHabitacionPacienteCuidadoso(piso, lado, habitacion, p, acompanyante);
		} else {
			ocuparHabitacionPacienteNormal (piso, lado, habitacion, p);
		}
	}

	public void ocuparHabitacionPacienteNormal (int piso, String lado, int habitacion, Paciente p) {
		Habitacion h = hab(piso, lado, habitacion);
		if (h.getPaciente() != null) {
			System.out.println("ERRO: Habitacion ocupada");
			return; 
		}
		h.setPaciente(p);
	}
	public void ocuparHabitacionPacienteCuidadoso(int piso, String lado, int habitacion, Paciente p , Persona acompanyante) {
		Habitacion h = hab ( piso, lado , habitacion);
		if (h.getPaciente() != null) {
			System.out.println("Error: Habitacion ocupada");
			return; 
		}
		h.setPaciente(p);
		h.setAcompanyante(acompanyante);
	}

	public void ocuparHabitacionMedico(int piso, String lado, int habitacion, Medico m) {
		Habitacion h = hab ( piso, lado, habitacion);
		if (h.getMedico()!= null) {
			System.out.println("ERROR: Medico ya asignado");
			return ;

		} else {
			System.out.println("NO hay acompanyante");
		}
	}

	public void asignacionMedicoAleatoria(ArrayList<Medico> meds) {
		for (int i = 0; i < totalPisos; i++) {
			for (int j=0; j< 2 ; j++) {
				for (int k = 0; k< totalHabitaciones; k++) {
					Habitacion h = edificio[i][j][k];
					if (h.getPaciente() != null && h.getMedico() == null) {
						int posicion = (int) (Math.random()*meds.size());
						h.setMedico(meds.get(posicion));
					}
				}
			}
		}
	}

	public boolean comprobarHabitacion ( int piso, String lado, int habitacion) {
		return hab ( piso, lado , habitacion).getPaciente()== null;
	}

	public String habitacionLibre () {
		for (int i = 0; i< totalPisos ; i++) {
			for (int j = 0; j < 2 ; j++) {
				for (int z = 0; z < totalHabitaciones;z++) {
					if (edificio[i][j][z].getPaciente() == null) {
						String lado = (j == 0)? "izquierdo": "derecho";
						return "Habitacion en el psio "+ i +" lado "+lado+" nº "+z+" esta libre";					
					}
				}
			}
		}
		return "Edificio lleno";
	}

	public int habitacionesLibres() {
		int contador = 0;
		for ( int i = 0; i < totalPisos; i++) {
			for (int j = 0; j< 2 ; j++ ) {
				for ( int z = 0; z< totalHabitaciones; z++) {
					if (edificio [i][j][z].getPaciente() != null) {
						contador++;
					}
				}
			}
		}
		return contador;
	}


	public void darAlta (int piso, String lado, int habitacion) {
		Habitacion h = hab(piso, lado, habitacion);
		if (h.getPaciente()!= null) {
			h.setPaciente(null);
			h.setMedico(null);
			h.setAcompanyante(null);
			System.out.println("Habitacion libre");
		} else {
			System.err.println("Error: La habitacion ya esta libre");
		}
	}

	public void darAltaPorNombre(String nombre) {
		for (int i = 0; i < totalPisos; i++)
			for (int j = 0; j < 2; j++)
				for (int z = 0; z < totalHabitaciones; z++) {
					Habitacion h = edificio[i][j][z];
					if (h.getPaciente() != null &&
							h.getPaciente().getNombre().equalsIgnoreCase(nombre)) {
						h.setPaciente(null);
						h.setMedico(null);
						h.setAcompanyante(null);
					}
				}
	}

	public void trasladarPaciente(int piso1, String lado1, int hab1, int piso2, String lado2, int hab2) {
		Habitacion origen  = hab(piso1, lado1, hab1);
		Habitacion destino = hab(piso2, lado2, hab2);

		if (origen.getPaciente() == null) {
			System.err.println("La habitacion de origen esta vacia");
			return;
		}
		if (destino.getPaciente() != null) {
			System.err.println("La habitacion destino ya tiene un paciente");
			return;
		}
		destino.setPaciente(origen.getPaciente());
		destino.setMedico(origen.getMedico());
		destino.setAcompanyante(origen.getAcompanyante());
		origen.setPaciente(null);
		origen.setMedico(null);
		origen.setAcompanyante(null);

		System.out.println("Paciente " + destino.getPaciente().getNombre() + " trasladado con éxito");
	}
	public void trasladarMedico(int piso1, String lado1, int hab1,
			int piso2, String lado2, int hab2) {
		Habitacion origen  = hab(piso1, lado1, hab1);
		Habitacion destino = hab(piso2, lado2, hab2);

		if (origen.getMedico() == null) {
			System.err.println("ERROR: No hay medico en la habitacion de origen");
			return;
		}
		destino.setMedico(origen.getMedico());
		origen.setMedico(null);
	}
	public void buscarPacientePorNombre(String nombre) {
		for (int i = 0; i < totalPisos; i++)
			for (int k = 0; k < 2; k++)
				for (int j = 0; j < totalHabitaciones; j++) {
					Paciente p = edificio[i][k][j].getPaciente();
					if (p != null && p.getNombre().equalsIgnoreCase(nombre)) {
						String lado = (k == 0) ? "izquierdo" : "derecho";
						System.out.println("Paciente " + nombre + " → piso " + i
								+ ", lado " + lado + ", habitación " + j);
						return;
					}
				}
	}

	public TreeSet<Paciente> pacientesPorMedico(Medico m) {
		TreeSet<Paciente> pacs = new TreeSet<>();
		for (int i = 0; i < totalPisos; i++)
			for (int j = 0; j < 2; j++)
				for (int z = 0; z < totalHabitaciones; z++) {
					Habitacion h = edificio[i][j][z];
					if (m.equals(h.getMedico()) && h.getPaciente() != null)
						pacs.add(h.getPaciente());
				}
		return pacs;
	}

	public TreeSet<Paciente> pacientesPorRangoEdad(int min, int max) {
		TreeSet<Paciente> pacs = new TreeSet<>();
		for (int i = 0; i < totalPisos; i++)
			for (int j = 0; j < 2; j++)
				for (int z = 0; z < totalHabitaciones; z++) {
					Paciente p = edificio[i][j][z].getPaciente();
					if (p != null && p.getEdad() >= min && p.getEdad() <= max)
						pacs.add(p);
				}
		return pacs;
	}

	public int totalHabs() {
		return totalPisos * 2 * totalHabitaciones;
	}

	public void porcentajePacientesMenores() {
		int contador = 0;
		for (int i = 0; i < totalPisos; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < totalHabitaciones; k++) {
					Paciente p = edificio[i][j][k].getPaciente();
					if (p != null && p.esMenor()) contador++;
				}

		double calc = (double) contador / totalHabs() * 100;
		System.out.println("Porcentaje de Menores es : "+ calc);
	}

	public void porcentajePacientesGenero() {
		int contF = 0;
		int contM = 0;
		for (int i = 0; i < totalPisos; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < totalHabitaciones; k++) {
					Paciente p = edificio[i][j][k].getPaciente();
					if (p != null) {
						if (p.getGenero().equalsIgnoreCase("Femenino")) contF++;
						else contM++;
					}
				}
		System.out.println("Femenino : "+ (double)contF/totalHabs()*100+"\n"+
				"Masculino: "+(double)contM / totalHabs()*100);
	}

	public void porcentajeOcupacion() {
		int global = 0;
		for (int i = 0; i < totalPisos; i++) {
			int porPiso = 0;
			for (int j = 0; j < 2; j++) {
				int porLado = 0;
				for (int k = 0; k < totalHabitaciones; k++)
					if (edificio[i][j][k].getPaciente() != null) { porPiso++; porLado++; global++; }
				System.out.println("Piso " + i + " lado " + (j == 0 ? "izquierdo" : "derecho")
						+ ": " + porLado + " ocupadas");
			}
			System.out.println("Piso " + i + " total: " + porPiso);
		}
		System.out.printf("Ocupacion global: ", (double) global / totalHabs() * 100);
	}

	public int citasPorAtender() {
		int contador = 0;
		for (int i = 0; i < totalPisos; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < totalHabitaciones; k++) {
					Paciente p = edificio[i][j][k].getPaciente();
					if (p != null)
						for (Cita c : p.getCitas())
							if (!c.isAtendida()) contador++;
				}
		return contador;
	}

	public int contadorEspecialidad(TipoMedico tm) {
		int contador = 0;
		for (int i = 0; i < totalPisos; i++)
			for (int k = 0; k < 2; k++)
				for (int j = 0; j < totalHabitaciones; j++) {
					Medico m = edificio[i][k][j].getMedico();
					if (m != null && m.getEspecialidades().contains(tm))
						contador++;
				}
		System.out.println("Hay " + contador + " medicos con la especialidad " + tm);
		return contador;
	}

	public void mostrarHabitacion(int piso, String lado, int habitacion) {
		Habitacion h = hab(piso, lado, habitacion);
		if (h.getPaciente() != null) {
			if (h.getAcompanyante() != null)
				System.out.println("Acompanyante: " + h.getAcompanyante());
			System.out.println("Paciente: " + h.getPaciente());
			System.out.println("Medico: " + h.getMedico());
		} else {
			System.out.println("Habitación vacia");
		}
	}

	@Override
	public String toString() {
		return "GestionInterno [edificio=" + Arrays.toString(edificio) + ", totalPisos=" + totalPisos
				+ ", totalHabitaciones=" + totalHabitaciones + "]";
	}

}


