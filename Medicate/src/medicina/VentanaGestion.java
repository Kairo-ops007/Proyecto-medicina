package medicina;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaGestion extends JFrame {
	private GestionInterno gestion = new GestionInterno(5, 10);
	public VentanaGestion(Control control) {
		
		ArrayList<Paciente> pacientes = control.getListaPacientes();
		ArrayList<Medico> medicos = control.getListaMedicos();
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("MEDICATE -GestionInterna");
		this.setSize(1000,800);
		this.setLayout(new BorderLayout());
		
		JLabel titulo = new JLabel("Gestion de la clínica");
		titulo.setFont(new Font("Arial", Font.ITALIC, 13));
		
		this.add(titulo,BorderLayout.NORTH);
		
		
		
	}
	
}
 