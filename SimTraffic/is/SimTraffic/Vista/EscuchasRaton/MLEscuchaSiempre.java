/**
 * 
 */
package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.PanelNodo;
import is.SimTraffic.Vista.PanelTramo;

import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
	private int x1;
	private int y1;
	
	public MLEscuchaSiempre(IModelo modelo, IControlador controlador,PanelMapa panel, JLabel posicionX, JLabel posicionY) 
	{
		super(modelo, controlador, panel);
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		estado = 0;
		millis = 0;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// Aqui ahi que poner que queremos que salga cuando
		// hacemos click con el boton derecho...
		
		// Esta puesto de prueba que cuando le des con el boton
		// derecho en el mapa, te salga la ventana de propiedades
		// del nodo
		if (arg0.getButton() == MouseEvent.BUTTON1) 
		{	
			if (estado == 0)
			{
				estado = 1;
				millis = System.currentTimeMillis();
				x1 = panel.x_RepAMapa(arg0.getX());
				y1 = panel.y_RepAMapa(arg0.getY());
			}
			else
			{
				if (estado == 1 && mismoPunto(arg0) && (System.currentTimeMillis() - millis) < 250)
				{
					Nodo nodoAux=this.buscarNodo(panel.x_RepAMapa(arg0.getX()), panel
							.y_RepAMapa(arg0.getY()));
					if (nodoAux != null) {
						//JFrame ventanaNodo = new JFrame();
						PanelNodo panelNod = new PanelNodo(nodoAux);
						panelNod.setTitle("Propiedades del Nodo");
						panelNod.setBounds(80, 120, 480, 600);
						panelNod.setVisible(true);
						
						//ventanaNodo.setContentPane(panelNod);
						//ventanaNodo.setTitle("Propiedades del Nodo");
						// ventanaNodo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						//ventanaNodo.setBounds(80, 120, 400, 600);
						//ventanaNodo.setVisible(true);
					}
					else if (this.buscarTramo(panel.x_RepAMapa(arg0.getX()), panel
							.y_RepAMapa(arg0.getY())) != null) {
						JFrame ventanaTramo = new JFrame("Propiedades del Tramo");
						PanelTramo panelTram = new PanelTramo();
						ventanaTramo.add(panelTram);
						ventanaTramo.setBounds(80, 120, 400, 600);
						ventanaTramo.setVisible(true);
					}
				}
				estado = 0;
			}
		}
		
	}
	
	private boolean mismoPunto(MouseEvent arg0) 
	{
		int x2 = panel.x_RepAMapa(arg0.getX());
		int y2 = panel.y_RepAMapa(arg0.getY());
		return (x1 == x2 && y1 == y2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseMoved(MouseEvent e) {  
		int posX = panel.x_RepAMapa(e.getX());
		int posY = panel.y_RepAMapa(e.getY());
		posicionX.setText("" + posX);
		posicionY.setText("" + posY);
	}
}
