package is.SimTraffic.Simulacion;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.LibreriaIA.Algoritmos.AEstrella;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.ExploraNodo;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.PrincipalDistanciaNodos;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;

public class Ambulancia extends Vehiculo {
	private Random random = new Random();
	
	List<Nodo> hospitales;
	
	private ArrayList<Tramo> tramos = new ArrayList<Tramo>();
	private int cuentaTramos = 0;

	public Ambulancia(List<Nodo> hospitales) {
		// TODO este constructor deberia dar valores a todos
		//   los atributos de un vehiculo
		nombre = "Ambulancia";
		
		//Se genera un color aleatorio
		//generarColorAleatorio();
		this.color = Color.PINK;
		this.figura = new Rectangle2D.Double(-5, -RepresentacionSimple.tamaño_carril, 5,
				RepresentacionSimple.tamaño_carril);
		
		random = new Random();
		this.aceleracion = 0;
		this.aceleracionMax = (double) random.nextInt(30) / 100 + 0.2;
		this.distanciaSeguridad = 4;
		this.posicion = 0;
		this.velocidad = 0;
		this.velocidadMax = (double) random.nextInt(60) / 100 + 0.6;
		this.id = ncochesglobal;
		ncochesglobal++;

	}
	
	public synchronized void setTramo(Tramo tramo) {
		cuentaTramos++;
		this.tramo = tramo;
	}
	
	public boolean inicializar(Nodo entrada, Nodo salida) {
		super.inicializar(entrada, salida);
		Random random = new Random();
		entrada = hospitales.get(random.nextInt(hospitales.size()));
		
		IPrincipal problemaDistancias = new PrincipalDistanciaNodos(
				entrada, salida);
		AEstrella algoritmoAEstrella = new AEstrella(problemaDistancias
				.getEstadoInicial(), problemaDistancias.getEstadoObjetivo(),
				problemaDistancias.getOperadores(), problemaDistancias
						.getHeuristica());
		boolean resul = algoritmoAEstrella.ejecutar();
		if (resul == false) {
			// no ha sido posible encontrar un camino entre los nodos
			return false;
		} else {
			// Mostrar solución en el mapa
			tramos.clear();
			cuentaTramos = 0;
			
			for (int i = (algoritmoAEstrella.getSolucion().size()); i > 0 ; i--) {
			//for (int i = 0; i < (algoritmoAEstrella.getSolucion().size()); i++) {
				 // Solo es null en la raíz (se puede mejorar)
				/*if (algoritmoAEstrella.getSolucion().elementAt(i).getOperador() != null) {
					Tramo tramoAux = ((ExploraNodo) (algoritmoAEstrella
							.getSolucion().elementAt(i).getOperador()))
							.getTramoElegido();*/
				  if (algoritmoAEstrella.getSolucion().elementAt(i-1).getOperador() != null) {
					Tramo tramoAux = ((ExploraNodo) (algoritmoAEstrella
							.getSolucion().elementAt(i-1).getOperador()))
							.getTramoElegido();
					tramos.add(tramoAux);
					// Ver luego si almacenarlo en algún sitio.
				}
			}
			
			for (int i = tramos.size() -1; i >= 0; i++) {
				tramos.add(tramos.get(i));
			}
			return true;
		}

	}
	
	@Override
	public synchronized Tramo siguienteTramo() {
		if (tramos != null && tramos.size() >= cuentaTramos) {
			return tramos.get(cuentaTramos);
		}
		return null;
	}

	@Override
	public void variarAceleracion(int cuanto) {
		// TODO Auto-generated method stub

	}

}
