package is.SimTraffic.LibreriaIA.Problema.DistanciaNodos;

import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.IOperador;
import is.SimTraffic.LibreriaIA.NodoIA;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Simulacion.Simulacion;

import java.util.Vector;


public class ExploraNodo implements IOperador {

	String descripcion;
	Nodo nodoOrigen;
	Tramo tramoElegido;
	int tipoCoste;
	Simulacion simulacion;
	
	public ExploraNodo(Nodo origen, Tramo elegido) {
		super();
		// TODO Auto-generated constructor stub
		nodoOrigen = origen;
		tramoElegido = elegido;
		this.tipoCoste = 1;
		descripcion = "Explora nodo"; //Habría que completarlo.
	}

	public ExploraNodo(int tipoCoste, Simulacion simulacion) {
		super();
		this.tipoCoste = tipoCoste;
		this.descripcion = "Explora Nodo";
		this.simulacion = simulacion;
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
		//El coste de momento es únicamente la longitud del tramo
		//if (tipoCoste == 1/* && simulacion!=null*/) {
		/*if (simulacion==null) {
			System.out.println("!!!!!!!!!!!!!$%&$(%%&/%%/%&/");
			return 1;
		}
		else if (tipoCoste == 1){
			System.out.println("Tipocoste = "+tipoCoste);
			double epsilon = 1;
			float densidad = (float) (simulacion.densidadTramo(tramoElegido)+epsilon);
			return tramoElegido.getLargo()+densidad;
		}
		else if (tipoCoste == 0) {
			return tramoElegido.getLargo();
		}
		else 
			return 1;
		else {
			double epsilon = 0.1;
			double densidad = (float) (simulacion.densidadTramo(tramoElegido)+epsilon);
			return (float) densidad;
		}*/
		//}
		if (tipoCoste==0)
			return tramoElegido.getLargo();
		else if (tipoCoste==1 && simulacion!=null) {
			float epsilon = 5;
			float densidad = (float) (simulacion.densidadTramo(tramoElegido)+tramoElegido.getLargo());
			if (densidad<epsilon)
				densidad = epsilon;
			return densidad;
		}
		else
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

	public Tramo getTramoElegido() {
		return tramoElegido;
	}

	public void setTramoElegido(Tramo tramoElegido) {
		this.tramoElegido = tramoElegido;
	}

}
