package is.SimTraffic.Vista.EscuchasRaton;
import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.PanelMapa;
import java.awt.event.MouseEvent;

public class MLMapaBDerecho extends EscuchaRaton {
		
		
		public MLMapaBDerecho(IModelo modelo, IControlador controlador, PanelMapa panel){
			super(modelo, controlador, panel);
			panel.setFocusable(true);		
		}

			public void mouseClicked(MouseEvent arg0) {
				/*if(arg0.isPopupTrigger()){
					seleccionado = buscarTramo(arg0.getX(), arg0.getY());
					if (seleccionado != null){
						if (modelo.getMapa().getSeleccion().esVacia()){
							modelo.getMapa().getSeleccion().aņadirTramo(seleccionado);
						}
					}				
					panel.repaint();				
					panel.setPosE(arg0.getX(), arg0.getY());
				}*/
			}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			/*if(arg0.isPopupTrigger() && buscarNodo(arg0.getX(), arg0.getY())!= null){
				panel.setPosE(arg0.getX(), arg0.getY());
				panel.setPuntoInicial(arg0.getPoint());
				panel.getMenuEmergenteTramo().show(panel,arg0.getX(),arg0.getY());
			}*/
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if(arg0.isPopupTrigger() && buscarTramo(arg0.getX(), arg0.getY()) == null &&
					buscarNodo(arg0.getX(), arg0.getY())==null){
				
				panel.setPosE(arg0.getX(), arg0.getY());
				panel.setPuntoInicial(arg0.getPoint());
				if (modelo.getMapa().getPortapapeles().esVacia()){
					
				}
				panel.getMenuEmergenteMapa().show(panel,arg0.getX(),arg0.getY());
				panel.repaint();
			}
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			
		}

		@Override
		public String getAyuda() 
		{
			return "";
		}
		

	
}
