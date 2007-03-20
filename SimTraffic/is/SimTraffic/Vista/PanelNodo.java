package is.SimTraffic.Vista;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Semaforos.MasterSemaforo;
import is.SimTraffic.Mapa.TipoElemento.TipoElemento;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;
import is.SimTraffic.Vista.Acciones.PanelNodo.AccionAceptar;
import is.SimTraffic.Vista.Acciones.PanelNodo.AccionSeleccionarTipo;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;


public class PanelNodo extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JTabbedPane panelDatos;
	private JPanel panelBotones;
	private JPanel panelAuxiliar;
	private JComboBox comboTipoSe�ales;
	private JComboBox comboTipo;
	private JComboBox comboValor;
	private JTextField campoFrecuencia;
	private JTextField campoNombre;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JPanel panelPropiedades = new JPanel();
	private JPanel panelSe�ales = new JPanel();
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
	 * Dise�o de cuadro de dialogo emergente de propiedades de nodo
	 * TODO terminar el dise�o de las pesta�as
	 */
	public void creaPanelDatos(){
		creaPanelTipo();
		creaPanelSe�ales();
		creaPanelTramos();
	}
	
	/**
	 * Esto correponde a la pesta�a de las propiedades de los tramos de un nodo
	 * TODO Todo
	 */
	public void creaPanelTramos(){
		panelDatos.addTab("Tramos", null, panelTramos,"Tramos asociados al Nodo");
	}

	/**
	 * Esto correponde a la pesta�a de las propiedades de las se�ales de un nodo
	 * falta mucho por hacer
	 * TODO casi todo
	 */
	public void creaPanelSe�ales(){

	    panelSe�ales.setLayout(new BorderLayout());
	    JPanel panelTipoSe�al= new JPanel();
	    JPanel panelDetallesSe�al= new JPanel();
	    this.panelSe�ales.add(panelTipoSe�al,BorderLayout.NORTH);
	    this.panelSe�ales.add(panelDetallesSe�al,BorderLayout.CENTER);
	    
	    panelTipoSe�al.setLayout(new FlowLayout());
	    panelDetallesSe�al.setLayout(new FlowLayout());
	    
	    panelTipoSe�al.setBorder(BorderFactory.createTitledBorder("Tipo de Se�al"));
	    panelDetallesSe�al.setBorder(BorderFactory.createTitledBorder("Detalles"));
	    
	    
	    JLabel etiquetaTipoSe�al = new JLabel("Tipo");
	    String[] tiposSe�ales = { "                  ", "STOP", "Ceda el Paso", "Sem�foros"};
	    comboTipoSe�ales = new JComboBox(tiposSe�ales);
	    
	    panelTipoSe�al.add(etiquetaTipoSe�al);
	    panelTipoSe�al.add(comboTipoSe�ales);
	    
	    
	    
	    JLabel etiquetaDetallesSe�al1= new JLabel("Tipo de Semaforo");
	    String[] tiposSemaforos = { "                  ", "Circular", "Perpendicular"};
	    JComboBox comboTipoSemaforos = new JComboBox();
	    comboTipoSemaforos = new JComboBox(tiposSemaforos);
	    JLabel etiquetaDetallesSe�al2= new JLabel("Tiempo de cliclo");
	    JTextField campoTiempoCicloSemaforo= new JTextField("      ");
	    
	    
	    
	    panelDetallesSe�al.add(etiquetaDetallesSe�al1);
	    panelDetallesSe�al.add(comboTipoSemaforos);
	    panelDetallesSe�al.add(etiquetaDetallesSe�al2);
	    panelDetallesSe�al.add(campoTiempoCicloSemaforo);
	    
	    panelDatos.addTab("Se�ales", null, panelSe�ales,"Se�ales asociadas al Nodo");	    
	    
	    // Cargar los datos del nodo al formulario
	    
	    
	    MasterSemaforo semaforoDelNodo=(MasterSemaforo)this.nodo.getSe�al();
	    if (semaforoDelNodo!=null){
	    	    campoTiempoCicloSemaforo.setText(Integer.toString(semaforoDelNodo.getTiempoDeCiclo())+" s");
	   	    }
	    
/*	    if(comboTipoSe�ales.getSelectedItem()=="Semaforo"){
	    	String[] tiposSemaforos = { "                  ", "Circular", "Perpendicular (�?)"};
	    	JComboBox comboTipoSemaforos = new JComboBox(tiposSemaforos);
		    panelSe�ales.add(comboTipoSemaforos);
	    }
*/
	    /*JLabel etiquetaValorSe�al = new JLabel("Valor");
	    panelSe�ales.add(etiquetaValorSe�al);
	    panelSe�ales.add(comboValor);*/
	    
	    
	}
	
	public void creaPanelTipo(){
					  
	    JPanel panelTipo = new JPanel();
	    panelTipo.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
	    JLabel etiquetaTipo = new JLabel("Tipo");
	    
	    //Modificando
	    String[] tiposNodos = { "                  ", "Carretera", "Tiempo Libre", "Construcci�n", "Infraestructura", "No definido"};
	    
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
	    
	    JPanel panelEntrada = new JPanel();
	    panelEntrada.setLayout(new FlowLayout(FlowLayout.CENTER,40,20));
	  
	    JPanel panelRadioButtons = new JPanel();
	    panelRadioButtons.setLayout(new GridLayout(3,1));
	    JRadioButton radioNormal = new JRadioButton("Normal");
	    JRadioButton radioEntradaSalida = new JRadioButton("Entrada/Salida");
	    //JRadioButton radioSalida = new JRadioButton("Salida");
	    radioNormal.setSelected(true);
	    ButtonGroup radioGrupo = new ButtonGroup();
	    radioGrupo.add(radioNormal);
	    radioGrupo.add(radioEntradaSalida);
	    //radioGrupo.add(radioSalida);
	    panelRadioButtons.add(radioNormal);
	    panelRadioButtons.add(radioEntradaSalida);
	    //panelRadioButtons.add(radioSalida);
	    
	    JPanel panelFrecuencia = new JPanel();
	    panelFrecuencia.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
	    JLabel etiquetaFrecuencia = new JLabel("Frecuencia");
	    campoFrecuencia = new JTextField(5);
	    panelFrecuencia.add(etiquetaFrecuencia);
	    panelFrecuencia.add(campoFrecuencia);
	    
	    panelEntrada.add(panelRadioButtons);
	    panelEntrada.add(panelFrecuencia);
	    panelEntrada.setBorder(BorderFactory.createTitledBorder("Funcionalidad del Nodo"));
	    
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
	    JLabel etiquetaPosicion = new JLabel("Posici�n=  ");
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
	    panelAuxiliar.setBorder(BorderFactory.createTitledBorder("Informaci�n del Nodo"));
	        
	    panelPropiedades.setLayout(new BorderLayout(10,20));
	    panelPropiedades.add(panelTipo,BorderLayout.NORTH);
	    panelPropiedades.add(panelEntrada,BorderLayout.CENTER);
	    panelPropiedades.add(panelAuxiliar,BorderLayout.SOUTH);
	    
	    
	    
	    panelDatos.addTab("Propiedades",null, panelPropiedades, "Propiedades del Nodo");
	    panelDatos.setSelectedIndex(0);
		
	}
	
	public void crearAcciones() {
		ActionListener accionSeleccionarTipo = new AccionSeleccionarTipo(comboTipo,comboValor);
	    comboTipo.addActionListener(accionSeleccionarTipo);
	    
	    // Oyentes para los botones aceptar y cancelar
	    ActionListener accionAceptar = new AccionAceptar(nodo,comboTipo,comboValor,campoFrecuencia,campoNombre,this, mapa);
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
	
	public void creaPanelBotones(){
		
		botonAceptar = new JButton("Aceptar");
		botonCancelar = new JButton("Cancelar");
		
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER,80,28));
		
		panelBotones.add(botonAceptar);
		panelBotones.add(botonCancelar);
		
		panelBotones.setBorder(BorderFactory.createEtchedBorder());
	}
	
	/**
	 * Redundante (m�todo que ya exist�a en un oyente peor necesario aqu�, 
	 * redise�ar m�s adelante).
	 */
	public void configurarValoresSe�al() {
		if (comboTipoSe�ales.getSelectedItem().equals("Semaforo")) {
		 	String[] tiposSemaforos = { "                  ", "Circular", "Perpendicular (�?)"};
	    	JComboBox comboTipoSemaforos = new JComboBox(tiposSemaforos);
		    panelSe�ales.add(comboTipoSemaforos);
	  }
		else {
			comboValor.removeAllItems();
			comboValor.addItem("No definido");
		}
	}
	
	
	public void configurarValoresNodo() {
		if (comboTipo.getSelectedItem().equals("Carretera")) {
			String[] s1={"Mini-rotonda","Stop","Cruce","Port�n para veh�culos", "Cambio De Rasante", "Puente", "Viaducto"};
			comboValor.removeAllItems();
			for (int i=0;i<s1.length;i++)
				comboValor.addItem(s1[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Tiempo Libre")) {
			String[] s2={"Campo de golf","Estadio","Marina","Pista de carreras", "Campo de deporte", "Parque acu�tico", "Parque", "Jard�n"};
			comboValor.removeAllItems();
			for (int i=0;i<s2.length;i++)
				comboValor.addItem(s2[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Construcci�n")) {
			String[] s3={"Planta e�lica","Planta Hidroel�ctrica","Central Hidroel�ctrica","Central nuclear","Faro"};
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
		
}
