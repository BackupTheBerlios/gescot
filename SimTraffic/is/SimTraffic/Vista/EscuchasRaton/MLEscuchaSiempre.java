/**
 * 
 */
package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.PanelNodo;
import is.SimTraffic.Vista.PanelTramo;
import is.SimTraffic.Vista.Ventana;

import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * Clase que extiende MouseListener para recoger todos los eventos que ocurran
 * en cualquier momento en el mapa.
 * <p>
 * Esta clase implemeta los métodos de MouseListener para poder caturar los en
 * todo momento el desplazamiento y los clicks del ratón sobre el mapa.
 * 
 * @author Grupo ISTrafico
 */
public class MLEscuchaSiempre extends EscuchaRaton {
	
	private JLabel posicionX;
	private JLabel posicionY;
	private long millis;
	private int estado;
	private double x1;
	private double y1;
	private DecimalFormat cincoCifras;
	private Ventana ventana;
	
	public MLEscuchaSiempre(IModelo modelo, IControlador controlador,PanelMapa panel, JLabel posicionX, JLabel posicionY, Ventana ventana) 
	{
		super(modelo, controlador, panel);
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		estado = 0;
		millis = 0;
		cincoCifras = new DecimalFormat("0.00000");
		this.ventana = ventana;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Aqui ahi que poner que queremos que salga cuando
		// hacemos click con el boton derecho...
		
		// Esta puesto de prueba que cuando le des con el boton
		// derecho en el mapa, te salga la ventana de propiedades
		// del nodo
		

		// Esta parte no se para que vale, creo que sobra, ademas de milis, estado y mismo punto
		// ¿que pasa cuando se hace un click?
		if (arg0.getClickCount() == 1 && arg0.getButton() == MouseEvent.BUTTON1) 
		{	

			if (estado == 0)
			{
				estado = 1;
				millis = System.currentTimeMillis();
				x1 = panel.lon_RepAMapa(arg0.getX());
				y1 = panel.lat_RepAMapa(arg0.getY());
			}
				
			/*else
			{
				if (estado == 1 && mismoPunto(arg0) && (System.currentTimeMillis() - millis) < 250)
				{
					Nodo nodoAux=this.buscarNodo(arg0.getX(), arg0.getY());
					if (nodoAux != null) {
						//JFrame ventanaNodo = new JFrame();
						PanelNodo panelNod = new PanelNodo(nodoAux, panel);
						panelNod.setTitle("Propiedades del Nodo");
						panelNod.setBounds(80, 120, 500, 600);
						panelNod.setVisible(true);
					}
					else
					{
						Tramo tramoAux = this.buscarTramo(arg0.getX(), arg0.getY());
						if (tramoAux != null) 
						{
							//JFrame ventanaTramo = new JFrame("Propiedades del Tramo");
							PanelTramo panelTram = new PanelTramo(tramoAux, panel,controlador);
							panelTram.setTitle("Propiedades del Tramo");
							panelTram.setBounds(80, 120, 400, 600);
							panelTram.setVisible(true);
						}	
					}
				}
				estado = 0;
			}*/
		}
		// Que hacer si hay doble click
		else if (arg0.getClickCount() == 2 && arg0.getButton() == MouseEvent.BUTTON1) {
				// Si es doble click soble un nodo
				Nodo nodoAux=this.buscarNodo(arg0.getX(), arg0.getY());
				Tramo tramoAux = this.buscarTramo(arg0.getX(), arg0.getY());
				if (nodoAux != null) {
					//JFrame ventanaNodo = new JFrame();
					PanelNodo panelNod = new PanelNodo(nodoAux, panel);
					panelNod.setTitle("Propiedades del Nodo");
					panelNod.setBounds(80, 120, 500, 600);
					panelNod.setVisible(true);
				}
				// Si es doble click sobre un tramo
				
				else if (tramoAux != null) 
				{
					//JFrame ventanaTramo = new JFrame("Propiedades del Tramo");
					PanelTramo panelTram = new PanelTramo(tramoAux, panel,controlador);
					panelTram.setTitle("Propiedades del Tramo");
					panelTram.setBounds(80, 120, 480, 600);
					panelTram.setVisible(true);
				}
			}
	}
	
	private boolean mismoPunto(MouseEvent arg0) 
	{
		double x2 = panel.lon_RepAMapa(arg0.getX());
		double y2 = panel.lat_RepAMapa(arg0.getY());
		return (x1 == x2 && y1 == y2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		if(!arg0.isPopupTrigger()) 
			panel.getMenuEmergente().setVisible(false);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}
	
	public void mouseMoved(MouseEvent e) {  
		double posX = panel.lon_RepAMapa(e.getX());
		double posY = panel.lat_RepAMapa(e.getY());
		posicionX.setText(panel.getRepresentacion().pasarAGrados(posX));//"" + cincoCifras.format(posX)+"º");
		posicionY.setText(panel.getRepresentacion().pasarAGrados(posY));//"" + cincoCifras.format(posY)+"º");
		ventana.cambiarAyuda(panel.getAyudaInf());
	}

	@Override
	public String getAyuda() {
		// TODO Auto-generated method stub
		return "";
	}
}
