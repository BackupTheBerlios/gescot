package is.SimTraffic.Mapa;

import is.SimTraffic.Messages;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class LineaBus extends Via{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5853555892570813651L;

	/**
	 * En nuestra implementacion las lineas de autobuses son tambien vias y pero estas deben 
	 * de añadir un vector de paradas que no tienen las demas vias
	 */
	private ArrayList<Nodo> Paradas;
	
	/**
	 * Intervalo de salida entre cada dos autobuses,configurable para varias horas del dia 
	 * diferentes
	 */
	private int[] intervalos;
	
	/**
	 * Horas de comienzo y fin de servicio durante el dia
	 */
	private int comienzoServicio;
	
	private int finServicio;
	
	
	/**
	 * Color de la linea
	 */
	private Color color;
	
	/**
	 * Se puede añadir aqui si circula festivos etc....
	 * @return
	 */
	
	public LineaBus(Via via,ArrayList<Nodo> Paradas,int[] intervalos,int start,int end){
		
		//Ponemos a la linea las caracteristicas de la via
		this.setNombre(via.getNombre());
		this.setID(via.getID());
		this.setTramos(via.getTramos());
		this.setTipo(via.getTipo());
		
		//Ponemos las caracteristicas propias de la linea de bus
		this.Paradas=Paradas;
		this.intervalos=intervalos;
		this.comienzoServicio=start;
		this.finServicio=end;
	}

	public void addParada(Nodo parada){
		Paradas.add(parada);
	}
	
	public ArrayList<Nodo> getParadas() {
		return Paradas;
	}

	public void setParadas(ArrayList<Nodo> paradas) {
		Paradas = paradas;
	}

	public String transformarLineaBusaOSM() {
		String s=new String();
		Iterator<Tramo> tram = this.getTramos().iterator();
		s=s.concat(Messages.getString("LineaBus.0")+this.getID()+Messages.getString("LineaBus.1")); //$NON-NLS-1$ //$NON-NLS-2$
		while (tram.hasNext()) {
			Tramo tramo_aux = tram.next();
			s=s.concat(Messages.getString("LineaBus.2")+tramo_aux.getID()+Messages.getString("LineaBus.3")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (this.getTipo()!=null) 
			s=s.concat(Messages.getString("LineaBus.4")+this.getTipo().getTipo()+Messages.getString("LineaBus.5")+this.getTipo().getValorTipo()+Messages.getString("LineaBus.6")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if (this.getNombre()!=null) 
			s=s.concat(Messages.getString("LineaBus.7")+this.getNombre()+Messages.getString("LineaBus.8")); //$NON-NLS-1$ //$NON-NLS-2$
		s=s.concat(Messages.getString("LineaBus.9")); //$NON-NLS-1$
		Iterator<Nodo> nodos = this.getParadas().iterator();
		while (nodos.hasNext()){
			Nodo nodo_aux = nodos.next();
			s=s.concat(Messages.getString("LineaBus.10")+nodo_aux.getID()+Messages.getString("LineaBus.11")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		s=s.concat(Messages.getString("LineaBus.12")); //$NON-NLS-1$
		return s;
	}

	public int[] getIntervalos() {
		return intervalos;
	}

	public void setIntervalos(int[] intervalos) {
		this.intervalos = intervalos;
	}

	public int getComienzoServicio() {
		return comienzoServicio;
	}

	public void setComienzoServicio(int comienzoServicio) {
		this.comienzoServicio = comienzoServicio;
	}

	public int getFinServicio() {
		return finServicio;
	}

	public void setFinServicio(int finServicio) {
		this.finServicio = finServicio;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
