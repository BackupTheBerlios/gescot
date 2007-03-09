package is.SimTraffic.Herramientas.CargarMapa;


import is.SimTraffic.Mapa.*;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoHighway;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoManMade;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;


public class CargadorMapa implements DocHandler {

	static CargadorMapa cargadormapa = new CargadorMapa();

	static ArrayList<Nodo> nodos;

	static ArrayList<Tramo> tramos;
	
	public CargadorMapa() {
	//	this.nodos = new ArrayList<Nodo>();
	//	this.tramos = new ArrayList<Tramo>();
	}

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
		int id = 0;
		double lat = 0;
		double lon = 0;
		int indexFrom = 0, indexTo = 0;
		String nombre = null,tipoElemStringNombre = null,tipoElemStringValor = null;
		Integer integer;
		Boolean bool;
		Double vdouble;
		boolean visible;
		Enumeration e = h.keys();
		while (e.hasMoreElements()) {
			String key = new String((String) e.nextElement());

			String val = new String((String) h.get(key));

			System.out.println("      " + key + " = " + val);

			if (key.compareTo("id") == 0) {
				integer = new Integer(val);
				id = integer.intValue();
			}
			if (key.compareTo("lat") == 0) {
				//integer = new Integer(val);
				vdouble = new Double(val);
				lat = vdouble.doubleValue();
				//posX = integer.intValue();
			}
			if (key.compareTo("lon") == 0){
				//integer = new Integer(val);
				//posY = integer.intValue();
				vdouble = new Double(val);
				lon = vdouble.doubleValue();
			}

			if (key.compareTo("to") == 0){
				integer = new Integer(val);
				indexTo = integer.intValue();// -1 ;
			}
			
			if (key.compareTo("from") == 0){
				integer = new Integer(val);
				indexFrom = integer.intValue(); //-1;
			}
			
			if (key.compareTo("visible") == 0){
				bool = new Boolean(val);	
				if (val.equals("false")) visible=false;
				else visible=true;
				//visible = bool.booleanValue();
			}
			/** Siempre que se lea la etiqueta k (key), se lee posteriormente la etiqueta  v(value), 
			 * luego no es necesario incluir en esta secuencia de "intrucciones if" el string v 
			 */
			/*if (key.compareTo("k") == 0){
				String keyV = new String((String) e.nextElement());
				if (!keyV.equals("v")) 
					System.out.println("Atributo no esperado, keyV");
				String valV = new String((String) h.get(key));
				if (val.equals("nombre")) {
					nombre=valV;
				}
				else {
					tipoElemStringNombre = val;
					tipoElemStringValor = valV;
				}
				
			}*/
			
		}

		if (elem.compareTo("node") == 0) {
			System.out.println("reconocido nodo");
			//Posicion pos = new Posicion(posX,posY);
			Posicion pos = new Posicion(lat,lon);
			//ITipoElemento tipo =identificarTipoElem(tipoElemStringNombre,tipoElemStringValor);
			//Luego poner nombre y tipo en vez de null,null.
			nodos.add(new Nodo(id,null,pos,null));
			
		}
		  if (elem.compareTo("segment") == 0) {
			System.out.println("reconoce tramo");
			Nodo nodoI=buscarNodoConId(indexFrom);
			Nodo nodoF=buscarNodoConId(indexTo);
			Tramo nuevoTramo = new Tramo(id,nodoI,nodoF);
			nodoI.añadirTramo(nuevoTramo);
			nodoF.añadirTramo(nuevoTramo);
			tramos.add(nuevoTramo);
			/*tramos.add(new Tramo(nodos.get(indexFrom), nodos.get(indexTo),
					nodos.get(indexFrom).distancia(nodos.get(indexTo)), 2, 2,
					false));*/
		}

	}
	
	public Nodo buscarNodoConId(int idnodo) {
		Iterator<Nodo> nod = nodos.iterator();
		Nodo nodoaux;
		while (nod.hasNext()) {
			nodoaux = nod.next();
			if (nodoaux.getID() == idnodo) 
				return nodoaux;
		}
		return null;
	}
	
	public ITipoElemento identificarTipoElem(String nombre,String valor){
		  if (nombre.equals("highway")) 
			  return new TipoNodoHighway(valor);
		  else if (nombre.equals("leisure"))
			  return new TipoNodoLeisure(valor); 
		  else if (nombre.equals("man_made"))
			  return new TipoNodoManMade(valor);
		  return null;		
	}

	public void text(String text) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("        text: " + text);

	}

	/*public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++)
			reportOnFile(args[0]);
	}*/

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
		System.out.println("Tamaño de nodos:"+nodos.size());
		System.out.println("Tamaño de tramos:"+tramos.size());
		
		System.out.println("Localización de nodo: "+nodos.get(0).getID());
		System.out.println("PosX:"+nodos.get(0).getPos().getPosX());
		System.out.println("PosY:"+nodos.get(0).getPos().getPosY());
		
		Mapa mapaADevolver = new Mapa(nodos,tramos); 
		return mapaADevolver;
		//return null;
	}
	
	public static void main(String[] args) throws Exception{
		Mapa mapa=cargar("C:/Documents and Settings/Ignacio/Escritorio/IS-ultimamente/Mapas-hechos/unapruebamasSinVias.osm");
		System.out.println("cargado");
	}
}
