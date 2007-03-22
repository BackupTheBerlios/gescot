package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoAmenity extends TipoElemento implements ITipoElemento {

	/**
	 * Caracter�sticas de Nodos asociadas a Infraestructuras (Amenity)
	 */
	//private String tipo="amenity";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un nodo asociado a una carretera)
	 *  pub	A place selling beer and other alcholic drinks; may also provide food or accommodation (UK)
		parking	Aparcamiento
		fuel	Gasolinera (tambi�n estaciones de gas butano/natural para taxis, keroseno para aviaci�n, combustible para barcos, etc)
		telephone	Cabina de tel�fono
		toilets	Aseos p�blicos
		public_building	Edificio de uso p�blico
		place_of_worship	Iglesias, mezquitas, o templos de cualquier religi�n
		grave_yard	Cementerio
		post_office	Oficina de correos
		post_box	Buz�n de correos
		school	Colegio o instituto
		supermarket	Supermercado o hipermercado
		hospital	Hospital
		library	Biblioteca
		police	Comisar�a de polic�a
		fire_station	Parque de bomberos
		biergarten	Terrazas de bares y restaurantes (donde se sirve comida/bebida al aire libre)	
		restaurant	Restaurante (no de comida r�pida)	
		fast_food	Restaurante de comida r�pida	
		bus_station	Estaci�n de autobuses (no parada de autob�s)	
		theatre	Teatro
	 */
	//private String valorTipo;
	
	public TipoNodoAmenity(String vTipo) {
		super(vTipo);
		tipo="amenity";
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in ingl�s (en formato osm).
	 */
	public String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {"Pub","pub"}, 
								   {"Parking","parking"},
								   {"Gasolinera","fuel"},
								   {"Cabina de tel�fono","telephone"},
								   {"Aseos p�blicos","toilets"},
								   {"Edificio p�blico","public_building"},
								   {"Iglesia","place_of_worship"},
								   {"Cementerio","grave_yard"}, 
								   {"Oficina de correos","post_office"},
								   {"Buz�n de correos","post_box"},
								   {"Cabina de tel�fono","telephone"},
								   {"Colegio","school"},
								   {"Supermercado","supermarket"},
								   {"Hospital","hospital"},
								   {"Librer�a","library"}, 
								   {"Comisar�a","police"},
								   {"Parque de bomberos","fire_station"},
								   {"Terrazas (de hosteler�a)","biergarten"},
								   {"Restaurante","restaurant"},
								   {"Cadena de comida r�pida","fast_food"},
								   {"Estaci�n de autobus","bus_station"},
								   {"Teatro","theatre"},
									};
		return tTraduccion;
	}

	public String getTipoCastellano() {
		return "Infraestructura";
	}

}
