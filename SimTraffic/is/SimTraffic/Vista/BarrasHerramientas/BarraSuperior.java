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
import is.SimTraffic.Vista.EscuchasRaton.EscuchaAyuda;
import is.SimTraffic.Vista.EscuchasRaton.MLPegar;

import javax.swing.BoxLayout;
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

		this.add(crearBotonesSimulacion(controlador, modelo, ventana));

	}

	private JToolBar crearBarraArchivo(IControlador controlador,
			IModelo modelo, Ventana ventana) {
		Barra archivoTB = new Barra();

		JButton nuevoTB = (JButton) archivoTB.añadirBoton("document-new.png",
				"document-new2.png", "Nuevo mapa", new AccionNuevo(modelo,
						ventana.getPanel_mapa()), false);
		nuevoTB.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para crear un nuevo mapa.", ventana));

		JButton cargarTB = (JButton) archivoTB.añadirBoton("document-open.png",
				"document-open2.png", "Cargar mapa", new AccionCargar(
						controlador, ventana.getPanel_mapa()), false);
		cargarTB.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para cargar un mapa.", ventana));

		JButton guardarTB = (JButton) archivoTB.añadirBoton(
				"document-save.png", "document-save2.png", "Guardar mapa",
				new AccionGuardar(controlador), false);
		guardarTB.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para guardar el mapa actual.", ventana));

		return archivoTB;
	}

	private JToolBar crearBarraEdicion(IControlador controlador,
			IModelo modelo, Ventana ventana) {
		Barra edicionTB = new Barra();

		JButton cortarTB = (JButton) edicionTB.añadirBoton("edit-cut.png",
				"edit-cut2.png", "Cortar", new AccionCortar(modelo,
						controlador, ventana.getPanel_mapa()), false);
		cortarTB
				.addMouseMotionListener(new EscuchaAyuda(
						"Pulse aquí para cortar los elementos seleccionados.",
						ventana));

		JButton copiarTB = (JButton) edicionTB.añadirBoton("edit-copy.png",
				"edit-copy2.png", "Copiar", new AccionCopiar(modelo,
						controlador, ventana.getPanel_mapa()), false);
		copiarTB
				.addMouseMotionListener(new EscuchaAyuda(
						"Pulse aquí para copiar los elementos seleccionados.",
						ventana));

		JButton pegarTB = (JButton) edicionTB.añadirBoton("edit-paste.png",
				"edit-paste2.png", "Pegar", new AccionSobreMapa(new MLPegar(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), -1), false);
		pegarTB.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para pegar los elementos cortados o copiados.",
				ventana));

		JButton deshacerTB = (JButton) edicionTB.añadirBoton("edit-undo.png",
				"edit-undo2.png", "Deshacer", new AccionDeshacer(controlador,
						ventana.getPanel_mapa()), false);
		deshacerTB.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para deshacer la última acción.", ventana));

		return edicionTB;
	}

	private JToolBar crearBarraZoom(IControlador controlador, IModelo modelo,
			Ventana ventana) {
		Barra zoomTB = new Barra();

		JButton zoomin = (JButton) zoomTB.añadirBoton("zoom_in.png",
				"zoom_in2.png", "Aumentar tamaño de la representación",
				new AccionZoom(ventana.getPanel_mapa(), 0.5), false);
		zoomin.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para acercar el zoom.", ventana));

		JButton zoomout = (JButton) zoomTB.añadirBoton("zoom_out.png",
				"zoom_out2.png", "Disminuir tamaño de la representación",
				new AccionZoom(ventana.getPanel_mapa(), 2), false);
		zoomout.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para alejar el zoom.", ventana));

		return zoomTB;
	}

	private JToolBar crearBotonesSimulacion(IControlador controlador,
			IModelo modelo, Ventana ventana) {
		Barra esto = new Barra();

		JButton boton1 = (JButton) esto.añadirBoton("simular.png",
				"simular2.png", "Comenzar simulación",
				new AccionComenzarSimulacion(controlador), false);
		boton1
				.addMouseMotionListener(new EscuchaAyuda(
						"Pulse aquí para comenzar una nueva simulación sobre el mapa actual.",
						ventana));

		JButton boton2 = (JButton) esto.añadirBoton("parar.png", "parar2.png",
				"Detener simulación", new AccionDetenerSimulacion(controlador),
				false);
		boton2.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para parar la simulación actual.", ventana));

		return esto;
	}

}
