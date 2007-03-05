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

public class HGuardarMapa implements IHerramienta {

	public int hacer(IModelo modelo) {
		Mapa mapa = modelo.getMapa();
		generarXMLinfo(mapa);
		/*Faltarían guardar las otras cosas relevantes que no se puedan guardar en el formato osm
		Analizar y decidir cuales */
		return 0;
	}

	public int deshacer(IModelo modelo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Método inacabado.
	 * @param mapa
	 */
	public void generarXMLinfo(Mapa mapa) {

		JFileChooser fc = new JFileChooser();
		int val = fc.showSaveDialog(null);
		
		if(val == JFileChooser.APPROVE_OPTION) {
		PrintWriter salida;
		try {
			salida = new PrintWriter(new BufferedWriter(new FileWriter(fc.getSelectedFile().getAbsolutePath())));
				System.out.println("w/n");
				//Escribir
				salida.println("<?xml version='1.0' encoding='UTF-8'?>");
				salida.println("<osm version='0.3' generator='JOSM'>");
				
				Iterator<Nodo> nod = mapa.getNodos().iterator();
				while (nod.hasNext()) {
					Nodo nodoaux = nod.next();
					salida.println(nodoaux.transformaraOSM());
				}
				
				Iterator<Tramo> tram = mapa.getTramos().iterator();
				while (nod.hasNext()) {
					Tramo tramoaux = tram.next();
					salida.println(tramoaux.transformaraOSM());
				}
				
				//Falta parte de vías (en proceso)
				
				salida.println("</osm>");
				salida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	
	}

}
