package is.SimTraffic.LibreriaIA.Problema.DistanciaNodos;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.IHeuristica;
import is.SimTraffic.LibreriaIA.IOperador;
import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Simulacion.Simulacion;
import is.SimTraffic.Simulacion.Vehiculo;

public class PrincipalDistanciaNodos implements IPrincipal {

	Vector<IOperador> operadores;
	IEstado estadoInicial;
	IEstado estadoObjetivo;
	IHeuristica heuristica;
	Simulacion simulacion;
	
	/**
	 * 0 - El coste es la distancia.
	 * 1 - El coste es la distancia + la densidad del tramo.
	 */
	int tipoCoste;
	
	//Constructor que se adapta a la información que recibe del usuario (nodoOrigen y nodoDestino).
	//Se utilizará cuando se quieran calcular recorridos sin haber iniciado una simulación.
	public PrincipalDistanciaNodos(Nodo nodoInicial, Nodo nodoObjetivo) {
		super();
		// TODO Auto-generated constructor stub
		this.simulacion = null;
		estadoInicial = new EstadoDistanciaNodos(nodoInicial);
		estadoObjetivo = new EstadoDistanciaNodos(nodoObjetivo);
		this.tipoCoste = 1;
		operadores=generarOperadores();
		heuristica=seleccionarHeuristica();
	}
	
	/*public PrincipalDistanciaNodos(Nodo nodoInicial, Nodo nodoObjetivo, Simulacion simulacion) {
		super();
		// TODO Auto-generated constructor stub
		this.simulacion = simulacion;
		estadoInicial = new EstadoDistanciaNodos(nodoInicial);
		estadoObjetivo = new EstadoDistanciaNodos(nodoObjetivo);
		operadores=generarOperadores();
		heuristica=seleccionarHeuristica();
		this.tipoCoste = 0;*/
	//}
	
	public PrincipalDistanciaNodos(Nodo nodoInicial, Nodo nodoObjetivo,	Simulacion simulacion, int tipoCoste) {
		super();
		// TODO Auto-generated constructor stub
		this.simulacion = simulacion;
		estadoInicial = new EstadoDistanciaNodos(nodoInicial);
		estadoObjetivo = new EstadoDistanciaNodos(nodoObjetivo);
		this.tipoCoste = tipoCoste;
		operadores=generarOperadores();
		heuristica=seleccionarHeuristica();
	}

	/*public PrincipalDistanciaNodos(IEstado inicial, IEstado objetivo, IHeuristica heuristica, Vector<IOperador> operadores) {
		super();
		// TODO Auto-generated constructor stub
		estadoInicial = inicial;
		estadoObjetivo = objetivo;
		this.heuristica = heuristica;
		this.operadores = operadores;
	}

	public PrincipalDistanciaNodos() {
		super();
		estadoInicial=generarEstadoInicial();
		estadoObjetivo=generarEstadoObjetivo();
		operadores=generarOperadores();
		heuristica=seleccionarHeuristica();
	}*/

	public IEstado getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(IEstado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public IEstado getEstadoObjetivo() {
		return estadoObjetivo;
	}

	public void setEstadoObjetivo(IEstado estadoObjetivo) {
		this.estadoObjetivo = estadoObjetivo;
	}

	public Vector<IOperador> getOperadores() {
		return operadores;
	}

	public void setOperadores(Vector<IOperador> operadores) {
		this.operadores = operadores;
	}
	
	public IHeuristica getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(IHeuristica heuristica) {
		this.heuristica = heuristica;
	}

	public IEstado generarEstadoInicial() {
		//Generar uno aleatorio, aqui no tiene sentido más que para pruebas internas.
		return null;
	}

	public IEstado generarEstadoObjetivo() {
		//Generar uno aleatorio, aqui no tiene sentido más que para pruebas internas.
		return null;
	}

	public Vector<IOperador> generarOperadores() {
		Vector<IOperador> vOperadores=new Vector<IOperador>();
		if (simulacion==null)
			System.out.println("DDDDD44444");
		IOperador operador = new ExploraNodo(tipoCoste,simulacion);
		
		//Se inserta
		vOperadores.add(operador);
		
		return vOperadores;
	}

	public IHeuristica seleccionarHeuristica() {
		IHeuristica h=new HeuristicaDistanciaLineaRecta((EstadoDistanciaNodos)estadoObjetivo);
		return h;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
