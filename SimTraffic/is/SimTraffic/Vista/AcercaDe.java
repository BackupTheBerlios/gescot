package is.SimTraffic.Vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;

/**
 * 
 * @author Grupo ISTrafico
 *
 */
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
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcercaDe.this.setVisible(false);
			}
		});
		
		add(aceptar, BorderLayout.SOUTH);
		this.pack();
		this.setResizable(false);
		this.setLocation(50, 50);
	}
}
