package is.SimTraffic.Mapa.TipoNodo;

public class TipoNodoLeisure implements TipoNodo {

	/**
	 * Características de Nodos asociadas a  Recreo (Leisure)
	 */
	private char tipo="leisure";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un nodo asociado a un lugar de recreo)
	 *  sports_centre	Polideportivo
	 golf_course	Campo de golf
	 stadium	Estadio (con gradas)
	 marina	Marina para yates y barcos de recreo
	 track	Pista de carreras (ciclismo, galgos, hipódromo, etc)
	 pitch	Campo de deportes (campo de fútbol, cancha de baloncesto, pista de pádel, etc)
	 water_park	Parque acuático
	 slipway	Rampa para la botadura de barcos
	 fishing	Área de pesca
	 nature_reserve	Parque natural, reservas ecológicas
	 park	Parque
	 garden	Jardín
	 common	Plazas
	 User Defined
	 */
	private char valorTipo;
	
	public TipoNodoLeisure(char valorTipo) {
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
