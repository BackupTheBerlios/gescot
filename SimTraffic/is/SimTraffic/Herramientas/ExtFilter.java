package is.SimTraffic.Herramientas;
import java.io.File;
import javax.swing.filechooser.*;

/**
 * Clase filtro de fichero.
 * <p>
 * Esta clase extiende FileFilter para poder filtar los fichero que son
 * mostrados en un dialogo para abrir o guardar ficheros.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class ExtFilter extends FileFilter {

	/**
	 * Vector con las distintas extensiones que se filtraran
	 */
	private String[] extensions;

	/**
	 * String con la descripcion de los archivos que se estan filtrando
	 */
	private String description;

	/**
	 * Constructora que toma como parámetro una extensión
	 * 
	 * @param ext
	 *            String con la extension a filtrar
	 */
	public ExtFilter(String ext) {
		this(new String[] { ext }, null);
	}

	/**
	 * Construtora que toma como parámetoros un vector de instruciones y una
	 * descripcion.
	 * 
	 * @param exts
	 *            Vector de String con extensiones
	 * @param descr
	 *            String con descripción de fichero a filtrar
	 */
	public ExtFilter(String[] exts, String descr) {
		extensions = new String[exts.length];
		for (int i = exts.length - 1; i >= 0; i--) {
			extensions[i] = exts[i].toLowerCase();
		}
		description = (descr == null ? exts[0] + " files" : descr);
	}

	/**
	 * (non-Javadoc) Método hereadado
	 * 
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String name = f.getName().toLowerCase();
		for (int i = extensions.length - 1; i >= 0; i--) {
			if (name.endsWith(extensions[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * (non-Javadoc) Método heredado
	 * 
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	public String[] getExtensions() {
		return extensions;
	}
}
