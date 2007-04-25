package is.SimTraffic.Mapa;

import java.util.ArrayList;
import java.util.Iterator;

public class LineaBus extends Via{
	
	
	/**
	 * En nuestra implementacion las lineas de autobuses son tambien vias y pero estas deben 
	 * de añadir un vector de paradas que no tienen las demas vias
	 */
	private ArrayList<Boolean> Paradas;
	
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
	 * Se puede añadir aqui si circula festivos etc....
	 * @return
	 */
	
	public LineaBus(ArrayList<Tramo> tramos,ArrayList<Boolean> Paradas,int[] intervalos,int start,int end){
		this.setTramos(tramos);
		this.Paradas=Paradas;
		this.intervalos=intervalos;
		this.comienzoServicio=start;
		this.finServicio=end;
	}
	
	public LineaBus(Via via,ArrayList<Boolean> paradas,int[] intervalos,int start,int end){
	
		
	}
	
	public ArrayList<Boolean> getParadas() {
		return Paradas;
	}

	public void setParadas(ArrayList<Boolean> paradas) {
		Paradas = paradas;
	}

	public String transformarLineaBusaOSM() {
		String s=new String();
		Iterator<Tramo> tram = this.getTramos().iterator();
		s=s.concat("<way id='"+this.getID()+"'>\n");
		while (tram.hasNext()) {
			Tramo tramoaux = tram.next();
			s=s.concat("<seg id='"+tramoaux.getID()+"'/>\n");
		}
		if (this.getTipo()!=null) s=s.concat("<tag k='"+this.getTipo().getTipo()+"' v='"+this.getTipo().getValorTipo()+"' />\n");
		if (this.getNombre()!=null) s=s.concat("<tag k='nombre' v='"+this.getNombre()+"' />\n");
		s=s.concat("<tag k ='bus line' v='yes' />\n");
		s=s.concat("</way>\n");
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
}
