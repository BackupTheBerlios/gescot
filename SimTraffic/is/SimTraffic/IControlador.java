package is.SimTraffic;

import java.util.List;

import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Vista.IVista;

/**
 * @author Grupo ISTrafico
 *
 */
public interface IControlador {

	/**
	 * Establece la instancia del modelo que se utilizar� en la aplicaci�n.
	 * 
	 * @param modelo
	 */
	public void setModelo(IModelo modelo);

	/**
	 * Establece la instancia de la vista que se utilizar� en la aplicaci�n.
	 * 
	 * @param vista
	 */
	public void setVista(IVista vista);

	/**
	 * M�todo que intenta deshacer la aplicaci�n de la �ltima herramienta.
	 * <p>
	 * Este m�todo trata de deshacer los cambios realizados por la �ltima
	 * herramienta aplicada sobre el modelo, elimin�ndola de la lista si es
	 * exitoso.
	 * 
	 * @return 0 en el caso de �xito, c�digo de error en otro caso.
	 */
	public int deshacer();

	/**
	 * M�todo que intenta aplicar una herramienta sobre el modelo.
	 * <p>
	 * Este m�todo intenta aplicar una herramienta sobre el modelo, y en el caso
	 * de �xito la almacena para poder deshacer los cambios. Concluya con
	 * �xito o fracaso, se lo indica al usuario
	 * 
	 * @param herramienta IHerramienta con la herramienta que se desea aplicar al modelo
	 * @return int 0 en caso de �xito, c�digo de error en otro caso.
	 * @roseuid 45B8A7B80386
	 */
	public int herramienta(IHerramienta herramienta);

	/**
	 * M�todo para el que controlador indique a la vista que han cambiado los coches y los
	 * debe pintar.<p>
	 */
	public void repintarCoches();
	
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
	
	public boolean mapaTieneHospital();
	
	public List getHistorial();

	public boolean mapaTieneLineasBus();

	public boolean cambiosEnMapa();
	
	public boolean limpiarDatosSimulacion();
}
