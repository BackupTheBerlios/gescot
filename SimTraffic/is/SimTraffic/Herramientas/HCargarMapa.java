package is.SimTraffic.Herramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;

import is.SimTraffic.Herramientas.CargarMapa.*;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Se�al;
import is.SimTraffic.Utils.Tiempo;
import is.SimTraffic.Vista.PanelMapa;

/**
 * Herramienta para cargar un mapa desde un fichero osm.
 * <p>
 * 
 * @author Grupo ISTrafico
 * 
 */
public class HCargarMapa implements IHerramienta {
	/**
	 * 
	 */
	IControlador controlador;

	/**
	 * 
	 */
	PanelMapa panel;
	
	private JFileChooser fc;

	/**
	 * @param controlador
	 * @roseuid 45B8B3A70182
	 */
	public HCargarMapa(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel = panel;
	}

	/**
	 * @param modelo
	 * @return int
	 * @roseuid 45C1E5740203
	 */
	public int hacer(IModelo modelo) {
		Mapa mapaNuevo = null;
		fc = new JFileChooser(".//is/SimTraffic/Ejemplos");
		String[] ext = new String[] { "osm" };
		fc.addChoosableFileFilter(new ExtFilter(ext, "Mapa OSM (*.osm)"));
		int val = fc.showOpenDialog(null);

		if (val == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			// Faltar�a definir comportamiento ante fallos.
			try {
				mapaNuevo = CargadorMapa.cargar(file.getAbsolutePath());
				if (mapaNuevo.getNodos().get(0) != null) {
					panel.centrarEnPosicion(mapaNuevo.getNodos().get(0)
							.getPos().getLat(), mapaNuevo.getNodos().get(0)
							.getPos().getLon());
				}
				modelo.setMapa(mapaNuevo);
						
					

			} catch (Exception e) {
				System.out.println("Error al leer archivo");
				e.printStackTrace();
			}
			// if (mapaNuevo==null)
			// System.out.println("Mapa vac�o?");
		
			//Ahora cargamos las se�ales.
			cargarSe�ales(modelo.getMapa());
		}
		
		return 0;
	}
	
	public void cargarSe�ales(Mapa mapa){
		List<Se�al> listaSe�ales = null;
		
		String rutaSe�ales = this.fc.getSelectedFile().getAbsolutePath();
		
		if (rutaSe�ales.contains(".osm")){
			rutaSe�ales = rutaSe�ales.replaceAll(".osm",".sem");
		} else {
			rutaSe�ales += ".sem";
		}
		
		try{
			FileInputStream flujoIn = new FileInputStream(rutaSe�ales);
			ObjectInputStream objetoDentro = new ObjectInputStream(flujoIn);
			listaSe�ales = (ArrayList)objetoDentro.readObject();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		if (listaSe�ales != null){
			Iterator iteradorListaSe�ales = listaSe�ales.iterator();
			Iterator iteradorListaNodos = mapa.getNodos().iterator();
			
			//Dobre bucle que asigna las se�ales a los nodos.
			while(iteradorListaNodos.hasNext()){
				Nodo nodoActual = (Nodo)iteradorListaNodos.next();
				iteradorListaSe�ales = listaSe�ales.iterator();
				while (iteradorListaSe�ales.hasNext()){
					Se�al se�alActual = (Se�al)iteradorListaSe�ales.next();
					if(se�alActual.getNodoUbicacion().getID() == nodoActual.getID()){
						nodoActual.setSe�al(se�alActual);
					}
				}
			}
		}
		
	}

	/**
	 * @param modelo
	 * @return int
	 * @roseuid 45C1E5740270
	 */
	public int deshacer(IModelo modelo) {
		return 0;
	}

	public String toString() {
		return Tiempo.Hora() + " - " + "Mapa Cargado";
	}
}
