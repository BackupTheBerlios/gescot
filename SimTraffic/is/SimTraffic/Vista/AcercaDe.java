package is.SimTraffic.Vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;

public class AcercaDe extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4996009540610924353L;

	AcercaDe() {
		setTitle("Acerda de SimTraffic");
		setVisible(true);
		
		JTextPane texto = new JTextPane();
		texto.setText("SimTraffic: un simulador de tráfico\nVersion 1.0\n" +
				"(c) Grupo IS Tráfico, 2007\nTodos los derechos reservados");
		
		setLayout(new BorderLayout());
		
		add(texto, BorderLayout.NORTH);
		add(new JButton("Acceptar"), BorderLayout.SOUTH);
		this.pack();
	
	}
}
