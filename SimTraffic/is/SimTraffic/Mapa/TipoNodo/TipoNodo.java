package is.SimTraffic.Mapa.TipoNodo;

/**
 * Expresa las caracter�sticas f�sicas y no f�sicas de un nodo, en 2 categor�as:
 * tipo y valorTipo. La variable tipo se referir� a la categor�a m�s general (Carreteras, Ocio, etc.)
 * y valorTipo a una descripci�n concreta (cruce, hospital, parada de bus, etc.) 
 * 
 * En una primera etapa de desarrollo, todos los nodos ser�n de tipo highway (asociados a carreteras)
 *
 */
public interface TipoNodo {
	
	public char getTipo();
	public char getValorTipo();
	public void setValorTipo(char valorTipo);

}
