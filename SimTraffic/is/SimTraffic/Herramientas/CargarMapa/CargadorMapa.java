
package is.SimTraffic.Herramientas.CargarMapa;


import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.*;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoHighway;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoManMade;
import is.SimTraffic.Mapa.TipoElemento.TipoViaHighway;

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
		System.out.println(Messages.getString("CargadorMapa.0")); //$NON-NLS-1$

	}

	public void endElement(String elem) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Messages.getString("CargadorMapa.1") + elem); //$NON-NLS-1$

	}

	public void startDocument() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Messages.getString("CargadorMapa.2")); //$NON-NLS-1$

	}

	public void startElement(String elem, Hashtable h) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Messages.getString("CargadorMapa.3") + elem); //$NON-NLS-1$
		int id = 0;		
		double lat = 0;
		double lon = 0;
		EntradaSalida es = null;
		int indexFrom = 0, indexTo = 0;
		Integer integer;
		Boolean bool;
		Double vdouble;
		String v = null;
		String k = null;
		boolean visible;
		Enumeration e = h.keys();
		while (e.hasMoreElements()) {
			String key = new String((String) e.nextElement());

			String val = new String((String) h.get(key));

			System.out.println(Messages.getString("CargadorMapa.6") + key + Messages.getString("CargadorMapa.7") + val); //$NON-NLS-1$ //$NON-NLS-2$

			if(key.compareTo(Messages.getString("CargadorMapa.8"))==0){ //$NON-NLS-1$
				v=new String(val);
			}
			if(key.compareTo(Messages.getString("CargadorMapa.9"))==0){ //$NON-NLS-1$
				k=new String(val);
			}
			if (key.compareTo(Messages.getString("CargadorMapa.10")) == 0) { //$NON-NLS-1$
				integer = new Integer(val);
				id = integer.intValue();
			}
			if (key.compareTo(Messages.getString("CargadorMapa.11")) == 0) { //$NON-NLS-1$
				vdouble = new Double(val);
				lat = vdouble.doubleValue();
			}
			if (key.compareTo(Messages.getString("CargadorMapa.12")) == 0){ //$NON-NLS-1$
				vdouble = new Double(val);
				lon = vdouble.doubleValue();
			}

			if (key.compareTo(Messages.getString("CargadorMapa.13")) == 0){ //$NON-NLS-1$
				integer = new Integer(val);
				indexTo = integer.intValue();// -1 ;
			}
			
			if (key.compareTo(Messages.getString("CargadorMapa.14")) == 0){ //$NON-NLS-1$
				integer = new Integer(val);
				indexFrom = integer.intValue(); //-1;
			}
			
			if (key.compareTo(Messages.getString("CargadorMapa.15")) == 0){ //$NON-NLS-1$
				bool = new Boolean(val);	
				if (val.equals(Messages.getString("CargadorMapa.16"))) visible=false; //$NON-NLS-1$
				else visible=true;
			}
			
		}

		if (elem.compareTo(Messages.getString("CargadorMapa.17")) == 0) { //$NON-NLS-1$
			System.out.println(Messages.getString("CargadorMapa.18")); //$NON-NLS-1$
			Posicion pos = new Posicion(lat,lon);
			//Luego poner nombre y tipo en vez de null,null.
			
			ultimoElemReconocido=elem;
			idUltimoElemReconocido=id;
			nodos.add(new Nodo(es,id,null,pos,null));
			es = null;
		}
		if (elem.compareTo(Messages.getString("CargadorMapa.19"))==0){ //$NON-NLS-1$
			if (ultimoElemReconocido.compareTo(Messages.getString("CargadorMapa.20")) == 0){ //$NON-NLS-1$
				System.out.println(Messages.getString("CargadorMapa.21")); //$NON-NLS-1$
				Nodo nodoAux = buscarNodoConId(idUltimoElemReconocido);
				if (k.compareTo(Messages.getString("CargadorMapa.22"))==0 || k.compareTo(Messages.getString("CargadorMapa.23"))==0){ //$NON-NLS-1$ //$NON-NLS-2$
					nodoAux.setNombre(v);
				} else if (k.compareTo(Messages.getString("CargadorMapa.24")) == 0) { //$NON-NLS-1$
					int[] salida = new int[3];
					int[] entrada = new int[3];
					StringTokenizer st = new StringTokenizer(v, Messages.getString("CargadorMapa.25")); //$NON-NLS-1$
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
			else if (ultimoElemReconocido.compareTo(Messages.getString("CargadorMapa.26")) == 0){ //$NON-NLS-1$
				System.out.println(Messages.getString("CargadorMapa.27")); //$NON-NLS-1$
				Tramo tramoAux = buscarTramoConId(idUltimoElemReconocido);
				if (k.compareTo(Messages.getString("CargadorMapa.28"))==0 || k.compareTo(Messages.getString("CargadorMapa.29"))==0){ //$NON-NLS-1$ //$NON-NLS-2$
					tramoAux.setNombre(v);
				}
				if (k.compareTo(Messages.getString("CargadorMapa.30")) == 0) //$NON-NLS-1$
					tramoAux.setNumCarrilesDir1(new Integer(v).intValue());
				else if (k.compareTo(Messages.getString("CargadorMapa.31")) == 0) //$NON-NLS-1$
					tramoAux.setNumCarrilesDir2(new Integer(v).intValue());
				else if (k.compareTo(Messages.getString("CargadorMapa.32")) == 0) //$NON-NLS-1$
					tramoAux.setVelMax(new Float(v).floatValue());				
			}
			else if (ultimoElemReconocido.compareTo(Messages.getString("CargadorMapa.33")) == 0){ //$NON-NLS-1$
				System.out.println(Messages.getString("CargadorMapa.34")); //$NON-NLS-1$
				Via viaAux = buscarViaConId(idUltimoElemReconocido);
				if (k.compareTo(Messages.getString("CargadorMapa.35"))==0 || k.compareTo(Messages.getString("CargadorMapa.36"))==0){ //$NON-NLS-1$ //$NON-NLS-2$
					viaAux.setNombre(v);
				}
				else if (k.compareTo(Messages.getString("CargadorMapa.37")) == 0 && v.compareTo(Messages.getString("CargadorMapa.38")) == 0){ //$NON-NLS-1$ //$NON-NLS-2$
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
					lineasAutobuses.add(new LineaBus(viaAux,new ArrayList<Nodo>(), new int[0], 0, 0));					
				}
				else if (k.compareTo(Messages.getString("CargadorMapa.39")) == 0 && v.compareTo(Messages.getString("CargadorMapa.40")) == 0){ //$NON-NLS-1$ //$NON-NLS-2$
					//no hacer nada
				}
				else if(k.compareTo(Messages.getString("CargadorMapa.41")) == 0){ //$NON-NLS-1$
						  if(idUltimoElemReconocido > 0){
							System.out.println(Messages.getString("CargadorMapa.42")); //$NON-NLS-1$
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
				}//else if (k.compareTo("parada") == 0){
					  
				//}
				else {
					if (k.equals(Messages.getString("CargadorMapa.43"))) { //$NON-NLS-1$
						  viaAux.setTipo(new TipoViaHighway(v));
					}
					else { //Como estaba antes, aquí podría haber/surgir algún error porque utiliza información de nodo.
						ITipoElemento tipoVia = identificarTipoElem(k,v);
						if (tipoVia != null) {
							viaAux.setTipo(identificarTipoElem(k,v));
						}
					}
					
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
		  if (elem.compareTo(Messages.getString("CargadorMapa.44")) == 0) { //$NON-NLS-1$
			System.out.println(Messages.getString("CargadorMapa.45")); //$NON-NLS-1$
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
				System.out.println(Messages.getString("CargadorMapa.46")); //$NON-NLS-1$
			}
			/*tramos.add(new Tramo(nodos.get(indexFrom), nodos.get(indexTo),
					nodos.get(indexFrom).distancia(nodos.get(indexTo)), 2, 2,
					false));*/
		}
		  if (elem.compareTo(Messages.getString("CargadorMapa.47")) == 0 /*&& id>0*/){ //$NON-NLS-1$
			  System.out.println(Messages.getString("CargadorMapa.48")); //$NON-NLS-1$
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
		  if(elem.compareTo(Messages.getString("CargadorMapa.49")) == 0){ //$NON-NLS-1$
			  if(idUltimoElemReconocido > 0){
				System.out.println(Messages.getString("CargadorMapa.50")); //$NON-NLS-1$
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
		   if(elem.compareTo(Messages.getString("CargadorMapa.51")) == 0){ //$NON-NLS-1$
			   if(idUltimoElemReconocido > 0){
				 LineaBus lineaAux = buscarLineaBusConId(idUltimoElemReconocido);
				 Nodo parada = buscarNodoConId(id);
				 if (parada!=null){
					lineaAux.addParada(parada); 
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
	
	public LineaBus buscarLineaBusConId(int idlinea){
		Iterator<LineaBus> itLinea = lineasAutobuses.iterator();
		LineaBus lineaAux;
		while (itLinea.hasNext()){
			lineaAux = itLinea.next();
			if(lineaAux.getID() == idlinea)
				return lineaAux;
		}
		return null;
	}
	
	public ITipoElemento identificarTipoElem(String nombre,String valor){
		  if (nombre.equals(Messages.getString("CargadorMapa.52")))  //$NON-NLS-1$
			  return new TipoNodoHighway(valor);
		  else if (nombre.equals(Messages.getString("CargadorMapa.53"))) //$NON-NLS-1$
			  return new TipoNodoLeisure(valor); 
		  else if (nombre.equals(Messages.getString("CargadorMapa.54"))) //$NON-NLS-1$
			  return new TipoNodoManMade(valor);
		  else if (nombre.equals(Messages.getString("CargadorMapa.55"))) //$NON-NLS-1$
			  return new TipoNodoAmenity(valor);
		  return null;		
	}

	public void text(String text) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Messages.getString("CargadorMapa.56") + text); //$NON-NLS-1$

	}

	/*public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++)
			reportOnFile(args[0]);
	}*/

	public static void reportOnFile(String file) throws Exception {
		System.out.println(Messages.getString("CargadorMapa.57")); //$NON-NLS-1$
		System.out.println(Messages.getString("CargadorMapa.58") + file); //$NON-NLS-1$

		// This is all the code we need to parse
		// a document with our DocHandler.
		FileReader fr = new FileReader(file);
		QDParser.parse(cargadormapa, fr);

		fr.close();
	}

	public static Mapa cargar(String file)
			throws Exception {
		System.out.println(Messages.getString("CargadorMapa.59")); //$NON-NLS-1$
		System.out.println(Messages.getString("CargadorMapa.60") + file); //$NON-NLS-1$

		nodos = new ArrayList<Nodo>();
		tramos = new ArrayList<Tramo>();
		vias = new ArrayList<Via>();
		lineasAutobuses = new ArrayList<LineaBus>();
		

		// This is all the code we need to parse
		// a document with our DocHandler.
		FileReader fr = new FileReader(file);
		QDParser.parse(cargadormapa, fr);
		
		fr.close();
		System.out.println(Messages.getString("CargadorMapa.61")+nodos.size()); //$NON-NLS-1$
		System.out.println(Messages.getString("CargadorMapa.62")+tramos.size()); //$NON-NLS-1$
		System.out.println(Messages.getString("CargadorMapa.63") + vias.size()); //$NON-NLS-1$
		System.out.println(Messages.getString("CargadorMapa.64") + lineasAutobuses.size()); //$NON-NLS-1$
		
		System.out.println(Messages.getString("CargadorMapa.65")+nodos.get(0).getID()); //$NON-NLS-1$
		System.out.println(Messages.getString("CargadorMapa.66")+nodos.get(0).getPos().getLat()); //$NON-NLS-1$
		System.out.println(Messages.getString("CargadorMapa.67")+nodos.get(0).getPos().getLon()); //$NON-NLS-1$
		
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
		Mapa mapa=cargar(Messages.getString("CargadorMapa.69")); //$NON-NLS-1$
		System.out.println(Messages.getString("CargadorMapa.70")); //$NON-NLS-1$
	}
}
