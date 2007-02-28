package is.SimTraffic.Mapa.TipoNodo;

public class TipoNodoHighway implements TipoNodo {

	/**
	 * Caracter�sticas de Nodos asociadas a Carreteras (Highway)
	 */
	private char tipo="highway";
	
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
