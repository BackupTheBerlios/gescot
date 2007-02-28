package is.SimTraffic.Mapa.TipoNodo;

public class TipoNodoHighway implements TipoNodo {

	/**
	 * Características de Nodos asociadas a Carreteras (Highway)
	 */
	private char tipo="highway";
	
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
	private char valorTipo;
	
	public TipoNodoHighway(char valorTipo) {
		// TODO Auto-generated constructor stub
		this.valorTipo = valorTipo;
	}

	public char getTipo() {
		return tipo;
	}
	
	public char getValorTipo() {
		return valorTipo;
	}
	
	/**
	 * 
	 */
	public void setValorTipo(char valorTipo) {
		this.valorTipo=valorTipo;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
