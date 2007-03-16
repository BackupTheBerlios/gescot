package is.SimTraffic.Vista;

import is.SimTraffic.Mapa.Nodo;
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


public class PanelNodo extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTabbedPane panelDatos;
	private JPanel panelBotones;
	private JPanel panelAuxiliar;
	private JComboBox comboTipoSe�ales;
	private JComboBox comboTipo;
	private JComboBox comboValor;
	private JTextField campoFrecuencia;
	private JTextField campoNombre;
	private Nodo nodo;
	private JButton botonAceptar;
	private JButton botonCancelar;
	
	
	public PanelNodo(Nodo nodo){
		this.setLayout(new BorderLayout(2,2));
		this.nodo = nodo;
		
		panelDatos = new JTabbedPane();
		creaPanelDatos();
		
		panelBotones = new JPanel();
		creaPanelBotones();
		
		crearAcciones();
	    this.add(panelDatos,BorderLayout.NORTH);
	    this.add(panelBotones,BorderLayout.SOUTH);
	    
	}
	
	public void creaPanelDatos(){
	
		JPanel panelPropiedades = new JPanel();
	    JPanel panelSe�ales = new JPanel();
	    JPanel panelTramos = new JPanel();
	      
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
	    
	    
	    panelSe�ales.setLayout(new FlowLayout());
	    JLabel etiquetaTipoSe�al = new JLabel("Tipo");
	    panelSe�ales.add(etiquetaTipoSe�al);
	    String[] tiposSe�ales = { "                  ", "STOP", "Ceda el Paso", "Sem�foros"};
	    comboTipoSe�ales = new JComboBox(tiposSe�ales);
	    panelSe�ales.add(comboTipoSe�ales);
	    /*JLabel etiquetaValorSe�al = new JLabel("Valor");
	    panelSe�ales.add(etiquetaValorSe�al);
	    panelSe�ales.add(comboValor);*/
	    panelSe�ales.setBorder(BorderFactory.createTitledBorder("Tipo de Nodo"));
	    
	    
	    panelDatos.addTab("Propiedades",null, panelPropiedades, "Propiedades del Nodo");
	    panelDatos.setSelectedIndex(0);
		
	    panelDatos.addTab("Se�ales", null, panelSe�ales,"Se�ales asociadas al Nodo");
	    panelDatos.addTab("Tramos", null, panelTramos,"Tramos asociados al Nodo");
	}
	
	public void crearAcciones() {
		ActionListener accionSeleccionarTipo = new AccionSeleccionarTipo(comboTipo,comboValor);
	    comboTipo.addActionListener(accionSeleccionarTipo);
	    ActionListener accionAceptar = new AccionAceptar(nodo,comboTipo,comboValor,campoFrecuencia,campoNombre,this);
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
