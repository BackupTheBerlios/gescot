package is.SimTraffic.LibreriaIA.Problema.DistanciaNodos;

import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.IOperador;
import is.SimTraffic.LibreriaIA.NodoIA;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

import java.util.Vector;


public class ExploraNodo implements IOperador {

	String descripcion;
	Nodo nodoOrigen;
	Tramo tramoElegido;
	
	public ExploraNodo(Nodo origen, Tramo elegido) {
		super();
		// TODO Auto-generated constructor stub
		nodoOrigen = origen;
		tramoElegido = elegido;
		descripcion = "Explora nodo"; //Habr�a que completarlo.
	}

	public ExploraNodo() {
		super();
		this.descripcion = "Explora Nodo";
	}

	public Vector<NodoIA> aplicarOperador(NodoIA nodo) {
		Vector<NodoIA> vector= new Vector<NodoIA>();
		Nodo nodoDestino;
		nodoOrigen = ((EstadoDistanciaNodos)nodo.getEstado()).getNodoPosicion();
		for (int i=0;i<nodoOrigen.getTramos().size();i++) {
			tramoElegido = nodoOrigen.getTramos().get(i);
			
			if ( tramoElegido.getNodoInicial() != nodoOrigen )
				nodoDestino = tramoElegido.getNodoInicial();
			else
				nodoDestino = tramoElegido.getNodoFinal();
			
			if ( (tramoElegido.getNodoInicial() == nodoOrigen && tramoElegido.getNumCarrilesDir1()==0)
				|| (tramoElegido.getNodoFinal() == nodoOrigen && tramoElegido.getNumCarrilesDir2()==0) ) {
				//Hay tramo, pero no puede recorrerse en ese sentido.
			}
			else {
				IEstado estadoNuevo = new EstadoDistanciaNodos(nodoDestino);
				NodoIA nodoNuevo=new NodoIA(estadoNuevo);
				nodoNuevo.setNodoPadre(nodo);
				nodoNuevo.setCoste_camino(nodo.getCoste_camino()+this.getCoste());
				nodoNuevo.setOperador(new ExploraNodo(nodoOrigen,tramoElegido));
				nodoNuevo.setProfundidad(nodo.getProfundidad()+1);
				
				vector.add(nodoNuevo);
			}
				
		}
		return vector;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public float getCoste() {
		//El coste de momento es �nicamente la longitud del tramo
		return tramoElegido.getLargo();
	}

	public boolean esInversoDe(IOperador operador) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Nodo getNodoOrigen() {
		return nodoOrigen;
	}

	public void setNodoOrigen(Nodo nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}

}
