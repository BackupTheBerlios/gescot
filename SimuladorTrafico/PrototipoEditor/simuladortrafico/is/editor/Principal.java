package PrototipoEditor.simuladortrafico.is.editor;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Arrancando ventana");
		
		Mapa mapa = new Mapa(450,450);
		Controlador controlador = new Controlador();
		VentanaAux ventanaAux = new VentanaAux(controlador);
		Ventana ventana = new Ventana(controlador,mapa);
		VentanaTools ventanaTools = new VentanaTools(controlador);
		
		controlador.elegirVentanas(ventana,ventanaAux,ventanaTools);
		
		controlador.elegirMapa(mapa);
		controlador.dibujarMapa();
		//ventana.paint(ventana.getGraphics());
		
		/*while(true){
			ventana.paint(ventana.getGraphics());
		}*/
		
	}

}
