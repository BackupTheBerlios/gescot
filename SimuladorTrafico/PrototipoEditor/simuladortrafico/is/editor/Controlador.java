package PrototipoEditor.simuladortrafico.is.editor;

import java.awt.Point;

public class Controlador {

	//Vistas que controla el controlador.
	Ventana ventana;
	VentanaAux ventanaAux;
	VentanaTools ventanaTools;
	Mapa mapa;
	
	
	int clickUnir; //Permite saber qué nodo estamos intentando unir.
	int modo; //Establece en qué modo estamos: Seleccionar(1), crear(2), borrar(3)
	Nodo nodoTemporalUnir;
	
	public Controlador(){
		modo = 1;
		clickUnir = 0;
	}
	
	public void elegirMapa(Mapa mapa){
		this.mapa = mapa;
		this.ventana.actualizarMapa(mapa);
	}
	
	public void elegirVentanas(Ventana ventana, VentanaAux ventanaAux, VentanaTools ventanaTools){
		this.ventana = ventana;
		this.ventanaAux = ventanaAux;
		this.ventanaTools = ventanaTools;
	}
	
	public Nodo seleccionaNodo(Point punto){
		//Primero obtengo las coordenadas reales sobre las que se ha hecho click.
		int xNodo = punto.x - mapa.margenLeft;
		int yNodo = punto.y - mapa.margenUp;
		//Controlará el bucle.
		boolean encontrado = false;
		
		Nodo nodoAux = new Nodo(0,0);
		//Nodo nodoSelecc = new Nodo(0,0);
		
		int i = 0;
		while (i<=(mapa.listaNodos.size()-1) && !(encontrado)){
			nodoAux = mapa.listaNodos.get(i);
			
			//tengo un Radio de pulsación de 4 píxeles alrededor del nodo.
			if ((xNodo - 3 <= nodoAux.x) && (nodoAux.x <= xNodo + 3)) {
				if ((yNodo - 3 <= nodoAux.y) && (nodoAux.y <= yNodo + 3)) {
					
					//nodoSelecc = nodoAux;
					encontrado = true;
				}
			}
			
			i++;
		}
		if (encontrado == true) {
			System.out.println("El nodo seleccionado es: "+ Integer.toString(i));
			return nodoAux;
		} else {
			System.out.println("Ningún nodo seleccionado");
			return null;
		}
	}
	
	public void unirNodos(Nodo nodo){
		
		if (nodo != null) {
			if (clickUnir == 0){
				clickUnir = 1;
				this.nodoTemporalUnir = nodo;
			} else { //Ya hemos seleccionado el primer nodo para unir
				mapa.insertarPareja(nodoTemporalUnir, nodo);
				clickUnir = 0;
			}
		}		
	}
	
	public void borrarNodoDeListaArista(Nodo nodo){
		Pareja parejaAux = new Pareja();
		
		if (nodo!=null){
			System.out.println("El nodo que quiero borrar: ("+ nodo.x + "," + nodo.y + ")");
			
			for (int i=0;i<=(mapa.listaParejas.size()-1);i++){
				parejaAux = mapa.listaParejas.get(i);
				System.out.println("Recorriendo lista aristas para borrar " + i);
				if ((parejaAux.nodo1 == nodo) || (parejaAux.nodo2 == nodo)){
					System.out.println("¡Borrada Arista!");				
					mapa.listaParejas.remove(i);
					i = i-1;
				}
			}
		}
	}
	
	//Qué hacemos cuando se produce un click
	public void click(Point punto, int boton){
		modificarTextoCoordenadasVentanaAux(punto.x,punto.y);
		String textoBoton = "Pulsado botón "+Integer.toString(boton)+"!";
		
		switch (modo) {
		case 1: this.seleccionaNodo(punto);
				ventana.update(ventana.getGraphics());
				clickUnir = 0;
			break;
		case 2:	mapa.insertaNodo(punto.x - mapa.margenLeft, punto.y - mapa.margenUp);
				ventana.update(ventana.getGraphics());
				clickUnir = 0;
			break;
		case 3: Nodo nodoABorrar = new Nodo(0,0);
				nodoABorrar = this.seleccionaNodo(punto);
				mapa.listaNodos.remove(nodoABorrar);
				borrarNodoDeListaArista(nodoABorrar);
				ventana.update(ventana.getGraphics());
				clickUnir = 0;
			break;
		case 4: this.unirNodos(this.seleccionaNodo(punto));
				ventana.update(ventana.getGraphics());
			break;
		default: break;
		}
		ventanaAux.actualizarBotonPulsado(textoBoton);
	}
	
	public void dibujarMapa(){
		ventana.actualizarMapa(this.mapa);
	}
	

	public void modificarTextoCoordenadasVentanaAux(int x, int y) {
		ventanaAux.actualizarPuntoPulsadoInfo(x,y);
	}

	public void cambiarModo(String modo) {
		this.modo = Integer.parseInt(modo);
	}
	
	public void exportar() {
		mapa.generarXMLinfo();
	}
}
