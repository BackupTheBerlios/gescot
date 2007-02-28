package is.SimTraffic;

import java.util.List;
import java.util.ArrayList;
import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Vista.IVista;

public class Controlador implements IControlador {
	public IVista vista;

	public IModelo modelo;

	public List<IHerramienta> herramientas;
	/**
	 * @roseuid 45B8B3A90134
	 */
	public Controlador() {
		herramientas = new ArrayList<IHerramienta>();
	}

	public void setModelo(IModelo modelo) {
		this.modelo = modelo;
	}

	public void setVista(IVista vista) {
		this.vista = vista;

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
		int resultado = herramienta.hacer(modelo);
		if (resultado != 0) { // fallo
			return resultado;
		}
		herramientas.add(herramienta);
		return 0;
	}

	public int deshacer() {
		int resultado = herramientas.get(herramientas.size()).deshacer(modelo);
		if (resultado == 0) {
			herramientas.remove(herramientas.size());
			return 0;
		}
		return resultado;
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
}
