/**
 * 
 */
package is.SimTraffic;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.theme.SubstanceCharcoalTheme;
import org.jvnet.substance.watermark.SubstanceMetalWallWatermark;
import org.jvnet.substance.watermark.SubstanceMosaicWatermark;
import org.jvnet.substance.watermark.SubstanceWoodWatermark;

import com.incors.plaf.alloy.AlloyLookAndFeel;
import com.incors.plaf.alloy.AlloyTheme;
import com.incors.plaf.alloy.themes.acid.AcidTheme;
import com.incors.plaf.alloy.themes.glass.GlassTheme;

import is.SimTraffic.Herramientas.HCargarMapa;
import is.SimTraffic.Vista.IVista;
import is.SimTraffic.Vista.Vista;

/**
 * Clase principal del programa.
 * <p>
 * Esta clase contiene el método main que se ejecuta al comenzar el programa.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Principal {

	/**
	 * Método main principal del sistema.
	 * <p>
	 * Este método se encarga de crear las instancias de modelo, vista y
	 * controlador y asignarles los valores iniciales para que se pueda utilizar
	 * la aplicación. Además, una vez que está todo listo muestra la ventana al
	 * usuario para que pueda usar la aplicación.
	 * 
	 * @param args Argumentos de tipo string utilizados por defecto en el método 
	 * main.
	 */
	public static void main(String[] args) {
		try {
			//com.incors.plaf.alloy.AlloyLookAndFeel.setProperty("alloy.licenseCode", "2007/06/24#darthguado@gmail.com#n7i79d#15zvo4");
			//UIManager.setLookAndFeel(new com.incors.plaf.alloy.AlloyLookAndFeel(new GlassTheme()));
			new SubstanceLookAndFeel();
			SubstanceLookAndFeel.setCurrentTheme(new org.jvnet.substance.theme.SubstanceCremeTheme()); //Quedan Bien Oliva, Sepia y Crema
			SubstanceLookAndFeel.setCurrentWatermark(new SubstanceWoodWatermark());
			UIManager.setLookAndFeel(new org.jvnet.substance.SubstanceLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) 
		{
			JOptionPane.showMessageDialog(null, "<html>Error al cargar la ventana.<br> El tema seleccionado noes soportado por esta plataforma.</html>", "Problemas al comenzar la aplicación", JOptionPane.WARNING_MESSAGE);
		}
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

}
