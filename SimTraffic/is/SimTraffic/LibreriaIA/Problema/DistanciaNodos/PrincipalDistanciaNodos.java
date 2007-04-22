package is.SimTraffic.LibreriaIA.Problema.DistanciaNodos;

import java.util.Vector;

import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.IHeuristica;
import is.SimTraffic.LibreriaIA.IOperador;
import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.Mapa.Nodo;

public class PrincipalDistanciaNodos implements IPrincipal {

	Vector<IOperador> operadores;
	IEstado estadoInicial;
	IEstado estadoObjetivo;
	IHeuristica heuristica;
	
	/**
	 * 0 - El coste es la distancia.
	 * 1 - El coste es la distancia + la densidad del tramo.
	 */
	int tipoCoste;
	
	//Constructor que se adapta a la informaci�n que recibe del usuario (nodoOrigen y nodoDestino)
	public PrincipalDistanciaNodos(Nodo nodoInicial, Nodo nodoObjetivo) {
		super();
		// TODO Auto-generated constructor stub
		estadoInicial = new EstadoDistanciaNodos(nodoInicial);
		estadoObjetivo = new EstadoDistanciaNodos(nodoObjetivo);
		operadores=generarOperadores();
		heuristica=seleccionarHeuristica();
		this.tipoCoste = 0;
	}
	
	public PrincipalDistanciaNodos(Nodo nodoInicial, Nodo nodoObjetivo, int tipoCoste) {
		super();
		// TODO Auto-generated constructor stub
		estadoInicial = new EstadoDistanciaNodos(nodoInicial);
		estadoObjetivo = new EstadoDistanciaNodos(nodoObjetivo);
		operadores=generarOperadores();
		heuristica=seleccionarHeuristica();
		this.tipoCoste = tipoCoste;
	}

	public PrincipalDistanciaNodos(IEstado inicial, IEstado objetivo, IHeuristica heuristica, Vector<IOperador> operadores) {
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
	}

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
		//Generar uno aleatorio, aqui no tiene sentido m�s que para pruebas internas.
		return null;
	}

	public IEstado generarEstadoObjetivo() {
		//Generar uno aleatorio, aqui no tiene sentido m�s que para pruebas internas.
		return null;
	}

	public Vector<IOperador> generarOperadores() {
		Vector<IOperador> vOperadores=new Vector<IOperador>();
		IOperador operador = new ExploraNodo(tipoCoste);
		
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
