package is.SimTraffic.Herramientas;

import javax.swing.JOptionPane;

import is.SimTraffic.IModelo;
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
		//Guardar valores anteriores de posici�n (para deshacer luego y poder volver
		//a la posici�n anterior)
		
		
		if (elementoACambiar.equals("Nodo")) {
			Nodo nodoEncontrado;
			nodoEncontrado = modelo.getMapa().buscarNodo(nombre);
			if (nodoEncontrado==null) { 
				 JOptionPane.showMessageDialog(null,"No existe ning�n nodo con el nombre especificado",
						 "Nodo no encontrado", 
				            JOptionPane.INFORMATION_MESSAGE);
				return 1; //La acci�n no se realiz� con �xito, y por tanto no se podr� deshacer
			}
			else {
				//Faltar�a ver si hay que desambiguar (si se repiten los nombres).
				panel.centrarEnPosicion(nodoEncontrado.getPos().getLat(),nodoEncontrado.getPos().getLon());
				modelo.getMapa().limpiaSeleccion();
				modelo.getMapa().getSeleccion().a�adirNodo(nodoEncontrado);
			}
		}
		else if (elementoACambiar.equals("Tramo")) {
			Tramo tramoEncontrado;
			tramoEncontrado = modelo.getMapa().buscarTramo(nombre);
			if (tramoEncontrado==null) {
				 JOptionPane.showMessageDialog(null,"No existe ning�n tramo con el nombre especificado",
						 "Tramo no encontrado", 
				            JOptionPane.INFORMATION_MESSAGE);
				return 1; //La acci�n no se realiz� con �xito, y por tanto no se podr� deshacer
			}
			else {
				//Faltar�a ver si hay que desambiguar (si se repiten los nombres).
				panel.centrarEnPosicion(tramoEncontrado.getNodoInicial().getPos().getLat(),tramoEncontrado.getNodoInicial().getPos().getLon());
				modelo.getMapa().limpiaSeleccion();
				modelo.getMapa().getSeleccion().a�adirTramo(tramoEncontrado);
			}
			
		}
		
		//Falta completar parte de v�as y tramos.
		
		else if (elementoACambiar.equals("V�a")) {
			Via viaEncontrada;
			viaEncontrada = modelo.getMapa().buscarVia(nombre);
			if (viaEncontrada==null)
				return 1; //La acci�n no se realiz� con �xito, y por tanto no se podr� deshacer
			else {
				//Faltar�a ver si hay que desambiguar (si se repiten los nombres).
				panel.centrarEnPosicion(viaEncontrada.getTramos().get(0).getNodoInicial().getPos().getLat(),
										viaEncontrada.getTramos().get(0).getNodoInicial().getPos().getLon() );
				modelo.getMapa().limpiaSeleccion();
				//Ver si se quiere seleccionar la via entera o no //Hacer un m�todo sobre esto.
			}
			
		}
		else if (elementoACambiar.equals("L�nea de bus")) {
			modelo.getMapa().buscarLineaBus(nombre);
			Via viaEncontrada;
			viaEncontrada = modelo.getMapa().buscarLineaBus(nombre);
			if (viaEncontrada==null) 
				return 1; //La acci�n no se realiz� con �xito, y por tanto no se podr� deshacer
			else {
				//Faltar�a ver si hay que desambiguar (si se repiten los nombres).
				panel.centrarEnPosicion(viaEncontrada.getTramos().get(0).getNodoInicial().getPos().getLat(),
										viaEncontrada.getTramos().get(0).getNodoInicial().getPos().getLon() );
				modelo.getMapa().limpiaSeleccion();
				//Ver si se quiere seleccionar la linea de bus entera o no. //Hacer un m�todo sobre esto.
			}		
		}
		else {
			//Error, aunque viene de un comboBox y no deber�a ser posible
		}
		return 0;
	}

	public int deshacer(IModelo modelo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void mostrarError(String s) {
		 JOptionPane.showMessageDialog(null,"No existe ning�n " + s + " con el nombre especificado",
				 s+ " no encontrado", 
		            JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String toString(){
		return Tiempo.Hora()+" - "+"B�squeda realizada";
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
