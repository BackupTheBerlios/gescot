package is.SimTraffic.LibreriaIA;

/**
 * Interfaz gen�rica que deben implementar todos los juegos (subproblemas) al definirse. 
 * Representa un m�dulo que asignar� un valor heur�stico a cada estado de un juego, respecto
 * a una funci�n heur�stica definida y acorde al problema. En general se ha trabajado suponiendo
 * que la heuristica da un valor heuristico por primera vez a un objeto de referencia IEstado, 
 * y luego ese Estado concreto almacena su valor.  
 *
 */
public interface IHeuristica {
	public float darValorHeuristico(IEstado estado);
}
