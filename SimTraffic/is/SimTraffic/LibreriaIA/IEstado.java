package is.SimTraffic.LibreriaIA;

/**
 * Interfaz genérica que deben implementar todos los juegos (subproblemas) al definirse. 
 * Representa la información necesaria para diferenciar los estados de un juego, obligando 
 * la interfaz a que el estado pueda evaluarse y devolver su valor heurístico (en general, 
 * almacenado en el estado aunque calculado en su IHeurística correspondiente), además de 
 * obligar a que los estados puedan compararse con la función equals, requisito para poder 
 * hallar soluciones. Si el estado no sabe evaluarse y devolver un valor heurístico, deberá 
 * reescribir al menos a un valor constante el métodos getValorHeuristico.
 *
 */
public interface IEstado {
	public float getValorHeuristico();
	public void setValorHeuristico(float valorHeuristico);
	public boolean equals(IEstado estado);
}
