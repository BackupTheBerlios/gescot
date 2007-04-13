package is.SimTraffic.LibreriaIA;

import java.util.Vector;

/**
 * Interfaz genérica que deben implementar todos los algoritmos que se quieran 
 * añadir a la aplicación. Uniformiza los nombres de las funciones y define los
 * métodos necesarios para todo algoritmo, definiendo su comprotamiento.
 *
 */
public interface IAlgoritmo {
	public Vector<NodoIA> crearSolucion(NodoIA nodohoja);
	public boolean ejecutar();
	public boolean esObjetivo(NodoIA nodo);
	public String toString();
}
