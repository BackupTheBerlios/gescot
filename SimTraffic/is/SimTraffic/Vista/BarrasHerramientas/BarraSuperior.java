package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
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
import is.SimTraffic.Vista.Acciones.AccionPausarSimulacion;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.Acciones.AccionZoom;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaAyuda;
import is.SimTraffic.Vista.EscuchasRaton.MLPegar;

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
	private JButton pausar;
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
		this.add(crearReloj(controlador, modelo,ventana));
		this.add(Box.createGlue());
		
		
		
	}

	public JPanel crearReloj(IControlador controlador,IModelo modelo, Ventana ventana){
		cal = new GregorianCalendar();
		this.setTiempo(0);
		tiempo = new JTextField(Messages.getString("BarraSuperior.0"), 8); //$NON-NLS-1$
		
		tiempo.setHorizontalAlignment(JTextField.CENTER);
		tiempo.setEditable(false);
		tiempo.setMargin(new Insets(1,1,1,1));
		tiempo.setFont(new Font(Messages.getString("BarraSuperior.1"), Font.BOLD, 16)); //$NON-NLS-1$
		tiempo.setBackground(Color.BLACK);
		tiempo.setForeground(Color.GREEN);
		
		reloj = new JPanel(new BorderLayout());
		reloj.add(tiempo, BorderLayout.EAST);
		return reloj;
		
	}
	
	private JToolBar crearBarraArchivo(IControlador controlador,
			IModelo modelo, Ventana ventana) {
		Barra archivoTB = new Barra();
		archivoTB.setName(Messages.getString("BarraSuperior.2")); //$NON-NLS-1$

		JButton nuevoTB = (JButton) archivoTB.añadirBoton(Messages.getString("BarraSuperior.3"), //$NON-NLS-1$
				Messages.getString("BarraSuperior.4"), Messages.getString("BarraSuperior.5"), new AccionNuevo(controlador, //$NON-NLS-1$ //$NON-NLS-2$
						ventana.getPanel_mapa()), false);
		nuevoTB.addMouseMotionListener(new EscuchaAyuda(
				Messages.getString("BarraSuperior.6"), ventana)); //$NON-NLS-1$

		JButton cargarTB = (JButton) archivoTB.añadirBoton(Messages.getString("BarraSuperior.7"), //$NON-NLS-1$
				Messages.getString("BarraSuperior.8"), Messages.getString("BarraSuperior.9"), new AccionCargar( //$NON-NLS-1$ //$NON-NLS-2$
						controlador, ventana.getPanel_mapa()), false);
		cargarTB.addMouseMotionListener(new EscuchaAyuda(
				Messages.getString("BarraSuperior.10"), ventana)); //$NON-NLS-1$

		JButton downloadTB = (JButton) archivoTB.añadirBoton(Messages.getString("BarraSuperior.11"), //$NON-NLS-1$
				Messages.getString("BarraSuperior.12"), Messages.getString("BarraSuperior.13"), new AccionDescargar(controlador,ventana.getPanel_mapa()), false); //$NON-NLS-1$ //$NON-NLS-2$
		downloadTB.addMouseMotionListener(new EscuchaAyuda(
				Messages.getString("BarraSuperior.14"), ventana)); //$NON-NLS-1$
		
		
		JButton guardarTB = (JButton) archivoTB.añadirBoton(
				Messages.getString("BarraSuperior.15"), Messages.getString("BarraSuperior.16"), Messages.getString("BarraSuperior.17"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionGuardar(controlador), false);
		guardarTB.addMouseMotionListener(new EscuchaAyuda(
				Messages.getString("BarraSuperior.18"), ventana)); //$NON-NLS-1$

		return archivoTB;
	}

	private JToolBar crearBarraEdicion(IControlador controlador,
			IModelo modelo, Ventana ventana) {
		Barra edicionTB = new Barra();
		edicionTB.setName(Messages.getString("BarraSuperior.19")); //$NON-NLS-1$
		JButton cortarTB = (JButton) edicionTB.añadirBoton(Messages.getString("BarraSuperior.20"), //$NON-NLS-1$
				Messages.getString("BarraSuperior.21"), Messages.getString("BarraSuperior.22"), new AccionCortar(modelo, //$NON-NLS-1$ //$NON-NLS-2$
						controlador, ventana.getPanel_mapa()), false);
		cortarTB
				.addMouseMotionListener(new EscuchaAyuda(
						Messages.getString("BarraSuperior.23"), //$NON-NLS-1$
						ventana));

		JButton copiarTB = (JButton) edicionTB.añadirBoton(Messages.getString("BarraSuperior.24"), //$NON-NLS-1$
				Messages.getString("BarraSuperior.25"), Messages.getString("BarraSuperior.26"), new AccionCopiar(modelo, //$NON-NLS-1$ //$NON-NLS-2$
						controlador, ventana.getPanel_mapa()), false);
		copiarTB
				.addMouseMotionListener(new EscuchaAyuda(
						Messages.getString("BarraSuperior.27"), //$NON-NLS-1$
						ventana));

		pegarTB = (JButton) edicionTB.añadirBoton(Messages.getString("BarraSuperior.28"), //$NON-NLS-1$
				Messages.getString("BarraSuperior.29"), Messages.getString("BarraSuperior.30"), new AccionSobreMapa(new MLPegar( //$NON-NLS-1$ //$NON-NLS-2$
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), -1), false);
		pegarTB.addMouseMotionListener(new EscuchaAyuda(
				Messages.getString("BarraSuperior.31"), //$NON-NLS-1$
				ventana));

		JButton deshacerTB = (JButton) edicionTB.añadirBoton(Messages.getString("BarraSuperior.32"), //$NON-NLS-1$
				Messages.getString("BarraSuperior.33"), Messages.getString("BarraSuperior.34"), new AccionDeshacer(controlador, //$NON-NLS-1$ //$NON-NLS-2$
						ventana.getPanel_mapa()), false);
		deshacerTB.addMouseMotionListener(new EscuchaAyuda(
				Messages.getString("BarraSuperior.35"), ventana)); //$NON-NLS-1$

		return edicionTB;
	}

	private JToolBar crearBarraZoom(IControlador controlador, IModelo modelo,
			Ventana ventana) {
		Barra zoomTB = new Barra();
		zoomTB.setName(Messages.getString("BarraSuperior.36")); //$NON-NLS-1$

		zoomin = (JButton) zoomTB.añadirBoton(Messages.getString("BarraSuperior.37"), //$NON-NLS-1$
				Messages.getString("BarraSuperior.38"), Messages.getString("BarraSuperior.39"), //$NON-NLS-1$ //$NON-NLS-2$
				new AccionZoom(ventana.getPanel_mapa(), 0.5), false);
		zoomin.addMouseMotionListener(new EscuchaAyuda(
				Messages.getString("BarraSuperior.40"), ventana)); //$NON-NLS-1$

		zoomout = (JButton) zoomTB.añadirBoton(Messages.getString("BarraSuperior.41"), //$NON-NLS-1$
				Messages.getString("BarraSuperior.42"), Messages.getString("BarraSuperior.43"), //$NON-NLS-1$ //$NON-NLS-2$
				new AccionZoom(ventana.getPanel_mapa(), 2), false);
		zoomout.addMouseMotionListener(new EscuchaAyuda(
				Messages.getString("BarraSuperior.44"), ventana)); //$NON-NLS-1$

		return zoomTB;
	}

	private JToolBar crearBotonesSimulacion(IControlador controlador,
			IModelo modelo, Ventana ventana) {
		Barra esto = new Barra();
		esto.setName(Messages.getString("BarraSuperior.45")); //$NON-NLS-1$

		simular = (JButton) esto.añadirBoton(Messages.getString("BarraSuperior.46"), //$NON-NLS-1$
				Messages.getString("BarraSuperior.47"), Messages.getString("BarraSuperior.48"), //$NON-NLS-1$ //$NON-NLS-2$
				new AccionComenzarSimulacion(ventana,controlador, modelo), false);
		simular
				.addMouseMotionListener(new EscuchaAyuda(
						Messages.getString("BarraSuperior.49"), //$NON-NLS-1$
						ventana));
		
		pausar =(JButton) esto.añadirBoton(Messages.getString("BarraSuperior.50"), //$NON-NLS-1$
				Messages.getString("BarraSuperior.51"), Messages.getString("BarraSuperior.52"), //$NON-NLS-1$ //$NON-NLS-2$
				new AccionPausarSimulacion(controlador), false);
		pausar.addMouseMotionListener(new EscuchaAyuda(
				Messages.getString("BarraSuperior.53"), ventana)); //$NON-NLS-1$
		
		detener = (JButton) esto.añadirBoton(Messages.getString("BarraSuperior.54"), Messages.getString("BarraSuperior.55"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraSuperior.56"), new AccionDetenerSimulacion(controlador), //$NON-NLS-1$
				false);
		detener.addMouseMotionListener(new EscuchaAyuda(
				Messages.getString("BarraSuperior.57"), ventana)); //$NON-NLS-1$

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
	    		//tiempo.setForeground(Color.WHITE);
	    		tiempo.setToolTipText(Messages.getString("BarraSuperior.58")); //$NON-NLS-1$
	    	} else if (hour24 >= 15 && hour24 <23 ) {
	    		//tiempo.setBackground(Color.YELLOW);
	    		tiempo.setForeground(Color.RED);
	    		tiempo.setToolTipText(Messages.getString("BarraSuperior.59")); //$NON-NLS-1$
	    	}
	    	else {
	    		tiempo.setBackground(Color.BLACK);
	    		//tiempo.setForeground(Color.GREEN);
	    		tiempo.setToolTipText(Messages.getString("BarraSuperior.60")); //$NON-NLS-1$
	    	}
	    		String hora = convierte(hour24)+Messages.getString("BarraSuperior.61")+convierte(min)+Messages.getString("BarraSuperior.62")+convierte(sec); //$NON-NLS-1$ //$NON-NLS-2$

	    	tiempo.setText(hora);
	    	//this.repaint();
	    }
	    
	    
	    
	}
	
	
	
	private static String convierte(int valor){
		if (valor <10){
			return Messages.getString("BarraSuperior.63")+valor; //$NON-NLS-1$
		}
		else return Messages.getString("BarraSuperior.64")+valor; //$NON-NLS-1$
	}
	
	public void setTiempoVacio() {
		tiempo.setText(""); //$NON-NLS-1$
	}
	
}
