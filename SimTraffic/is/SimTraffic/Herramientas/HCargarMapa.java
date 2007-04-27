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
import is.SimTraffic.Mapa.Señal;
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
			// Faltaría definir comportamiento ante fallos.
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
			// System.out.println("Mapa vacío?");
		
			//Ahora cargamos las señales.
			cargarSeñales(modelo.getMapa());
		}
		
		return 0;
	}
	
	public void cargarSeñales(Mapa mapa){
		List<Señal> listaSeñales = null;
		
		String rutaSeñales = this.fc.getSelectedFile().getAbsolutePath();
		
		if (rutaSeñales.contains(".osm")){
			rutaSeñales = rutaSeñales.replaceAll(".osm",".sem");
		} else {
			rutaSeñales += ".sem";
		}
		
		try{
			FileInputStream flujoIn = new FileInputStream(rutaSeñales);
			ObjectInputStream objetoDentro = new ObjectInputStream(flujoIn);
			listaSeñales = (ArrayList)objetoDentro.readObject();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		if (listaSeñales != null){
			Iterator iteradorListaSeñales = listaSeñales.iterator();
			Iterator iteradorListaNodos = mapa.getNodos().iterator();
			
			//Dobre bucle que asigna las señales a los nodos.
			while(iteradorListaNodos.hasNext()){
				Nodo nodoActual = (Nodo)iteradorListaNodos.next();
				iteradorListaSeñales = listaSeñales.iterator();
				while (iteradorListaSeñales.hasNext()){
					Señal señalActual = (Señal)iteradorListaSeñales.next();
					if(señalActual.getNodoUbicacion().getID() == nodoActual.getID()){
						nodoActual.setSeñal(señalActual);
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
