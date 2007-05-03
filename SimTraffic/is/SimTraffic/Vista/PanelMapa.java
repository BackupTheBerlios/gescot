/**
 * 
 */
package is.SimTraffic.Vista;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Simulacion.Vehiculo;
import is.SimTraffic.Vista.Acciones.AccionScrollX;
import is.SimTraffic.Vista.Acciones.AccionScrollY;
import is.SimTraffic.Vista.Representaciones.*;
import is.SimTraffic.Vista.Sugerencias.Flecha;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;

/**
 * Clase (por ahora provisional) para representar simplemente el mapa en
 * pantalla.
 * <p>
 * Utiliza iteradores para recorrer los distintos componentes del mapa y
 * dibujarlos.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class PanelMapa extends JPanel 
{
	
	private static final long serialVersionUID = -3680412115222562074L;
	
	/**
	 * Almacena el modelo que se va a mostrar por pantalla
	 */
	private IModelo modelo;
	
	/**
	 * Imagen del mapa usada para duplicar el buffer
	 */
	private Image mapa;
	
	/**
	 * Zoom que se esta utilizando en la representacion del mapa
	 */
	private double zoom;
	
	/**
	 * Almacena tama�os en x e y del panel, utilizados a la hora de dibujar
	 */
	private int tamX, tamY;
	
	/**
	 * Almacenan el punto mas arriba a la izquierda del mapa que aparece en
	 * pantalla
	 */
	private double posLat, posLon;
	
	/**
	 * Utilizados para detectar cambios en la posicion del mapa
	 */
	private double posLattemp, posLontemp;
	
	/**
	 * Booleano que indica si se debe recreear o no el mapa la proxima vez que
	 * se dibjue el panel en pantalla
	 */
	private boolean recrear;
	
	/**
	 * Contador de las veces que se dibujo el mapa, para efectos de evaluacion
	 */
	private int contador;
	
	/**
	 * Elemento a mostrar como sugerido, segun donde esta el puntero del
	 * usuario. Para operaciones de modificacion/borrado/etc
	 */
	private ElementoMapa sugerencia;
	
	/**
	 * Segunda sugerencia, necesaria cuando se desean sugerir dos elementos a la vez. 
	 */
	private ElementoMapa sugerencia2;
	
	/**
	 * Alamcena la representacion que se utiliza para mostrar el mapa por
	 * pantalla.
	 */
	private Representacion representacion;
	
	private JScrollBar largo;
	
	private JScrollBar alto;
	
	private int posEx;
	
	private int posEy;
	
	/**
	 * Punto que limitan el rect�ngulo del �rea de selecci�n.
	 */
	Point puntoInicial;
	
	Point puntoDrag;
	
	/**
	 * Rectangulo que se muestra cuando se seleccionan varios nodos.
	 */
	private Rectangle rectanguloSeleccion;
	
	/**
	 * Permite conocer si se est� seleccionando.
	 */
	boolean modoSeleccion;
	
	/**
	 * String que muestra la ayuda al usuario en la parte inferior del interfaz.
	 */
	private String ayudaInf;
	
	private Flecha flecha;
	
	private JPopupMenu emergenteNodo;
	
	private JPopupMenu emergenteTramo;
	
	private JPopupMenu emergenteMapa;
	
	/**
	 * @return Returns the ayudaInf.
	 */
	public String getAyudaInf() {
		return ayudaInf;
	}
	
	/**
	 * @param ayudaInf The ayudaInf to set.
	 */
	public void setAyudaInf(String ayudaInf) {
		this.ayudaInf = ayudaInf;
	}
	
	/**
	 * Constructor de la clase PanelMapa.
	 * <p>
	 * Este constructor toma como par�metros el tama�o que se le quiere dar
	 * inicialmente
	 * 
	 * @param tamX
	 * @param tamY
	 */
	public PanelMapa(int tamX, int tamY) {
		super();
		setSize(new Dimension(200, 200));
		modelo = null;
		zoom = 1;
		this.tamX = tamX;
		this.tamY = tamY;
		recrear = true;
		contador = 0;
		representacion = new RepresentacionSimple();
		this.setLayout(new BorderLayout());
		a�adirScrolls();
		posLat = 0; // latitud cero
		posLon = 0; // longitud cero
		posLattemp = 0;
		posLontemp = 0;
		modoSeleccion = false;
		rectanguloSeleccion = new Rectangle();
	}
	
	/**
	 * Para establecer el modelo donde esta la infromaci�n.
	 * <p>
	 * Se utliza este m�todo para establecer el modelo y no el contructor, dado
	 * que el modelo podr�a cambiar.
	 * 
	 * @param modelo
	 *            IModelo con el modelo a mostrar
	 */
	public void setModelo(IModelo modelo) {
		this.modelo = modelo;
		recrear = true;
	}
	
	public JPopupMenu getMenuEmergenteNodo(){
		return emergenteNodo;
	}
	
	public JPopupMenu getMenuEmergenteTramo(){
		return emergenteTramo;
	}
	
	public JPopupMenu getMenuEmergenteMapa(){
		return emergenteMapa;
	}
	
	public void setMenuEmergenteNodo(JPopupMenu emergenteNodo){
		this.emergenteNodo= emergenteNodo;
	}
	
	public void setMenuEmergenteTramo(JPopupMenu emergenteTramo) {
		this.emergenteTramo = emergenteTramo;
		
	}
	
	public void setMenuEmergenteMapa(JPopupMenu emergenteMapa) {
		this.emergenteMapa = emergenteMapa;
		
	}
	
	public void menusInvisibles () {
		getMenuEmergenteNodo().setVisible(false);
		getMenuEmergenteTramo().setVisible(false);
		getMenuEmergenteMapa().setVisible(false);
	}
	
	public JPopupMenu getMenuEmergenteActivo () {					  	
		if (getMenuEmergenteNodo().isVisible())
			return getMenuEmergenteNodo();
		else if (getMenuEmergenteTramo().isVisible())
			return getMenuEmergenteTramo();
		else if (getMenuEmergenteMapa().isVisible())
			return getMenuEmergenteMapa();
		else return getMenuEmergenteNodo();
	}
	
	/*
	 * public void setSize(int tamX, int tamY) { this.tamX = tamX; this.tamY =
	 * tamY; recrear = true; super.setSize(tamX, tamY); }
	 */
	public void a�adirScrolls() {
		alto = new JScrollBar();
		alto.setMinimum(0);
		alto.setMaximum(100);
		alto.setValue(40);
		alto.setUnitIncrement(100);
		alto.addAdjustmentListener(new AccionScrollY(this));
		alto.addMouseListener(new AccionScrollY(this));
		this.add(alto, BorderLayout.EAST);
		largo = new JScrollBar();
		largo.setOrientation(0);
		largo.setMinimum(0);
		largo.setMaximum(100);
		largo.setValue(45);
		largo.setUnitIncrement(100);
		largo.addAdjustmentListener(new AccionScrollX(this));
		largo.addMouseListener(new AccionScrollX(this));
		this.add(largo, BorderLayout.SOUTH);
	}
	
	/**
	 * M�todo que recera la imagen del mapa a partir de la infromaci�n del
	 * modelo.
	 * <p>
	 * 
	 */
	public void recrearMapa() {
		if (modelo == null)
			return;
		
		long tiempo = System.currentTimeMillis();
		
		Dimension tama�o = this.getSize();
		tamX = tama�o.width;
		tamY = tama�o.height;
		// posX = largo.getValue();
		// posY = alto.getValue();
		representacion.setTamX(tamX);
		representacion.setTamY(tamY);
		representacion.setLon0(posLon);
		representacion.setLat0(posLat);
		representacion.setZoom(zoom);
		
		representacion.recalculaCons();
		
		if (tamX == 0 || tamY == 0)
			mapa = this.createImage(200, 200);
		else
			mapa = this.createImage(tamX, tamY);
		Graphics2D g = (Graphics2D) mapa.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		// TODO tarda un 30% menos, en mi ordenador, para crear los mapas de esta manera. quedan
		//      peor, ahi que decidir si merece la pena
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		//		RenderingHints.VALUE_ANTIALIAS_OFF);
		//g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		//g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		
		representacion.ponerImagenes(g);
		
		representacion.iniciarRepresentacion();
		
		// dibujar nodos
		Iterator<Nodo> inodos = modelo.getMapa().getNodos().iterator();
		while (inodos.hasNext()) {
			representacion.pintar(g, inodos.next());
		}
		
		// dibujar tramos
		Iterator<Tramo> itramos = modelo.getMapa().getTramos().iterator();
		Tramo tramo;
		while (itramos.hasNext()) {
			tramo = itramos.next();
			representacion.pintar(g, tramo, modelo.getMapa().getTipoVia(tramo));		
		}
		

		representacion.pintarCoordenadas(g);
		//representacion.pintarSugerencia(g, sugerencia);
		//representacion.pintarSugerencia(g, sugerencia2);
		
		System.out.println("tiempo: " + (System.currentTimeMillis() - tiempo));
	}
	
	private void dibujarVehiculos(Graphics2D g, List<Vehiculo> vehiculos, Tramo tramo) {
		Iterator<Vehiculo> it = vehiculos.iterator();
		while (it.hasNext()) {
			representacion.pintarVehiculo(g, it.next(), tramo);
		}
	}
	
	public void repaint() {
		if (tamX != getSize().width || tamY != getSize().height
				|| (posLon != posLontemp) || (posLat != posLattemp)) {
			posLontemp = posLon;
			posLattemp = posLat;
			recrear = true;
		}
		contador++;
		super.repaint();
	}
	
	public void paintComponent(Graphics g_normal) {
		Graphics2D g = (Graphics2D) g_normal;
		if (recrear) {
			// mapa = super.createImage(tamX, tamY);
			recrearMapa();
			recrear = false;
		}
		g.drawImage(mapa, 0, 0, null);
		// Si estamos seleccionando, entonces dibujar el rectangulo de
		// selecci�n.
		if (modoSeleccion)
			representacion.pintar(g, this.rectanguloSeleccion);
		//representacion.pintar(g, flecha);
		
		//g.drawString("Redibujando: " + contador, 80, 80);
		
		// Aqu� se pintan los nodos que est�n seleccionados como si estuvieran
		// sugiriendo.
		/*
		 * for (int i = 0; i < modelo.getMapa().getSeleccion()
		 * .getNodosSeleccionados().size(); i++) {
		 * representacion.pintarSugerencia(g, modelo.getMapa().getSeleccion()
		 * .getNodosSeleccionados().get(i)); }
		 */
		
		Iterator<Tramo> itramos = modelo.getMapa().getTramos().iterator();
		ArrayList<Tramo> auxiliar = new ArrayList<Tramo>();
		while (itramos.hasNext()) {
			Tramo tramo = itramos.next();
			auxiliar.add(tramo);
			representacion.pintar(g, tramo, modelo.getMapa().getTipoVia(tramo));	
			if (this.modelo.getSimulacion().estaActiva())
				dibujarVehiculos(g, this.modelo.getSimulacion().getVehiculos(), tramo);
		}
		
		Iterator<Tramo> it = auxiliar.iterator();
		while (it.hasNext()) {
			Tramo tramo = it.next();
			representacion.seleccionarColoresSemaforos(tramo, g);
		}
		
		// Se pintan los tramos que est�n seleccionados
		for (int i = 0; i < modelo.getMapa().getSeleccion()
		.getTramosSeleccionados().size(); i++) {
			representacion.pintarSugerenciaSeleccion(g, modelo.getMapa()
					.getSeleccion().getTramosSeleccionados().get(i));
		}
		
		// Aqu� se pintan los nodos que est�n seleccionados como si estuvieran
		// sugiriendo.
		for (int i = 0; i < modelo.getMapa().getSeleccion()
		.getNodosSeleccionados().size(); i++) {
			representacion.pintarSugerenciaSeleccion(g, modelo.getMapa()
					.getSeleccion().getNodosSeleccionados().get(i));
		}
		
		representacion.pintarSugerencia(g, sugerencia);
		representacion.pintarSugerencia(g, sugerencia2);
		
		if (flecha != null)
			representacion.pintar(g, flecha);
		
	}
	
	public void setRepresentacion(Representacion representacion) {
		this.representacion = representacion;
	}
	
	public Representacion getRepresentacion() {
		return representacion;
	}
	
	public void cambiaZoom(double cambio) {
		this.zoom = this.zoom / cambio;
		if (zoom > 32 || zoom < 0.125) {
			this.zoom = this.zoom * cambio;
			return;
		}
		recrear = true;
		super.repaint();
	}
	
	public void sugerir(ElementoMapa sugerencia) {
		boolean refresco = false;
		if ((this.sugerencia != null && sugerencia == null)
				|| (this.sugerencia == null && sugerencia != null)
				|| (this.sugerencia != sugerencia))
			refresco = true;
		this.sugerencia = sugerencia;
		if (refresco)
			this.repaint();
	}
	
	public void sugerir2(ElementoMapa sugerencia) {
		boolean refresco = false;
		if ((this.sugerencia2 != null && sugerencia == null)
				|| (this.sugerencia2 == null && sugerencia != null)
				|| (this.sugerencia2 != sugerencia))
			refresco = true;
		this.sugerencia2 = sugerencia;
		if (refresco)
			this.repaint();
	}
	
	public void cambiaPosX(int cambio) {
		posLon = posLon + ((double) cambio * zoom) / 60000;
		if (posLon > 180)
			posLon = 180;
		if (posLon < -180)
			posLon = -180;
		recrear = true;
		this.repaint();
	}
	
	public void centrarEnPosicion(double lat, double lon) {
		if ( lat > 180 || lat < -180
				|| lon > 180 || lon <-180 )
			return;
		else {
			posLat = lat;
			posLon = lon;
		}
		recrear=true;
		this.repaint();
	}
	
	public void cambiaPosY(int cambio) {
		posLat = posLat - ((double) cambio * zoom) / 60000;
		if (posLat > 89)
			posLat = 89;
		if (posLat < -89)
			posLat = -89;
		recrear = true;
		this.repaint();
	}
	
	public void configurarRectanguloSeleccion() {
		rectanguloSeleccion.setFrameFromDiagonal(puntoInicial, puntoDrag);
	}
	
	public double lon_RepAMapa(int posX) {
		return representacion.lon_RepAMapa(posX);
	}
	
	public double lat_RepAMapa(int posY) {
		return representacion.lat_RepAMapa(posY);
	}
	
	/**
	 * Llama al mapa para indicarle qu� �rea (rect�ngulo) ha seleccionado el
	 * usuario y que este haga las gestiones oportunas.
	 */
	public void notificaSeleccion(int tipoDeSeleccion) {
		Rectangle rectanguloCoordenadasReales = new Rectangle();
		
		// Al rectangulo que pasamos al mapa para que conozca que �rea ha
		// seleccionado el usuario, le aplicamos
		// la transformaci�n a coordenadas relativas del mapa.
		rectanguloCoordenadasReales.setFrameFromDiagonal(puntoInicial.getX(),
				puntoInicial.getY(), puntoDrag.getX(), puntoDrag.getY());
		
		// this.modelo.getMapa().seleccionaEnRectangulo(rectanguloCoordenadasReales);
		this.modelo.getMapa().seleccionaEnRectangulo(
				rectanguloCoordenadasReales, tipoDeSeleccion, representacion);
	}
	
	public boolean isModoSeleccion() {
		return modoSeleccion;
	}
	
	public void setModoSeleccion(boolean modoSeleccion) {
		this.modoSeleccion = modoSeleccion;
	}
	
	public double getLat0() {
		return posLat;
	}
	
	public double getLon0() {
		return posLon;
	}
	
	public Point getPuntoDrag() {
		return puntoDrag;
	}
	
	public void recrear() {
		this.recrear = true;
	}
	
	public void setPuntoDrag(Point puntoDrag) {
		this.puntoDrag = puntoDrag;
		configurarRectanguloSeleccion();
	}
	
	public Point getPuntoInicial() {
		return puntoInicial;
	}
	
	public void setPuntoInicial(Point puntoInicial) {
		this.puntoInicial = puntoInicial;
	}
	
	public Rectangle getRectanguloSeleccion() {
		return rectanguloSeleccion;
	}
	
	public void setRectanguloSeleccion(Rectangle rectanguloSeleccion) {
		this.rectanguloSeleccion = rectanguloSeleccion;
	}
	
	public boolean isRecrear() {
		return recrear;
	}
	
	public void setRecrear(boolean recrear) {
		this.recrear = recrear;
	}
	
	public void setPosE(int posEx, int posEy){
		this.posEx = posEx;
		this.posEy = posEy;
	}
	
	public int getPosEx(){
		return posEx;
	}
	
	public int getPosEy(){
		return posEy;
	}
	
	public void crearFlecha(Nodo nodo, Tramo tramoOrigen, Tramo tramoDestino) 
	{
		this.flecha = new Flecha(nodo, tramoOrigen, tramoDestino);
		this.repaint();
	}
	
	public void quitarFlecha() 
	{
		this.flecha = null;
	}
	
}
