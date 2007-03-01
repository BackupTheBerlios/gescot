package is.SimTraffic;

import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Vista.IVista;

public interface IControlador {

	/**
	 * @roseuid 45B8A7B103D4
	 */
	public void crear();

	/**
	 * Establece la instancia del modelo que se utilizara en la aplicación
	 * 
	 * @param modelo
	 */
	public void setModelo(IModelo modelo);

	/**
	 * Establece la instancia de la vista que se utilizara en la aplicación
	 * 
	 * @param vista
	 */
	public void setVista(IVista vista);

	/**
	 * Método que intenta deshacer la aplicación de la ultima herramienta.
	 * <p>
	 * Este metodo tratar de deshacer los cambios realizados por la ultima
	 * herramienta aplicada sobre el modelo, eliminandola de la lista si es
	 * exitoso.
	 * 
	 * @return 0 en el caso de exito, codigo de error en otro caso
	 */
	public int deshacer();

	/**
	 * Método que intenta aplicar una herramienta sobre el modelo.
	 * <p>
	 * Este método intenta aplicar una herramienta sobre el modelo, y en el caso
	 * de ser exito la almacena para poder deshacer los cambios. En caso de
	 * exito o fracaso, se lo indica al usuario
	 * 
	 * @param herramienta
	 *            IHerramienta con la herramienta que se desea aplicar al modelo
	 * @return int 0 en caso de exito, codigo de error en otro caso
	 * @roseuid 45B8A7B80386
	 */
	public int herramienta(IHerramienta herramienta);

	/**
	 * @param tipoError
	 * @return String
	 * @roseuid 45B8A814000B
	 */
	public String obtenerMensajeError(int tipoError);

	/**
	 * @param ayuda
	 * @return String
	 * @roseuid 45B8A85C03B5
	 */
	public String obtenerVinculoAyuda(int ayuda);
}
