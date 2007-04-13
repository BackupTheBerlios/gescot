package is.SimTraffic.LibreriaIA;
import java.util.Vector;

/**
 * Interfaz genérica que deben implementar todos los juegos (subproblemas) al definirse. 
 * Representa la información necesaria para resolver un problema: Agrupa toda la información 
 * necesaria para definir un problema de inteligencia artificial, como es el estado inicial,
 * el estado objetivo, los operadores disponibles y la función heurística a utilizar. Además
 * se incluyen funciones también en el interfaz apra generar estos valores y mantener la 
 * uniformidad de nombres de la aplicación.  
 *
 */
public interface IPrincipal {
	public IEstado getEstadoObjetivo();
	public IEstado getEstadoInicial();
	public Vector<IOperador> getOperadores();
	public IHeuristica getHeuristica();
	public void setHeuristica(IHeuristica h);
	public IEstado generarEstadoInicial();
	public IEstado generarEstadoObjetivo();
	public Vector<IOperador> generarOperadores();
	public IHeuristica seleccionarHeuristica();
}
