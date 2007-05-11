package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoAmenity extends TipoElemento implements ITipoElemento {

	/**
	 * Características de Nodos asociadas a Infraestructuras (Amenity)
	 */
	//private String tipo="amenity";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un nodo asociado a una carretera)
	 *  pub	A place selling beer and other alcholic drinks; may also provide food or accommodation (UK)
		parking	Aparcamiento
		fuel	Gasolinera (también estaciones de gas butano/natural para taxis, keroseno para aviación, combustible para barcos, etc)
		telephone	Cabina de teléfono
		toilets	Aseos públicos
		public_building	Edificio de uso público
		place_of_worship	Iglesias, mezquitas, o templos de cualquier religión
		grave_yard	Cementerio
		post_office	Oficina de correos
		post_box	Buzón de correos
		school	Colegio o instituto
		supermarket	Supermercado o hipermercado
		hospital	Hospital
		library	Biblioteca
		police	Comisaría de policía
		fire_station	Parque de bomberos
		biergarten	Terrazas de bares y restaurantes (donde se sirve comida/bebida al aire libre)	
		restaurant	Restaurante (no de comida rápida)	
		fast_food	Restaurante de comida rápida	
		bus_station	Estación de autobuses (no parada de autobús)	
		theatre	Teatro
	 */
	//private String valorTipo;
	
	public TipoNodoAmenity(String vTipo) {
		super(vTipo);
		tipo="amenity";
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	public String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {"Aseos públicos","toilets"}, 
				   				   {"Buzón de correos","post_box"},
								   {"Cabina de teléfono","telephone"},
								   {"Cadena de comida rápida","fast_food"},
								   {"Cementerio","grave_yard"},
								   {"Colegio","school"},
								   {"Comisaría","police"},
								   {"Edificio público","public_building"},
								   {"Estación de autobus","bus_station"},
								   {"Gasolinera","fuel"},{"Parking","parking"},
								   {"Hospital","hospital"},
								   {"Iglesia","place_of_worship"},
								   {"Librería","library"}, 
								   {"Oficina de correos","post_office"},
								   {"Parque de bomberos","fire_station"},
								   {"Pub","pub"},  
								   {"Restaurante","restaurant"},
								   {"Supermercado","supermarket"},
								   {"Teatro","theatre"},
								   {"Terrazas (de hostelería)","biergarten"},
									};
		return tTraduccion;
	}

	public String getTipoCastellano() {
		return "Infraestructura";
	}

}
