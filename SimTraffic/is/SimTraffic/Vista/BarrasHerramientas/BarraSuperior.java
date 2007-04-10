package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.Acciones.AccionCargar;
import is.SimTraffic.Vista.Acciones.AccionComenzarSimulacion;
import is.SimTraffic.Vista.Acciones.AccionCopiar;
import is.SimTraffic.Vista.Acciones.AccionCortar;
import is.SimTraffic.Vista.Acciones.AccionDeshacer;
import is.SimTraffic.Vista.Acciones.AccionDetenerSimulacion;
import is.SimTraffic.Vista.Acciones.AccionGuardar;
import is.SimTraffic.Vista.Acciones.AccionNuevo;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.Acciones.AccionZoom;
import is.SimTraffic.Vista.EscuchasRaton.MLPegar;

import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class BarraSuperior extends JPanel {
	private static final long serialVersionUID = 1L;

	public BarraSuperior(IControlador controlador, IModelo modelo,
			Ventana ventana) {

		this.add(crearBarraArchivo(controlador, modelo, ventana));
		this.add(crearBarraEdicion(controlador, modelo, ventana));
		this.add(crearBarraZoom(controlador, modelo, ventana));

		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		this.add(crearBotonesSimulacion(controlador, modelo));

	}

	private JToolBar crearBarraArchivo(IControlador controlador,
			IModelo modelo, Ventana ventana) {
		Barra archivoTB = new Barra();

		JButton nuevoTB = archivoTB.añadirBoton("document-new.png",
				"document-new2.png", "Nuevo mapa", new AccionNuevo(modelo, ventana.getPanel_mapa()));

		JButton cargarTB = archivoTB.añadirBoton("document-open.png",
				"document-open2.png", "Cargar mapa", new AccionCargar(
						controlador, ventana.getPanel_mapa()));

		JButton guardarTB = archivoTB.añadirBoton("document-save.png",
				"document-save2.png", "Guardar mapa", new AccionGuardar(
						controlador));

		return archivoTB;
	}

	private JToolBar crearBarraEdicion(IControlador controlador,
			IModelo modelo, Ventana ventana) {
		Barra edicionTB = new Barra();

		JButton cortarTB = edicionTB.añadirBoton("edit-cut.png",
				"edit-cut2.png", "Cortar", new AccionCortar(modelo,
						controlador, ventana.getPanel_mapa()));

		JButton copiarTB = edicionTB.añadirBoton("edit-copy.png",
				"edit-copy2.png", "Copiar", new AccionCopiar(modelo,
						controlador, ventana.getPanel_mapa()));

		JButton pegarTB = edicionTB.añadirBoton("edit-paste.png",
				"edit-paste2.png", "Pegar", new AccionSobreMapa(new MLPegar(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), -1));

		JButton deshacerTB = edicionTB.añadirBoton("edit-undo.png",
				"edit-undo2.png", "Deshacer", new AccionDeshacer(controlador,
						ventana.getPanel_mapa()));

		return edicionTB;
	}

	private JToolBar crearBarraZoom(IControlador controlador, IModelo modelo,
			Ventana ventana) {
		Barra zoomTB = new Barra();

		JButton zoomin = zoomTB.añadirBoton("zoom_in.png", "zoom_in2.png",
				"Aumentar tamaño de la representación", new AccionZoom(ventana
						.getPanel_mapa(), 0.5));

		JButton zoomout = zoomTB.añadirBoton("zoom_out.png", "zoom_out2.png",
				"Disminuir tamaño de la representación", new AccionZoom(ventana
						.getPanel_mapa(), 2));

		return zoomTB;
	}

	private JToolBar crearBotonesSimulacion(IControlador controlador,
			IModelo modelo) {
		Barra esto = new Barra();

		JButton boton1 = esto.añadirBoton("simular.png", "simular2.png",
				"Comenzar simulación", new AccionComenzarSimulacion(
						controlador, modelo));

		JButton boton2 = esto.añadirBoton("parar.png", "parar2.png",
				"Detener simulación", new AccionDetenerSimulacion(controlador,
						modelo));

		return esto;
	}

}
