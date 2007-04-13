package is.SimTraffic.Herramientas;

import javax.swing.JOptionPane;

import is.SimTraffic.IModelo;
import is.SimTraffic.LibreriaIA.IAlgoritmo;
import is.SimTraffic.LibreriaIA.IPrincipal;
import is.SimTraffic.LibreriaIA.Algoritmos.AEstrella;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.ExploraNodo;
import is.SimTraffic.LibreriaIA.Problema.DistanciaNodos.PrincipalDistanciaNodos;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

public class HCreaItinerarioEntreDosNodos implements IHerramienta {

	Nodo nodoInicial;
	Nodo nodoObjetivo;
	
	public HCreaItinerarioEntreDosNodos(Nodo nodoInicial, Nodo nodoObjetivo) {
		this.nodoInicial = nodoInicial;
		this.nodoObjetivo = nodoObjetivo;
	}

	public int hacer(IModelo modelo) {
		IPrincipal problemaDistancias = new PrincipalDistanciaNodos();
		AEstrella algoritmoAEstrella=new AEstrella(problemaDistancias.getEstadoInicial(), 
				problemaDistancias.getEstadoObjetivo(),problemaDistancias.getOperadores(),problemaDistancias.getHeuristica());
		boolean resul = algoritmoAEstrella.ejecutar();
		if (resul == false) {
			//Mostrar ventana, no ha sido posible encontrar un camino entre esos puntos.
			 JOptionPane.showMessageDialog(null, "Puntos no comunicados por carretera",
			            "No ha sido posible establecer un itinerario entre los 2 nodos especificados", 
			            JOptionPane.INFORMATION_MESSAGE);
			 modelo.getMapa().limpiaSeleccion();
		}
		else {
			//Mostrar soluci�n en el mapa
			modelo.getMapa().limpiaSeleccion();
			for (int i=0; i < (algoritmoAEstrella.getSolucion().size()); i++) {
				if (algoritmoAEstrella.getSolucion().elementAt(i).getOperador() != null) { //Solo es null en la ra�z (se puede mejorar)
					Tramo tramoAux= ((ExploraNodo)(algoritmoAEstrella.getSolucion().elementAt(i).getOperador())).getTramoElegido();
					modelo.getMapa().getSeleccion().a�adirTramo(tramoAux);
					//Ver luego si almacenarlo en alg�n sitio.
				}
			}
		}
		return 0;
	}

	public int deshacer(IModelo modelo) {
		//Deshacer ser�a eliminar la selecci�n en el momento de ejecutar la herramienta, por lo que no 
		//tiene mucho sentido (como mucho, guardar la selecci�n anterior). Aun por decidir.
		return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
