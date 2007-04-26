package is.SimTraffic.Simulacion;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.LibreriaIA.Algoritmos.AEstrella;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.ExploraNodo;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.PrincipalDistanciaNodos;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;



/**
 * @author Grupo ISTrafico
 *
 */
public class Camion extends Vehiculo {
	
	private Random random;
	
	private ArrayList<Tramo> tramos = new ArrayList<Tramo>();
	
	private int cuentaTramos = 0;
	
	public Camion() {
		nombre="Camion";
		//Se genera un color aleatorio
		//generarColorAleatorio();
		this.color = Color.GREEN;
		this.figura = new Rectangle2D.Double(-6, -RepresentacionSimple.tamaño_carril, 6,
				RepresentacionSimple.tamaño_carril);
		
		// TODO este constructor deberia dar valores a todos
		//   los atributos de un camión
		
		random = new Random();
		this.aceleracion = 0;
		this.aceleracionMax = (double) random.nextInt(10) / 100 + 0.15;
		this.distanciaSeguridad = 5;
		this.posicion = 0;
		this.velocidad = 0;
		this.velocidadMax = (double) random.nextInt(20) / 100 + 0.5;
		this.id = ncochesglobal;
		ncochesglobal++;
	}

	@Override
	public void variarAceleracion(int cuanto) {
		// TODO posiblemente se deberia utilizar una escala logaritmica o exponencial o algo asi
		if (cuanto > 0)
		this.aceleracion += (double) cuanto*cuanto / 50000;
		else
			this.aceleracion -= (double) cuanto*cuanto / 50000;
		if (aceleracion > this.aceleracionMax)
			aceleracion = aceleracionMax;
		
	}

	@Override
	public synchronized Tramo siguienteTramo() {
		// TODO da el tramo siguiente
		if (tramos.size() <= cuentaTramos) {
			return null;
		}
		return tramos.get(cuentaTramos);
	}
	
	public boolean inicializar(Nodo entrada, Nodo salida) {
		super.inicializar(entrada, salida);
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
			
			//Puede que influya el orden de inserción de los tramos (por comprobar).
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
			return true;
		}
	}

}
