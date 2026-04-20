package medicina;

import java.awt.*;

import javax.swing.*;

public class VentanaEstadisticas extends JFrame{
	private Control control;

	public VentanaEstadisticas(Control control) throws HeadlessException {
	
		this.control = control;
		this.setTitle("Estadisticas");
		this.setSize(1000,800);
		this.setLayout(new BorderLayout());
		
		JTabbedPane tab = new JTabbedPane();
		tab.add("Pacientes", crearEstPacientes());
		tab.add("Medicos", crearEstMedicos());
		tab.add("Citas", crearEstCitas());
		this.add(tab, BorderLayout.CENTER);
		
		JPanel panelMain = new JPanel( new FlowLayout(FlowLayout.LEFT));
		JLabel titulo = new JLabel("Estadisticas");
		
		titulo.setFont(new Font ("Arial", Font.ITALIC,12));		
		panelMain.add(titulo);
        
        this.add(panelMain, BorderLayout.NORTH);

        this.setVisible(true);
	}
	private JPanel crearEstPacientes() {
		JPanel panel = new JPanel();
		
		return panel;
	}
	private JPanel crearEstMedicos() {
		JPanel panel = new JPanel();
		return panel;
	}
	private JPanel crearEstCitas() {
		JPanel panel = new JPanel();
		return panel;
	}
	
}
