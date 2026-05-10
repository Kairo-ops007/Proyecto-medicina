package medicina;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Control {
	private HashMap<String, Paciente> mapaPacientes;
	private HashMap<String, Medico> mapaMedicos;
	private HashMap<TipoMedico, ArrayList<Medico>> especialidades;

	private GestionClinica gestion = new GestionClinica(5, 20);

	/**
	 * No se le pasan argumentos porque construye los arrays de Pacientes y Medicos
	 */
	public Control() {
		this.mapaMedicos = new HashMap<String, Medico>();
		this.mapaPacientes = new HashMap<String, Paciente>();
		this.especialidades = new HashMap<TipoMedico, ArrayList<Medico>>();
	}

	public ArrayList<Paciente> getListaPacientes(){
		return new ArrayList<Paciente>(mapaPacientes.values());
	}
	public ArrayList<Medico> getListaMedicos(){
		return new ArrayList<Medico>(mapaMedicos.values());
	}

	
	
	
	
	//Registros
	public void registrarPaciente(Paciente p) {
		if (p == null) {
			System.err.println("ERROR: El paciente es nulo");
			return;
		}
		if (pacienteYaRegistrado(p)) {
			System.err.println("El paciente "+p.getNombre()+" ya esta registrado");
			return;
		}
		mapaPacientes.put(p.getDni(), p);
		System.out.println("El paciente "+ p.getNombre()+ " a sido registrado.");

	}
	public void registrarMedico(Medico m) {
		if (m == null) {
			System.err.println("ERROR: El medico es nulo");
			return; 
		}
		if (medicoYaRegistrado(m)) {
			System.err.println("ERROR: El medico "+m.getNombre()+" ya ha sido registrado");
			return ;
		}
		mapaMedicos.put(m.getDni(), m);
		System.out.println("El medico se ha podido registrar. ");
		guardarPorEspecialidades();
	}

	//Bajas

	public void quitarPacientes(Paciente p) {
		mapaPacientes.remove(p.getDni(), p);
		
	}
		public void quitarMedicos(Medico m) {
			mapaMedicos.remove(m.getDni(),m);
		}

	// Comprobaciones



		
		public Paciente buscarPacientePorDni(String dni) {
			return mapaPacientes.get(dni);
		}
		public Medico buscarMedicoPorDni(String dni) {
			return mapaMedicos.get(dni);
		}
		public ArrayList<Paciente> buscarPacientesBajoPeso(){
			ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
			for (Paciente p: mapaPacientes.values()) {
				if (p.imc() < 18.5) {
					listaPacientes.add(p);
				}
			}
			return listaPacientes;
		}
		public ArrayList<Paciente> buscarPacientesObesos(){
			ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
			for (Paciente p : mapaPacientes.values()) {
				if (p.imc()> 30) {
					listaPacientes.add(p);
				}
			}
			return listaPacientes;
		}
		public ArrayList<Medico> medicosEspecialistasEn(TipoMedico tm){
			return (especialidades.containsKey(tm)? especialidades.get(tm): new ArrayList<Medico>());
		}
		
		public void guardarPorEspecialidades () {
			for (Medico m : mapaMedicos.values()) {
				for (TipoMedico tm : m.getEspecialidades()) {
					if (!especialidades.containsKey(tm)) {
						especialidades.put(tm, new ArrayList<Medico>());
					}
					especialidades.get(tm).add(m);
				}
			}
		}
		
		//Citas
		public void agendar(Paciente p , Medico m, String motivo) {

			Cita cita = new Cita (p, m, LocalDate.now(), LocalTime.NOON, motivo, false );
			p.getCitas().add(cita);
//			m.citasMedico.put(LocalDate.now(), m.citasMedico); // mirar

		}
		public void cancelarCita(Paciente p, Cita c) {

			int indice = FuncionesAux.buscarCitas(p.getCitas(), c);
			if (indice != -1) {
				p.getCitas().remove(indice);
			} else {
				System.err.println("ERROR: No se ha encontrado la cita");
			}
		}
		
		public void reprogramarCita(Cita c, LocalDate fechaNueva, LocalTime nuevaHora) {

			if (c == null) {
				System.err.println("ERROR: La cita no puede ser nula");
				return;
			}
			if (c.isAtendida()) {
				System.out.println("ADVERTENCIA: La cita ya ha sido atendida");
				return;
			}
			c.setFecha(fechaNueva);
			c.setHora(nuevaHora);
			System.out.println("La cita ha sido reprogramada al dia "+c.getFecha()+ " a la hora "+c.getHora());


		}
		
		public void atenderCita(Cita c, String Diagnostico, String medicamento, String dosis, int duracionDias) {

			if (c == null) {
				System.err.println("ERROR: La cita no puede ser nula");
				return;
			}

			if (c.isAtendida()) {
				System.err.println("ERROR: La cita ya ha sido atendida");
				return; 
			}

			c.setAtendida(true);




			if (Diagnostico != null && !Diagnostico.isEmpty() ) {

				if (medicamento != null && !medicamento.isEmpty()  ) {

					Paciente p = c.getPaciente();
					p.getHistorial().agregarEnfermedad(Diagnostico);
					Receta receta = new Receta (c.getMedico(),p, medicamento, dosis, duracionDias, LocalDate.now(), false );
					p.getHistorial().agregarReceta(receta);
				} else {

					System.err.println("ERROR: El medicamento no puede ser nulo");
				}

			} else {

				System.err.println("ERROR: El diagnostico no puede ser nulo");
			}

		}
		
		public ArrayList<Cita> citasDia (LocalDate dia){
			ArrayList<Cita> citas = new ArrayList<Cita>();
			for (Paciente p : mapaPacientes.values()) {
				for (Cita c : p.getCitas()) {
					if (c.getFecha().equals(dia)) {
						citas.add(c);
					}
				}
			}
			return citas;
		}
		
		public void mostrarCitas(Paciente p ) {
			p.getCitas().forEach(System.out :: println);
		}
		
		public void mostrarCitasMedico(Medico m) {
			int cont = 0;
			for (Paciente p : mapaPacientes.values()) {
				for (Cita c : p.getCitas()) {
					if (c.getMedico().equals(m)) {
						System.out.println(c);
						cont++;
					}
				}
			}
			if (cont == 0) {
				System.out.println("EL medico "+m.getNombre()+" no tiene ninguna cita asignada");
			}
		}
		
		
		//Stats
		public boolean pacienteYaRegistrado(Paciente p) {
			return mapaPacientes.containsKey(p.getDni());
		}




		public boolean medicoYaRegistrado(Medico m) {
			return mapaMedicos.containsKey(m.getDni());
		}
		
		public double porcentajeMenores() {

			int cont = 0;
			for (Paciente p : mapaPacientes.values()) {
				if (p != null) {
					if (p.esMenor()) {
						cont ++;
					}
				}else {
					System.err.println("ERROR: El paciente no pude ser nulo");
				}
			}
			double calculo = (double) cont/mapaPacientes.values().size() *100;
			return calculo;
		}
		public int contarCitasDeUnMedico(Medico m) {
			int cont = 0;
			for (Paciente p : mapaPacientes.values()) {
				for (Cita c : p.getCitas()) {
					if (c.getMedico().equals(m)) {
						cont++;
					}
				}
			}
			return cont;
		}
		
		
		public Medico medicoMasCitas () {
			int maximo = -1;

			Medico resultado = null;
			for (Medico m : mapaMedicos.values()) {
				int citas = contarCitasDeUnMedico(m);
				if (citas > maximo) {
					maximo = citas;
					resultado = m;
				}

			}
			return resultado;

		}
		public Medico medicoMenosCitas() {

			int minimo = Integer.MAX_VALUE;
			Medico med = null;
			for (Medico m : mapaMedicos.values()) {
				int citas = contarCitasDeUnMedico(m);
				if (citas < minimo) {
					minimo = citas;
					med  = m ;

				}
			}
			return med;
		}


	/**
	 * Muestra las citas del paciente pedido
	 * @param p
	 */
	public void mostrarCitasPaciente(Paciente p) {

		for (Cita c : p.getCitas()) {
			System.out.println(c);
		}

	}


	/**
	 * Muestra los pacientes NO argumentos
	 */
	public void mostrarPacientes() {
		System.out.println("\n-----------Pacientes-----------\n");
		for (Paciente p : mapaPacientes.values()) {
			System.out.println(p);
		}

	}

	/**
	 * Muestra los medicos NO argumentos
	 */
	public void mostrarMedicos() {
		System.out.println("\n-----------Medicos-----------\n");
		for (Medico m : mapaMedicos.values()) {
			System.out.println(m);
		}
	}
	/**
	 * Muestra todas las personas registradas
	 * NO argumentos
	 */
	public void mostrarPersonasAsociadas() {
		System.out.println("\n-----------Personas Totales asociadas-----------\n");
		System.out.println("Los pacientes:\n");
		mostrarPacientes();
		System.out.println("\nLos medicos\n");
		mostrarMedicos();
	}







	public ArrayList<Receta> recetasSinRecoger(){
		ArrayList<Receta> recetasS = new ArrayList<Receta>();
		for (Paciente p : getListaPacientes() ) {
			for (Receta r : p.getHistorial().getListaRecetas()) {
				if (!( r.isRecogida())) {
					recetasS.add(r);
				}
			}
		}
		return recetasS;
	}

	public double porcentajeObesos() {
		int contador = 0;
		for (Paciente p : mapaPacientes.values()) {
			if (p.imc()> 30) {
				contador ++;
			}
		}
		
		double calculo = (double) contador / mapaPacientes.values().size() *100 ;
		return calculo;
	}
	public double porcentajeBajoPeso() {
		int contador = 0;
		for (Paciente p : mapaPacientes.values()) {
			if (p.imc()<18.5) {
				contador ++;
			}
		}
		
		double calculo = (double) contador / mapaPacientes.size() *100 ;
		return calculo;
	}
	

	public ArrayList<Paciente> pacientesMedico(Medico m){
		ArrayList<Paciente> pacs = new ArrayList<Paciente>();
		for (Paciente p: getListaPacientes()) {
			for (Cita c : p.getCitas()) {
				if (c.getMedico().equals(m)) {
					pacs.add(p);
				}
			}
		}
		return pacs;
	}


	public double porcentajeMenores(ArrayList<Paciente> pacs) {
        if (pacs == null || pacs.isEmpty()) return 0;
        int cont = 0;
        for (Paciente p : pacs) {
        	if (p != null && p.esMenor()) cont++;
        }
        return (double) cont / pacs.size() * 100;
    }
 
    public double porcentajeObesos(ArrayList<Paciente> pacs) {
        if (pacs == null || pacs.isEmpty()) return 0;
        long cont = 0;
        for (Paciente p : pacs) {
        	if (p.imc() > 30) cont++;
        	}
        return (double) cont / pacs.size() * 100;
    }
 
    public double porcentajeBajoPeso(ArrayList<Paciente> pacs) {
        if (pacs == null || pacs.isEmpty()) return 0;
        long cont = 0;
        for (Paciente p : pacs) {
        	if (p.imc() < 18.5) cont++;
        }
        return (double) cont / pacs.size() * 100;
    }
	public Medico medicoMasCercano(Paciente p) {
		return FuncionesAux.medicoMasCercano(new ArrayList<Medico>(mapaMedicos.values()), p);
	}


	public void mostrar (Mostrable mos ) {
		mos.mostrarResumen();
	}
	
	
	
	//Cargas y descargas
	/**
	 * Guarda los ficheros en archivo dado
	 * ej: "Pacientes.csv"
	 * @param fichero
	 */
	public void guardarPacientes(String fichero) {
		try {
			PrintStream ps = new PrintStream(fichero);
			for (Paciente p : getListaPacientes()) {
				ps.println(
						p.getNombre()+";"+
						p.getApellido1()+";"+
						p.getApellido2()+";"+
						p.getX()+";"+
						p.getY()+";"+
						p.getDomicilio()+";"+
						p.getDni()+";"+
						p.getEdad()+";"+
						p.getGenero()+";"+
						p.getAltura()+";"+
						p.getPeso()+";"+
						p.getVacunas()
						);
			}
			ps.close();	
			} catch (FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Fichero no se ha podido crear");
		}
	}

	/**
	 * Carga los pacientes de un fichero dado 
	 * ej: "Pacientes.csv"
	 * @param String fichero
	 */
	public void cargarPacientes(String fichero) {
		try {
			Scanner sc = new Scanner(new File(fichero));
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] partes= linea.split(";");
				Paciente p = new Paciente(
						partes[0],
						partes[1],
						partes[2],
						Integer.parseInt(partes[3]),
						Integer.parseInt(partes[4]),
						partes[5],
						partes[6],
						Integer.parseInt(partes[7]),
						partes[8],
						Double.parseDouble(partes[9]),
						Double.parseDouble(partes[10]),
						new ArrayList<String>()
						);
				registrarPaciente(p);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el fichero");
		}
	}
	
	/**
	 * Carga los medicos en un archivo de tipo csv
	 * ej: Medicos.csv
	 * @param Stringfichero
	 */
	public void guardarMedicos(String fichero) {
		try {
			PrintStream ps = new PrintStream(fichero);
			for (Medico m : mapaMedicos.values()) {
				String especialidades = "";
				for (TipoMedico tm : m.getEspecialidades()) {
					especialidades += tm.name()+"|";
				}
				ps.println(
						m.getNombre()+";"+
						m.getApellido1()+";"+
						m.getApellido2()+";"+
						m.getX()+";"+
						m.getY()+";"+
						m.getDomicilio()+";"+
						m.getDni()+";"+
						m.getEdad()+";"+
						m.getGenero()+";"+
						especialidades+";"+
						m.getAñosExp()
						);
				
			}
			ps.close();
		} catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "El fichero "+fichero+" no se ha podido crear");
		}
	}
	

	/**
	 * Carga los medicos en un archivo de tipo csv
	 * ej: Medicos.csv
	 * @param String fichero
	 */
	public void cargarMedicos(String fichero) {
		try(Scanner sc = new Scanner(new File(fichero))) {
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				String [] partes = linea.split(";");
				ArrayList<TipoMedico> especialidades = new ArrayList<TipoMedico>();
				String [] tipos = partes[9].split("\\|");
				for (String t : tipos) {
					if (!t.isEmpty()) {
						especialidades.add(TipoMedico.valueOf(t));
					}
				}
				Medico m = new Medico(
						partes[0],
						partes[1],
						partes[2],
						Integer.parseInt(partes[3]),
						Integer.parseInt(partes[4]),
						partes[5],
						partes[6],
						Integer.parseInt(partes[7]),
						partes[8],
						especialidades,
						Integer.parseInt(partes[10]),
						new HashMap<LocalDate, Cita>()
						);
				registrarMedico(m);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "El fichero "+fichero+" no se ha encontrado");
		}
	}
	
	/**
	 * Guarda la gestion interna de un edificio
	 */
	public void guardarGestionInterna() {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("GestionInterna.dat"));
			oos.writeObject(gestion);
			oos.close();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "El fichero de Gestion interna no se ha podido crear");
		}
		
		
	}
	
	/**
	 * Carga la gestion interna de un edificio
	 */
	public void cargarGestionInterna() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("GestionInterna.dat"));) {
			this.gestion = (GestionClinica) ois.readObject();
		} catch(IOException|ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el fichero de GestionInterna");
		}
	}
	
	
	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Control)) return false;
		if (obj == this) return true;
		Control otroControl = (Control) obj;

		return getListaPacientes().equals(otroControl.getListaPacientes()) &&
				getListaMedicos().equals(otroControl.getListaMedicos());
	}

	@Override
	public String toString() {
		return "Clinica con "+ getListaPacientes().size()+ " pacientes"+
				" y " +getListaMedicos().size()+" medicos";
	}

}