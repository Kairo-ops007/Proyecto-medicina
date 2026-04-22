package medicina;

import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;

public class VentanaMain extends JFrame{
	private Control control = new Control();
	private JPanel panelControl;
	public VentanaMain() {
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setTitle("MEDICATE");
		this.setSize(1000,800);
		meterDatos();
		
		
		ArrayList<Paciente> pacientes = control.getListaPacientes();
          // TABLA DE PACS
		String [] nombreColumnas = { "Nombre", "Apellidos", "Domicilio","DNI","Edad","Genero","Altura", "Peso" };
        Object[] [] datos = new Object[pacientes.size()][8];
        for (int i = 0; i < pacientes.size();i++) {
        	Paciente p = pacientes.get(i);
        	datos[i][0] = p.getNombre();
        	datos[i][1] = p.getApellido1()+" " +p.getApellido2();
        	datos[i][2]  = p.getDomicilio();
        	datos[i][3] = p.getDni();
        	datos[i][4] = p.getEdad();
        	datos[i][5] = p.getGenero();
        	datos[i][6] = p.getAltura();
        	datos[i][7] = p.getPeso();
        	
        }
        
        JTable tablaPacientes = new JTable(datos, nombreColumnas);
        JScrollPane scrollPane = new JScrollPane(tablaPacientes);

        //Cabecera de tabla
        tablaPacientes.getTableHeader().setFont(new Font("ARIAL", Font.ITALIC, 15));
        tablaPacientes.getTableHeader().setBackground(new Color(170, 130, 10));
        tablaPacientes.getTableHeader().setForeground(Color.cyan);
        
        
        JButton botonMed = new JButton("Ver Medicos");
        botonMed.setBackground(new Color(130,75,90));
        botonMed.setForeground(Color.GREEN);
        botonMed.setFont(new Font("Arial",Font.ROMAN_BASELINE, 13));
        botonMed.setOpaque(true);
        botonMed.setBorderPainted(true);
        botonMed.addActionListener(e -> new VentanaMedicos(control));
        
        
        JButton botonEst = new JButton("Estadisticas");
        botonEst.setBackground(new Color(170,60,40));
        botonEst.setForeground(Color.ORANGE);
        botonEst.setFont(new Font("Arial",Font.ITALIC, 12));
        botonEst.setOpaque(true);
        botonEst.setBorderPainted(true);
        botonEst.addActionListener(e -> new VentanaEstadisticas(control));
        
        
        JButton botonControl  = new JButton("Ver clinica");
        botonControl.setBackground(new Color(180,92,37));
        botonControl.setBorderPainted(true);
        botonControl.setFont(new Font("Italia",Font.ROMAN_BASELINE, 12));
        botonControl.setForeground(new Color(151,64,24));
        botonControl.setOpaque(true);
        botonControl.setBorderPainted(true);
        botonControl.addActionListener(e -> new VentanaGestion(control));
        
        
        
        
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorte.add(new JLabel("  Pacientes"));
        ((JLabel)panelNorte.getComponent(0)).setFont(new Font("Arial", Font.BOLD, 16));
        panelNorte.add(botonMed);
        panelNorte.add(botonEst);
        panelNorte.add(botonControl);
        this.add(panelNorte, BorderLayout.NORTH);
        
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
        
	}

		

	
	private void meterDatos() {
		// ==========================================
				// PREPARACIÓN DE ESPECIALIDADES VÁLIDAS
				// ==========================================
				ArrayList<TipoMedico> esp1 = new ArrayList<>();
				esp1.add(TipoMedico.CIRUJANO_ORTOPEDICO); esp1.add(TipoMedico.REUMATOLOGO);
				
				ArrayList<TipoMedico> esp2 = new ArrayList<>();
				esp2.add(TipoMedico.GINECOLOGO); esp2.add(TipoMedico.PEDIATRA);
				
				ArrayList<TipoMedico> esp3 = new ArrayList<>();
				esp3.add(TipoMedico.CARDIOLOGO); esp3.add(TipoMedico.CIRUJANO_CARDIOVASCULAR);
				
				ArrayList<TipoMedico> esp4 = new ArrayList<>();
				esp4.add(TipoMedico.DERMATOLOGO); esp4.add(TipoMedico.ALERGOLOGO);
				
				ArrayList<TipoMedico> esp5 = new ArrayList<>();
				esp5.add(TipoMedico.PSIQUIATRA); esp5.add(TipoMedico.NEFROLOGO);

				ArrayList<ArrayList<TipoMedico>> listaEspecialidades = new ArrayList<>();
				listaEspecialidades.add(esp1); listaEspecialidades.add(esp2); 
				listaEspecialidades.add(esp3); listaEspecialidades.add(esp4); listaEspecialidades.add(esp5);

				String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE"; // Letras oficiales DNI

				// ==========================================
				// GENERACIÓN DE 25 MÉDICOS
				// ==========================================
				String[] nomMedicos = {"Alejandro", "Beatriz", "Carlos", "Diana", "Eduardo", "Fernanda", "Gabriel", "Hilda", "Ignacio", "Julia", "Kevin", "Laura", "Mario", "Natalia", "Oscar", "Patricia", "Quique", "Rosa", "Sergio", "Teresa", "Ulises", "Veronica", "Walter", "Ximena", "Yolanda"};
				String[] apellidosMedicos = {"Garcia", "Martinez", "Lopez", "Sanchez", "Perez", "Gomez", "Martin", "Jimenez", "Ruiz", "Hernandez", "Diaz", "Moreno", "Muñoz", "Alvarez", "Romero", "Alonso", "Gutierrez", "Navarro", "Torres", "Dominguez", "Vazquez", "Ramos", "Gil", "Ramirez", "Serrano"};
				
				ArrayList<Medico> medicosGenerados = new ArrayList<>();

				for (int i = 0; i < 25; i++) {
					// Generar DNI válido (Empezamos en el numero 10000000 para medicos)
					int numDni = 10000000 + i;
					char letra = letrasDNI.charAt(numDni % 23);
					String dniValido = numDni + "" + letra;
					
					String genero = (i % 2 == 0) ? "Masculino" : "Femenino";
					int edad = 30 + (i % 30); // Edades entre 30 y 59
					int exp = edad - 25; // Experiencia logica segun edad
					ArrayList<TipoMedico> especialidad = listaEspecialidades.get(i % 5);
					
					Medico m = new Medico(nomMedicos[i], apellidosMedicos[i], apellidosMedicos[24-i], 10*i, 20*i, "Calle " + nomMedicos[i] + " " + i, dniValido, edad, genero, especialidad, exp);
					control.registrarMedico(m);
					medicosGenerados.add(m); // Lo guardamos para hacer citas despues
				}

				// ==========================================
				// GENERACIÓN DE 30 PACIENTES
				// ==========================================
				String[] nomPacientes = {"Ana", "Borja", "Clara", "Daniel", "Elena", "Felipe", "Gloria", "Hugo", "Irene", "Jorge", "Kiara", "Luis", "Marta", "Nacho", "Olga", "Pablo", "Raquel", "Roberto", "Sara", "Tomas", "Ursula", "Victor", "Wendy", "Xabi", "Yaiza", "Zacarias", "Amelia", "Bruno", "Celia", "David"};
				String[] apellidosPacientes = {"Fernandez", "Blanco", "Soto", "Castro", "Ortiz", "Rubio", "Marin", "Sanz", "Nuñez", "Iglesias", "Garrido", "Cortes", "Lozano", "Cano", "Molina", "Delgado", "Castro", "Ortiz", "Rubio", "Marin", "Sanz", "Nuñez", "Iglesias", "Garrido", "Cortes", "Lozano", "Cano", "Molina", "Delgado", "Cruz"};
				
				ArrayList<Paciente> pacientesGenerados = new ArrayList<>();

				for (int i = 0; i < 30; i++) {
					// Generar DNI válido (Empezamos en el numero 20000000 para pacientes para no pisar a los medicos)
					int numDni = 20000000 + i;
					char letra = letrasDNI.charAt(numDni % 23);
					String dniValido = numDni + "" + letra;
					
					String genero = (i % 2 == 0) ? "Femenino" : "Masculino";
					int edad = 5 + (i * 3); // Edades variadas desde 5 hasta 90+
					double altura = 1.40 + (i * 0.01); // Alturas entre 1.40 y 1.70 aprox
					double peso = 43.0 + (i * 1.5); // Pesos entre 40kg y 85kg aprox
					
					Paciente p = new Paciente(nomPacientes[i], apellidosPacientes[i], apellidosPacientes[29-i], -5*i, 10*i, "Avenida " + nomPacientes[i] + " " + i, dniValido, edad, genero, altura, peso, new ArrayList<>());
					control.registrarPaciente(p);
					pacientesGenerados.add(p);
				}

				// ==========================================
				// GENERACIÓN DE CITAS ALEATORIAS
				// ==========================================
				// Agendamos 10 citas cruzando pacientes y médicos
				for (int i = 0; i < 10; i++) {
					Paciente pac = pacientesGenerados.get(i * 2); // Cogemos pacientes salteados
					Medico med = medicosGenerados.get(i); // Cogemos los primeros 10 medicos
					control.agendar(pac, med, "Revisión rutinaria " + (i+1));
				}
	
	}
	public static void main(String[] args) {
		
	    new VentanaMain();

	}
	
	
}
