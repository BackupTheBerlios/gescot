package is.SimTraffic.Mapa.TipoElemento;

/**
 * Expresa las características físicas y no físicas de un nodo o una vía, en 2 categorías:
 * tipo y valorTipo. La variable tipo se referirá a la categoría más general (Carreteras, Ocio, etc.)
 * y valorTipo a una descripción concreta (cruce, hospital, parada de bus, etc.) 
 * 
 * En una primera etapa de desarrollo, todos los nodos y vías serán de tipo highway (asociados a carreteras)
 *
 */
public interface ITipoElemento {

	public String getTipo();
	public String getValorTipo();
	public void setValorTipo(String valorTipo);
	//public String[] getPosiblesValoresTipoCastellano();
}
