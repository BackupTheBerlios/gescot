package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoHighway implements ITipoElemento {

	/**
	 * Características de Nodos asociadas a Carreteras (Highway)
	 */
	private String tipo="highway";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un nodo asociado a una carretera)
	 *  mini_roundabout:	 Mini-rotonda: Una rotonda que sólo consta de una marca vial en el suelo. 	
		stop:	Señal de stop.	
		traffic_signals:	Señales de tráfico, o semáforo. 	
		crossing:	Cruce.	
		gate:	Portón (para uso de vehículos).	
		stile:	Una puerta en un muro o valla, para uso de peatones. 	
		cattle_grid:	Puertas en vallas para ganado, para paso de vehículos o peatones. 	
		toll_booth: Cabina de peaje (principalmente para pago de peaje, también máquinas expendedoras de tickets relativos a un sistema de peaje, etc)	
		incline:	Cambio de rasante. 	
		incline_steep:	Cambio de rasante pronunciado	
		bridge:	Puente 	
		viaduct:	Viaducto. 		
		ford	Punto de un río que se puede atravesar fácilmente sin un puente. 	
		bus_stop	Parada de autobús 	
		User Defined
	 */
	private String valorTipo;
	
	/**
	 * Tabla de 2 columnas utilizada para traducir términos relativos a valores de este tipo de elemento: 
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	private String[][] tablaTraduccion;
	
	public TipoNodoHighway(String valorTipo) {
		tablaTraduccion=crearTablaTraduccion();
		String valorOSM =traduciraOSM(valorTipo);
		this.valorTipo = valorOSM;
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	private String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {"Mini-rotonda","mini_roundabout"}, 
								   {"Stop","stadium"},
								   {"Cruce","crossing"},
								   {"Portón para vehículos","gate"},
								   {"Cambio De Rasante","incline"},
								   {"Puente","bridge"},
								   {"Viaducto","viaduct"},	
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

	public String getValorTipoCastellano() {
		String s=traduciraCastellano(valorTipo);
		return s;
	}

	public String getTipoCastellano() {
		return "Carretera";
	}

}
