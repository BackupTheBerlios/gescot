package is.SimTraffic.Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Grupo ISTrafico
 *
 */
public class Log extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4996009540610924354L;
	
	
	/**
	 * Historial de acciones
	 */
	private List historial;
	
	
	/**
	 * Campo de texto para mostrar los eventos
	 */
	private JTextArea texto;
	
	
	private JScrollPane scrollPane;
	
	Log(List h) {
		setTitle("Historial de Eventos");
		setVisible(true);
		this.historial= h;
		texto = new JTextArea ();
		texto.setEditable(false);
		setLayout(new BorderLayout());
		
		
		
		scrollPane = new JScrollPane (texto, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		add(scrollPane, BorderLayout.CENTER);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Log.this.setVisible(false);
				Log.this.dispose();
			}
		}
		);
		add(aceptar, BorderLayout.SOUTH);
		
		this.setPreferredSize(new Dimension(400,200));
		this.pack();
		this.setResizable(true);
		this.setLocation(50, 50);
		if (historial.isEmpty()) texto.setText("Sin eventos");
		else escribirTexto();
	}
	
	/**
	 * Escribe los eventos del historial en el campo de texto
	 * 
	 */
	private void escribirTexto(){
		Iterator it = this.historial.iterator();
		this.texto.setText("");
		while (it.hasNext()){
			this.texto.append((String)it.next());
			this.texto.append("\n");
			
		}
	}
}
