package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoHighway implements ITipoElemento {

	/**
	 * Caracter�sticas de Nodos asociadas a Carreteras (Highway)
	 */
	private String tipo="highway";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un nodo asociado a una carretera)
	 *  mini_roundabout:	 Mini-rotonda: Una rotonda que s�lo consta de una marca vial en el suelo. 	
		stop:	Se�al de stop.	
		traffic_signals:	Se�ales de tr�fico, o sem�foro. 	
		crossing:	Cruce.	
		gate:	Port�n (para uso de veh�culos).	
		stile:	Una puerta en un muro o valla, para uso de peatones. 	
		cattle_grid:	Puertas en vallas para ganado, para paso de veh�culos o peatones. 	
		toll_booth: Cabina de peaje (principalmente para pago de peaje, tambi�n m�quinas expendedoras de tickets relativos a un sistema de peaje, etc)	
		incline:	Cambio de rasante. 	
		incline_steep:	Cambio de rasante pronunciado	
		bridge:	Puente 	
		viaduct:	Viaducto. 		
		ford	Punto de un r�o que se puede atravesar f�cilmente sin un puente. 	
		bus_stop	Parada de autob�s 	
		User Defined
	 */
	private String valorTipo;
	
	/**
	 * Tabla de 2 columnas utilizada para traducir t�rminos relativos a valores de este tipo de elemento: 
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in ingl�s (en formato osm).
	 */
	private String[][] tablaTraduccion;
	
	public TipoNodoHighway(String valorTipo) {
		tablaTraduccion=crearTablaTraduccion();
		String valorOSM =traduciraOSM(valorTipo);
		this.valorTipo = valorOSM;
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in ingl�s (en formato osm).
	 */
	private String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {"Mini-rotonda","mini_roundabout"}, 
								   {"Stop","stadium"},
								   {"Cruce","crossing"},
								   {"Port�n para veh�culos","gate"},
								   {"Cambio De Rasante","incline"},
								   {"Puente","bridge"},
								   {"Viaducto","viaduct"},	
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
