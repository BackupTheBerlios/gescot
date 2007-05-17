package is.SimTraffic.Mapa.TipoElemento;

import is.SimTraffic.Messages;

public class TipoNodoManMade extends TipoElemento implements ITipoElemento {

	/**
	 * Características de Nodos asociadas a Construcciones (man_made)
	 */
	//private String tipo="man_made";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  Palabras reservadas para los valores concretos de osm (un nodo asociado a una construccion)
	 *  works	
	 beacon	Baliza
	 power_wind	Planta de energía eólica
	 power_hydro	Planta hidroeléctrica
	 power_fossil	Central eléctrica de combustión (petróleo, gas, carbón)
	 power_nuclear	Central nuclear.
	 tower	Torre	
	 lighthouse	Faro (en la costa)
	 User Defined
	 */
	//private String valorTipo;
	
	public TipoNodoManMade(String vTipo) {
		super(vTipo);
		tipo=Messages.getString("TipoNodoManMade.0"); //$NON-NLS-1$
	}
	
	/**
	 * Tabla de 2 columnas utilizada para traducir términos relativos a valores de este tipo de elemento:
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	public String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {Messages.getString("TipoNodoManMade.1"),Messages.getString("TipoNodoManMade.2")},  //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoManMade.3"),Messages.getString("TipoNodoManMade.4")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoManMade.5"),Messages.getString("TipoNodoManMade.6")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoManMade.7"),Messages.getString("TipoNodoManMade.8")},  //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoManMade.9"),Messages.getString("TipoNodoManMade.10")}, //$NON-NLS-1$ //$NON-NLS-2$
									};
		return tTraduccion;
	}

	public String getTipoCastellano() {
		return Messages.getString("TipoNodoManMade.11"); //$NON-NLS-1$
	}

}
