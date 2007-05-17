package is.SimTraffic.Herramientas;

import javax.swing.JOptionPane;

import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.LibreriaIA.Algoritmos.AEstrella;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.ExploraNodo;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.PrincipalDistanciaNodos;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Utils.Tiempo;

public class HCreaItinerarioEntreDosNodos implements IHerramienta {

	Nodo nodoInicial;

	Nodo nodoObjetivo;

	public HCreaItinerarioEntreDosNodos(Nodo nodoInicial, Nodo nodoObjetivo) {
		this.nodoInicial = nodoInicial;
		this.nodoObjetivo = nodoObjetivo;
	}

	public int hacer(IModelo modelo) {
		IPrincipal problemaDistancias = new PrincipalDistanciaNodos(
				nodoInicial, nodoObjetivo);
		AEstrella algoritmoAEstrella = new AEstrella(problemaDistancias
				.getEstadoInicial(), problemaDistancias.getEstadoObjetivo(),
				problemaDistancias.getOperadores(), problemaDistancias
						.getHeuristica());
		boolean resul = algoritmoAEstrella.ejecutar();
		if (resul == false) {
			// Mostrar ventana, no ha sido posible encontrar un camino entre
			// esos puntos.
			JOptionPane
					.showMessageDialog(
							null,
							Messages.getString("HCreaItinerarioEntreDosNodos.0"), //$NON-NLS-1$
							Messages.getString("HCreaItinerarioEntreDosNodos.1"), //$NON-NLS-1$
							JOptionPane.INFORMATION_MESSAGE);
			modelo.getMapa().limpiaSeleccion();
			return -1;
		} else {
			// Mostrar solución en el mapa
			modelo.getMapa().limpiaSeleccion();
			for (int i = 0; i < (algoritmoAEstrella.getSolucion().size()); i++) {
				 // Solo es null en la raíz (se puede mejorar)
				if (algoritmoAEstrella.getSolucion().elementAt(i).getOperador() != null) {
					Tramo tramoAux = ((ExploraNodo) (algoritmoAEstrella
							.getSolucion().elementAt(i).getOperador()))
							.getTramoElegido();
					modelo.getMapa().getSeleccion().añadirTramo(tramoAux);
					// Ver luego si almacenarlo en algún sitio.
				}
			}
		}
		return 0;
	}

	public int deshacer(IModelo modelo) {
		// Deshacer sería eliminar la selección en el momento de ejecutar la
		// herramienta, por lo que no
		// tiene mucho sentido (como mucho, guardar la selección anterior). Aun
		// por decidir.
		return 0;
	}

	public String toString() {
		return Tiempo.Hora() + Messages.getString("HCreaItinerarioEntreDosNodos.2") //$NON-NLS-1$
				+ Messages.getString("HCreaItinerarioEntreDosNodos.3"); //$NON-NLS-1$
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Nodo getNodoInicial() {
		return nodoInicial;
	}

	public Nodo getNodoObjetivo() {
		return nodoObjetivo;
	}

}
