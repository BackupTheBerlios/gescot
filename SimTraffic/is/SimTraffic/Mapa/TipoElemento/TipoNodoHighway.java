package is.SimTraffic.Mapa.TipoElemento;

import is.SimTraffic.Messages;

public class TipoNodoHighway extends TipoElemento implements ITipoElemento {

	/**
	 * Características de Nodos asociadas a Carreteras (Highway)
	 */
	//private String tipo="highway";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		User Defined	Definido por el usuario
	 */
	//private String valorTipo;
	
	public TipoNodoHighway(String vTipo) {
		super(vTipo);
		tipo=Messages.getString("TipoNodoHighway.0"); //$NON-NLS-1$
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	public String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {Messages.getString("TipoNodoHighway.1"),Messages.getString("TipoNodoHighway.2")}, //$NON-NLS-1$ //$NON-NLS-2$
				   				   {Messages.getString("TipoNodoHighway.3"),Messages.getString("TipoNodoHighway.4")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoHighway.5"),Messages.getString("TipoNodoHighway.6")},  //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoHighway.7"),Messages.getString("TipoNodoHighway.8")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoHighway.9"),Messages.getString("TipoNodoHighway.10")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoHighway.11"),Messages.getString("TipoNodoHighway.12")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoHighway.13"),Messages.getString("TipoNodoHighway.14")},	 //$NON-NLS-1$ //$NON-NLS-2$
									};
		return tTraduccion;
	}

	public String getTipoCastellano() {
		return Messages.getString("TipoNodoHighway.15"); //$NON-NLS-1$
	}

}
