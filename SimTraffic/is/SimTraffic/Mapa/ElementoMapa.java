package is.SimTraffic.Mapa;

import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

/**
 * Interface de los elemntos del mapa.<p>
 * Interface que agrupa todos los tipos de elementos de mapa, para poder
 * recorrerlos más facilmente y agruparlos en estructuras comunes.
 * 
 * @author Grupo ISTrafico
 *
 */
public interface ElementoMapa {
	public int getID();
	public String transformaraOSM();
	public ITipoElemento getTipo();
}
