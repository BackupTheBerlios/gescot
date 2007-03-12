package is.SimTraffic.Mapa.TipoElemento;

/**
 * Expresa las caracter�sticas f�sicas y no f�sicas de un nodo o una v�a, en 2 categor�as:
 * tipo y valorTipo. La variable tipo se referir� a la categor�a m�s general (Carreteras, Ocio, etc.)
 * y valorTipo a una descripci�n concreta (cruce, hospital, parada de bus, etc.) 
 * 
 * En una primera etapa de desarrollo, todos los nodos y v�as ser�n de tipo highway (asociados a carreteras)
 *
 */
public interface ITipoElemento {

	public String getTipo();
	public String getValorTipo();
	public void setValorTipo(String valorTipo);
	//public String[] getPosiblesValoresTipoCastellano();
}
