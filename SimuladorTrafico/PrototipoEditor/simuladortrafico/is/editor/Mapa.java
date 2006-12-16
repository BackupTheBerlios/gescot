package PrototipoEditor.simuladortrafico.is.editor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;

public class Mapa {

	List<Nodo> listaNodos;
	List<Pareja> listaParejas;
	
	int ancho,alto;
	int margenLeft,margenUp;
	
	public Mapa (int ancho, int alto){
		this.listaNodos = new ArrayList<Nodo>();
		this.listaParejas = new ArrayList<Pareja>();
		
		this.ancho = ancho;
		this.alto = alto;
		margenLeft = 5;
		margenUp = 25;
		
	}
	
	public void insertaNodo(int x, int y){
		Nodo nodo = new Nodo(x,y);
		listaNodos.add(nodo);
		
		//Activar para visualizar la lista de nodos
		/*Nodo nodoAux = new Nodo(0,0);
		for (int i=0;i<listaNodos.size();i++){
			nodoAux = listaNodos.get(i);
			System.out.println("Nodo "+i+" - Coordenadas ("+ nodoAux.x+","+nodoAux.y+")");
		}*/
		
	}
	
	public boolean insertaNodo(Nodo nodo){
		return listaNodos.add(nodo);
	}
	
	public void insertarPareja(Nodo nodo1, Nodo nodo2){
		Pareja pareja = new Pareja(nodo1, nodo2);
		this.listaParejas.add(pareja);
		
		Pareja parejaAux = new Pareja();
		for (int i=0;i<listaParejas.size();i++){
			parejaAux = listaParejas.get(i);
			System.out.println("Pareja " + i + ": (" + parejaAux.nodo1.x + "," + parejaAux.nodo1.y + ") - ("+parejaAux.nodo2.x + "," + parejaAux.nodo2.y+")");
		}
	}
	
	public Nodo dameNodo(int pos){
		return listaNodos.get(pos);
	}
	
	
	public void generarXMLinfo() {

		JFileChooser fc = new JFileChooser();
		int val = fc.showSaveDialog(null);
		
		if(val == JFileChooser.APPROVE_OPTION) {
		PrintWriter salida;
		try {
			salida = new PrintWriter(new BufferedWriter(new FileWriter(fc.getSelectedFile().getAbsolutePath())));
			//while(nod.hasNext()) {
				System.out.println("w/n");
				//Escribir
				salida.println("<?xml version='1.0' encoding='UTF-8'?>");
				salida.println("<osm version='0.3' generator='JOSM'>");
				
				Iterator<Nodo> nod = listaNodos.iterator();
				int aux=1;
				for (int i=1;nod.hasNext();i++) {
					Nodo nodoaux=nod.next();
					nodoaux.setId(i);
					salida.println("<node id='"+nodoaux.getId()+"' lat='" + nodoaux.getPosX() + "' lon='" + nodoaux.getPosY() + "' />");       
					aux=i;
					//salida.println("/n");
				}
				
				Iterator<Pareja> tram = listaParejas.iterator();
				for (int j=aux+1;tram.hasNext();j++) {
					Pareja tramoaux=tram.next();
					tramoaux.setId(j);
					salida.println("<segment id='"+tramoaux.getId()+"' from='" + tramoaux.getComienzo().getId() + "' to='" + tramoaux.getFin().getId() + "' />");       
					//salida.println("/n");
				}
				 salida.println("</osm>");
			//}
			salida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
}
