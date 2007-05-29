package is.SimTraffic.LibreriaIA.Problema.DistanciaNodos;

import java.util.Vector;

import is.SimTraffic.Messages;
import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.IHeuristica;
import is.SimTraffic.LibreriaIA.IOperador;
import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Simulacion.Simulacion;

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
		this.simulacion = null;
		estadoInicial = new EstadoDistanciaNodos(nodoInicial);
		estadoObjetivo = new EstadoDistanciaNodos(nodoObjetivo);
		this.tipoCoste = 1;
		operadores=generarOperadores();
		heuristica=seleccionarHeuristica();
	}
	
	public PrincipalDistanciaNodos(Nodo nodoInicial, Nodo nodoObjetivo,	Simulacion simulacion, int tipoCoste) {
		super();
		this.simulacion = simulacion;
		estadoInicial = new EstadoDistanciaNodos(nodoInicial);
		estadoObjetivo = new EstadoDistanciaNodos(nodoObjetivo);
		this.tipoCoste = tipoCoste;
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
		//Generar uno aleatorio, aqui no tiene sentido más que para pruebas internas.
		return null;
	}

	public IEstado generarEstadoObjetivo() {
		//Generar uno aleatorio, aqui no tiene sentido más que para pruebas internas.
		return null;
	}

	public Vector<IOperador> generarOperadores() {
		Vector<IOperador> vOperadores=new Vector<IOperador>();
		/*if (simulacion==null)
			System.out.println(Messages.getString("PrincipalDistanciaNodos.0")); //$NON-NLS-1$*/
		IOperador operador = new ExploraNodo(tipoCoste,simulacion);
		
		//Se inserta
		vOperadores.add(operador);
		
		return vOperadores;
	}

	public IHeuristica seleccionarHeuristica() {
		IHeuristica h=new HeuristicaDistanciaLineaRecta((EstadoDistanciaNodos)estadoObjetivo);
		return h;
	}
}
