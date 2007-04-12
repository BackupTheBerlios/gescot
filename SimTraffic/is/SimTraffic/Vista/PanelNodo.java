package is.SimTraffic.Vista;

import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Semaforos.MasterSemaforo;
import is.SimTraffic.Mapa.TipoElemento.TipoElemento;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;
import is.SimTraffic.Vista.Acciones.PanelNodo.AccionAceptar;
import is.SimTraffic.Vista.Acciones.PanelNodo.AccionSeleccionarTipo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class PanelNodo extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JTabbedPane panelDatos;
	private JPanel panelBotones;
	private JPanel panelAuxiliar;
	private JComboBox comboTipoSeñales;
	private JComboBox comboTipoSemaforos;
	private JTextField campoTiempoCicloSemaforo;
	private JComboBox comboTipo;
	private JComboBox comboValor;
	private EntradaSalida es;
	private JTextField entran;
	private JTextField salen;
    private JTextField[] entrada = new JTextField[12];
    private JTextField[] salida = new JTextField[12];
	private JTextField campoNombre;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JPanel panelPropiedades = new JPanel();
	private JPanel panelSeñales = new JPanel();
	private JPanel panelTramos = new JPanel();
	
	private Nodo nodo;
	private PanelMapa mapa;

	
	public PanelNodo(Nodo nodo){
		
		
	}
	
	public PanelNodo(Nodo nodo, PanelMapa mapa)
	{
		this.setLayout(new BorderLayout(2,2));
		this.nodo = nodo;
		this.mapa = mapa;
		
		panelDatos = new JTabbedPane();
		creaPanelDatos();
		
		panelBotones = new JPanel();
		creaPanelBotones();
		
		crearAcciones();
	    this.add(panelDatos,BorderLayout.NORTH);
	    this.add(panelBotones,BorderLayout.SOUTH);
	    
	}
	
	/**
	 * Diseño de cuadro de dialogo emergente de propiedades de nodo
	 * TODO terminar el diseño de las pestañas
	 */
	public void creaPanelDatos(){
		creaPanelTipo();
		creaPanelSeñales();
		creaPanelTramos();
	}
	
	/**
	 * Esto correponde a la pestaña de las propiedades de los tramos de un nodo
	 * TODO Todo
	 */
	public void creaPanelTramos(){
		panelDatos.addTab("Tramos", null, panelTramos,"Tramos asociados al Nodo");
	}

	/**
	 * Esto correponde a la pestaña de las propiedades de las señales de un nodo
	 * falta mucho por hacer
	 * TODO casi todo
	 */
	public void creaPanelSeñales(){

	    panelSeñales.setLayout(new BorderLayout());
	    JPanel panelTipoSeñal= new JPanel();
	    JPanel panelDetallesSeñal= new JPanel();
	    this.panelSeñales.add(panelTipoSeñal,BorderLayout.NORTH);
	    this.panelSeñales.add(panelDetallesSeñal,BorderLayout.CENTER);
	    
	    panelTipoSeñal.setLayout(new FlowLayout());
	    panelDetallesSeñal.setLayout(new FlowLayout());
	    
	    panelTipoSeñal.setBorder(BorderFactory.createTitledBorder("Tipo de Señal"));
	    panelDetallesSeñal.setBorder(BorderFactory.createTitledBorder("Detalles"));
	    
	    
	    JLabel etiquetaTipoSeñal = new JLabel("Tipo");
	    String[] tiposSeñales = { "                  ", "STOP", "Ceda el Paso", "Semáforos"};
	    comboTipoSeñales = new JComboBox(tiposSeñales);
	    
	    panelTipoSeñal.add(etiquetaTipoSeñal);
	    panelTipoSeñal.add(comboTipoSeñales);
	    
	    
	    
	    JLabel etiquetaDetallesSeñal1= new JLabel("Tipo de Semaforo");
	    String[] tiposSemaforos = { "                  ", "Circular", "Perpendicular"};
	    comboTipoSemaforos = new JComboBox();
	    comboTipoSemaforos = new JComboBox(tiposSemaforos);
	    JLabel etiquetaDetallesSeñal2= new JLabel("Tiempo de ciclo (segundos)");
	    campoTiempoCicloSemaforo= new JTextField("");
	    campoTiempoCicloSemaforo.setPreferredSize(new Dimension(30,20));
	    

	    
	    panelDetallesSeñal.add(etiquetaDetallesSeñal1);
	    panelDetallesSeñal.add(comboTipoSemaforos);
	    panelDetallesSeñal.add(etiquetaDetallesSeñal2);
	    panelDetallesSeñal.add(campoTiempoCicloSemaforo);
	    
	    panelDatos.addTab("Señales", null, panelSeñales,"Señales asociadas al Nodo");	    
	    
	    // Cargar los datos del nodo al formulario
	    
	    
	    MasterSemaforo semaforoDelNodo=(MasterSemaforo)this.nodo.getSeñal();
	    if (semaforoDelNodo!=null){
	    	    campoTiempoCicloSemaforo.setText(Integer.toString(semaforoDelNodo.getTiempoDeCiclo()));
	   	    }
	    
/*	    if(comboTipoSeñales.getSelectedItem()=="Semaforo"){
	    	String[] tiposSemaforos = { "                  ", "Circular", "Perpendicular (¿?)"};
	    	JComboBox comboTipoSemaforos = new JComboBox(tiposSemaforos);
		    panelSeñales.add(comboTipoSemaforos);
	    }
*/
	    /*JLabel etiquetaValorSeñal = new JLabel("Valor");
	    panelSeñales.add(etiquetaValorSeñal);
	    panelSeñales.add(comboValor);*/
	    
	    
	}
	
	/**
	 * 
	 *
	 */
	public void creaPanelTipo(){
					  
	    JPanel panelTipo = new JPanel();
	    panelTipo.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
	    JLabel etiquetaTipo = new JLabel("Tipo");
	    
	    //Modificando
	    String[] tiposNodos = { "                  ", "Carretera", "Tiempo Libre", "Construcción", "Infraestructura", "No definido"};
	    
	    comboTipo = new JComboBox(tiposNodos);
	    
	    JLabel etiquetaValor = new JLabel("Valor");
	    
	    String[] valorNodos = { "                  "};
	    
	    comboValor = new JComboBox(valorNodos);
	    
	    //Por depurar
	    boolean encontrado=false;
	    if (nodo.getTipo()!= null) {
	    	for (int i=0;(i<comboTipo.getItemCount()) && (!encontrado); i++) {
	    		if (comboTipo.getItemAt(i).equals(nodo.getTipo().getTipoCastellano())) {
	    			//comboTipo.setSelectedItem(i);
	    			comboTipo.setSelectedItem(nodo.getTipo().getTipoCastellano());
	    			configurarValoresNodo();
	    			encontrado = true;
	    			for (int j=0;j<comboValor.getItemCount(); j++) {
	    				if (comboValor.getItemAt(j).equals(nodo.getTipo().getValorTipoCastellano()))
	    					comboValor.setSelectedItem(nodo.getTipo().getValorTipoCastellano());
	    			}
	    		}
	    	}
	    }
	    
	    panelTipo.add(etiquetaTipo);
	    panelTipo.add(comboTipo);
	    panelTipo.add(etiquetaValor);
	    panelTipo.add(comboValor);
	    panelTipo.setBorder(BorderFactory.createTitledBorder("Tipo de Nodo"));
	    
	    JPanel panelEntrada = crearSeccionEntradaSalida();
	    
	    panelAuxiliar = new JPanel();
	    panelAuxiliar.setLayout(new BorderLayout());
	    JPanel panelNombre = new JPanel();
	    panelNombre.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
	    JLabel etiquetaNombre = new JLabel("Nombre");
	    campoNombre = new JTextField(14);
	    if (nodo.getNombre()!=null)
	    	campoNombre.setText(nodo.getNombre());
	    panelNombre.add(etiquetaNombre);
	    panelNombre.add(campoNombre);
	    JPanel panelPosicion = new JPanel();
	    panelPosicion.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
	    JLabel etiquetaPosicion = new JLabel("Posición=  ");
	    JLabel etiquetaposicionX = new JLabel("Lat:");
	    JLabel etiquetaposicionY = new JLabel("Lon:");
	    JLabel posicionX = new JLabel((""+nodo.getPos().getLat()));
	    JLabel posicionY = new JLabel((""+nodo.getPos().getLon()));
	    panelPosicion.add(etiquetaPosicion);
	    panelPosicion.add(etiquetaposicionX);
	    panelPosicion.add(posicionX);
	    panelPosicion.add(etiquetaposicionY);
	    panelPosicion.add(posicionY);
	    
	    panelAuxiliar.add(panelNombre,BorderLayout.NORTH);
	    panelAuxiliar.add(panelPosicion,BorderLayout.SOUTH);
	    panelAuxiliar.setBorder(BorderFactory.createTitledBorder("Información del Nodo"));
	        
	    panelPropiedades.setLayout(new BorderLayout(10,20));
	    panelPropiedades.add(panelTipo,BorderLayout.NORTH);
	    panelPropiedades.add(panelEntrada,BorderLayout.CENTER);
	    panelPropiedades.add(panelAuxiliar,BorderLayout.SOUTH);
	    
	    
	    
	    panelDatos.addTab("Propiedades",null, panelPropiedades, "Propiedades del Nodo");
	    panelDatos.setSelectedIndex(0);
		
	}
	
	/**
	 * 
	 *
	 */
	public void crearAcciones() {
		ActionListener accionSeleccionarTipo = new AccionSeleccionarTipo(comboTipo,comboValor);
	    comboTipo.addActionListener(accionSeleccionarTipo);
	   
	    
	    // Oyentes para los botones aceptar y cancelar
	    ActionListener accionAceptar = new AccionAceptar(nodo,
	    		comboTipo,comboValor, es ,campoNombre,this,
	    		comboTipoSemaforos, campoTiempoCicloSemaforo, comboTipoSeñales,
	    		mapa);
	    botonAceptar.addActionListener(accionAceptar);
	    final PanelNodo panelPpal=this;
	    botonCancelar.addActionListener(
	    		new ActionListener(){
					public void actionPerformed(ActionEvent e){
						panelPpal.dispose();
					}
	    		}
	    );
	}
	
	/**
	 * 
	 *
	 */
	public void creaPanelBotones(){
		
		botonAceptar = new JButton("Aceptar");
		botonCancelar = new JButton("Cancelar");
		
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER,80,28));
		
		panelBotones.add(botonAceptar);
		panelBotones.add(botonCancelar);
		
		panelBotones.setBorder(BorderFactory.createEtchedBorder());
	}
	
	/**
	 * Redundante (método que ya existía en un oyente peor necesario aquí, 
	 * rediseñar más adelante).
	 */
	public void configurarValoresSeñal() {
		if (comboTipoSeñales.getSelectedItem().equals("Semaforo")) {
		 	String[] tiposSemaforos = { "                  ", "Circular", "Perpendicular (¿?)"};
	    	JComboBox comboTipoSemaforos = new JComboBox(tiposSemaforos);
		    panelSeñales.add(comboTipoSemaforos);
	  }
		else {
			comboValor.removeAllItems();
			comboValor.addItem("No definido");
		}
	}
	
	/**
	 * 
	 *
	 */
	public void configurarValoresNodo() {
		if (comboTipo.getSelectedItem().equals("Carretera")) {
			String[] s1={"Mini-rotonda","Stop","Cruce","Portón para vehículos", "Cambio De Rasante", "Puente", "Viaducto"};
			comboValor.removeAllItems();
			for (int i=0;i<s1.length;i++)
				comboValor.addItem(s1[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Tiempo Libre")) {
			String[] s2={"Campo de golf","Estadio","Marina","Pista de carreras", "Campo de deporte", "Parque acuático", "Parque", "Jardín"};
			comboValor.removeAllItems();
			for (int i=0;i<s2.length;i++)
				comboValor.addItem(s2[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Construcción")) {
			String[] s3={"Planta eólica","Planta Hidroeléctrica","Central Hidroeléctrica","Central nuclear","Faro"};
			comboValor.removeAllItems();
			for (int i=0;i<s3.length;i++)
				comboValor.addItem(s3[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Infraestructura")) {
			TipoElemento inf=new TipoNodoAmenity("Pub");
			String[] s4=inf.devolverListaValores();
			comboValor.removeAllItems();
			for (int i=0;i<s4.length;i++)
				comboValor.addItem(s4[i]);
		}
		
		else {
			comboValor.removeAllItems();
			comboValor.addItem("No definido");
		}
	}

	private JPanel crearSeccionEntradaSalida() {
	    JPanel panelEntrada = new JPanel();
	    panelEntrada.setLayout(new GridLayout(2,1));
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(3,13));

	    
	    
	    panel.add(new JLabel("Int\\Hr"));
	    for (int i = 0; i < 12; i++) {
	    	panel.add(new JLabel("" + i*2 + "-" + (i*2 + 1)));
	    }
	    
	    panel.add(new JLabel("Entran"));
	    for (int i = 0; i < 12; i++) {
	    	entrada[i] = new JTextField(3);
	    	panel.add(entrada[i]);
	    }

	    panel.add(new JLabel("Salen"));
	    for (int i = 0; i < 12; i++) {
	    	salida[i] = new JTextField(3);
	    	panel.add(salida[i]);
	    }

	    panelEntrada.add(new JTextArea("Por favor indique con un número del 0 al 999 la importancia de este nodo como\nentrada o como salida a las distintas horas del día"));
	    panelEntrada.add(panel);
	    panelEntrada.setBorder(BorderFactory.createTitledBorder("Funcionalidad del Nodo"));
	    
	    return panelEntrada;
	}
	
	/**
	 * Método que lee los valores ingresados por el usario sobre frecuencias y genera el
	 * atributo es del tipo EntradaSalida
	 */
	private void generarEs () {
		int[] entradas = new int[12];
		int[] salidas = new int[12];
		int entran;
		int salen;
		entran = Integer.parseInt(this.entran.getText());
		salen = Integer.parseInt(this.salen.getText());
		for (int i = 0; i < 12; i ++) {
			entradas[i] = Integer.parseInt(this.entrada[i].getText());
			System.out.print(entradas[i]);
		}
		System.out.println();
		for (int i = 0; i < 12; i++) {
			salidas[i] = Integer.parseInt(this.salida[i].getText());
			System.out.print(salidas[i]);
		}
		es = new EntradaSalida(entran, salen, entradas, salidas);
	}
}
