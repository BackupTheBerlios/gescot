package is.SimTraffic.Utils;

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
		Pattern p= Pattern.compile("[0-9]+");
		Matcher m=p.matcher(a);
		return m.matches();
	}
	
	//En caso de error al introducir un dato mostramos un mensaje de error
	private void muestraMensajeErrorEnInput(){
		 JOptionPane.showMessageDialog(null, "Error al introducir los datos",
		            "Intentelo de nuevo", 
		            JOptionPane.INFORMATION_MESSAGE);
	}

}
