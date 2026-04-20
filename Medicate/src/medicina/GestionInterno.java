package medicina;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
// Extends de control? 
public class GestionInterno {
	private Persona [][][][] edificio;
	private int totalPisos;
	private int totalHabitaciones;
	public GestionInterno( int totalPisos, int totalHabitaciones) {
		this.edificio = new Persona [totalPisos][2][totalHabitaciones][3];

	}
	public int totalHabs() {
		return getTotalPisos()*2*getTotalHabitaciones();
	}
	public Persona [][][][] getEdificio() {
		return edificio;
	}

	public int getTotalPisos() {
		return totalPisos;
	}
	public int getTotalHabitaciones() {
		return totalHabitaciones;
	}



	public void ocuparHabitacionPacienteTotal(Paciente p, Persona p1, int piso, String lado, int habitacion) {

		if (p.getEdad() < 18 || p.getEdad()>65|| p1!= null) {
			ocuparHabitacionPacienteCuidadoso(piso, lado, habitacion, p, p1);
		} else {
			ocuparHabitacionPacienteNormal(piso, lado, habitacion, p);
		}	
	}





	public void ocuparHabitacionPacienteNormal (int piso, String lado, int habitacion, Paciente p) {
		if (!comprobarHabitacion(piso, lado, habitacion,1)) {
			System.err.println("ERROR: Habitacion ocupada");
			return;
		}
		int indice = (lado.equalsIgnoreCase("Izquierda")? 0: 1);
		this.edificio[piso][indice][habitacion][1] = p;

	}

	public void ocuparHabitacionPacienteCuidadoso (int piso, String lado, int habitacion, Paciente p, Persona p1) {
		if (!comprobarHabitacion(piso, lado, habitacion,1)) {
			System.err.println("ERROR: Habitacion ocupada");
			return;
		}
		int indice = (lado.equalsIgnoreCase("Izquierda")? 0: 1);
		this.edificio[piso][indice][habitacion][1] = p;
		this.edificio[piso][indice][habitacion][0] = p1;

	}




	public void ocuparHabitacionMedico(int piso, String lado, int habitacion, Medico m) {
		if (!comprobarHabitacion(piso, lado, habitacion, 2 )) {
			System.err.println("ERROR: Medico ya asignado");
			return; 
		}
		int indice = (lado.equalsIgnoreCase("Izquierda")? 0:1);
		this.edificio[piso][indice][habitacion][2] = m;
	}


	public void asignacionMedicoAleatoria(ArrayList<Medico> meds) {
		for (int i = 0; i < getTotalPisos(); i++) {
			for (int j = 0 ; j < 2; j++) {
				for (int k = 0 ; k < getTotalHabitaciones(); k++) {
					if (edificio[i][j][k][1] != null && edificio[i][j][k][2]== null) {
						int M = meds.size();
						int calculo = (int) (Math.random()*M);
						this.edificio[i][j][k][2] = meds.get(calculo);

					}
				}
			}
		}
	}

	public void agregarAcompanyante (int piso , String lado, int habitacion, Persona p) {
		int indice = (lado.equalsIgnoreCase("izquierda")? 0: 1);
		this.edificio[piso][indice][habitacion][0] = p;
	}


	public boolean  comprobarHabitacion (int piso, String lado, int habitacion, int posible) {

		int indice = (lado.equalsIgnoreCase("Izquierda")? 0: 1);
		return  ( edificio[piso][indice][habitacion][posible] == null) ? true: false;

	}

	public void porcentajePacientesMenores() {
		int contador = 0;
		for (int i= 0; i< getTotalPisos(); i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < totalHabitaciones; k++) {
					if (edificio[i][j][k][1] != null) {
						Paciente p = (Paciente) (edificio[i][j][k][1]) ;
						if (p.esMenor()) {
							contador ++;
						}
					}
				}
			}
		}
		double calculo = ((double) contador/totalHabs())*100;
		System.out.println("El "+calculo+"% de los pacientes son menores de edad.");

	}

	public int citasPorAtender() {
		int contador = 0;
		for (int i= 0; i< getTotalPisos(); i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < totalHabitaciones; k++) {
					if (edificio[i][j][k][1] != null) {
						Paciente p = (Paciente) (edificio[i][j][k][1]) ;
						for (int z = 0;z < p.getCitas().size(); z++) {
							if (!p.getCitas().get(z).isAtendida()) {
								contador++;
							}
						}	
					}
				}
			}
		}
		return contador;
	}

	public void porcentajePacientesGenero() {
		int contadorF = 0;
		int contadorM = 0;
		for (int i= 0; i< getTotalPisos(); i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < totalHabitaciones; k++) {
					if (edificio[i][j][k][1] != null) {
						if (edificio[i][j][k][1].getGenero().equalsIgnoreCase("Femenino")) {
							contadorF ++;
						} else {
							contadorM++;
						}	
					}
				}
			}
		}
		double calc1 = ((double) contadorF/totalHabs())*100;
		double calc2 = ((double) contadorM / totalHabs())*100;
		System.out.println("El "+calc1+"% de los pacientes son femeninas");
		System.out.println("El "+ calc2+"% de los pacientes son masculinos");
	}

	public void porcentajeOcupacion() {
		int contadorGlobal = 0;
		for (int i= 0 ; i <getTotalPisos(); i++ ) {
			int contadorPorPiso = 0;
			for (int j=0; j <2; j++ ) {
				int contadorPorLado = 0;
				for (int k=0;k< getTotalHabitaciones();k++) {
					if (edificio[i][j][k][1] != null) {
						contadorPorPiso++;
						contadorPorLado++;
						contadorGlobal++;
					}
				}
				System.out.println("En el piso "+i+" en el lado "+ ((j ==0)?"izquierdo":"derecho")+ " hay "+contadorPorLado+" habitaciones ocupadas.");
			}
			System.out.println("En el piso "+ i+ " hay "+ contadorPorPiso+" habitaciones ocupadas");
		}

		double calculo = ((double )contadorGlobal/ totalHabs() )*100;
		System.out.println("Hay una tasa de ocupacion de "+ calculo+"%");
	}


	public void darAlta(int piso, String lado, int habitacion) {
		int indice = (lado.equalsIgnoreCase("Izquierda")? 0: 1);
		if (edificio[piso][indice][habitacion][1] != null) {
			this.edificio[piso][indice][habitacion][0] = null;
			this.edificio[piso][indice][habitacion][1] = null;
			this.edificio[piso][indice][habitacion][2] = null;
			System.out.println("Habitacion liberada");
		} else {
			System.err.println("ERROR: La habitacion ya estaba vacia");
		}
	}

	public void darAltaPorNombre (String nombre) {
		for (int i = 0 ; i < getTotalPisos(); i++) {
			for (int j = 0; j < 2; j++) {
				for (int z = 0; z < getTotalHabitaciones(); z++) {
					if (edificio[i][j][z][1] != null) {
						if (edificio[i][j][z][1].getNombre().equalsIgnoreCase(nombre)) {
							this.edificio[i][j][z][1] = null;
							this.edificio[i][j][z][0] = null;
							this.edificio[i][j][z][2] = null;
						}
					}
				}
			}
		}
	}

	public void buscarPacientePorNombre(String nombre) {
		for (int i = 0; i < getTotalPisos(); i++) {
			for (int k = 0; k < 2; k++) {
				for (int j = 0; j < getTotalHabitaciones(); j++) {
					if (edificio[i][k][j][1] != null) {
						Paciente p = (Paciente)edificio[i][k][j][1] ;
						if (p.getNombre().equalsIgnoreCase(nombre)) {
							String lado = (k == 0)? "izquierdo": "derecho";
							System.out.println("El paciente "+ nombre+" estan en el piso "+i+" en el lado "+lado+" en la habitacion "+j);
							return; 
						}

					}
				}
			}
		}
	}


	public int contadorEspecialidad(TipoMedico tm) {
		int contador = 0;

		for (int i = 0; i < getTotalPisos(); i++) {
			for (int k = 0; k < 2; k++) {
				for (int j = 0; j < getTotalHabitaciones(); j++) {
					if (edificio[i][k][j][2] != null) {
						Medico m = (Medico)edificio[i][k][j][2] ;
						for (int z = 0; z < m.getEspecialidades().size(); z++) {
							if (m.getEspecialidades().get(z).equals(tm) ) {
								contador++;
							}
						}
					}
				}
			}
		}
		System.out.println("Hay "+contador+" medicos con la especialidad "+tm);
		return contador;
	}


	public String HabitacionLibre() {
		for (int i = 0; i < getTotalPisos(); i++) {
			for (int j = 0; j <2; j++) {
				for (int z=0; z< getTotalHabitaciones(); z++) {
					if (edificio[i][j][z][1]== null) {
						String lado = (j== 0)? "izquierdo": "derecho";
						return "Habitacion en el piso " + i + " en el lado "+lado+" en el numero "+ z+" esta libre";
					}
				}
			}
		}
		return "Edificio lleno";
	}
	public void trasladarPaciente(int piso1,String lado1, int habitacion1, int piso2, String lado2, int habitacion2) {
		int indice1 = (lado1.equalsIgnoreCase("izquierda")? 0: 1);
		int indice2 = (lado2.equalsIgnoreCase("izquierda")? 0: 1);

		if (edificio[piso1][indice1][habitacion1][1] == null) {
			System.err.println("La habitacion de origen esta vacia");
			return; 
		}
		if (edificio[piso2][indice2][habitacion2][1]!= null) {
			System.err.println("La habitacion destino ya esta un paciente");
			return; 
		}
		edificio[piso2][indice2][habitacion2][1] = edificio[piso1][indice1][habitacion1][1];
		//Traslado de medico tambien
		edificio[piso2][indice2][habitacion2][2] = edificio[piso1][indice1][habitacion1][2];
		if (edificio[piso1][indice1][habitacion1][0] != null) {
			edificio[piso2][indice2][habitacion2][0] = edificio[piso1][indice1][habitacion1][0];
		}
		edificio[piso1][indice1][habitacion1][2] = null;
		edificio[piso1][indice1][habitacion1][1] = null;
		System.out.println("Paciente "+edificio[piso2][indice2][habitacion2][1].getNombre()+ " ha sido trasladado con exito");
	}

	public void trasladarMedico(int piso1, String lado1, int habitacion1, int piso2, String lado2, int habitacion2, Medico m) {
		int indice1 = (lado1.equalsIgnoreCase("Izquierda")?0:1);
		int indice2 = (lado2.equalsIgnoreCase("Izquierda")?0:1);

		if (edificio[piso1][indice1][habitacion1][2]== null) {
			System.err.println("ERROR: No hay medico en la habitacion asignada de origen");
			return;
		}
		edificio[piso2][indice2][habitacion2][2] =edificio[piso1][indice1][habitacion1][2];
		edificio[piso1][indice1][habitacion1][2]= null;


	}

	public void mostrarHabitacion(int piso , String lado, int habitacion) {
		int indice = (lado.equalsIgnoreCase("izquierda")?0:1);
		if (edificio[piso][indice][habitacion][1] != null) {
			if (edificio[piso][indice][habitacion][0] != null) {
				System.out.println("El acompañante es "+ edificio[piso][indice][habitacion][0]);
			}
			System.out.println("El paciente es "+edificio[piso][indice][habitacion][1]);
			System.out.println(edificio[piso][indice][habitacion][2]);
		}
	}


	public TreeSet<Paciente> pacientesPorMedico(Medico m){

		TreeSet<Paciente> pacs = new TreeSet<Paciente>();

		for (int i= 0; i < getTotalPisos(); i++) {
			for (int j =0; j < 2; j++) {
				for (int z= 0; z < getTotalHabitaciones();z++) {
					if (edificio[i][j][z][2].equals(m)) {
						pacs.add((Paciente)edificio[i][j][z][1]);
					}
				}
			}
		}
		return pacs;
	}


	public int habitacionesLibres () {
		int contador = 0;
		for (int i= 0; i < getTotalPisos(); i++) {
			for (int j =0; j < 2; j++) {
				for (int z= 0; z < getTotalHabitaciones();z++) {
					if (edificio[i][j][z][1]!= null) {
						contador++;
					}
				}
			}
		}
		return contador;
	}


	public TreeSet<Paciente> pacientesPorRangoEdad(int min, int max){
		TreeSet<Paciente> pacs = new TreeSet<Paciente>();

		for (int i= 0; i < getTotalPisos(); i++) {
			for (int j =0; j < 2; j++) {
				for (int z= 0; z < getTotalHabitaciones();z++) {
					if (edificio[i][j][z][1]!= null) {
						if (edificio[i][j][z][1].getEdad() > min||edificio[i][j][z][1].getEdad()<max) {
							pacs.add((Paciente)edificio[i][j][z][1]);							
						}
					}
				}
			}
		}
		return pacs;
	}

	public void retirarAcompanyante(int piso, String lado, int habitacion) {
		int indice = (lado.equalsIgnoreCase("Izquierda")? 0: 1);
		if (edificio[piso][indice][habitacion][0] != null) {
			edificio[piso][indice][habitacion][0] = null;
		} else {
			System.err.println("No hay acompañante");
		}
	}
	@Override
	public String toString() {
		return "GestionInterno [edificio=" + Arrays.toString(edificio) + ", totalPisos=" + totalPisos
				+ ", totalHabitaciones=" + totalHabitaciones + "]";
	}





}
