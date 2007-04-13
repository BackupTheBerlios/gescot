package is.SimTraffic.LibreriaIA;

import java.util.Vector;

/**
 * Interfaz gen�rica que deben implementar todos los algoritmos que se quieran 
 * a�adir a la aplicaci�n. Uniformiza los nombres de las funciones y define los
 * m�todos necesarios para todo algoritmo, definiendo su comprotamiento.
 *
 */
public interface IAlgoritmo {
	public Vector<NodoIA> crearSolucion(NodoIA nodohoja);
	public boolean ejecutar();
	public boolean esObjetivo(NodoIA nodo);
	public String toString();
}
