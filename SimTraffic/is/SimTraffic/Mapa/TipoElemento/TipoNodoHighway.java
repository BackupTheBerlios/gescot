package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoHighway extends TipoElemento implements ITipoElemento {

	/**
	 * Caracter�sticas de Nodos asociadas a Carreteras (Highway)
	 */
	//private String tipo="highway";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		User Defined	Definido por el usuario
	 */
	//private String valorTipo;
	
	public TipoNodoHighway(String vTipo) {
		super(vTipo);
		tipo="highway";
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in ingl�s (en formato osm).
	 */
	public String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {"Cambio De Rasante","incline"},
				   				   {"Cruce","crossing"},
								   {"Mini-rotonda","mini_roundabout"}, 
								   {"Port�n para veh�culos","gate"},
								   {"Puente","bridge"},
								   {"Stop","stadium"},
								   {"Viaducto","viaduct"},	
									};
		return tTraduccion;
	}

	public String getTipoCastellano() {
		return "Carretera";
	}

}
