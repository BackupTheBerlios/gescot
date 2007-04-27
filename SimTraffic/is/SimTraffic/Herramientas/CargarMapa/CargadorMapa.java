
package is.SimTraffic.Herramientas.CargarMapa;


import is.SimTraffic.Mapa.*;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoHighway;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoManMade;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;


public class CargadorMapa implements DocHandler {
	
	static int idUltimoElemReconocido;
	static String ultimoElemReconocido;
	static CargadorMapa cargadormapa = new CargadorMapa();

	static ArrayList<Nodo> nodos;

	static ArrayList<Tramo> tramos;
	
	static ArrayList<Via> vias;
	
	static ArrayList<LineaBus> lineasAutobuses;
	
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
		EntradaSalida es = null;
		int indexFrom = 0, indexTo = 0;
		Integer integer;
		@SuppressWarnings("unused")
		Boolean bool;
		Double vdouble;
		String v = null;
		String k = null;
		@SuppressWarnings("unused")
		boolean visible;
		Enumeration e = h.keys();
		while (e.hasMoreElements()) {
			String key = new String((String) e.nextElement());

			String val = new String((String) h.get(key));

			System.out.println("      " + key + " = " + val);

			if(key.compareTo("v")==0){
				v=new String(val);
			}
			if(key.compareTo("k")==0){
				k=new String(val);
			}
			if (key.compareTo("id") == 0) {
				integer = new Integer(val);
				id = integer.intValue();
			}
			if (key.compareTo("lat") == 0) {
				vdouble = new Double(val);
				lat = vdouble.doubleValue();
			}
			if (key.compareTo("lon") == 0){
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
			}
			
		}

		if (elem.compareTo("node") == 0) {
			System.out.println("reconocido nodo");
			Posicion pos = new Posicion(lat,lon);
			//Luego poner nombre y tipo en vez de null,null.
			
			ultimoElemReconocido=elem;
			idUltimoElemReconocido=id;
			nodos.add(new Nodo(es,id,null,pos,null));
			es = null;
		}
		if (elem.compareTo("tag")==0){
			if (ultimoElemReconocido.compareTo("node") == 0){
				System.out.println("tag de nodo reconocido");
				Nodo nodoAux = buscarNodoConId(idUltimoElemReconocido);
				if (k.compareTo("nombre")==0 || k.compareTo("name")==0){
					nodoAux.setNombre(v);
				} else if (k.compareTo("entradasalida") == 0) {
					int[] salida = new int[3];
					int[] entrada = new int[3];
					StringTokenizer st = new StringTokenizer(v, ",");
					for (int i = 0; i < 3; i++)
						entrada[i] = Integer.parseInt(st.nextToken());
					for (int i = 0; i< 3; i++)
						salida[i] = Integer.parseInt(st.nextToken());
					es = new EntradaSalida(entrada, salida);
					nodoAux.setEs(es);
				}
				else {
					if (identificarTipoElem(k,v) != null)
					nodoAux.setTipo(identificarTipoElem(k,v));}
			}
			else if (ultimoElemReconocido.compareTo("segment") == 0){
				System.out.println("tag de segmento reconocido");
				Tramo tramoAux = buscarTramoConId(idUltimoElemReconocido);
				if (k.compareTo("nombre")==0 || k.compareTo("name")==0){
					tramoAux.setNombre(v);
				}
				if (k.compareTo("nCarrilesIda") == 0)
					tramoAux.setNumCarrilesDir1(new Integer(v).intValue());
				else if (k.compareTo("nCarrilesVuelta") == 0)
					tramoAux.setNumCarrilesDir2(new Integer(v).intValue());
				else if (k.compareTo("velMax") == 0)
					tramoAux.setVelMax(new Float(v).floatValue());				
			}
			else if (ultimoElemReconocido.compareTo("way") == 0){
				System.out.println("tag de via reconocido");
				Via viaAux = buscarViaConId(idUltimoElemReconocido);
				if (k.compareTo("nombre")==0 || k.compareTo("name")==0){
					viaAux.setNombre(v);
				}
				else if (k.compareTo("bus line") == 0 && v.compareTo("yes") == 0){
					//Iterator<Via> itVia = vias.iterator();
					vias.remove(viaAux);
					//Eliminar info de via de cada tramo que la forma (noe s que pertenezca a una vía, sino que es parte de una linea de bus)
					/*Iterator<Tramo> tram = viaAux.getTramos().iterator();
					while (tram.hasNext()) {
						Tramo tramoaux=tram.next();
						if (tramoaux.getVia()==viaAux ) {
							tramoaux.setVia(null);
						}
					}*/
					//TODO cargar otra información de los carriles de bus
					lineasAutobuses.add(new LineaBus(viaAux, null, new int[0], 0, 0));					
				}
				else if (k.compareTo("bus line") == 0 && v.compareTo("no") == 0){
					//no hacer nada
				}
				else if(k.compareTo("seg") == 0){
						  if(idUltimoElemReconocido > 0){
							System.out.println("segmento de via reconocido");
							//Via viaAux = buscarViaConId(idUltimoElemReconocido);
							/*Tramo tramoAux = buscarTramoConId(id);
							//Probando
							if (tramoAux!=null) {
								//if (tramoAux.getVia()==null) {
								//	tramoAux.setVia(viaAux);
								//}
								viaAux.addTramo(tramoAux);
							}*/
						  }
				}
				else {
					if (identificarTipoElem(k,v) != null)
						viaAux.setTipo(identificarTipoElem(k,v));
					
					//Probando
					/*Iterator<Tramo> tram = viaAux.getTramos().iterator();
					boolean encontrado = false;
					while (tram.hasNext() && (!encontrado)) {
						Tramo tramoaux=tram.next();
						if (tramoaux.getVia()!=null) {
							encontrado = true;
						}
						else {
							tramoaux.setVia(viaAux);
						}
					}*/
				}
			}
		
		}
		  if (elem.compareTo("segment") == 0) {
			System.out.println("reconoce tramo");
			Nodo nodoI=buscarNodoConId(indexFrom);
			Nodo nodoF=buscarNodoConId(indexTo);
			if (nodoI != nodoF) { 
				Tramo nuevoTramo = new Tramo(id,nodoI,nodoF);
				nodoI.añadirTramo(nuevoTramo);
				nodoF.añadirTramo(nuevoTramo);
				tramos.add(nuevoTramo);
				ultimoElemReconocido=elem;
				idUltimoElemReconocido=id;
			}
			else { //Si el inicial y el final son el mismo nodo se descarta el tramo.
				System.out.println("Se descarta el tramo \n");
			}
			/*tramos.add(new Tramo(nodos.get(indexFrom), nodos.get(indexTo),
					nodos.get(indexFrom).distancia(nodos.get(indexTo)), 2, 2,
					false));*/
		}
		  if (elem.compareTo("way") == 0 /*&& id>0*/){
			  System.out.println("reconoce via");
			  Via nuevaVia = new Via();
			  nuevaVia.setID(id);
			  vias.add(nuevaVia);
			  ultimoElemReconocido=elem;
			  idUltimoElemReconocido=id;
		  }
		  /*if (elem.compareTo("way") == 0 && id<0){
			  ultimoElemReconocido=elem;
			  idUltimoElemReconocido=id;
		  } */
		  if(elem.compareTo("seg") == 0){
			  if(idUltimoElemReconocido > 0){
				System.out.println("segmento de via reconocido");
				Via viaAux = buscarViaConId(idUltimoElemReconocido);
				Tramo tramoAux = buscarTramoConId(id);
				//Probando
				if (tramoAux!=null) {
				//	if (tramoAux.getVia()==null) {
				//		tramoAux.setVia(viaAux);
				//	}
					viaAux.addTramo(tramoAux);
				}
			  }
		  }

	}
	
	public Tramo buscarTramoConId(int idtramo){
		Iterator<Tramo> tram = tramos.iterator();
		Tramo tramaux;
		while(tram.hasNext()){
			tramaux = tram.next();
			if(tramaux.getID() == idtramo)
				return tramaux;
		}
		return null;
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
	
	public Via buscarViaConId(int idvia){
		Iterator<Via> itVia = vias.iterator();
		Via viaAux;
		while (itVia.hasNext()){
			viaAux = itVia.next();
			if(viaAux.getID() == idvia)
				return viaAux;
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
		  else if (nombre.equals("amenity"))
			  return new TipoNodoAmenity(valor);
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
		vias = new ArrayList<Via>();
		lineasAutobuses = new ArrayList<LineaBus>();
		

		// This is all the code we need to parse
		// a document with our DocHandler.
		FileReader fr = new FileReader(file);
		QDParser.parse(cargadormapa, fr);
		
		fr.close();
		System.out.println("Tamaño de nodos: "+nodos.size());
		System.out.println("Tamaño de tramos: "+tramos.size());
		System.out.println("Tamaño de vias: " + vias.size());
		System.out.println("Tamaño de lineas de autobuses: " + lineasAutobuses.size());
		
		System.out.println("Localización de nodo: "+nodos.get(0).getID());
		System.out.println("Lat:"+nodos.get(0).getPos().getLat());
		System.out.println("Lon:"+nodos.get(0).getPos().getLon());
		
		Mapa mapaADevolver = new Mapa(nodos,tramos); 
		mapaADevolver.setVias(vias);
		mapaADevolver.setLineasAutobuses(lineasAutobuses);
		
		for (int i=0;i<vias.size();i++) {
			Via viaAuxiliar=vias.get(i);
			Iterator<Tramo> tramosIt = viaAuxiliar.getTramos().iterator();
			while (tramosIt.hasNext()) {
				Tramo tramoAuxiliar = tramosIt.next();
				tramoAuxiliar.setVia(viaAuxiliar);
			}
		}
			
		
		return mapaADevolver;
		//return null;
	}
	
	public static void main(String[] args) throws Exception{
		@SuppressWarnings("unused")
		Mapa mapa=cargar("C:/Documents and Settings/Ignacio/Escritorio/IS-ultimamente/Mapas-hechos/prueba110307_2.osm");
		System.out.println("cargado");
	}
}
