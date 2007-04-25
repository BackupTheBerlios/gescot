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
 * Herramienta para descargar un mapa desde un servidor osm.
 * <p>
 * 
 * @author Grupo ISTrafico
 * 
 */
public class HDescargarMapa implements IHerramienta{

	IControlador controlador;

	/**
	 * 
	 */
	PanelMapa panel;
	
	public HDescargarMapa(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel = panel;
	}
	
	public int hacer(IModelo modelo) {
		
		return 0;
	}

	public int deshacer(IModelo modelo) {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}
}
