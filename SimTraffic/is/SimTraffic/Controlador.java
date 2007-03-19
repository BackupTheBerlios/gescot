package is.SimTraffic;

import java.util.List;
import java.util.ArrayList;
import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Vista.IVista;

/**
 * @author Grupo ISTrafico
 * 
 */
public class Controlador implements IControlador {
	/**
	 * Mantiene la instancia acutal de la vista
	 */
	public IVista vista;

	/**
	 * Mantiene la instancia actual del modelo
	 */
	public IModelo modelo;

	/**
	 * Lista de herramientas apliacadas sobre el modelo, para poder deshacer los
	 * cambios realizados
	 */
	public List<IHerramienta> herramientas;

	/**
	 * Constructor de la clase.
	 * <p>
	 * Metodo que simplemente inicializa la lista. Todav�a hace falta establecer
	 * el modelo y la vista que se desean utilizar.
	 * 
	 * @roseuid 45B8B3A90134
	 */
	public Controlador() {
		herramientas = new ArrayList<IHerramienta>();
	}

	/**
	 * @roseuid 45C1E08103C8
	 */
	public void crear() {

	}

	/**
	 * @param herramienta
	 * @return int
	 * @roseuid 45C1E08103E7
	 */
	public int herramienta(IHerramienta herramienta) {
		// habria que pensar limitar el tama�o de la cola
		int resultado = herramienta.hacer(modelo);
		if (resultado != 0) { // fallo
			return resultado;
		}
		herramientas.add(herramienta);
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.IControlador#deshacer()
	 */
	public int deshacer() {
		modelo.getMapa().limpiaSeleccion();
		if (herramientas.size() > 0) {
			int resultado = herramientas.get(herramientas.size() - 1).deshacer(
					modelo);
			if (resultado == 0) {
				herramientas.remove(herramientas.size() - 1);
				return 0;
			}
			//Si el resultado de la �ltima accion es mayor que 0
			//es porque es una accion que no se puede deshacer pero se pueden seguir
			//deshaciendo las anteriores(ejemplo: guardar). 
			//En este caso se pasa a la siguiente accion
			else if (resultado>0){
				herramientas.remove(herramientas.size() - 1);				
				return deshacer();
			}
			//Si el resultado de la �ltima accion es menor que 0
			//es porque es una accion que no se puede deshacer y no se pueden seguir
			//deshaciendo las anteriores(ejemplo: nuevo mapa). 
			//En este caso se ya no se pasa a la siguiente accion
			else{
				return resultado;
			}
		}
		return 0; // no devuelve error, pero se podria indicar que no hay nada
					// para deshacer
	}

	/**
	 * @param tipoError
	 * @return String
	 * @roseuid 45C1E082004D
	 */
	public String obtenerMensajeError(int tipoError) {
		return null;
	}

	/**
	 * @param ayuda
	 * @return String
	 * @roseuid 45C1E082009B
	 */
	public String obtenerVinculoAyuda(int ayuda) {
		return null;
	}

	public void setModelo(IModelo modelo) {
		this.modelo = modelo;
	}

	public void setVista(IVista vista) {
		this.vista = vista;

	}
}
