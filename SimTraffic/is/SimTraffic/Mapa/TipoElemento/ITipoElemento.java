package is.SimTraffic.Mapa.TipoElemento;

/**
 * Expresa las características físicas y no físicas de un nodo o una vía, en 2 categorías:
 * tipo y valorTipo. La variable tipo se referirá a la categoría más general (Carreteras, Ocio, etc.)
 * y valorTipo a una descripción concreta (Cruce, Hospital, Parada de bus, etc.). Ambas se almacenarán 
 * en formato OSM (con las palabras en inglés).
 * 
 * Aunque tendrán prioridad en esta aplicación los nodos y vías de tipo highway (asociados a carreteras),
 * se permitirá insertar otor tipo de nodos y vías que serán adecuados para representar el entorno del 
 * mapa, aunque no se trate de la finalidad misma de la aplicación. El diseño con este interfaz permite 
 * la inclusión de forma sencilla de nuevos tipos de elementos si el formato OSM se amplía.
 *
 */
public interface ITipoElemento {
	/**
	 * Devolverá una variable tipo que se referirá a la categoría más general del elemento.
	 * @return Tipo del elemento en formato OSM
	 */
	public String getTipo();
	
	/**
	 * Devolverá una variable valorTipo que se referirá a una descripción concreta (Cruce, Hospital, 
	 * Parada de bus, etc.) relativa a la categoría general que indique tipo.
	 * @return Descripcion concreta del elemento en formato OSM
	 */
	public String getValorTipo();
	
	/**
	 * Fijará el valorTipo, es decir, la descripción concreta del elemento
	 * al valor pasado por parámetro.
	 * @param valorTipo String con el valorTipo a fijar.
	 */
	public void setValorTipo(String valorTipo);
	
	/**
	 * Las variables de estos tipos de elementos se almacenan en inglés (por respetar las palabras 
	 * utilizadas en el estándar osm). Con esta función se devolverá el valor en castellano de la 
	 * categoría del tipo de elemento que utilizan los interfaces gráficos diseñados por la aplicación.
	 * @return Tipo del elemento en castellano.
	 */
	public String getTipoCastellano();	
	
	/**
	 * Las variables de estos tipos de elementos se almacenan en inglés (por respetar las palabras 
	 * utilizadas en el estándar osm). Con esta función se devolverá el valor actual en castellano 
	 * de la descripción concreta del tipo de elemento (la que utilizan los interfaces gráficos 
	 * diseñados por la aplicación.
	 * @return Descripción conreta del elemento en castellano.
	 */
	public String getValorTipoCastellano();
	
	/**
	 * Método que toma una palabra en castellano relativa a un valor concreto de valorTipo y 
	 * devuelve su traducción en formato osm. Si no encuentra la traducción devuelve la palabra 
	 * dada.
	 * @param valorTipo Descripción concreta del elemento en castellano.
	 * @return Descripción concreta del elemento en formato OSM.
	 */
	public String traduciraOSM(String valorTipo);
	
	/**
	 * Método que toma una palabra en formato osm (en inglés) relativa a un valor concreto de 
	 * valorTipo y devuelve su traducción en formato osm. Si no encuentra la traducción devuelve 
	 * la palabra dada.
	 * @param valorTipo Descripción concreta del elemento en formato OSM
	 * @return Descripcion conreta del elemento en formato OSM.
	 */
	public String traduciraCastellano(String valorTipo);
}
