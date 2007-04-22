package is.SimTraffic.Vista.VentanaMatrizPaso;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.*;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Señales.Semaforo;
import is.SimTraffic.Vista.PanelNodo;
import is.SimTraffic.Vista.Acciones.VentanaMatrizDePaso.AccionModificarEstadoConexion;

public class VentanaMatrizDePaso extends JFrame {
	/**
	 * Nodo del cuyo semáforo queremos modificar la matriz de paso.
	 */
	private Nodo nodo;
	
	/**
	 * PanelNodo que invoca esta ventana. 
	 */
	private JFrame ventanaPadre;
	
	/**
	 * El intervalo en el que queremos modificar la matriz de paso. 
	 */
	private int numIntervalo;
	
	
	/**
	 * Etiqueta de la parte inferior que sirve para informar de los eventos. 
	 */
	private JLabel etiqInformacion;

	public VentanaMatrizDePaso(Nodo nodo, PanelNodo ventanaPadre, int numIntervalo) throws HeadlessException {
		this.nodo = nodo;
		this.ventanaPadre = ventanaPadre;
		this.numIntervalo = numIntervalo;
		
		this.setTitle("Configurar matriz de paso");
		this.setVisible(true);
		this.setSize(new Dimension(450,300));
		this.setLocation(0,400);
		
		//Creamos el panel general para introducir el resto de componentes.
		JPanel panelGeneral = new JPanel();
		panelGeneral.setBorder(BorderFactory.createEtchedBorder());
		panelGeneral.setLayout(new BorderLayout());
		this.getContentPane().add(panelGeneral);
		
		//Creamos el panel que contiene los botones de interconexión.
		JLabel titulo = new JLabel("Por favor, establezca las interconexiones entre los tramos");
		panelGeneral.add(titulo,BorderLayout.NORTH);
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(BorderFactory.createTitledBorder("Conexiones en intervalo " + numIntervalo));
		panelGeneral.add(panelBotones,BorderLayout.CENTER);
		
		//Creamos el panel inferior de información
		JPanel panelInformacion = new JPanel();
		panelGeneral.add(panelInformacion,BorderLayout.SOUTH);
		panelInformacion.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		etiqInformacion = new JLabel("Establezca las interconexiones");
		panelInformacion.add(etiqInformacion);
		
		crearBotonesDeConexion(panelBotones);
		
	}
	
	private void crearBotonesDeConexion(JPanel panelBotones){
		int numTramos = nodo.getTramos().size();
		
		panelBotones.setLayout(new BorderLayout());
		JLabel etiqOrigen = new JLabel();
		etiqOrigen.setIcon(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\origen.png"));
		JLabel etiqDestino = new JLabel();
		etiqDestino.setIcon(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\destino.png"));
		etiqDestino.setHorizontalAlignment(SwingConstants.CENTER);
		panelBotones.add(etiqOrigen,BorderLayout.WEST);
		panelBotones.add(etiqDestino,BorderLayout.NORTH);
		JPanel panelInterno = new JPanel();
		GridLayout layout = new GridLayout(numTramos,numTramos);
		//Juntamos un poco más los botones si son más de 8 tramos a interconexionar.
		if (numTramos >= 8) {
			layout.setVgap(5);
			layout.setHgap(5);
		} else {
			layout.setVgap(10);
			layout.setHgap(10);
		}
		panelInterno.setLayout(layout);
		EscuchaBotonInterconexion escuchaBotonConexion = new EscuchaBotonInterconexion(this);
		AccionModificarEstadoConexion accionBotonConexion = new AccionModificarEstadoConexion(this);
		
		//i es el origen
		for (int i = 0; i < numTramos;i++){
			//j es el destino
			for (int j = 0; j< numTramos; j++){
				BotonDeConexion botonConexion = new BotonDeConexion(i,j,numIntervalo,nodo);
				botonConexion.setFont(new Font("Verdana",0,8));
				botonConexion.setText(i+"->"+j);
				botonConexion.addMouseListener(escuchaBotonConexion);
				botonConexion.addActionListener(accionBotonConexion);
				panelInterno.add(botonConexion);
			}
		}
		panelBotones.add(panelInterno,BorderLayout.CENTER);
	}
	
	public void informa(String texto){
		this.etiqInformacion.setText(texto);
	}

	public JFrame getVentanaPadre() {
		return ventanaPadre;
	}

	public void setVentanaPadre(JFrame ventanaPadre) {
		this.ventanaPadre = ventanaPadre;
	}

	public Nodo getNodo() {
		return nodo;
	}

	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}

	public int getNumIntervalo() {
		return numIntervalo;
	}

	public void setNumIntervalo(int numIntervalo) {
		this.numIntervalo = numIntervalo;
	}
}
