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
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

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
	 * Método depurado y probado con el sistema actual (sin nombre ni tipos de elemento).
	 * Guarda la información relativa al mapa osm (ningún valor sobre entrada/salida, señales, etc.)
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
				
				//Parte de vías (en proceso)
				//Objetivo aquí: Tratar cada segmento como vía de un solo segmento.
				//Provisional mientras se decide sobre la inclusión de un tipo vía, 
				//pero incluido para tener toda la información de nodos y tramos guardada.
				//1 segmento por vía.
				String s=new String();
				tram = mapa.getTramos().iterator();
				while (tram.hasNext()) {
					Tramo tramoaux = tram.next();
					//Crea el código de vías (con 1 segmento por vía, con id el del tramo negativo)
					s=s.concat("<way id='"+(-tramoaux.getID())+"'>\n");
					s=s.concat("<seg id='"+tramoaux.getID()+"'/>\n");
					ITipoElemento tipo = tramoaux.getTipo();
					if (tipo!=null) s=s.concat("<tag k='"+tipo.getTipo()+"' v='"+tipo.getValorTipo()+"' />\n");
					if (tramoaux.getNombre()!=null) s=s.concat("<tag k='nombre' v='"+tramoaux.getNombre()+"' />\n");
					s=s.concat("</way>\n");
				}
				salida.println(s);
				salida.println("</osm>");
				salida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	
	}

}
