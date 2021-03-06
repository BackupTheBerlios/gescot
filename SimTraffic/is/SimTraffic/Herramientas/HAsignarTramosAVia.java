/**
 * 
 */
package is.SimTraffic.Herramientas;

import java.util.ArrayList;

import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;
import is.SimTraffic.Utils.Tiempo;

/**
 * @author Grupo ISTrafico
 *
 */
public class HAsignarTramosAVia implements IHerramienta {
	

	Via viaAInsertar;
	int id;
	ITipoElemento tipoVia;
	String nombreVia;
	ArrayList<Tramo> tramosVia;
	
	
	
	public HAsignarTramosAVia(int ID,ITipoElemento tipo,String nombre,ArrayList<Tramo> tramos){
		this.id=ID;
		this.nombreVia=nombre;
		this.tipoVia=tipo;
		this.tramosVia=tramos;
		this.viaAInsertar=configurarVia();
	}

	/** (non-Javadoc)
	 * @see is.SimTraffic.Herramientas.IHerramienta#hacer(is.SimTraffic.IModelo)
	 */
	public int hacer(IModelo modelo) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	/** (non-Javadoc)
	 * @see is.SimTraffic.Herramientas.IHerramienta#deshacer(is.SimTraffic.IModelo)
	 */
	public int deshacer(IModelo modelo) {
		// TODO Auto-generated method stub
		//modelo.getMapa().eliminar(this.viaAInsertar);		
		return 0;
	}
	
	/**
	 * 
	 * @return void
	 */
	public Via configurarVia(){
		Via viaATratar = new Via();
		viaATratar.setID(this.id);
		viaATratar.setNombre(this.nombreVia);
		viaATratar.setTipo(this.tipoVia);
		viaATratar.setTramos(this.tramosVia);
		return viaATratar;		
	}
	
	public String getNombre() {
		return nombreVia;
	}
	
	public int getID() {
		return id;
	}
	
	public ITipoElemento getTipoVia() {
		return tipoVia;
	}
	
	public ArrayList<Tramo> getTramos() {
		return tramosVia;
	}
	
	public Via getviaAInsertar(){
		return viaAInsertar;
	}
	
	public void setNombre(String nombre) {
		nombreVia=nombre;
	}
	
	public void setID(int ID) {
		this.id=ID;
	}
	
	public void setTipoVia(ITipoElemento TipoVia) {
		this.tipoVia = TipoVia;
	}
	
	public void setTramos(ArrayList<Tramo> Tramos) {
		this.tramosVia=Tramos;
	}
	
	public void setviaAInsertar(Via viaAInsertar){
		this.viaAInsertar=viaAInsertar;
	}
	public String toString(){
		return Tiempo.Hora()+Messages.getString("HAsignarTramosAVia.0")+Messages.getString("HAsignarTramosAVia.1"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
