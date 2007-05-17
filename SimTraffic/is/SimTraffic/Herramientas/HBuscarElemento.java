package is.SimTraffic.Herramientas;

import javax.swing.JOptionPane;

import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.Via;
import is.SimTraffic.Utils.Tiempo;
import is.SimTraffic.Vista.PanelMapa;

public class HBuscarElemento implements IHerramienta {

	private String elementoACambiar;
	private String nombre;
	private PanelMapa panel;
	
	public HBuscarElemento(String elementoACambiar, String nombre, PanelMapa panel) {
		// TODO Auto-generated constructor stub
		this.elementoACambiar = elementoACambiar;
		this.nombre = nombre;
		this.panel = panel;
	}

	public int hacer(IModelo modelo) {
		//Guardar valores anteriores de posición (para deshacer luego y poder volver
		//a la posición anterior)
		
		
		if (elementoACambiar.equals(Messages.getString("HBuscarElemento.0"))) { //$NON-NLS-1$
			Nodo nodoEncontrado;
			nodoEncontrado = modelo.getMapa().buscarNodo(nombre);
			if (nodoEncontrado==null) { 
				 JOptionPane.showMessageDialog(null,Messages.getString("HBuscarElemento.1"), //$NON-NLS-1$
						 Messages.getString("HBuscarElemento.2"),  //$NON-NLS-1$
				            JOptionPane.INFORMATION_MESSAGE);
				return 1; //La acción no se realizó con éxito, y por tanto no se podrá deshacer
			}
			else {
				//Faltaría ver si hay que desambiguar (si se repiten los nombres).
				panel.centrarEnPosicion(nodoEncontrado.getPos().getLat(),nodoEncontrado.getPos().getLon());
				modelo.getMapa().limpiaSeleccion();
				modelo.getMapa().getSeleccion().añadirNodo(nodoEncontrado);
			}
		}
		else if (elementoACambiar.equals(Messages.getString("HBuscarElemento.3"))) { //$NON-NLS-1$
			Tramo tramoEncontrado;
			tramoEncontrado = modelo.getMapa().buscarTramo(nombre);
			if (tramoEncontrado==null) {
				 JOptionPane.showMessageDialog(null,Messages.getString("HBuscarElemento.4"), //$NON-NLS-1$
						 Messages.getString("HBuscarElemento.5"),  //$NON-NLS-1$
				            JOptionPane.INFORMATION_MESSAGE);
				return 1; //La acción no se realizó con éxito, y por tanto no se podrá deshacer
			}
			else {
				//Faltaría ver si hay que desambiguar (si se repiten los nombres).
				panel.centrarEnPosicion(tramoEncontrado.getNodoInicial().getPos().getLat(),tramoEncontrado.getNodoInicial().getPos().getLon());
				modelo.getMapa().limpiaSeleccion();
				modelo.getMapa().getSeleccion().añadirTramo(tramoEncontrado);
			}
			
		}
		
		//Falta completar parte de vías y tramos.
		
		else if (elementoACambiar.equals(Messages.getString("HBuscarElemento.6"))) { //$NON-NLS-1$
			Via viaEncontrada;
			viaEncontrada = modelo.getMapa().buscarVia(nombre);
			if (viaEncontrada==null) {
				 JOptionPane.showMessageDialog(null,Messages.getString("HBuscarElemento.7"), //$NON-NLS-1$
						 Messages.getString("HBuscarElemento.8"),  //$NON-NLS-1$
				            JOptionPane.INFORMATION_MESSAGE);
				return 1; //La acción no se realizó con éxito, y por tanto no se podrá deshacer
			} 
			else {
				//Faltaría ver si hay que desambiguar (si se repiten los nombres).
				panel.centrarEnPosicion(viaEncontrada.getTramos().get(0).getNodoInicial().getPos().getLat(),
										viaEncontrada.getTramos().get(0).getNodoInicial().getPos().getLon() );
				modelo.getMapa().limpiaSeleccion();
				//Ver si se quiere seleccionar la via entera o no //Hacer un método sobre esto.
				for (int i=0;i<viaEncontrada.getTramos().size();i++) {
					modelo.getMapa().getSeleccion().añadirTramo(viaEncontrada.getTramos().get(i));
				}
			}
			
		}
		else if (elementoACambiar.equals(Messages.getString("HBuscarElemento.9"))) { //$NON-NLS-1$
			modelo.getMapa().buscarLineaBus(nombre);
			Via viaEncontrada;
			viaEncontrada = modelo.getMapa().buscarLineaBus(nombre);
			if (viaEncontrada==null) {
				 JOptionPane.showMessageDialog(null,Messages.getString("HBuscarElemento.10"), //$NON-NLS-1$
						 Messages.getString("HBuscarElemento.11"),  //$NON-NLS-1$
				            JOptionPane.INFORMATION_MESSAGE);
				return 1; //La acción no se realizó con éxito, y por tanto no se podrá deshacer
			}
			else {
				//Faltaría ver si hay que desambiguar (si se repiten los nombres).
				panel.centrarEnPosicion(viaEncontrada.getTramos().get(0).getNodoInicial().getPos().getLat(),
										viaEncontrada.getTramos().get(0).getNodoInicial().getPos().getLon() );
				modelo.getMapa().limpiaSeleccion();
				//Ver si se quiere seleccionar la linea de bus entera o no. //Hacer un método sobre esto
				for (int i=0;i<viaEncontrada.getTramos().size();i++) {
					modelo.getMapa().getSeleccion().añadirTramo(viaEncontrada.getTramos().get(i));
				}
			}		
		}
		else {
			//Error, aunque viene de un comboBox y no debería ser posible
		}
		return 0;
	}

	public int deshacer(IModelo modelo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void mostrarError(String s) {
		 JOptionPane.showMessageDialog(null,Messages.getString("HBuscarElemento.12") + s + Messages.getString("HBuscarElemento.13"), //$NON-NLS-1$ //$NON-NLS-2$
				 s+ Messages.getString("HBuscarElemento.14"),  //$NON-NLS-1$
		            JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String toString(){
		return Tiempo.Hora()+Messages.getString("HBuscarElemento.15")+Messages.getString("HBuscarElemento.16"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public String getElementoACambiar() {
		return elementoACambiar;
	}

	public String getNombre() {
		return nombre;
	}

	public PanelMapa getPanel() {
		return panel;
	}
}
