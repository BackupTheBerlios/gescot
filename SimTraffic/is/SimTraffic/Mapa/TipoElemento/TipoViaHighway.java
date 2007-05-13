package is.SimTraffic.Mapa.TipoElemento;

import java.io.Serializable;

public class TipoViaHighway extends TipoElemento implements ITipoElemento, Serializable {

	/**
	 * Características de Tipo de Vías asociadas a Carreteras (Highway)
	 */
	//private String tipo="highway";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un tipo de carretera para una vía)
	 *  motorway	 Autopistas[1] y Autovías[2], desde la señal de inicio de autopista/autovía (S-1/S-1a) hasta la señal de fin de autopista/autovía (S-2/S-2a). Constan de hitos kilométricos S-570, y su identificación se encuadra en señales S-410/S-410a de fondo azul.
	 	motorway_link	Enlaces a autopistas y autovías: Rampas de acceso/salida, nudos de autopista, o cualquier carretera que enlaza una autopista/autovía con otro tipo de vía.	
	 	trunk	Carreteras nacionales (en tramos que no sean autopista/autovía). Se caracterizan porque su identificación empieza con N- (N-1, N-6, N-110, etc) y se encuadra en una señal S-420, con fondo rojo. Tienen hitos kilométricos S-572 	
	 	trunk_link	Rampas de entrada/salida a carreteras nacionales, o vías que conectan carreteras nacionales con otro tipo de vías. En caso de duda entre motorway_link y trunk_link, usar motorway_link.
	 	primary	Carreteras autonómicas de primer nivel. La identificación comienza por las iniciales de la provincia o comunidad autónoma correspondiente, y se encuadra en una señal S-430 de fondo naranja.
	 	primary_link	Vías que conectan carreteras secundarias con cualquier otro tipo de vía.	
	 	secondary	Carreteras autonómicas de segundonivel. La identificación comienza por las iniciales de la provincia o comunidad autónoma correspondiente, y se encuadra en una señal S-430 de fondo verde.
	 	tertiary	Carreteras autonómicas de tercer nivel. La identificación comienza por las iniciales de la provincia o comunidad autónoma correspondiente, y se encuadra en una señal S-430 de fondo amarillo.	
	 	unclassified	Otras carreteras, sin identificación propia. Se incluyen las vías de servicio de autopistas/autovías.
	 	track 	Caminos forestales, caminos sin pavimentar para uso agrícola, y en general cualquier camino sin pavimentar no apto para la circulación de tráfico rodado. También, carreteras con el pavimento en muy malas condiciones.
	 	residential	Calles dentro de un núcleo urbano, excluyendo las travesías (que tendrán una denominación más alta)
	 	service	Vías de servicio; accesos a párkings, gasolineras y edificios de uso público; pistas de frenado de emergencia; zonas de descanso en autopistas; balanzas para pesaje de camiones; vías de peaje o fronteras; por extensión, cualquier vía cuya principal finalidad no sea la del tránsito de vehículos.
	 	bridleway	(No aplicable en España) 	
	 	cycleway	Vías de uso exclusivamente para bicicletas, "Carril-bici".
	 	footway	Vías para uso exclusivo de peatones, que no son calles peatonales. También, pasos elevados/subterráneos para peatones (véanse las secciones sobre túneles y puentes).
	 	pedestrian 	Calles peatonales (incluso si se permite el paso de vehículos de emergencias y servicios)	
	 	steps	Tramos de escaleras (en vías para uso de peatones)	
	 	User Defined
	 */
	//private String valorTipo;
	
	public TipoViaHighway(String valorTipo) {
		super(valorTipo);
		tipo="highway";
		// TODO Auto-generated constructor stub
		//this.valorTipo = valorTipo;
	}
	
	/**
	 * Método que traduce de las palabras utilizadas por el usuario (castellano) 
	 * a las palabras que utiliza el estándar osm (en inglés).
	 */
	/*public String traduciraOSM(String valorTipo) {
		String traducido=valorTipo;
		/*if (valorTipo.equals("")) traducido="";
		else if (valorTipo.equals("Mini-rotonda")) traducido="mini_roundabout";
		
		return traducido;
	}*/
	
	/**
	 * Método que traduce de las palabras utilizadas por el estándar osm (en inglés) 
	 * a las palabras que utiliza el usuario (en castellano).
	 */
	/*public String traduciraCastellano(String valorTipo) {
		return null;
	}*/
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	public String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {"Autovia"," motorway"}, 
								   {"Acceso/salida Autovia","motorway_link"},
								   {"Carretera Nacional","trunk"},
								   {"Acceso/salida Carretera Nacional","trunk_link"},
								   {"Carreteras principales","primary"},
								   {"Conexiones  principales y secundarias","primary_link"},
								   {"Carreteras secundarias","secondary"},
								   {"Carreteras terciarias","tertiary"},
								   {"Carreteras sin clasificar","unclassified"},
								   {"Caminos forestales","track"},
								   {"Calles residenciales","residential"},
								   {"Vias de servicio","service"},	
								   //{"","bridleway"}, (No tiene equivalente en España)
								   {"Carril-bici","cycleway"},
								   {"Via peatonal","footway"},
								   {"Calle peatonal","pedestrian"},
								   {"Escaleras","steps"},
								   //{"Definido por el usuario","User Defined"},
									};
		return tTraduccion;
	}
/*
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
	/*
	public void setValorTipo(String valorTipo) {
		this.valorTipo=valorTipo;
	}
	
	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

	public String getTipoCastellano() {
		return "Carretera";
	}


}
