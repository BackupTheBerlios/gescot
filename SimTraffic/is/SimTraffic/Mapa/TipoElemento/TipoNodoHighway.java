package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoHighway extends TipoElemento implements ITipoElemento {

	/**
	 * Características de Nodos asociadas a Carreteras (Highway)
	 */
	//private String tipo="highway";
	
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
	//private String valorTipo;
	
	public TipoNodoHighway(String vTipo) {
		super(vTipo);
		tipo="highway";
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	public String[][] crearTablaTraduccion() {
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

	public String getTipoCastellano() {
		return "Carretera";
	}

}
