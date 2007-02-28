package is.SimTraffic.Mapa.TipoNodo;

/**
 * Expresa las características físicas y no físicas de un nodo, en 2 categorías:
 * tipo y valorTipo. La variable tipo se referirá a la categoría más general (Carreteras, Ocio, etc.)
 * y valorTipo a una descripción concreta (cruce, hospital, parada de bus, etc.) 
 * 
 * En una primera etapa de desarrollo, todos los nodos serán de tipo highway (asociados a carreteras)
 *
 */
public interface TipoNodo {
	
	public char getTipo();
	public char getValorTipo();
	public void setValorTipo(char valorTipo);

}
