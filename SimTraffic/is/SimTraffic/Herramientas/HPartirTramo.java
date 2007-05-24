package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Utils.Tiempo;

/**
 * Herramienta que permite realizar/deshacer la operacion de partir un tramo
 * al insertar un nodo en el mapa.
 * 
 * @author Grupo ISTrafico
 */

public class HPartirTramo implements IHerramienta {

		/**
		 * Nodo nuevo que se insertará al partir el tramo
		 */
		public Nodo nuevoNodo;
		/**
		 * Tramo antiguo del mapa
		 */
		public Tramo antiguoTramo;
		/**
		 * Nuevo tramo que corresponde a la primera mitad del antiguo tramo
		 */
		public Tramo tramo1Nuevo;
		/**
		 * Nuevo tramo que corresponde a la segunda mitad del antiguo tramo
		 */
		public Tramo tramo2Nuevo;
		
		/**
		 * Constructora de la herramienta.
		 * Inicializa los valores correspondientes
		 * @param nuevoNodo
		 * @param antiguoTramo
		 * @param tramo1Nuevo
		 * @param tramo2Nuevo
		 */
		public HPartirTramo (Nodo nuevoNodo, Tramo antiguoTramo, 
				Tramo tramo1Nuevo, Tramo tramo2Nuevo) {
			this.nuevoNodo = nuevoNodo;
			this.antiguoTramo = antiguoTramo;
			this.tramo1Nuevo = tramo1Nuevo;
			this.tramo2Nuevo = tramo2Nuevo;
		}
		
		/**
		 * Método hacer.
		 * Inserta en el mapa el nuevo nodo y los 2 nuevos tramos, y elimina
		 * el antiguo tramo del mapa
		 */
		public int hacer(IModelo modelo) {
			modelo.getMapa().insertar(nuevoNodo);
			modelo.getMapa().insertar(tramo1Nuevo);
			modelo.getMapa().insertar(tramo2Nuevo);
			modelo.getMapa().eliminar(antiguoTramo);			
			return 0;
		}

		/**
		 * Método deshacer.
		 * Elimina del mapa las 2 mitades del antiguo trmao y el nuevo nodo
		 * A continuación, inserta en el mapa el antiguo tramo
		 */
		public int deshacer(IModelo modelo) {
			modelo.getMapa().eliminar(tramo1Nuevo);
			modelo.getMapa().eliminar(tramo2Nuevo);
			modelo.getMapa().eliminar(nuevoNodo);
			modelo.getMapa().insertar(antiguoTramo);
			return 0;
		}

		public String toString(){
			
			return Tiempo.Hora()+Messages.getString("HPartirTramo.0")+Messages.getString("HPartirTramo.1"); //$NON-NLS-1$ //$NON-NLS-2$
			
		}

		public Tramo getAntiguoTramo() {
			return antiguoTramo;
		}

		public Nodo getNuevoNodo() {
			return nuevoNodo;
		}

		public Tramo getTramo1Nuevo() {
			return tramo1Nuevo;
		}

		public Tramo getTramo2Nuevo() {
			return tramo2Nuevo;
		}
		
		
}