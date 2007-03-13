package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoLeisure implements ITipoElemento {

	/**
	 * Características de Nodos asociadas a  Recreo (Leisure)
	 */
	private String tipo="leisure";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un nodo asociado a un lugar de recreo)
	 *  sports_centre	Polideportivo
	 golf_course	Campo de golf
	 stadium	Estadio (con gradas)
	 marina	Marina para yates y barcos de recreo
	 track	Pista de carreras (ciclismo, galgos, hipódromo, etc)
	 pitch	Campo de deportes (campo de fútbol, cancha de baloncesto, pista de pádel, etc)
	 water_park	Parque acuático
	 slipway	Rampa para la botadura de barcos
	 fishing	Área de pesca
	 nature_reserve	Parque natural, reservas ecológicas
	 park	Parque
	 garden	Jardín
	 common	Plazas
	 User Defined
	 */
	private String valorTipo;
	
	/**
	 * Tabla de 2 columnas utilizada para traducir términos relativos a valores de este tipo de elemento: 
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	private String[][] tablaTraduccion;
	
	public TipoNodoLeisure(String vTipo) {
		tablaTraduccion=crearTablaTraduccion();
		String valorOSM =traduciraOSM(vTipo);
		this.valorTipo = valorOSM;
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	private String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {"Campo de golf","golf_course"}, 
								   {"Estadio","stadium"},
								   {"Marina","marina"},
								   {"Pista de carreras","track"},
								   {"Campo de deporte","pich"},
								   {"Parque acuático","water_park"},
								   {"Parque","park"},
								   {"Jardín","garden"},	
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
	
	public void setValorTipo(String valorTipo) {
		this.valorTipo=valorTipo;
	}
	
	public String getValorTipoCastellano() {
		String s=traduciraCastellano(valorTipo);
		return s;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getTipoCastellano() {
		return "Tiempo Libre";
	}

}
