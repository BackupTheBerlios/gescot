package is.SimTraffic.Simulacion;

import java.util.ArrayList;
import java.util.Vector;

import is.SimTraffic.LibreriaIA.IEstado;
import is.SimTraffic.LibreriaIA.IOperador;
import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.LibreriaIA.Algoritmos.AEstrella;
import is.SimTraffic.LibreriaIA.Algoritmos.CosteUniforme;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.ExploraNodo;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.PrincipalDistanciaNodos;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

public class BuscaCamino {
	
	static int maxSimultaneos = Simulacion.maxVehiculos / 50 + 4;
	
	static BuscaCamino[] instancia = new BuscaCamino[maxSimultaneos];
	
	static int cont = 0;
	
	static Simulacion simulacion;
	
	static void setSimulacion(Simulacion sim) {
		simulacion = sim;
	}
	
	static synchronized BuscaCamino obtenerInstancia() { //Faltaría introducirle la simulación, una buena solución no es inmediata.
		cont = (cont + 1) % maxSimultaneos;
		if (instancia[cont] == null) {
			instancia[cont] = new BuscaCamino();
		}
		return instancia[cont];
	}
	
	public BuscaCamino() {
	}
	
	public ArrayList<Tramo> buscar(Nodo entrada, Nodo salida) {
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		int tipoCoste = 1;
		IPrincipal problemaDistancias = new PrincipalDistanciaNodos(entrada,
				salida,simulacion,tipoCoste); //Se le permite conocer la simulación, de modo que se pueden evaluar
									//rutas en función del tráfico.
		/*
		AEstrella algoritmoAEstrella = new AEstrella(problemaDistancias
				.getEstadoInicial(), problemaDistancias.getEstadoObjetivo(),
				problemaDistancias.getOperadores(), problemaDistancias
						.getHeuristica());
		*/

		//public CosteUniforme(IEstado inicial,IEstado objetivo,Vector<IOperador> operadores,int tipoControlCiclos/*, InfoHabitaciones infoBase*/) {
		
		CosteUniforme algoritmoAEstrella = new CosteUniforme(problemaDistancias
				.getEstadoInicial(), problemaDistancias.getEstadoObjetivo(),
				problemaDistancias.getOperadores(), 3);
		
		boolean resul = algoritmoAEstrella.ejecutar();
		if (resul == false) {
			// no ha sido posible encontrar un camino entre los nodos
			return null;
		} else {
			for (int i = (algoritmoAEstrella.getSolucion().size()); i > 0; i--) {
				if (algoritmoAEstrella.getSolucion().elementAt(i - 1).getOperador() != null) {
					Tramo tramoAux = ((ExploraNodo) (algoritmoAEstrella
							.getSolucion().elementAt(i - 1).getOperador()))
							.getTramoElegido();
					tramos.add(tramoAux);
				}
			}
			return tramos;
		}
	}
}
