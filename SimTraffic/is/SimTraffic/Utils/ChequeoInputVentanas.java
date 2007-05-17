package is.SimTraffic.Utils;

import is.SimTraffic.Messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;



public class ChequeoInputVentanas {
	
	/**
	 * Clase que comprueba los valores introducidos en las ventanas del programa
	 *
	 */
	
	
	public ChequeoInputVentanas(){
		
	}
	public boolean esLatitud(String input){
		if (esDigito(input)){
		 int valor=Integer.parseInt(input);	
		 if (valor<=90 && valor>=-90)
		  return true;	 
		}
		muestraMensajeErrorEnInput();
		return false;
	}
	
	public boolean esLongitud(String input){
		if (esDigito(input)){
		 int valor=Integer.parseInt(input);	
		 if (valor<=180 && valor>=-180)
		  return true;	 
		}
		muestraMensajeErrorEnInput();
		return false;
	}
	
	
	//Comprueba si un string es un digito
	public boolean esDigito(String a){
		Pattern p= Pattern.compile(Messages.getString("ChequeoInputVentanas.0")); //$NON-NLS-1$
		Matcher m=p.matcher(a);
		return m.matches();
	}
	
	//En caso de error al introducir un dato mostramos un mensaje de error
	private void muestraMensajeErrorEnInput(){
		 JOptionPane.showMessageDialog(null, Messages.getString("ChequeoInputVentanas.1"), //$NON-NLS-1$
		            Messages.getString("ChequeoInputVentanas.2"),  //$NON-NLS-1$
		            JOptionPane.INFORMATION_MESSAGE);
	}

}
