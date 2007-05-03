package is.SimTraffic.Herramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import is.SimTraffic.Herramientas.CargarMapa.*;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Señal;
import is.SimTraffic.Utils.Tiempo;
import is.SimTraffic.Vista.PanelEsperaCargando;
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
	 * Variable de archivo, utilizada por el thread que carga el mapa.
	 */
	protected File file;

	/**
	 * Variable del mapa cargado, definida como protected porque es utilizada
	 * por el thread que carga el mapa.
	 */
	protected Mapa mapaNuevo;

	/**
	 * Variable del modelo, utilizada por el thread que carga el mapa.
	 */
	protected IModelo modelo;

	/**
	 * Panel para mostrar el mensaje de cargando...
	 * <p>
	 * Se utiliza una variable estatica para que nos se tengan que cargar sus
	 * componentes cada vez.
	 */
	public static PanelEsperaCargando p = new PanelEsperaCargando("Cargando...", "Cargando mapa...");

	protected boolean error;

	/**
	 * @param controlador
	 * @roseuid 45B8B3A70182
	 */
	public HCargarMapa(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel = panel;
		// p.validate();
	}

	/**
	 * @param modelo
	 * @return int
	 * @roseuid 45C1E5740203
	 */
	public int hacer(IModelo modelo) {
		mapaNuevo = null;
		this.modelo = modelo;
		error = false;
		fc = new JFileChooser(".//is/SimTraffic/Ejemplos");
		String[] ext = new String[] { "osm" };
		fc.addChoosableFileFilter(new ExtFilter(ext, "Mapa OSM (*.osm)"));
		int val = fc.showOpenDialog(null);

		if (val == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();

			new Thread(p).start();
			p.repaint();

			Thread t = new Thread() {
				public void run() {
					try {
						mapaNuevo = CargadorMapa.cargar(file.getAbsolutePath());
						if (mapaNuevo.getNodos().get(0) != null) {
							panel.centrarEnPosicion(mapaNuevo.getNodos().get(0)
									.getPos().getLat(), mapaNuevo.getNodos()
									.get(0).getPos().getLon());
						}
						HCargarMapa.this.modelo.setMapa(mapaNuevo);

					} catch (Exception e) {
						p.terminar();
						System.out.println("Error al leer archivo");
						e.printStackTrace();
						error = true;
					}
					
					controlador.herramienta(null);

					if (!error) {

						// Ahora cargamos las señales.
						try {
							cargarSeñales(HCargarMapa.this.modelo.getMapa());
						} catch (IOException e) {
							p.terminar();
							JOptionPane.showMessageDialog(null,
									"Error al abrir/leer fichero de semaforos");

						} catch (ClassNotFoundException e) {
							p.terminar();
							JOptionPane
									.showMessageDialog(null,
											"Problema en el fichero de señales, no se cargaran.");
						}

						p.terminar();
					}
				}
			};
			t.start();
		}
		if (error)
			return -1;
		return 0;
	}

	/**
	 * Método para cargar las señales de un fichero.
	 * <p>
	 * Este método lee las señales guardadas como objetos serializables en un
	 * fichero.
	 * 
	 * @param mapa
	 *            Mapa en el que se deben cargar las señales.
	 * @throws IOException
	 *             Excepcion si hay un error al leer el fichero.
	 * @throws ClassNotFoundException
	 *             Excepción si hay un problema en las clases encontradas en el
	 *             fichero.
	 */
	@SuppressWarnings("unchecked")
	public void cargarSeñales(Mapa mapa) throws IOException,
			ClassNotFoundException {
		List<Señal> listaSeñales = null;

		String rutaSeñales = this.fc.getSelectedFile().getAbsolutePath();

		if (rutaSeñales.contains(".osm")) {
			rutaSeñales = rutaSeñales.replaceAll(".osm", ".sem");
		} else {
			rutaSeñales += ".sem";
		}

		FileInputStream flujoIn = new FileInputStream(rutaSeñales);
		ObjectInputStream objetoDentro = new ObjectInputStream(flujoIn);
		listaSeñales = (List<Señal>) objetoDentro.readObject();

		if (listaSeñales != null) {
			Iterator iteradorListaSeñales = listaSeñales.iterator();
			Iterator iteradorListaNodos = mapa.getNodos().iterator();

			// Dobre bucle que asigna las señales a los nodos.
			while (iteradorListaNodos.hasNext()) {
				Nodo nodoActual = (Nodo) iteradorListaNodos.next();
				iteradorListaSeñales = listaSeñales.iterator();
				while (iteradorListaSeñales.hasNext()) {
					Señal señalActual = (Señal) iteradorListaSeñales.next();
					if (señalActual.getNodoUbicacion().getID() == nodoActual
							.getID()) {
						nodoActual.setSeñal(señalActual);
					}
				}
			}
		}

	}

	public int deshacer(IModelo modelo) {
		return 0;
	}

	public String toString() {
		return Tiempo.Hora() + " - " + "Mapa Cargado";
	}
}
