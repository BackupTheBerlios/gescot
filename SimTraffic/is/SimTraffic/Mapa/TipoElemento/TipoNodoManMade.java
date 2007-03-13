package is.SimTraffic.Mapa.TipoElemento;

public class TipoNodoManMade implements ITipoElemento {

	/**
	 * Caracter�sticas de Nodos asociadas a Construcciones (man_made)
	 */
	private String tipo="man_made";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un nodo asociado a una construccion)
	 *  works	
	 beacon	Baliza
	 power_wind	Planta de energ�a e�lica
	 power_hydro	Planta hidroel�ctrica
	 power_fossil	Central el�ctrica de combusti�n (petr�leo, gas, carb�n)
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
	
	//Por terminar
	public String traducirOSM(String valorTipo) {
		String traducido=valorTipo;
		/*if (valorTipo.equals("")) traducido="";
		else if (valorTipo.equals("Mini-rotonda")) traducido="mini_roundabout";
		*/
		return traducido;
	}
	
	public String[] getPosiblesValoresTipoCastellano() {
		String[] s={""};
		return s;
	}

	public String getTipo() {
		return tipo;
	}
	
	public String getValorTipo() {
		return valorTipo;
	}
	
	public String getValorTipoCastellano() {
		String s=traducirOSM(valorTipo);
		return s;
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
