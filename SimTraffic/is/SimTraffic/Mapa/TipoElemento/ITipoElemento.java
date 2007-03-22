package is.SimTraffic.Mapa.TipoElemento;

/**
 * Expresa las caracter�sticas f�sicas y no f�sicas de un nodo o una v�a, en 2 categor�as:
 * tipo y valorTipo. La variable tipo se referir� a la categor�a m�s general (Carreteras, Ocio, etc.)
 * y valorTipo a una descripci�n concreta (Cruce, Hospital, Parada de bus, etc.). Ambas se almacenar�n 
 * en formato OSM (con las palabras en ingl�s).
 * 
 * Aunque tendr�n prioridad en esta aplicaci�n los nodos y v�as de tipo highway (asociados a carreteras),
 * se permitir� insertar otor tipo de nodos y v�as que ser�n adecuados para representar el entorno del 
 * mapa, aunque no se trate de la finalidad misma de la aplicaci�n. El dise�o con este interfaz permite 
 * la inclusi�n de forma sencilla de nuevos tipos de elementos si el formato OSM se ampl�a.
 *
 */
public interface ITipoElemento {
	/**
	 * Devolver� una variable tipo que se referir� a la categor�a m�s general del elemento.
	 * @return Tipo del elemento en formato OSM
	 */
	public String getTipo();
	
	/**
	 * Devolver� una variable valorTipo que se referir� a una descripci�n concreta (Cruce, Hospital, 
	 * Parada de bus, etc.) relativa a la categor�a general que indique tipo.
	 * @return Descripcion concreta del elemento en formato OSM
	 */
	public String getValorTipo();
	
	/**
	 * Fijar� el valorTipo, es decir, la descripci�n concreta del elemento
	 * al valor pasado por par�metro.
	 * @param valorTipo String con el valorTipo a fijar.
	 */
	public void setValorTipo(String valorTipo);
	
	/**
	 * Las variables de estos tipos de elementos se almacenan en ingl�s (por respetar las palabras 
	 * utilizadas en el est�ndar osm). Con esta funci�n se devolver� el valor en castellano de la 
	 * categor�a del tipo de elemento que utilizan los interfaces gr�ficos dise�ados por la aplicaci�n.
	 * @return Tipo del elemento en castellano.
	 */
	public String getTipoCastellano();	
	
	/**
	 * Las variables de estos tipos de elementos se almacenan en ingl�s (por respetar las palabras 
	 * utilizadas en el est�ndar osm). Con esta funci�n se devolver� el valor actual en castellano 
	 * de la descripci�n concreta del tipo de elemento (la que utilizan los interfaces gr�ficos 
	 * dise�ados por la aplicaci�n.
	 * @return Descripci�n conreta del elemento en castellano.
	 */
	public String getValorTipoCastellano();
	
	/**
	 * M�todo que toma una palabra en castellano relativa a un valor concreto de valorTipo y 
	 * devuelve su traducci�n en formato osm. Si no encuentra la traducci�n devuelve la palabra 
	 * dada.
	 * @param valorTipo Descripci�n concreta del elemento en castellano.
	 * @return Descripci�n concreta del elemento en formato OSM.
	 */
	public String traduciraOSM(String valorTipo);
	
	/**
	 * M�todo que toma una palabra en formato osm (en ingl�s) relativa a un valor concreto de 
	 * valorTipo y devuelve su traducci�n en formato osm. Si no encuentra la traducci�n devuelve 
	 * la palabra dada.
	 * @param valorTipo Descripci�n concreta del elemento en formato OSM
	 * @return Descripcion conreta del elemento en formato OSM.
	 */
	public String traduciraCastellano(String valorTipo);
}
