package is.SimTraffic.LibreriaIA;

/**
 * Interfaz gen�rica que deben implementar todos los juegos (subproblemas) al definirse. 
 * Representa la informaci�n necesaria para diferenciar los estados de un juego, obligando 
 * la interfaz a que el estado pueda evaluarse y devolver su valor heur�stico (en general, 
 * almacenado en el estado aunque calculado en su IHeur�stica correspondiente), adem�s de 
 * obligar a que los estados puedan compararse con la funci�n equals, requisito para poder 
 * hallar soluciones. Si el estado no sabe evaluarse y devolver un valor heur�stico, deber� 
 * reescribir al menos a un valor constante el m�todos getValorHeuristico.
 *
 */
public interface IEstado {
	public float getValorHeuristico();
	public void setValorHeuristico(float valorHeuristico);
	public boolean equals(IEstado estado);
}
