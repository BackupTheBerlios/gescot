package is.SimTraffic.Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * 
 * @author Grupo ISTrafico
 *
 */
public class Ayuda extends JFrame implements HyperlinkListener,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4996009540610924374L;
	
	
		
	/**
	 * Campo de texto para mostrar los eventos
	 */
	private JEditorPane htmlPane;
	private JScrollPane scrollPane;
	
	Ayuda() {
		setTitle("Ayuda de SimTraffic");
		setVisible(true);
		setLayout(new BorderLayout());
		
		
		String url = "http://simtraffic.helker.com/Manual2/manual.html";
		try {
		  htmlPane = new JEditorPane(url);
		} catch(IOException ioe) {
		  System.err.println("Error displaying " + url);
		}
		
		htmlPane.setEditable(false);
		scrollPane = new JScrollPane (htmlPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		add(scrollPane, BorderLayout.CENTER);
		
		htmlPane.setContentType("text/html"); 
		
		
		
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Log.this.setVisible(false);
				Ayuda.this.dispose();
			}
		}
		);
		add(aceptar, BorderLayout.SOUTH);
		
		this.setPreferredSize(new Dimension(640,480));
		this.pack();
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(true);
		this.setLocation(50, 50);
		//this.repaint();
		
	}

	public void hyperlinkUpdate(HyperlinkEvent e) {
	    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
	        JOptionPane.showMessageDialog(null,"das");
	    	try {
	          htmlPane.setPage(e.getURL());
	        } catch(IOException ioe) {
	          // Some warning to user
	        }
	      }
	
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
