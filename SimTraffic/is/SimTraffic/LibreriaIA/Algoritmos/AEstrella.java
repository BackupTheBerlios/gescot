package is.SimTraffic.LibreriaIA.Algoritmos;

import is.SimTraffic.LibreriaIA.ComparadorNodosValorHyCoste;
import is.SimTraffic.LibreriaIA.IAlgoritmoInformado;
import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.IHeuristica;
import is.SimTraffic.LibreriaIA.IOperador;
import is.SimTraffic.LibreriaIA.NodoIA;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Vector;


/**
 * Algoritmo informado A*.
 *
 */
public class AEstrella implements IAlgoritmoInformado {

	IHeuristica heuristica;
	IEstado inicial;
	IEstado objetivo;
	Vector<IOperador> operadores;
	
	/**
	 * La estructura abiertos es una cola con prioridad, y los nodos se ordenarán en este
	 * algoritmo por el valor heurístico y por el coste actual.
	 */
	PriorityQueue<NodoIA> abiertos;
	Vector<NodoIA> cerrados;
	Vector<NodoIA> solucion;
	Comparator comparador;

	/**
	 * Constructor por defecto.
	 */
	public AEstrella(IEstado inicial,IEstado objetivo,Vector<IOperador> operadores, IHeuristica heuristica/*,InfoHabitaciones infoBase*/) {
		this.inicial = inicial;
		this.objetivo = objetivo;
		this.operadores=operadores;
		comparador=new ComparadorNodosValorHyCoste();
		this.abiertos = new PriorityQueue<NodoIA>(1,comparador);
		this.cerrados = new Vector<NodoIA>();
		this.solucion = new Vector<NodoIA>();
		this.heuristica = heuristica;
	}
	
	public float darValorHeuristico(IEstado estado) {
		return heuristica.darValorHeuristico(estado);
	}
	
	public String toString() {
		String s="A estrella (A*)";
		return s;
	}

	public Vector<NodoIA> getSolucion() {
		return solucion;
	}

	public void setSolucion(Vector<NodoIA> solucion) {
		this.solucion = solucion;
	}

	public PriorityQueue<NodoIA> getAbiertos() {
		return abiertos;
	}

	public void setAbiertos(PriorityQueue<NodoIA> abiertos) {
		this.abiertos = abiertos;
	}

	public IEstado getInicial() {
		return inicial;
	}

	public void setInicial(IEstado inicial) {
		this.inicial = inicial;
	}

	public IEstado getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(IEstado objetivo) {
		this.objetivo = objetivo;
	}
	public Vector<NodoIA> getCerrados() {
		return cerrados;
	}

	public void setCerrados(Vector<NodoIA> cerrados) {
		this.cerrados = cerrados;
	}


	public void generarNodosHijos(NodoIA nodo){
		
		for (int i=0;i<operadores.size();i++) {
			Vector<NodoIA> vectorNuevo=operadores.elementAt(i).aplicarOperador(nodo);
			
			//Se compara con null ya que si el operador no puede aplicarse o 
			//desemboca en un nodo en situación de peligro devuelve null.
			if (vectorNuevo!=null) {
				for (int j=0;j<vectorNuevo.size();j++) {
					NodoIA nodoNuevo=vectorNuevo.get(j);
					float valorh=darValorHeuristico(nodoNuevo.getEstado());
					nodoNuevo.getEstado().setValorHeuristico(valorh); //Creo que esta segunda linea no necesaria
					
					//Esta función ya se encarga si es necesario de añadir el nodo a la estructura abiertos.
					tratarSucesor(nodoNuevo,nodo);

				}
			}
		}

	}
	
	public void tratarSucesor(NodoIA nodoNuevo,NodoIA nodoPadre) {
		//1º Comprobar si está en cerrados
		boolean estaCerrados=false;
		int posicion=0;
		for (int i=0;(i<cerrados.size()) && (!estaCerrados);i++) {
			estaCerrados = cerrados.get(i).getEstado().equals(nodoNuevo.getEstado());
			posicion=i;
		}
		//Si el nodo nuevo ya estaba en cerrados, se comprueba si el coste de su camino era menor. En ese caso
		//se actualiza y se propaga a los hijos (en abiertos y en cerrados)
		if (estaCerrados) {
			
			if (cerrados.get(posicion).getCoste_camino() > nodoNuevo.getCoste_camino() ) {
				cerrados.get(posicion).setNodoPadre(nodoPadre); //Con lo cual, es como insertar el nuevo.
				//Actualizar valor del coste del camino y calcular su diferencia (para propagarla)
				float diferenciaCoste=cerrados.get(posicion).getCoste_camino() - nodoNuevo.getCoste_camino();
				cerrados.get(posicion).setCoste_camino(nodoNuevo.getCoste_camino());
				propagarMejorCosteCamino(nodoPadre,diferenciaCoste);
			}
			
			//Si el coste del camino es mayor en el nodo nuevo nos quedamos con el que ya había, 
			//y no añadiremos el nodo.
		}
		
		//Sino estaba en cerrados, se busca si está en abiertos.
		else {
			boolean encontrado=false;
			Iterator<NodoIA> nod= abiertos.iterator();
			NodoIA nodoaux = null;
			posicion=0;
			while (nod.hasNext() && !encontrado) {
				nodoaux = nod.next();
				encontrado = nodoaux.getEstado().equals(nodoNuevo.getEstado());
				posicion++;
			}
			if (encontrado) {
				if (nodoaux.getCoste_camino() > nodoNuevo.getCoste_camino()) {
					nodoaux.setNodoPadre(nodoPadre); //Con lo cual, es como insertar el nuevo.
					nodoaux.setCoste_camino(nodoNuevo.getCoste_camino());					
					//Añadido y necesario para respetar el camino
					nodoaux.setOperador(nodoNuevo.getOperador());
					nodoaux.setProfundidad(nodoNuevo.getProfundidad());
				}
			}
			else {
				abiertos.add(nodoNuevo);
			}
		}
			
	}
	
	/**
	 * Se propaga a los sucesores (identificados porque su nodoPadre es el nodoPadre que se pasa como parámetro)
	 * @param nodoPadre
	 * @param diferenciaCoste
	 */
	public void propagarMejorCosteCamino(NodoIA nodoPadre,float diferenciaCoste) {
		NodoIA nodoaux;
		NodoIA nodoaux1;
		Iterator<NodoIA> nod = cerrados.iterator();
		while (nod.hasNext()) {
			nodoaux1 = nod.next(); //Para propagar los cambios
			nodoaux = nodoaux1.getNodoPadre(); //Para el padre
			if (nodoaux == nodoPadre) {
				nodoaux1.setCoste_camino(nodoaux1.getCoste_camino()-diferenciaCoste);
				propagarMejorCosteCamino(nodoaux1,diferenciaCoste);
				System.out.println("Propagado en cerrados\n");
			}			
		}
		
		nod=abiertos.iterator();
		while (nod.hasNext()) {
			nodoaux1 = nod.next(); //Para propagar los cambios
			nodoaux = nodoaux1.getNodoPadre(); //Para el padre
			if (nodoaux == nodoPadre) {
				nodoaux1.setCoste_camino(nodoaux1.getCoste_camino()-diferenciaCoste);
				//Aquí ya no es necesario propagar el mejor coste, pues si está en abiertos no tiene hijos.
			}			
		}		
		
	}
		
	
	public boolean ejecutar() {
		NodoIA nodoActual=new NodoIA(inicial);
		abiertos.add(nodoActual);
		boolean encontreSolucion = false;
		boolean falloProducido = pararEjecucion();
		
		while ((!abiertos.isEmpty()) && !(esObjetivo(nodoActual)) && !falloProducido){
			nodoActual = abiertos.poll();
			generarNodosHijos(nodoActual);
			cerrados.add(nodoActual);
			//System.out.println("Nuevo nodo expandido pasa a cerrados:");
			//nodoActual.mostrarInfo();
			nodoActual = abiertos.peek();
			//Así permitimos detener la búsqueda al encontrar un fallo.
			falloProducido = pararEjecucion();
		}
		
		if (nodoActual!= null && esObjetivo(nodoActual)) {
			//Éxito
			System.out.println("Éxito");
			solucion=crearSolucion(nodoActual);
			mostrarSolucion(solucion);
			encontreSolucion = true;
		}
		else {
			System.out.println("No hay solución");
		}
		
		return encontreSolucion;
		
	}
	
	public boolean pararEjecucion(){
		return false;
	}
	
	public boolean esObjetivo(NodoIA nodo) {
		return (nodo.getEstado()).equals(objetivo);
	}
	
	/**
	 * Devuelve la solución en orden inverso, del nodo hoja al nodo raíz.
	 */
	public Vector<NodoIA> crearSolucion(NodoIA hoja) {
		Vector<NodoIA> s=new Vector<NodoIA>();
		s.add(hoja);
		NodoIA nodoPadre = hoja.getNodoPadre();
		while (nodoPadre.getNodoPadre()!=null) {
			s.add(nodoPadre);
			nodoPadre = nodoPadre.getNodoPadre();
		}
		return s;
	}
	

	public void mostrarSolucion(Vector<NodoIA> sol) {
		if (sol==null || sol.size()==0) 
			System.out.println("No se ha encontrado solución"); 
		else {
			System.out.println("Solución encontrada: (" + sol.size() + " pasos)");
			for (int i=(sol.size()); i>0 ; i--) {
				System.out.println("Operador "+(sol.size()-i)+": "+sol.elementAt(i-1).getOperador().getDescripcion() );
			}
		}
		
		//Datos de la ejecución del algoritmo
		System.out.println("Tamaño de abiertos"+abiertos.size());
		System.out.println("Tamaño de cerrados"+cerrados.size());		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
