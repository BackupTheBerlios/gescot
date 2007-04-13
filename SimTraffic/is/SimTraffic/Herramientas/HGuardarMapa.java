package is.SimTraffic.Herramientas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.swing.JFileChooser;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Utils.Tiempo;

public class HGuardarMapa implements IHerramienta {

	public int hacer(IModelo modelo) {
		Mapa mapa = modelo.getMapa();
		generarXMLinfo(mapa);
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

		JFileChooser fc = new JFileChooser();
		String[] ext = new String[] { "osm" };
		fc.addChoosableFileFilter(new ExtFilter(ext,"Mapa OSM (*.osm)"));
		int val = fc.showSaveDialog(null);
		
		if (val == JFileChooser.APPROVE_OPTION) {
			PrintWriter salida;
			try {
				if (fc.getSelectedFile().getAbsolutePath().contains(".osm"))
					salida = new PrintWriter(new BufferedWriter(new FileWriter(fc
						.getSelectedFile().getAbsolutePath())))  ;
				else {
					salida = new PrintWriter(new BufferedWriter(new FileWriter(fc
							.getSelectedFile().getAbsolutePath().concat(".osm"))))  ;	
				}
					
				System.out.println("w/n");
				// Escribir
				salida.println("<?xml version='1.0' encoding='UTF-8'?>");
				salida.println("<osm version='0.3' generator='SimTrafficTM'>");

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
				
				Iterator<Via> buses = mapa.getLineasAutobuses().iterator();
				while (buses.hasNext()) {
					Via lineaBusAux = buses.next();
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
				salida.println("</osm>");
				salida.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public String toString(){
		return Tiempo.Hora()+" - "+ "Se guarda mapa";
	}
}
