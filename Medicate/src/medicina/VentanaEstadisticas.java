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
		panel.setLayout(new BorderLayout());
		JLabel labelImc = new JLabel("IMC");
		labelImc.setFont(new Font("Arial", Font.BOLD, 12));
		labelImc.setBackground(Color.cyan);
//		titulo.setIcon(new Icon); TODO pensarla
		panel.add(labelImc, BorderLayout.NORTH);
		
		String [] nombreColumnas = { "Nombre", "Diagnostico" };
        Object[] [] datos = new Object[control.getListaPacientes().size()][2];
        for (int i = 0; i < control.getListaPacientes().size();i++) {
        	Paciente p = control.getListaPacientes().get(i);
        	datos[i][0] = p.getNombre()+" "+p.getApellido1()+" " +p.getApellido2();
        	datos[i][1] = p.diagnosticoImc()+"     "+ String.format("%.2f", p.imc());
        	
        }
        JTable tablaPacientes = new JTable(datos, nombreColumnas);
        JScrollPane scrollPane = new JScrollPane(tablaPacientes);
        tablaPacientes.getTableHeader().setFont(new Font("ARIAL", Font.ITALIC, 15));
        tablaPacientes.getTableHeader().setBackground(new Color(170, 130, 10));
        tablaPacientes.getTableHeader().setForeground(Color.cyan);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        
        
        //=============================================================================
        
		return panel;
	}

	private JPanel crearEstMedicos() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel titulo = new JLabel("Medicos");
		panel.add(titulo);
		return panel;
	}	

	private JPanel crearEstCitas() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel titulo = new JLabel("Registro de Citas");
		String[] nombreColumnas  = {"Paciente","DNI","Medico","Fecha", "Hora","Motivo" };
		int contMax = 0;
		for (Paciente p: control.getListaPacientes()) {
			for (Cita c : p.getCitas()) {
				if (!c.isAtendida()) {
					contMax++;
				}
			}
		}
		int cont = 0;
		
		Object [][] datos = new Object [contMax][6];
		for (int i = 0; i < control.getListaPacientes().size(); i++) {
			for (Cita c : control.getListaPacientes().get(i).getCitas()) {
				if (!c.isAtendida()) {
					datos[cont][0] = c.getPaciente().getNombre()+" "+c.getPaciente().getApellido1();
					datos[cont][1] = c.getPaciente().getDni();
					datos [cont][2] = c.getMedico().getNombre()+" "+"nº "+c.getMedico().getNumeroMedico();
					datos[cont][3] = c.getFecha();
					datos[cont][4]  = c.getHora();
					datos[cont][5] = c.getMotivo();
					cont++;
				}
			}
		}
		JTable tablaCitas = new JTable(datos,nombreColumnas);
		tablaCitas.getTableHeader().setBackground(new Color(142,61,79));
		tablaCitas.getTableHeader().setFont(new Font("Arial",Font.ITALIC,15));
		tablaCitas.getTableHeader().setForeground(new Color(186,97,220));
		JScrollPane scrollPane = new JScrollPane(tablaCitas);

		JLabel citas = new JLabel("Citas pendientes: ");
		panel.add(citas,BorderLayout.WEST);
		panel.add(titulo,BorderLayout.NORTH);
		panel.add(scrollPane,BorderLayout.CENTER);
		
		JPanel panelMenores = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JProgressBar pb = new JProgressBar(0,100);
		
		int porcentaje = (int) (control.porcentajeMenores(control.getListaPacientes()));
		JLabel texto = new JLabel("Menores de Edad");
		pb.setValue(porcentaje);
		pb.setStringPainted(true);
		pb.setString(porcentaje + "%");
		pb.setBackground(Color.orange);
		pb.setForeground(Color.DARK_GRAY);
		pb.setSize(20, 15); // Darle mas importancia a los menores de edad
		pb.setFont(new Font("Arial",Font.BOLD,20));
		panelMenores.add(texto);
		panelMenores.add(pb);
		
		JLabel texto2 = new JLabel("Mayores de Edad");
		int porcentajeMayores = 100- porcentaje;
		JProgressBar progressMayor = new JProgressBar(0,100);
		progressMayor.setValue(porcentajeMayores);
		progressMayor.setStringPainted(true);
		progressMayor.setBackground(Color.lightGray);
		progressMayor.setForeground(new Color(152,64,174));
		progressMayor.setString(porcentajeMayores+"%");
		JPanel panelMayores = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelMayores.add(texto2);
		panelMayores.add(progressMayor);

		JLabel texto3 = new JLabel("Obesos:");
		JProgressBar progressObesos = new JProgressBar(0,100);
		int porcentajeObesos = (int)control.porcentajeObesos(control.getListaPacientes());
		progressObesos.setValue(porcentajeObesos);
		progressObesos.setString(porcentajeObesos+"%");
		progressObesos.setStringPainted(true);
		progressObesos.setBackground(new Color(85,64,29));
		progressObesos.setForeground(new Color(65,87,164));
		JPanel panelObesos = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelObesos.add(texto3);
		panelObesos.add(progressObesos);
		
		JLabel texto4 = new JLabel("Bajo Peso");
		int calculo = (int) control.porcentajeBajoPeso(control.getListaPacientes());
		JProgressBar progressBajoPeso = new JProgressBar(0,100);
		progressBajoPeso.setValue(calculo);
		progressBajoPeso.setString(calculo+"%");
		progressBajoPeso.setStringPainted(true);
		progressBajoPeso.setBackground(Color.cyan);
		progressBajoPeso.setForeground(Color.GREEN);
		JPanel panelBajoPeso = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelBajoPeso.add(texto4);
		panelBajoPeso.add(progressBajoPeso);
		
	    JPanel panelSur = new JPanel(new GridLayout(2, 1));
	    panelSur.add(panelMenores);
	    panelSur.add(panelMayores);
	    panelSur.add(panelObesos);
	    panelSur.add(panelBajoPeso);
	    panel.add(panelSur,BorderLayout.SOUTH);
		
		return panel;
	}
	
}
