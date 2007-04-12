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
public class Log extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4996009540610924353L;

	Log() {
		setTitle("Historial de Eventos");
		setVisible(true);
		
		JTextPane texto = new JTextPane();
		texto.setText("Historial de eventos\nen construcion\n");
		
		setLayout(new BorderLayout());
		
		add(texto, BorderLayout.NORTH);
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Log.this.setVisible(false);
			}
		});
		
		add(aceptar, BorderLayout.SOUTH);
		this.pack();
		this.setResizable(true);
		this.setLocation(50, 50);
	}
}
