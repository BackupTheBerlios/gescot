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
		Object[] possibleValues = { "Espa�ol", "Ingl�s" };
		Object selectedValue = JOptionPane.showInputDialog(null, 
		"Elija el idioma", "Elecci�n de idioma",
		JOptionPane.INFORMATION_MESSAGE, null,
		possibleValues, possibleValues[0]);

		if (selectedValue == null) return;
		
		File fichero = new File(".\\SimTraffic.conf");
		//OutputStream os = new OutputStream(fichero);
		try {
			FileWriter fw = new FileWriter(fichero);
			BufferedWriter bw = new BufferedWriter(fw);
			
			if (((String) selectedValue).compareTo("Espa�ol") == 0) {
				bw.write("Espa�ol");
			}
			else if (((String) selectedValue).compareTo("Ingl�s") == 0) {
				bw.write("Ingl�s");
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
				
				if (((String) selectedValue).compareTo("Espa�ol") == 0) {
					bw.write("Espa�ol");
				}
				else if (((String) selectedValue).compareTo("Ingl�s") == 0) {
					bw.write("Ingl�s");
				}
				
				bw.close();
				fw.close();
				JOptionPane.showMessageDialog(null, "Para que los cambios hagan efecto, debe salir del programa", "Debe reinciar el programa", JOptionPane.INFORMATION_MESSAGE); 
			} catch (IOException e1) {
	
			}
	
		}

	}

}
