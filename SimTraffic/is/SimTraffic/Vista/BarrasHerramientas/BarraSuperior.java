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
		JToolBar archivoTB = new JToolBar();

		JButton nuevoTB = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\document-new.png"));
		nuevoTB.setMargin(new Insets(1, 1, 1, 1));
		nuevoTB.addActionListener(new AccionNuevo(modelo));
		String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\document-new2.png";
		nuevoTB.setToolTipText("<html>Nuevo mapa <img src=" + imageName
				+ "></html>");

		JButton cargarTB = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\document-open.png"));
		cargarTB.setMargin(new Insets(1, 1, 1, 1));
		cargarTB.addActionListener(new AccionCargar(controlador, ventana
				.getPanel_mapa()));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\document-open2.png";
		cargarTB.setToolTipText("<html>Cargar mapa <img src=" + imageName
				+ "></html>");

		JButton guardarTB = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\document-save.png"));
		guardarTB.setMargin(new Insets(1, 1, 1, 1));
		guardarTB.addActionListener(new AccionGuardar(controlador));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\document-save2.png";
		guardarTB.setToolTipText("<html>Guardar mapa <img src=" + imageName
				+ "></html>");

		archivoTB.add(nuevoTB);
		archivoTB.add(cargarTB);
		archivoTB.add(guardarTB);
		return archivoTB;
	}

	private JToolBar crearBarraEdicion(IControlador controlador,
			IModelo modelo, Ventana ventana) {
		JToolBar edicionTB = new JToolBar();

		JButton cortarTB = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\edit-cut.png"));
		cortarTB.setMargin(new Insets(1, 1, 1, 1));
		cortarTB.addActionListener(new AccionCortar(modelo, controlador,
				ventana.getPanel_mapa()));
		String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\edit-cut2.png";
		cortarTB.setToolTipText("<html>Cortar <img src=" + imageName
				+ "></html>");

		JButton copiarTB = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\edit-copy.png"));
		copiarTB.setMargin(new Insets(1, 1, 1, 1));
		copiarTB.addActionListener(new AccionCopiar(modelo, controlador,
				ventana.getPanel_mapa()));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\edit-copy2.png";
		copiarTB.setToolTipText("<html>Copiar <img src=" + imageName
				+ "></html>");
		// copiarTB.addActionListener(new AccionSobreMapa(
		// new MLCopiar(modelo, controlador, panel_mapa), this,
		// escuchaTeclado));

		JButton pegarTB = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\edit-paste.png"));
		pegarTB.setMargin(new Insets(1, 1, 1, 1));
		// pegarTB.addActionListener(new AccionCortar());
		pegarTB.addActionListener(new AccionSobreMapa(new MLPegar(modelo,
				controlador, ventana.getPanel_mapa()), ventana, ventana
				.getEscuchaTeclado(), -1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\edit-paste2.png";
		pegarTB
				.setToolTipText("<html>Pegar <img src=" + imageName
						+ "></html>");

		JButton deshacerTB = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\edit-undo.png"));
		deshacerTB.setMargin(new Insets(1, 1, 1, 1));
		deshacerTB.addActionListener(new AccionDeshacer(controlador, ventana
				.getPanel_mapa()));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\edit-undo2.png";
		deshacerTB.setToolTipText("<html>Deshacer <img src=" + imageName
				+ "></html>");

		edicionTB.add(cortarTB);
		edicionTB.add(copiarTB);
		edicionTB.add(pegarTB);
		edicionTB.add(deshacerTB);

		return edicionTB;
	}

	private JToolBar crearBarraZoom(IControlador controlador, IModelo modelo,
			Ventana ventana) {
		JToolBar zoomTB = new JToolBar();

		JButton zoomin = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\zoom_in.png"));
		zoomin.setMargin(new Insets(1, 1, 1, 1));
		String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\zoom_in2.png";
		zoomin.addActionListener(new AccionZoom(ventana.getPanel_mapa(), 0.5));
		zoomin
				.setToolTipText("<html>Disminuir tamaño de la representación <img src="
						+ imageName + "></html>");

		JButton zoomout = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\zoom_out.png"));
		zoomout.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\zoom_out2.png";
		zoomout.addActionListener(new AccionZoom(ventana.getPanel_mapa(), 2));
		zoomout
				.setToolTipText("<html>Aumentar tamaño de la representación <img src="
						+ imageName + "></html>");

		zoomTB.add(zoomin);
		zoomTB.add(zoomout);
		return zoomTB;
	}

	private JToolBar crearBotonesSimulacion(IControlador controlador,
			IModelo modelo) {
		JToolBar esto = new JToolBar();

		JButton boton1 = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\simular.png"));
		boton1.setMargin(new Insets(1, 1, 1, 1));
		String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\simular2.png";
		boton1.setToolTipText("<html>Comenzar simulación <img src=" + imageName
				+ "></html>");
		boton1.addActionListener(new AccionComenzarSimulacion(controlador,
				modelo));
		esto.add(boton1);

		JButton boton2 = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\parar.png"));
		boton2.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\parar2.png";
		boton2.setToolTipText("<html>Detener simulación <img src=" + imageName
				+ "></html>");
		boton2.addActionListener(new AccionDetenerSimulacion(controlador,
				modelo));
		esto.add(boton2);

		return esto;
	}

}
