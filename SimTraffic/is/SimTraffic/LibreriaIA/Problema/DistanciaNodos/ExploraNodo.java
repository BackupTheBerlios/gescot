package is.SimTraffic.LibreriaIA.Problema.DistanciaNodos;

import is.SimTraffic.Messages;
import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.IOperador;
import is.SimTraffic.LibreriaIA.NodoIA;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Simulacion.Simulacion;

import java.util.Iterator;
import java.util.Vector;


public class ExploraNodo implements IOperador {

	String descripcion;
	Nodo nodoOrigen;
	Tramo tramoElegido;
	int tipoCoste;
	Simulacion simulacion;
	
	public ExploraNodo(Nodo origen, Tramo elegido) {
		super();
		nodoOrigen = origen;
		tramoElegido = elegido;
		this.tipoCoste = 1;
		descripcion = Messages.getString("ExploraNodo.0"); //Habría que completarlo. //$NON-NLS-1$
	}

	public ExploraNodo(int tipoCoste, Simulacion simulacion) {
		super();
		this.tipoCoste = tipoCoste;
		this.descripcion = Messages.getString("ExploraNodo.1"); //$NON-NLS-1$
		this.simulacion = simulacion;
	}

	public Vector<NodoIA> aplicarOperador(NodoIA nodo) {
		Vector<NodoIA> vector= new Vector<NodoIA>();
		Nodo nodoDestino;
		nodoOrigen = ((EstadoDistanciaNodos)nodo.getEstado()).getNodoPosicion();
		Iterator<Tramo> itTramo = nodoOrigen.getTramos().iterator(); 
		while(itTramo.hasNext()) {
			tramoElegido = itTramo.next();
			int carriles;
			if (tramoElegido.getNodoInicial() != nodoOrigen) {
				nodoDestino = tramoElegido.getNodoInicial();
				carriles = tramoElegido.getNumCarrilesDir2();
			}
			else {
				nodoDestino = tramoElegido.getNodoFinal();
				carriles = tramoElegido.getNumCarrilesDir1();
			}
			
			if (carriles > 0) {
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
		float largo = tramoElegido.getLargo();
		if (tipoCoste==0)
			return largo;
		else if (tipoCoste==1 && simulacion!=null) {
			float epsilon = 20;
			float constanteDensidadMaxima = (float) (1.2 * largo);
			float densidad = (float) (simulacion.densidadTramo(tramoElegido));
			if (densidad < epsilon)
				densidad = epsilon;
			else if (densidad > constanteDensidadMaxima)
				densidad = constanteDensidadMaxima;
			densidad = (densidad + largo);
			return densidad;
		}
		else
			return tramoElegido.getLargo();
	}

	public boolean esInversoDe(IOperador operador) {
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

	public Tramo getTramoElegido() {
		return tramoElegido;
	}

	public void setTramoElegido(Tramo tramoElegido) {
		this.tramoElegido = tramoElegido;
	}

}
