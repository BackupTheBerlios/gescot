package is.SimTraffic.Vista;

import is.SimTraffic.Messages;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		setTitle(Messages.getString("AcercaDe.0")); //$NON-NLS-1$
		setVisible(true);
		
		/*JTextPane texto = new JTextPane();
		texto.setEditable(false);
		texto.setFocusable(false);
		texto.setText("SimTraffic: un simulador de tráfico\nVersion 1.0\n" +
				"(c) Grupo IS Tráfico, 2007\nTodos los derechos reservados");*/
		
		JLabel l1 = new JLabel(Messages.getString("AcercaDe.1")); //$NON-NLS-1$
		JLabel l2 = new JLabel(Messages.getString("AcercaDe.2")); //$NON-NLS-1$
		JLabel l3 = new JLabel(Messages.getString("AcercaDe.3")); //$NON-NLS-1$
		JLabel l4 = new JLabel(Messages.getString("AcercaDe.4")); //$NON-NLS-1$
		
		JPanel aux = new JPanel(new GridLayout(0,1,2,0));
		aux.add(l1);
		aux.add(l2);
		aux.add(l3);
		aux.add(l4);
		
		add(aux);
		JButton aceptar = new JButton(Messages.getString("AcercaDe.5")); //$NON-NLS-1$
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
