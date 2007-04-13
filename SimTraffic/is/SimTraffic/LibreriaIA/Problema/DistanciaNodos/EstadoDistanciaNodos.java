package is.SimTraffic.LibreriaIA.Problema.DistanciaNodos;

import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.NodoIA;
import is.SimTraffic.Mapa.Nodo;

public class EstadoDistanciaNodos implements IEstado {

	Nodo nodoPosicion;
	float valorHeuristico; //Se almacena el valor (aunque pueda calcularse) por cuestiones de eficiencia
	
	public EstadoDistanciaNodos(Nodo posicion) {
		super();
		nodoPosicion = posicion;
	}

	public float getValorHeuristico() {
		return valorHeuristico;
	}

	public void setValorHeuristico(float valorHeuristico) {
		this.valorHeuristico = valorHeuristico;
	}

	public boolean equals(IEstado estado) {
		//2 estados serán iguales si sus nodos son iguales (y no es necesario utilizar el método equals, pues
		//si dos nodos tienen la misma posición deben tener la misma referencia).
		if ( ((EstadoDistanciaNodos)estado).getNodoPosicion() == nodoPosicion )
			return true;
		else
			return false;
	}

	public Nodo getNodoPosicion() {
		return nodoPosicion;
	}

	public void setNodoPosicion(Nodo nodoPosicion) {
		this.nodoPosicion = nodoPosicion;
	}

}
