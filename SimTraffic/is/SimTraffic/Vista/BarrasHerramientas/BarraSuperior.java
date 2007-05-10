package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.Acciones.AccionCargar;
import is.SimTraffic.Vista.Acciones.AccionComenzarSimulacion;
import is.SimTraffic.Vista.Acciones.AccionCopiar;
import is.SimTraffic.Vista.Acciones.AccionCortar;
import is.SimTraffic.Vista.Acciones.AccionDescargar;
import is.SimTraffic.Vista.Acciones.AccionDeshacer;
import is.SimTraffic.Vista.Acciones.AccionDetenerSimulacion;
import is.SimTraffic.Vista.Acciones.AccionGuardar;
import is.SimTraffic.Vista.Acciones.AccionNuevo;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.Acciones.AccionZoom;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaAyuda;
import is.SimTraffic.Vista.EscuchasRaton.MLPegar;
import is.SimTraffic.Utils.Tiempo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;



public class BarraSuperior extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private JButton pegarTB;
	private JButton zoomin;
	private JButton zoomout;
	private JButton simular;
	private JButton detener;
	private JPanel reloj;
	private JTextField tiempo;
	private Calendar cal;
	private IModelo modelo;
	
	public BarraSuperior(IControlador controlador, IModelo modelo,
			Ventana ventana) {
		
		this.modelo = modelo;
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		this.add(crearBarraArchivo(controlador, modelo, ventana));
		this.add(crearBarraEdicion(controlador, modelo, ventana));
		this.add(crearBarraZoom(controlador, modelo, ventana));
		this.add(crearBotonesSimulacion(controlador, modelo, ventana));
		
		this.add(Box.createGlue());
		
		cal = new GregorianCalendar();
		this.setTiempo(0);
		tiempo = new JTextField("", 8);
		


		
		tiempo.setHorizontalAlignment(JTextField.CENTER);
		tiempo.setEditable(false);
		tiempo.setMargin(new Insets(1,1,1,1));
		tiempo.setFont(new Font("Courier", Font.BOLD, 16));
		tiempo.setBackground(Color.BLACK);
		tiempo.setForeground(Color.GREEN);
		
		reloj = new JPanel(new BorderLayout());
		reloj.add(tiempo, BorderLayout.EAST);
		this.add(reloj);
	}

	private JToolBar crearBarraArchivo(IControlador controlador,
			IModelo modelo, Ventana ventana) {
		Barra archivoTB = new Barra();
		archivoTB.setName("Archivo");

		JButton nuevoTB = (JButton) archivoTB.añadirBoton("document-new.png",
				"document-new2.png", "Nuevo mapa", new AccionNuevo(controlador,
						ventana.getPanel_mapa()), false);
		nuevoTB.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para crear un nuevo mapa.", ventana));

		JButton cargarTB = (JButton) archivoTB.añadirBoton("document-open.png",
				"document-open2.png", "Cargar mapa", new AccionCargar(
						controlador, ventana.getPanel_mapa()), false);
		cargarTB.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para cargar un mapa.", ventana));

		JButton downloadTB = (JButton) archivoTB.añadirBoton("document-open.png",
				"document-open2.png", "Descargar mapa desde WEB", new AccionDescargar(controlador), false);
		downloadTB.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para descargar un mapa via web.", ventana));
		
		
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
		edicionTB.setName("Edición");
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

		pegarTB = (JButton) edicionTB.añadirBoton("edit-paste.png",
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
		zoomTB.setName("Zoom");

		zoomin = (JButton) zoomTB.añadirBoton("zoom_in.png",
				"zoom_in2.png", "Aumentar tamaño de la representación",
				new AccionZoom(ventana.getPanel_mapa(), 0.5), false);
		zoomin.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para acercar el zoom.", ventana));

		zoomout = (JButton) zoomTB.añadirBoton("zoom_out.png",
				"zoom_out2.png", "Disminuir tamaño de la representación",
				new AccionZoom(ventana.getPanel_mapa(), 2), false);
		zoomout.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para alejar el zoom.", ventana));

		return zoomTB;
	}

	private JToolBar crearBotonesSimulacion(IControlador controlador,
			IModelo modelo, Ventana ventana) {
		Barra esto = new Barra();
		esto.setName("Simulación");

		simular = (JButton) esto.añadirBoton("simular.png",
				"simular2.png", "Comenzar simulación",
				new AccionComenzarSimulacion(controlador, modelo.getSimulacion().getParam()), false);
		simular
				.addMouseMotionListener(new EscuchaAyuda(
						"Pulse aquí para comenzar una nueva simulación sobre el mapa actual.",
						ventana));

		detener = (JButton) esto.añadirBoton("parar.png", "parar2.png",
				"Detener simulación", new AccionDetenerSimulacion(controlador),
				false);
		detener.addMouseMotionListener(new EscuchaAyuda(
				"Pulse aquí para parar la simulación actual.", ventana));

		return esto;
	}
	
	public JButton getBotonPegar(){
		return this.pegarTB;
	}
	
	public JButton getBotonZoomIn(){
		return this.zoomin;
	}
	
	public JButton getBotonZoomOut(){
		return this.zoomout;
	}
	
	public JButton getBotonSimular(){
		return this.simular;
	}
		
	public JButton getBotonDetener(){
		return this.detener;
	}

	public void setTiempo(long t) {
		//cal.setTimeInMillis(t);
		
		/*int hour12 = cal.get(Calendar.HOUR);            // 0..11
	    int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
	    int min = cal.get(Calendar.MINUTE);             // 0..59
	    int sec = cal.get(Calendar.SECOND);             // 0..59
	    int ms = cal.get(Calendar.MILLISECOND);         // 0..999
	    int ampm = cal.get(Calendar.AM_PM);             // 0=AM, 1=PM
	    
	    String hora = convierte(hour24)+":"+convierte(min)+":"+convierte(sec);
	    */
		int tiempoaux = (int) t;
		int hour24 = ( tiempoaux /(60*60) );
		tiempoaux = tiempoaux-((hour24)*3600);
		int min = (tiempoaux / 60);
		tiempoaux = tiempoaux-(min*60);		
		int sec = (tiempoaux % 60);
	    if (tiempo!=null){
	    	if (hour24 >= 7 && hour24 <15 ) {
	    		tiempo.setBackground(Color.BLUE);
	    		tiempo.setToolTipText("MAÑANA");
	    	} else if (hour24 >= 15 && hour24 <23 ) {
	    		tiempo.setBackground(Color.RED);
	    		tiempo.setToolTipText("TARDE");
	    	}
	    	else {
	    		tiempo.setBackground(Color.BLACK);
	    		tiempo.setToolTipText("NOCHE");
	    	}
	    	String hora = convierte(hour24)+":"+convierte(min)+":"+convierte(sec);

	    	tiempo.setText(hora);
	    	//this.repaint();
	    }
	    
	    
	    
	}
	
	
	
	private static String convierte(int valor){
		if (valor <10){
			return "0"+valor;
		}
		else return ""+valor;
	}
}
