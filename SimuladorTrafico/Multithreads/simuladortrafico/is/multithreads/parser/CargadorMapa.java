package Multithreads.simuladortrafico.is.multithreads.parser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import Multithreads.simuladortrafico.is.multithreads.mapa.Mapa;
import Multithreads.simuladortrafico.is.multithreads.mapa.Nodo;
import Multithreads.simuladortrafico.is.multithreads.mapa.Semaforo;
import Multithreads.simuladortrafico.is.multithreads.mapa.Tramo;

public class CargadorMapa implements DocHandler {

	static CargadorMapa cargadormapa = new CargadorMapa();

	static List<Nodo> nodos;

	static List<Tramo> tramos;

	public void endDocument() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("  end document");

	}

	public void endElement(String elem) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("    end elem: " + elem);

	}

	public void startDocument() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("  start document");

	}

	public void startElement(String elem, Hashtable h) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("    start elem: " + elem);
		int posX = 0, posY = 0;
		int indexFrom = 0, indexTo = 0;
		Integer integer;
		Enumeration e = h.keys();
		while (e.hasMoreElements()) {
			String key = new String((String) e.nextElement());

			String val = new String((String) h.get(key));

			System.out.println("      " + key + " = " + val);

			if (key.compareTo("lat") == 0) {
				integer = new Integer(val);
				posX = integer.intValue();
			}
			if (key.compareTo("lon") == 0){
				integer = new Integer(val);
				posY = integer.intValue();
			}

			if (key.compareTo("to") == 0){
				integer = new Integer(val);
				indexTo = integer.intValue() -1 ;
			}
			if (key.compareTo("from") == 0){
				integer = new Integer(val);
				indexFrom = integer.intValue() -1;
			}
		}

		if (elem.compareTo("node") == 0) {
			System.out.println("reconoce nodo");
			nodos.add(new Nodo(posX, posY, new Semaforo(false)));

		}
		if (elem.compareTo("segment") == 0) {
			System.out.println("reconoce tramo");

			tramos.add(new Tramo(nodos.get(indexFrom), nodos.get(indexTo),
					nodos.get(indexFrom).distancia(nodos.get(indexTo)), 2, 2,
					false));
		}

	}

	public void text(String text) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("        text: " + text);

	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++)
			reportOnFile(args[0]);
	}

	public static void reportOnFile(String file) throws Exception {
		System.out.println("===============================");
		System.out.println("file: " + file);

		// This is all the code we need to parse
		// a document with our DocHandler.
		FileReader fr = new FileReader(file);
		QDParser.parse(cargadormapa, fr);

		fr.close();
	}

	public static Mapa cargar(String file)
			throws Exception {
		System.out.println("===============================");
		System.out.println("file: " + file);

		nodos = new ArrayList<Nodo>();
		tramos = new ArrayList<Tramo>();

		// This is all the code we need to parse
		// a document with our DocHandler.
		FileReader fr = new FileReader(file);
		QDParser.parse(cargadormapa, fr);
		
		fr.close();
		
		return new Mapa(nodos,tramos);
	}
}
