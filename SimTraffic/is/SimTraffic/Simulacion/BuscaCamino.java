package is.SimTraffic.Simulacion;

import java.util.ArrayList;

import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.LibreriaIA.Algoritmos.AEstrella;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.ExploraNodo;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.PrincipalDistanciaNodos;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

public class BuscaCamino {
	
	static int maxSimultaneos = 6;
	
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
		AEstrella algoritmoAEstrella = new AEstrella(problemaDistancias
				.getEstadoInicial(), problemaDistancias.getEstadoObjetivo(),
				problemaDistancias.getOperadores(), problemaDistancias
						.getHeuristica());
		
		boolean resul = algoritmoAEstrella.ejecutar();
		if (resul == false) {
			// no ha sido posible encontrar un camino entre los nodos
			return null;
		} else {
			// Mostrar solución en el mapa
			for (int i = (algoritmoAEstrella.getSolucion().size()); i > 0; i--) {
				if (algoritmoAEstrella.getSolucion().elementAt(i - 1)
						.getOperador() != null) {
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
