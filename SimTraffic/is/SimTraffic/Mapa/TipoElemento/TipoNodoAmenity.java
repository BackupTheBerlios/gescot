package is.SimTraffic.Mapa.TipoElemento;

import is.SimTraffic.Messages;

public class TipoNodoAmenity extends TipoElemento implements ITipoElemento {

	/**
	 * Características de Nodos asociadas a Infraestructuras (Amenity)
	 */
	//private String tipo="amenity";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		tipo=Messages.getString("TipoNodoAmenity.0"); //$NON-NLS-1$
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in inglés (en formato osm).
	 */
	public String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {Messages.getString("TipoNodoAmenity.1"),Messages.getString("TipoNodoAmenity.2")},  //$NON-NLS-1$ //$NON-NLS-2$
				   				   {Messages.getString("TipoNodoAmenity.3"),Messages.getString("TipoNodoAmenity.4")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.5"),Messages.getString("TipoNodoAmenity.6")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.7"),Messages.getString("TipoNodoAmenity.8")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.9"),Messages.getString("TipoNodoAmenity.10")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.11"),Messages.getString("TipoNodoAmenity.12")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.13"),Messages.getString("TipoNodoAmenity.14")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.15"),Messages.getString("TipoNodoAmenity.16")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.17"),Messages.getString("TipoNodoAmenity.18")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.19"),Messages.getString("TipoNodoAmenity.20")},{Messages.getString("TipoNodoAmenity.21"),Messages.getString("TipoNodoAmenity.22")}, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
								   {Messages.getString("TipoNodoAmenity.23"),Messages.getString("TipoNodoAmenity.24")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.25"),Messages.getString("TipoNodoAmenity.26")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.27"),Messages.getString("TipoNodoAmenity.28")},  //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.29"),Messages.getString("TipoNodoAmenity.30")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.31"),Messages.getString("TipoNodoAmenity.32")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.33"),Messages.getString("TipoNodoAmenity.34")},   //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.35"),Messages.getString("TipoNodoAmenity.36")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.37"),Messages.getString("TipoNodoAmenity.38")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.39"),Messages.getString("TipoNodoAmenity.40")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoAmenity.41"),Messages.getString("TipoNodoAmenity.42")}, //$NON-NLS-1$ //$NON-NLS-2$
									};
		return tTraduccion;
	}

	public String getTipoCastellano() {
		return Messages.getString("TipoNodoAmenity.43"); //$NON-NLS-1$
	}

}
