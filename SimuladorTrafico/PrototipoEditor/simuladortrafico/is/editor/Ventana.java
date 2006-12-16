package PrototipoEditor.simuladortrafico.is.editor;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

public class Ventana extends JFrame {
	
	//Valores de la ventana.
	int ancho, alto, centroX, centroY;
	
	private static final long serialVersionUID = 1L;	
	
	Image image;
	
	//Controlador que gestiona el funcionamiento de la ventana.
	Controlador controlador;
	Mapa mapa; //El mapa que estoy representando.
	
	public Ventana(Controlador controlador, Mapa mapa) {
		// Make sure we have nice window decorations.
		//JFrame.setDefaultLookAndFeelDecorated(true);
		this.controlador = controlador;
		this.mapa = mapa;
		
		// Create and set up the window.
		this.setTitle("Editor Prototipo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.alto = 500;
		this.ancho = 500;
		this.centroX = 0;
		this.centroX = 0;
		
		this.setSize(ancho, alto);
//		 This call returns immediately and pixels are loaded in the background
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			// This is where a real application would open the file.
		image = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());
	    int width = image.getWidth(null);
	    int height = image.getHeight(null);
	    
	    if (width >= 0) {
	        // The image has been fully loaded
	    } else {
	        // The image has not been fully loaded
	    }
		}
		// Display the window.
		this.setVisible(true);
		
		OyenteRaton ratonero = new OyenteRaton();
		this.addMouseListener(ratonero);		
	}
	
	public void actualizarMapa(Mapa mapa){
		this.mapa = mapa;
		//this.update(getGraphics());
	}
	
	//Dibuja un nodo de un tipo determinado en el punto de la ventana (x,y).
	public void dibujaNodo(int x, int y, int tipo, Graphics2D g2){
		int posX,posY;
		Stroke strokeAntiguo;
		BasicStroke strokeNuevo = new BasicStroke(1f);
		
		strokeAntiguo = g2.getStroke();
		g2.setStroke(strokeNuevo);
		
		posX = x - 3;
		posY = y - 3;
		
		g2.setColor(Color.RED);
		g2.drawOval(posX,posY,6,6);
		
		//Volvemos a poner el trazo antiguo
		g2.setStroke(strokeAntiguo);
		
	}
	
	public void dibujarNodos(Graphics2D g2){
		Nodo nodoAux = new Nodo(0,0);
		
		for (int i=0;i<=(mapa.listaNodos.size()-1);i++){
			nodoAux = mapa.listaNodos.get(i);
			dibujaNodo(nodoAux.x + mapa.margenLeft, nodoAux.y + mapa.margenUp, 0, g2);	
		}
	}
	
	//	Dibuja una arista de un tipo determinado entre 2 puntosde la ventana (x1,y1) y (x2,y2).
	public void dibujaArista (int x1, int y1, int x2, int y2, int tipo, Graphics2D g2){
		Stroke strokeAntiguo;
		BasicStroke strokeBase = new BasicStroke(10f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,1.0f,null,0);
		BasicStroke strokeLinea = new BasicStroke(0.5f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,1.0f,new float[] { 4, 0, 4 },0);
		
		strokeAntiguo = g2.getStroke();
		
		g2.setColor(Color.BLACK);
		g2.setStroke(strokeBase);
		g2.drawLine(x1 + mapa.margenLeft, y1 + mapa.margenUp, x2 + mapa.margenLeft, y2 + mapa.margenUp);
		
		g2.setColor(Color.WHITE);
		g2.setStroke(strokeLinea);
		g2.drawLine(x1 + mapa.margenLeft, y1 + mapa.margenUp, x2 + mapa.margenLeft, y2 + mapa.margenUp);
		
		g2.setStroke(strokeAntiguo);

	}
	
	public void dibujarAristas(Graphics2D g2){
		Pareja parejaAux = new Pareja();
		
		for (int i=0;i<=(mapa.listaParejas.size()-1);i++){
			parejaAux = mapa.listaParejas.get(i);
			dibujaArista(parejaAux.nodo1.x,parejaAux.nodo1.y,parejaAux.nodo2.x,parejaAux.nodo2.y,0,g2);	
		}		
	}
	
	
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(Color.white);
        
        g2.drawImage(image, 0, 0, null);
        
        BasicStroke discontinua = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,1.0f,new float[] { 5, 5 },0);
        BasicStroke trazo = new BasicStroke(3.0f);        
        
        
        //Dibujo el contorno del mapa
        g2.setStroke(discontinua);
        
       // g2.drawRect(mapa.margenLeft,mapa.margenUp,mapa.ancho,mapa.alto);
       // g2.setColor(Color.WHITE);
       // g2.fillRect(mapa.margenLeft+1,mapa.margenUp+1,mapa.ancho-1,mapa.alto-1);
        
        dibujarAristas(g2);
        dibujarNodos(g2);
        
        //Desplazo la imagen al nuevo centro
        g2.setStroke(trazo);

	}
	
	
	class OyenteRaton extends MouseInputAdapter {
		public void mouseClicked(MouseEvent e){
			int boton = e.getButton();
			Point puntoPulsado = new Point();
			puntoPulsado = e.getPoint();
			controlador.click(puntoPulsado,boton);
			puntoPulsado = e.getPoint();
		}
	}
}
