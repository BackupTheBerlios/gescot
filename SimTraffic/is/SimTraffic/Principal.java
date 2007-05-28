/**
 * 
 */
package is.SimTraffic;

import is.SimTraffic.Herramientas.HCargarMapa;
import is.SimTraffic.Vista.IVista;
import is.SimTraffic.Vista.Vista;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;

/**
 * Clase principal del programa.
 * <p>
 * Esta clase contiene el m�todo main que se ejecuta al comenzar el programa.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Principal {

	/**
	 * M�todo main principal del sistema.
	 * <p>
	 * Este m�todo se encarga de crear las instancias de modelo, vista y
	 * controlador y asignarles los valores iniciales para que se pueda utilizar
	 * la aplicaci�n. Adem�s, una vez que est� todo listo muestra la ventana al
	 * usuario para que pueda usar la aplicaci�n.
	 * 
	 * @param args Argumentos de tipo string utilizados por defecto en el m�todo 
	 * main.
	 */
	public static void main(String[] args) {
		cargarLookandFeel();
		new Messages();
		HCargarMapa.p.validate();
		IModelo modelo = new Modelo();
		IVista vista = new Vista(modelo);
		IControlador controlador = new Controlador();
		vista.setControlador(controlador);
		modelo.setControlador(controlador);
		controlador.setModelo(modelo);
		controlador.setVista(vista);
		vista.mostrar();
	}

	private static void cargarLookandFeel() 
	{
		try 
		{
			FileReader fr = new FileReader(new File(".\\look.conf"));
			BufferedReader buf = new BufferedReader(fr);
			String nombre = buf.readLine();
			if (!nombre.equals("Substance"))
			{	
				new SubstanceLookAndFeel();
				SubstanceLookAndFeel.setCurrentTheme(new org.jvnet.substance.theme.SubstanceCremeTheme()); //Quedan Bien Oliva, Sepia y Crema
				cargarTema();
				cargarMarcadeAgua();
				cargarTipoBotones();
				UIManager.setLookAndFeel(new org.jvnet.substance.SubstanceLookAndFeel());
			}
		}
		catch (UnsupportedLookAndFeelException e) 
		{
			JOptionPane.showMessageDialog(null, "<html>Error al cargar la ventana.<br> El tema seleccionado noes soportado por esta plataforma.</html>", "Problemas al comenzar la aplicaci�n", JOptionPane.WARNING_MESSAGE);
		} 
		catch (FileNotFoundException e) 
		{ 
			//Por defecto es el L&F de java.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void cargarTipoBotones() 
	{
		try 
		{
			FileReader fr = new FileReader(new File(".\\button.conf"));
			BufferedReader buf = new BufferedReader(fr);
			String nombre = buf.readLine();
			SubstanceLookAndFeel.setCurrentButtonShaper(nombre);
		} 
		catch (Exception e) 
		{}
	}

	private static void cargarTema() 
	{
		try 
		{
			FileReader fr = new FileReader(new File(".\\theme.conf"));
			BufferedReader buf = new BufferedReader(fr);
			String nombre = buf.readLine();
			SubstanceLookAndFeel.setSkin(nombre);
		} 
		catch (Exception e) 
		{}
	}

	private static void cargarMarcadeAgua() 
	{
		try 
		{
			FileReader fr = new FileReader(new File(".\\watermark.conf"));
			BufferedReader buf = new BufferedReader(fr);
			String nombre = buf.readLine();
			SubstanceLookAndFeel.setCurrentWatermark(nombre);
		} 
		catch (Exception e) 
		{
			SubstanceLookAndFeel.setCurrentWatermark(new org.jvnet.substance.watermark.SubstanceWoodWatermark());
		}
	}

}
