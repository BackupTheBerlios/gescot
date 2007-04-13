package is.SimTraffic.LibreriaIA;

/**
 * Interfaz gen�rica que deben implementar todos los algoritmos informados que se 
 * quieran a�adir a la aplicaci�n. Adem�s de los m�todos de cualqueir algoritmo, se 
 * obliga a que todo algortimo informado trabaje a partir del valor de una heur�stica.
 *
 */
public interface IAlgoritmoInformado extends IAlgoritmo {
	public float darValorHeuristico(IEstado estado);
}
