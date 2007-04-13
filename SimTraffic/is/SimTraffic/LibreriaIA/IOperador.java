package is.SimTraffic.LibreriaIA;

import java.util.Vector;

/**
 * Interfaz genérica que deben implementar todos los juegos (subproblemas) al definirse. 
 * Representa la información necesaria para cambiar el estado de un juego realizando una operación.
 * Todos los jeugos pueden definirse a partir de acciones más simples, y son estas acciones las que
 * deben implementar esta interfaz, que le obliga a rellenar unso datos mínimos sobre la función: La 
 * forma en que trata el nodo recibido y el nodo (0, 1 o más) hijo producido, la descripción del 
 * operador, el coste y una función específica de cada oeprador para detectar los operadores inversos.
 *
 */
public interface IOperador {
	public Vector<NodoIA> aplicarOperador(NodoIA nodo);
	public String getDescripcion();
	public float getCoste();
	public boolean esInversoDe(IOperador operador);
}
