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
	
	public TipoNodoHighway(String valorTipo) {
		// TODO Auto-generated constructor stub
		String valorOSM =traducirOSM(valorTipo);
		this.valorTipo = valorTipo;
	}
	
	public String traducirOSM(String valorTipo) {
		String traducido=valorTipo;
		if (valorTipo.equals("")) traducido="";
		else if (valorTipo.equals("Mini-rotonda")) traducido="mini_roundabout";
		else if (valorTipo.equals("Stop")) traducido="Stop";
		else if (valorTipo.equals("Cruce")) traducido="crossing";
		else if (valorTipo.equals("Portón para vehículos")) traducido="gate";
		else if (valorTipo.equals("Cambio De Rasante")) traducido="incline";
		else if (valorTipo.equals("Puente")) traducido="bridge";
		else if (valorTipo.equals("Viaducto")) traducido="viaduct";
		return traducido;
	}
	
	/*public String[] getPosiblesValoresTipoCastellano() {
		String[] s={"Mini-rotonda","Stop","Cruce","Portón para vehículos", "Cambio De Rasante", "Puente", "Viaducto"};
		return s;
	}*/

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

}
