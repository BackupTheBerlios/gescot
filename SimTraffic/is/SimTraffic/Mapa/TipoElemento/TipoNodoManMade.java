package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoManMade extends TipoElemento implements ITipoElemento {

	/**
	 * Caracter�sticas de Nodos asociadas a Construcciones (man_made)
	 */
	//private String tipo="man_made";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un nodo asociado a una construccion)
	 *  works	
	 beacon	Baliza
	 power_wind	Planta de energ�a e�lica
	 power_hydro	Planta hidroel�ctrica
	 power_fossil	Central el�ctrica de combusti�n (petr�leo, gas, carb�n)
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
	 * Tabla de 2 columnas utilizada para traducir t�rminos relativos a valores de este tipo de elemento:
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in ingl�s (en formato osm).
	 */
	public String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {"Planta e�lica","power_wind"}, 
								   {"Planta hidroel�ctrica","power_hydro"},
								   {"Central el�ctrica","power_fossil"},
								   {"Central nuclear","power_nuclear"},
								   {"Faro","lighthouse"}
									};
		return tTraduccion;
	}

	public String getTipoCastellano() {
		return "Construcci�n";
	}

}
