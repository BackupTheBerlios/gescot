package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JOptionPane;

public class AccionIdioma implements ActionListener {
	private Ventana ventana;
	public AccionIdioma(Ventana V){
		ventana=V;
	}
	public void actionPerformed(ActionEvent arg0) {
		

		Object[] possibleValues = { "Español", "English", "Deutsch" };
		Object selectedValue = JOptionPane.showInputDialog(null, 
		"Elija el idioma", "Elección de idioma",
		JOptionPane.INFORMATION_MESSAGE, null,
		possibleValues, possibleValues[0]);

		if (selectedValue == null) return;
		
		File fichero = new File(".\\SimTraffic.conf");
		//OutputStream os = new OutputStream(fichero);
		try {
			FileWriter fw = new FileWriter(fichero);
			BufferedWriter bw = new BufferedWriter(fw);
			
			if (((String) selectedValue).compareTo("Español") == 0) {
				bw.write("Español");
			}
			else if (((String) selectedValue).compareTo("English") == 0) {
				bw.write("English");
			}
			else if (((String) selectedValue).compareTo("Deutsch") == 0) {
				bw.write("Deutsch");
			}

			
			bw.close();
			fw.close();
//			JOptionPane.showMessageDialog(null, "Para que los cambios hagan efecto, debe salir del programa", "Debe reinciar el programa", JOptionPane.INFORMATION_MESSAGE); 
			new Messages();
			ventana.actualizar();
		} catch (IOException e) {
			
		}
		
		try {
			FileReader fr = new FileReader(fichero);
		} catch (FileNotFoundException e) {
			fichero = new File("c:\\hlocal\\SimTraffic.conf");
			FileWriter fw;
			try {
				fw = new FileWriter(fichero);
				BufferedWriter bw = new BufferedWriter(fw);
				
				if (((String) selectedValue).compareTo("Español") == 0) {
					bw.write("Español");
				}
				else if (((String) selectedValue).compareTo("English") == 0) {
					bw.write("English");
				}
				else if (((String) selectedValue).compareTo("Deutsch") == 0) {
					bw.write("Deutsch");
				}				

				bw.close();
				fw.close();
				JOptionPane.showMessageDialog(null, "Para que los cambios hagan efecto, debe salir del programa", "Debe reinciar el programa", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (IOException e1) {
			    	
			}
			    
			
			
	
		}

	}		

}
