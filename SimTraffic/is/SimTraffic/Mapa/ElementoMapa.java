package is.SimTraffic.Mapa;

import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

/**
 * Interface de los elemntos del mapa.<p>
 * Interface que agrupa todos los tipos de elementos de mapa, para poder
 * recorrerlos m�s facilmente y agruparlos en estructuras comunes.
 * 
 * @author Grupo ISTrafico
 *
 */
public interface ElementoMapa {
	/**
	 * Devuelve el ID �nico necesario apra asignar un elemento en el formato osm
	 * @return
	 */
	public int getID();
	
	/**
	 * Devuelve un string con la traducci�n del elemento al formato osm, necesario 
	 * para el proceso de guardar el mapa. De este modo se modulariza (y simplifica) 
	 * el proceso de guardar el mapa, obligando a todo ElementoMapa a saber traducirse.
	 */
	
	public String transformaraOSM();
	
	/**
	 * Devuelve el tipo de informaci�n adicional que puede tener un elemento osm 
	 * (y que posteriormente se traducir� a informaci�n en tags).
	 * @return
	 */
	public ITipoElemento getTipo();
	
	/**
	 * Se obliga a todos los elementos de un mapa a tener un nombre (aunque �ste puede ser vac�o).
	 * @return
	 */
	public String getNombre();
}
