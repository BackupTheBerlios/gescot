package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoManMade implements ITipoElemento {

	/**
	 * Características de Nodos asociadas a Construcciones (man_made)
	 */
	private String tipo="man_made";
	
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
	private String valorTipo;
	
	private String[][] tablaTraduccion;
	
	public TipoNodoManMade(String vTipo) {
		tablaTraduccion=crearTablaTraduccion();
		String valorOSM =traduciraOSM(vTipo);
		this.valorTipo = valorOSM;
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	private String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {"Planta eólica","power_wind"}, 
								   {"Planta hidroeléctrica","power_hydro"},
								   {"Central eléctrica","power_fossil"},
								   {"Central nuclear","power_nuclear"},
								   {"Faro","lighthouse"}
									};
		return tTraduccion;
	}
	
	/**
	 * Método que traduce de las palabras utilizadas por el usuario (castellano) 
	 * a las palabras que utiliza el estándar osm (en inglés). Si no encuentra la 
	 * traducción devuelve la palabra dada.
	 */
	public String traduciraOSM(String vTipo) {
		String traducido=vTipo;
		
		if (vTipo.equals("")) return "";
		
		boolean encontrado=false;
		for (int i=0; (i<tablaTraduccion.length) && (!encontrado); i++) {
			if (tablaTraduccion[i][0].equals(vTipo)) {
				traducido=tablaTraduccion[i][1];
				encontrado=true;
			}
		}
		
		return traducido;
	}
	
	/**
	 * Método que traduce de las palabras utilizadas por el estándar osm (en inglés) 
	 * a las palabras que utiliza el usuario (en castellano).
	 */
	public String traduciraCastellano(String vTipo) {
		
		String traducido=vTipo;
		
		if (vTipo.equals("")) return "";
		
		boolean encontrado=false;
		for (int i=0; (i<tablaTraduccion.length) && (!encontrado); i++) {
			if (tablaTraduccion[i][1].equals(vTipo)) {
				traducido=tablaTraduccion[i][0];
				encontrado=true;
			}
		}
		
		return traducido;
	}

	public String getTipo() {
		return tipo;
	}
	
	public String getValorTipo() {
		return valorTipo;
	}
	
	public String getValorTipoCastellano() {
		String s=traduciraCastellano(valorTipo);
		return s;
	}
	
	/**
	 * 
	 */
	public void setValorTipo(String valorTipo) {
		this.valorTipo=valorTipo;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getTipoCastellano() {
		return "Construcción";
	}

}
