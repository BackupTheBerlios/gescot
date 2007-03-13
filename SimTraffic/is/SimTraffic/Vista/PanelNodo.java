package is.SimTraffic.Vista;

import is.SimTraffic.Mapa.Nodo;
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
	    JPanel panelSeñales = new JPanel();
	    JPanel panelTramos = new JPanel();
	      
	    JPanel panelTipo = new JPanel();
	    panelTipo.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
	    JLabel etiquetaTipo = new JLabel("Tipo");
	    
	    //Modificando
	    String[] tiposNodos = { "                  ", "Carretera", "Tiempo Libre", "Construcción", "No definido"};
	    
	    comboTipo = new JComboBox(tiposNodos);
	    
	    JLabel etiquetaValor = new JLabel("Valor");
	    
	    String[] valorNodos = { "                  "};
	    
	    comboValor = new JComboBox(valorNodos);
	    
	    //Por depurar
	    boolean encontrado=false;
	    if (nodo.getTipo()!= null) {
	    	for (int i=0;i<comboTipo.getItemCount() && !encontrado; i++) {
	    		if (comboTipo.getItemAt(i).equals(nodo.getTipo().getTipo())) {
	    			comboTipo.setSelectedItem(i);
	    			//comboTipo.setSelectedItem(comboTipo.getItemAt(i));
	    			encontrado = true;
	    			for (int j=0;j<comboValor.getItemCount(); j++) {
	    				if (comboValor.getItemAt(j).equals(nodo.getTipo().getValorTipo()))
	    					comboValor.setSelectedItem(j);
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
	    JLabel etiquetaPosicion = new JLabel("Posición=  ");
	    JLabel etiquetaposicionX = new JLabel("X:");
	    JLabel etiquetaposicionY = new JLabel("Y:");
	    JLabel posicionX = new JLabel((""+nodo.getPos().getPosX()));
	    JLabel posicionY = new JLabel((""+nodo.getPos().getPosY()));
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
		
	    panelDatos.addTab("Señales", null, panelSeñales,"Señales asociadas al Nodo");
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
		
}
