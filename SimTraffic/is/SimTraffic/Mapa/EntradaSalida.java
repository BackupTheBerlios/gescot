package is.SimTraffic.Mapa;

/**
 * Esta clase define las propiedades de los nodos que estan configurados como
 * nodos de E/S
 * 
 * @author Grupo ISTrafico
 */
public class EntradaSalida {

	/**
	 * Este valor representa la frecuencia con la que salen los coches del nodo
	 */
	int cochesUnidadTiempo;

	/**
	 * Porcentajes entre 0 y 100 del numero total de coches del mapa en dicha
	 * franja horaria que salen por este punto de E/S. Esto es para que en cada
	 * franja horaria pueda salir diferente número de coches de cada nodo.
	 */
	int[] valoresFranjaHoraria;

	/**
	 * Contador que mide el número de coches que han entrado por este nodo en el
	 * mapa y no han salido del mapa, necesario para mantener constante el
	 * número de coches que hay en el mapa (para no sobrecargarlo).
	 */
	int cochesQueHanEntradoYnoHanSalido;

	public EntradaSalida(int cut, int[] franjas) {
		cochesUnidadTiempo = cut;
		valoresFranjaHoraria = franjas;
		cochesQueHanEntradoYnoHanSalido = 0;
	}

	/**
	 * Cada vez que un coche sale del mapa se puede volver a lanzar un coche
	 * desde este nodo,es lo que representan estos contadores Obviamente cuando
	 * un coche sale del mapa es porque entra por un nodo de e/s
	 */
	public void saleCocheDelMapa() {
		cochesQueHanEntradoYnoHanSalido--;
	}

	/**
	 * Cada vez que un coche entra en el mapa impide que se pueda lanzar un
	 * coche menos hasta que este salga del mapa. Obviamente cuando un coche
	 * entra en el mapa es porque sale de este nodo de e/s
	 */
	public void entraCocheEnMapa() {
		cochesQueHanEntradoYnoHanSalido++;
	}

	public int getCochesQueHanEntradoYnoHanSalido() {
		return cochesQueHanEntradoYnoHanSalido;
	}

	public void setCochesQueHanEntradoYnoHanSalido(
			int cochesQueHanEntradoYnoHanSalido) {
		this.cochesQueHanEntradoYnoHanSalido = cochesQueHanEntradoYnoHanSalido;
	}

	public int getCochesUnidadTiempo() {
		return cochesUnidadTiempo;
	}

	public void setCochesUnidadTiempo(int cochesUnidadTiempo) {
		this.cochesUnidadTiempo = cochesUnidadTiempo;
	}

	public int[] getValoresFranjaHoraria() {
		return valoresFranjaHoraria;
	}

	public void setValoresFranjaHoraria(int[] valoresFranjaHoraria) {
		this.valoresFranjaHoraria = valoresFranjaHoraria;
	}

}
