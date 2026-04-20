package medicina;
import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;
public class VentanaMedicos extends JFrame {
	public VentanaMedicos(Control control) {
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setTitle("MEDICATE - Medicos");
		this.setSize(1000,800);
		this.setLayout(new BorderLayout());
		
		ArrayList<Medico> medicos = control.getListaMedicos();
          // TABLA DE PACS
		String [] nombreColumnas = { "Nombre", "Apellidos", "Domicilio","DNI",
				"Edad","Genero","AñosExp", "nº medico","Especialidades" };
        Object[] [] datos = new Object[medicos.size()][9];
        for (int i = 0; i < medicos.size();i++) {
        	Medico m = medicos.get(i);
        	datos[i][0] = m.getNombre();
        	datos[i][1] = m.getApellido1()+" " +m.getApellido2();
        	datos[i][2]  = m.getDomicilio();
        	datos[i][3] = m.getDni();
        	datos[i][4] = m.getEdad();
        	datos[i][5] = m.getGenero();
        	datos [i][6] = m.getAñosExp();
        	datos[i][7] = m.getNumeroMedico();
        	datos[i][8] = m.getEspecialidades();
        	
        }
        
        JTable tablaPacientes = new JTable(datos, nombreColumnas);
        JScrollPane scrollPane = new JScrollPane(tablaPacientes);

        //Cabecera de tabla
        tablaPacientes.getTableHeader().setFont(new Font("ARIAL", Font.ITALIC, 15));
        tablaPacientes.getTableHeader().setBackground(new Color(170, 130, 10));
        tablaPacientes.getTableHeader().setForeground(Color.cyan);
        
        JButton botonPacs = new JButton("Ver Pacientes");
        botonPacs.getBorder();
        botonPacs.setBackground(new Color(10,75,90));
        botonPacs.setForeground(Color.GREEN);
        botonPacs.setFont(new Font("Arial",Font.ROMAN_BASELINE, 13));
        botonPacs.setOpaque(true);
        botonPacs.setBorderPainted(true);
        botonPacs.addActionListener(e -> new VentanaPacientes(control));
        
        JButton botonEst = new JButton("Estadisticas");
        botonEst.getBorder();
        botonEst.setBackground(new Color(170,60,40));
        botonEst.setForeground(Color.ORANGE);
        botonEst.setFont(new Font("Arial",Font.ITALIC, 12));
        botonEst.setOpaque(true);
        botonEst.setBorderPainted(true);
        botonEst.addActionListener(e -> new VentanaEstadisticas(control));
        
        
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titulo = new JLabel("MEDICOS");
        titulo.setFont(new Font("Arial",Font.BOLD,14));
        panelNorte.add(titulo);
        panelNorte.add(botonPacs);
        panelNorte.add(botonEst);
        
        this.add(panelNorte, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
}
}
