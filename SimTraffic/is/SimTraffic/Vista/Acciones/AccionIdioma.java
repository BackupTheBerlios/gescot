package is.SimTraffic.Vista.Acciones;

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

	public void actionPerformed(ActionEvent arg0) {
		Object[] possibleValues = { "Español", "Inglés" };
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
			else if (((String) selectedValue).compareTo("Inglés") == 0) {
				bw.write("Inglés");
			}
			
			bw.close();
			fw.close();
			JOptionPane.showMessageDialog(null, "Para que los cambios hagan efecto, debe salir del programa", "Debe reinciar el programa", JOptionPane.INFORMATION_MESSAGE); 
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
				else if (((String) selectedValue).compareTo("Inglés") == 0) {
					bw.write("Inglés");
				}
				
				bw.close();
				fw.close();
				JOptionPane.showMessageDialog(null, "Para que los cambios hagan efecto, debe salir del programa", "Debe reinciar el programa", JOptionPane.INFORMATION_MESSAGE); 
			} catch (IOException e1) {
	
			}
	
		}

	}

}
