package medicina;
import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;
public class VentanaPacientes extends JFrame{
	public VentanaPacientes(Control control) {
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setTitle("MEDICATE - Pacientes");
		this.setSize(1000,800);
		this.setLayout(new BorderLayout());
		
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
        botonMed.getBorder();
        botonMed.setBackground(new Color(130,75,90));
        botonMed.setForeground(Color.GREEN);
        botonMed.setFont(new Font("Arial",Font.ROMAN_BASELINE, 13));
        botonMed.setOpaque(true);
        botonMed.setBorderPainted(true);
        botonMed.addActionListener(e -> new VentanaMedicos(control));
        
        JButton botonEst = new JButton("Estadisticas");
        botonEst.getBorder();
        botonEst.setBackground(new Color(170,60,40));
        botonEst.setForeground(Color.ORANGE);
        botonEst.setFont(new Font("Arial",Font.ITALIC, 12));
        botonEst.setOpaque(true);
        botonEst.setBorderPainted(true);
        botonEst.addActionListener(e -> new VentanaEstadisticas(control));
        
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titulo = new JLabel("Pacientes");
        titulo.setFont(new Font("Arial",Font.BOLD,14));
        panelNorte.add(titulo);
        panelNorte.add(botonMed);
        panelNorte.add(botonEst);
        
        this.add(panelNorte, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
}

	
}
