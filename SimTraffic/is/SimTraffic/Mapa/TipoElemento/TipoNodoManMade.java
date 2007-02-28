package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoManMade implements ITipoElemento {

	/**
	 * Características de Nodos asociadas a Construcciones (man_made)
	 */
	private String tipo="man_made";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un nodo asociado a una construccion)
	 *  works	
	 beacon	Baliza
	 power_wind	Planta de energía eólica
	 power_hydro	Planta hidroeléctrica
	 power_fossil	Central eléctrica de combustión (petróleo, gas, carbón)
	 power_nuclear	Central nuclear.
	 tower	Torre	
	 lighthouse	Faro (en la costa)
	 User Defined
	 */
	private String valorTipo;
	
	public TipoNodoManMade(String valorTipo) {
		// TODO Auto-generated constructor stub
		this.valorTipo = valorTipo;
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
