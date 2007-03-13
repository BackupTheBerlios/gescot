package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoLeisure implements ITipoElemento {

	/**
	 * Características de Nodos asociadas a  Recreo (Leisure)
	 */
	private String tipo="leisure";
	
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
	private String valorTipo;
	
	public TipoNodoLeisure(String valorTipo) {
		// TODO Auto-generated constructor stub
		this.valorTipo = valorTipo;
	}
	
	//Por terminar
	public String traducirOSM(String valorTipo) {
		String traducido=valorTipo;
		/*if (valorTipo.equals("")) traducido="";
		else if (valorTipo.equals("Mini-rotonda")) traducido="mini_roundabout";
		*/
		return traducido;
	}

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
	
	public String getValorTipoCastellano() {
		String s=traducirOSM(valorTipo);
		return s;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
