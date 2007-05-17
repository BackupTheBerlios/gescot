package is.SimTraffic.Herramientas;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;

import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.LineaBus;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Se�al;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Utils.Tiempo;

public class HGuardarMapa implements IHerramienta {

	private JFileChooser fc;
	
	/** Informa de si el usuario ha pulsado el boton de aceptar al guardar o no.*/
	private boolean aceptado;
	
	public int hacer(IModelo modelo) {
		Mapa mapa = modelo.getMapa();
		aceptado = false;
		generarXMLinfo(mapa);
		if (aceptado)
			guardarSe�ales(mapa);
		modelo.getMapa().setCambios_en_mapa(false);
		
		/*
		 * Faltar�an guardar las otras cosas relevantes que no se puedan guardar
		 * en el formato osm Analizar y decidir cuales
		 */
		return 0;
	}

	/**
	 * Las operaciones como Guardar Mapa que no tiene operaci�n deshacer devuelven el codigo indicativo 50
	 */
	public int deshacer(IModelo modelo) {
		// TODO Auto-generated method stub
		return 50;
	}

	/**
	 * M�todo depurado y probado con el sistema actual (sin nombre ni tipos de
	 * elemento). Guarda la informaci�n relativa al mapa osm (ning�n valor sobre
	 * entrada/salida, se�ales, etc.)
	 * 
	 * @param mapa
	 */
	public void generarXMLinfo(Mapa mapa) {

		fc = new JFileChooser(Messages.getString("HGuardarMapa.0")); //$NON-NLS-1$
		String[] ext = new String[] { Messages.getString("HGuardarMapa.1") }; //$NON-NLS-1$
		fc.addChoosableFileFilter(new ExtFilter(ext,Messages.getString("HGuardarMapa.2"))); //$NON-NLS-1$
		int val = fc.showSaveDialog(null);
		
		if (val == JFileChooser.APPROVE_OPTION) {
			PrintWriter salida;
			try {
				aceptado = true;
				if (fc.getSelectedFile().getAbsolutePath().contains(Messages.getString("HGuardarMapa.3"))) //$NON-NLS-1$
					salida = new PrintWriter(new BufferedWriter(new FileWriter(fc
						.getSelectedFile().getAbsolutePath())))  ;
				else {
					salida = new PrintWriter(new BufferedWriter(new FileWriter(fc
							.getSelectedFile().getAbsolutePath().concat(Messages.getString("HGuardarMapa.4")))))  ;	 //$NON-NLS-1$
				}
					
				System.out.println(Messages.getString("HGuardarMapa.5")); //$NON-NLS-1$
				// Escribir
				salida.println(Messages.getString("HGuardarMapa.6")); //$NON-NLS-1$
				salida.println(Messages.getString("HGuardarMapa.7")); //$NON-NLS-1$

				Iterator<Nodo> nod = mapa.getNodos().iterator();
				while (nod.hasNext()) {
					Nodo nodoaux = nod.next();
					salida.println(nodoaux.transformaraOSM());
				}

				Iterator<Tramo> tram = mapa.getTramos().iterator();
				while (tram.hasNext()) {
					Tramo tramoaux = tram.next();
					salida.println(tramoaux.transformaraOSM());
				}
				
				Iterator<Via> via = mapa.getVias().iterator();
				while (via.hasNext()) {
					Via viaAux = via.next();
					salida.println(viaAux.transformaraOSM());
				}
				
				Iterator<LineaBus> buses = mapa.getLineasAutobuses().iterator();
				while (buses.hasNext()) {
					LineaBus lineaBusAux = buses.next();
					salida.println(lineaBusAux.transformarLineaBusaOSM());
				}

				// Parte de v�as (en proceso)
				// Objetivo aqu�: Tratar cada segmento como v�a de un solo
				// segmento.
				// Provisional mientras se decide sobre la inclusi�n de un tipo
				// v�a,
				// pero incluido para tener toda la informaci�n de nodos y
				// tramos guardada.
				// 1 segmento por v�a.
				/*String s = new String();
				tram = mapa.getTramos().iterator();
				while (tram.hasNext()) {
					Tramo tramoaux = tram.next();
					// Crea el c�digo de v�as (con 1 segmento por v�a, con id el
					// del tramo negativo)
					s = s.concat("<way id='" + (-tramoaux.getID()) + "'>\n");
					s = s.concat("<seg id='" + tramoaux.getID() + "'/>\n");
					ITipoElemento tipo = tramoaux.getTipo();
					if (tipo != null)
						s = s.concat("<tag k='" + tipo.getTipo() + "' v='"
								+ tipo.getValorTipo() + "' />\n");
					if (tramoaux.getNombre() != null)
						s = s.concat("<tag k='nombre' v='"
								+ tramoaux.getNombre() + "' />\n");
					s = s.concat("</way>\n");
				}
				salida.println(s);*/
				salida.println(Messages.getString("HGuardarMapa.8")); //$NON-NLS-1$
				salida.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void guardarSe�ales(Mapa mapa){
		String rutaSe�ales = this.fc.getSelectedFile().getAbsolutePath();
		
		if (rutaSe�ales.contains(Messages.getString("HGuardarMapa.9"))){ //$NON-NLS-1$
			rutaSe�ales = rutaSe�ales.replace(Messages.getString("HGuardarMapa.10"),Messages.getString("HGuardarMapa.11")); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			rutaSe�ales += Messages.getString("HGuardarMapa.12"); //$NON-NLS-1$
		}
		
		List<Se�al> listaSe�ales = new ArrayList<Se�al>();
		
		//A�adimos todas las se�ales de los nodos a una lista para almacenarlas.
		Iterator<Nodo> iteradorNodos = mapa.getNodos().iterator();
		while(iteradorNodos.hasNext()){
			Nodo nodoActual = iteradorNodos.next();
			if (nodoActual.getSe�al() != null) {
				listaSe�ales.add(nodoActual.getSe�al());
			}
		}
		
		if (listaSe�ales.size() > 0){
			//Guardamos a continuacion las se�ales en un archivo extensi�n *.sem.
			try{
				FileOutputStream flujoOut = new FileOutputStream(rutaSe�ales);
				ObjectOutputStream objetoFuera = new ObjectOutputStream(flujoOut);
				
				objetoFuera.writeObject(listaSe�ales);
				objetoFuera.close();
			} catch (Exception e){
				e.printStackTrace();
			}
			
		}				
	}
	
	public String toString(){
		return Tiempo.Hora()+Messages.getString("HGuardarMapa.13")+ Messages.getString("HGuardarMapa.14"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
