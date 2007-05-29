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
		

		Object[] possibleValues = { Messages.getString("AccionIdioma.0"), Messages.getString("AccionIdioma.1"), Messages.getString("AccionIdioma.2") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Object selectedValue = JOptionPane.showInputDialog(null, 
		Messages.getString("AccionIdioma.3"), Messages.getString("AccionIdioma.4"), //$NON-NLS-1$ //$NON-NLS-2$
		JOptionPane.INFORMATION_MESSAGE, null,
		possibleValues, possibleValues[0]);

		if (selectedValue == null) return;
		
		File fichero = new File(".\\SimTraffic.conf"); //$NON-NLS-1$
		//OutputStream os = new OutputStream(fichero);
		try {
			FileWriter fw = new FileWriter(fichero);
			BufferedWriter bw = new BufferedWriter(fw);
			
			if (((String) selectedValue).compareTo(Messages.getString("AccionIdioma.6")) == 0) { //$NON-NLS-1$
				bw.write(Messages.getString("AccionIdioma.7")); //$NON-NLS-1$
			}
			else if (((String) selectedValue).compareTo(Messages.getString("AccionIdioma.8")) == 0) { //$NON-NLS-1$
				bw.write(Messages.getString("AccionIdioma.9")); //$NON-NLS-1$
			}
			else if (((String) selectedValue).compareTo(Messages.getString("AccionIdioma.10")) == 0) { //$NON-NLS-1$
				bw.write(Messages.getString("AccionIdioma.11")); //$NON-NLS-1$
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
			fichero = new File("c:\\hlocal\\SimTraffic.conf"); //$NON-NLS-1$
			FileWriter fw;
			try {
				fw = new FileWriter(fichero);
				BufferedWriter bw = new BufferedWriter(fw);
				
				if (((String) selectedValue).compareTo(Messages.getString("AccionIdioma.13")) == 0) { //$NON-NLS-1$
					bw.write(Messages.getString("AccionIdioma.14")); //$NON-NLS-1$
				}
				else if (((String) selectedValue).compareTo(Messages.getString("AccionIdioma.15")) == 0) { //$NON-NLS-1$
					bw.write(Messages.getString("AccionIdioma.16")); //$NON-NLS-1$
				}
				else if (((String) selectedValue).compareTo(Messages.getString("AccionIdioma.17")) == 0) { //$NON-NLS-1$
					bw.write(Messages.getString("AccionIdioma.18")); //$NON-NLS-1$
				}				

				bw.close();
				fw.close();
				JOptionPane.showMessageDialog(null, Messages.getString("AccionIdioma.19"), Messages.getString("AccionIdioma.20"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			}
			catch (IOException e1) {
			    	
			}
			    
			
			
	
		}

	}		

}
