package is.SimTraffic.LibreriaIA;

import java.util.Vector;

/**
 * Interfaz gen�rica que deben implementar todos los juegos (subproblemas) al definirse. 
 * Representa la informaci�n necesaria para cambiar el estado de un juego realizando una operaci�n.
 * Todos los jeugos pueden definirse a partir de acciones m�s simples, y son estas acciones las que
 * deben implementar esta interfaz, que le obliga a rellenar unso datos m�nimos sobre la funci�n: La 
 * forma en que trata el nodo recibido y el nodo (0, 1 o m�s) hijo producido, la descripci�n del 
 * operador, el coste y una funci�n espec�fica de cada oeprador para detectar los operadores inversos.
 *
 */
public interface IOperador {
	public Vector<NodoIA> aplicarOperador(NodoIA nodo);
	public String getDescripcion();
	public float getCoste();
	public boolean esInversoDe(IOperador operador);
}
