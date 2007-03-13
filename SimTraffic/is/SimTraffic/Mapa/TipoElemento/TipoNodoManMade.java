package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoManMade implements ITipoElemento {

	/**
	 * Caracter�sticas de Nodos asociadas a Construcciones (man_made)
	 */
	private String tipo="man_made";
	
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
	private String valorTipo;
	
	private String[][] tablaTraduccion;
	
	public TipoNodoManMade(String vTipo) {
		tablaTraduccion=crearTablaTraduccion();
		String valorOSM =traduciraOSM(vTipo);
		this.valorTipo = valorOSM;
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in ingl�s (en formato osm).
	 */
	private String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {"Planta e�lica","power_wind"}, 
								   {"Planta hidroel�ctrica","power_hydro"},
								   {"Central el�ctrica","power_fossil"},
								   {"Central nuclear","power_nuclear"},
								   {"Faro","lighthouse"}
									};
		return tTraduccion;
	}
	
	/**
	 * M�todo que traduce de las palabras utilizadas por el usuario (castellano) 
	 * a las palabras que utiliza el est�ndar osm (en ingl�s). Si no encuentra la 
	 * traducci�n devuelve la palabra dada.
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
	 * M�todo que traduce de las palabras utilizadas por el est�ndar osm (en ingl�s) 
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
		return "Construcci�n";
	}

}
