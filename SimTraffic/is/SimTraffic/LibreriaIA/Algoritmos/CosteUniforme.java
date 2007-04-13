package is.SimTraffic.LibreriaIA.Algoritmos;

import is.SimTraffic.LibreriaIA.ComparadorNodosCoste;
import is.SimTraffic.LibreriaIA.IAlgoritmo;
import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.IOperador;
import is.SimTraffic.LibreriaIA.NodoIA;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Vector;


/**
 * Algoritmo no informado de Coste Uniforme.
 *
 */
public class CosteUniforme implements IAlgoritmo {

	IEstado inicial;
	IEstado objetivo;
	Vector<IOperador> operadores;
	/**
	 * La estructura abiertos es una cola con prioridad, y los nodos se ordenarán en este
	 * algoritmo por el coste actual de su camino desde la raiz hasta el nodo analizado.
	 */
	PriorityQueue<NodoIA> abiertos;
	Vector<NodoIA> cerrados;
	Vector<NodoIA> solucion;
	Comparator comparador;
	
	/**
	 * 0 - Sin control de ciclos
	 * 1 - Evitar operadores inversos (deben informar de ello los operadores). (Muy bajo coste)
	 * 2 - Evitar un estado repetido en todo el camino. (Coste medio-bajo, depende de la profundidad)
	 * 3 - Mirar en cada paso que el estado a expandir no esté en cerrados. (Coste muy alto)
	 * 4 - Mirar en cada paso que el estado a expandir no esté en abiertos. (Coste muy alto)
	 */
	int tipoControlCiclos;

	/**
	 * Constructor por defecto.
	 */
	public CosteUniforme(IEstado inicial,IEstado objetivo,Vector<IOperador> operadores,int tipoControlCiclos/*, InfoHabitaciones infoBase*/) {
		this.inicial = inicial;
		this.objetivo = objetivo;
		this.operadores=operadores;
		comparador=new ComparadorNodosCoste();
		this.abiertos = new PriorityQueue<NodoIA>(1,comparador);
		this.cerrados = new Vector<NodoIA>();
		this.solucion = new Vector<NodoIA>();
		this.tipoControlCiclos = tipoControlCiclos;
	}
	
	public String toString() {
		String s="Coste Uniforme";
		return s;
	}

	public Vector getSolucion() {
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
					boolean detectaCiclos = controlDeCiclos(nodoNuevo);
					if (!detectaCiclos) abiertos.add(nodoNuevo);
				}
			}
		}

	}
	
	/**
	 * Módulo detector de ciclos, para poder elegir distintos métodos de controlar los ciclos
	 * @param nodo Nodo a analizar.
	 * @return Devuelve verdadero si se ha detectado un ciclo, y falso en caso contrario.
	 */
	public boolean controlDeCiclos(NodoIA nodo) {
		boolean detectaCiclos=false;
		if (tipoControlCiclos==0)
			detectaCiclos=false;
		else if (tipoControlCiclos==1) {
			//Se comprueba primero que no sea vacío, lo que pasa si es el primer hijo 
			//generado (en ese caso el padre sería la raíz y no tendría operador).
			if (nodo.getNodoPadre().getOperador()!=null) {
				detectaCiclos = nodo.getOperador().esInversoDe(nodo.getNodoPadre().getOperador());
			}
		}
		else if (tipoControlCiclos==2) {
			//Comprueba en su propio camino
			NodoIA aux=nodo;
			while (aux.getNodoPadre()!=null && !detectaCiclos) {
				detectaCiclos = nodo.getEstado().equals(aux.getNodoPadre().getEstado());
				aux=aux.getNodoPadre();
			}
		}
		else if (tipoControlCiclos==3) {
			//Recorre cerrados
			for (int i=0;(i<cerrados.size()) && (!detectaCiclos);i++) {
				detectaCiclos = cerrados.get(i).getEstado().equals(nodo.getEstado());
			}
		}
		else if (tipoControlCiclos==4) {
			//Recorre abiertos
			Iterator<NodoIA> ab=abiertos.iterator();
			for (int i=0;i<abiertos.size() && !detectaCiclos;i++) {
				NodoIA aux=ab.next();
				detectaCiclos = aux.getEstado().equals(nodo.getEstado());
			}
		}
		
		
		
		return detectaCiclos;
	}
	
	public boolean ejecutar() {
		NodoIA nodoActual=new NodoIA(inicial);
		abiertos.add(nodoActual);
		boolean encontreSolucion = false;
		boolean falloProducido = pararEjecucion();
		
		while ((!abiertos.isEmpty()) && !(esObjetivo(nodoActual)) && !falloProducido){
			nodoActual = abiertos.poll(); //Toma el primer elemento y lo elimina de la cola con prioridad.
			generarNodosHijos(nodoActual);
			cerrados.add(nodoActual);
			System.out.println("Nuevo nodo expandido pasa a cerrados:");
			nodoActual.mostrarInfo();
			nodoActual = abiertos.peek(); //Toma el primer elemento, pero no lo elimina de la cola con prioridad.
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
