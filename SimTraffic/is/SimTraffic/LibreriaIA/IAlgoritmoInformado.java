package is.SimTraffic.LibreriaIA;

/**
 * Interfaz genérica que deben implementar todos los algoritmos informados que se 
 * quieran añadir a la aplicación. Además de los métodos de cualqueir algoritmo, se 
 * obliga a que todo algortimo informado trabaje a partir del valor de una heurística.
 *
 */
public interface IAlgoritmoInformado extends IAlgoritmo {
	public float darValorHeuristico(IEstado estado);
}
