package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoManMade extends TipoElemento implements ITipoElemento {

	/**
	 * Características de Nodos asociadas a Construcciones (man_made)
	 */
	//private String tipo="man_made";
	
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
		tipo="man_made";
	}
	
	/**
	 * Tabla de 2 columnas utilizada para traducir términos relativos a valores de este tipo de elemento:
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	public String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {"Planta eólica","power_wind"}, 
								   {"Planta hidroeléctrica","power_hydro"},
								   {"Central eléctrica","power_fossil"},
								   {"Central nuclear","power_nuclear"},
								   {"Faro","lighthouse"}
									};
		return tTraduccion;
	}

	public String getTipoCastellano() {
		return "Construcción";
	}

}
