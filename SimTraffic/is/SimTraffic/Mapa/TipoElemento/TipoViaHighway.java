package is.SimTraffic.Mapa.TipoElemento;

import java.io.Serializable;

public class TipoViaHighway extends TipoElemento implements ITipoElemento, Serializable {

	/**
	 * Caracter�sticas de Tipo de V�as asociadas a Carreteras (Highway)
	 */
	//private String tipo="highway";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un tipo de carretera para una v�a)
	 *  motorway	 Autopistas[1] y Autov�as[2], desde la se�al de inicio de autopista/autov�a (S-1/S-1a) hasta la se�al de fin de autopista/autov�a (S-2/S-2a). Constan de hitos kilom�tricos S-570, y su identificaci�n se encuadra en se�ales S-410/S-410a de fondo azul.
	 	motorway_link	Enlaces a autopistas y autov�as: Rampas de acceso/salida, nudos de autopista, o cualquier carretera que enlaza una autopista/autov�a con otro tipo de v�a.	
	 	trunk	Carreteras nacionales (en tramos que no sean autopista/autov�a). Se caracterizan porque su identificaci�n empieza con N- (N-1, N-6, N-110, etc) y se encuadra en una se�al S-420, con fondo rojo. Tienen hitos kilom�tricos S-572 	
	 	trunk_link	Rampas de entrada/salida a carreteras nacionales, o v�as que conectan carreteras nacionales con otro tipo de v�as. En caso de duda entre motorway_link y trunk_link, usar motorway_link.
	 	primary	Carreteras auton�micas de primer nivel. La identificaci�n comienza por las iniciales de la provincia o comunidad aut�noma correspondiente, y se encuadra en una se�al S-430 de fondo naranja.
	 	primary_link	V�as que conectan carreteras secundarias con cualquier otro tipo de v�a.	
	 	secondary	Carreteras auton�micas de segundonivel. La identificaci�n comienza por las iniciales de la provincia o comunidad aut�noma correspondiente, y se encuadra en una se�al S-430 de fondo verde.
	 	tertiary	Carreteras auton�micas de tercer nivel. La identificaci�n comienza por las iniciales de la provincia o comunidad aut�noma correspondiente, y se encuadra en una se�al S-430 de fondo amarillo.	
	 	unclassified	Otras carreteras, sin identificaci�n propia. Se incluyen las v�as de servicio de autopistas/autov�as.
	 	track 	Caminos forestales, caminos sin pavimentar para uso agr�cola, y en general cualquier camino sin pavimentar no apto para la circulaci�n de tr�fico rodado. Tambi�n, carreteras con el pavimento en muy malas condiciones.
	 	residential	Calles dentro de un n�cleo urbano, excluyendo las traves�as (que tendr�n una denominaci�n m�s alta)
	 	service	V�as de servicio; accesos a p�rkings, gasolineras y edificios de uso p�blico; pistas de frenado de emergencia; zonas de descanso en autopistas; balanzas para pesaje de camiones; v�as de peaje o fronteras; por extensi�n, cualquier v�a cuya principal finalidad no sea la del tr�nsito de veh�culos.
	 	bridleway	(No aplicable en Espa�a) 	
	 	cycleway	V�as de uso exclusivamente para bicicletas, "Carril-bici".
	 	footway	V�as para uso exclusivo de peatones, que no son calles peatonales. Tambi�n, pasos elevados/subterr�neos para peatones (v�anse las secciones sobre t�neles y puentes).
	 	pedestrian 	Calles peatonales (incluso si se permite el paso de veh�culos de emergencias y servicios)	
	 	steps	Tramos de escaleras (en v�as para uso de peatones)	
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
	 * M�todo que traduce de las palabras utilizadas por el usuario (castellano) 
	 * a las palabras que utiliza el est�ndar osm (en ingl�s).
	 */
	/*public String traduciraOSM(String valorTipo) {
		String traducido=valorTipo;
		/*if (valorTipo.equals("")) traducido="";
		else if (valorTipo.equals("Mini-rotonda")) traducido="mini_roundabout";
		
		return traducido;
	}*/
	
	/**
	 * M�todo que traduce de las palabras utilizadas por el est�ndar osm (en ingl�s) 
	 * a las palabras que utiliza el usuario (en castellano).
	 */
	/*public String traduciraCastellano(String valorTipo) {
		return null;
	}*/
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in ingl�s (en formato osm).
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
								   //{"","bridleway"}, (No tiene equivalente en Espa�a)
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
