package is.SimTraffic.Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class PanelNodo extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTabbedPane panelDatos;
	private JPanel panelBotones;
	
	
	public PanelNodo(){
		this.setLayout(new BorderLayout(2,2));
		
		panelDatos = new JTabbedPane();
		creaPanelDatos();
		
		panelBotones = new JPanel();
		creaPanelBotones();
		
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
	    String[] tiposNodos = { "                    ", "tipo1", "tipo2", "tipo3", "tipo4", "tipo5" };
	    JComboBox comboTipo = new JComboBox(tiposNodos);
	    JLabel etiquetaValor = new JLabel("Valor");
	    String[] valorNodos = { "                    ", "valor1", "valor2", "valor3", "valor4", "valor5" };
	    JComboBox comboValor = new JComboBox(valorNodos);
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
	    JRadioButton radioEntrada = new JRadioButton("Entrada");
	    JRadioButton radioSalida = new JRadioButton("Salida");
	    radioNormal.setSelected(true);
	    ButtonGroup radioGrupo = new ButtonGroup();
	    radioGrupo.add(radioNormal);
	    radioGrupo.add(radioEntrada);
	    radioGrupo.add(radioSalida);
	    panelRadioButtons.add(radioNormal);
	    panelRadioButtons.add(radioEntrada);
	    panelRadioButtons.add(radioSalida);
	    
	    JPanel panelFrecuencia = new JPanel();
	    panelFrecuencia.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
	    JLabel etiquetaFrecuencia = new JLabel("Frecuencia");
	    JTextField campoFrecuencia = new JTextField(5);
	    panelFrecuencia.add(etiquetaFrecuencia);
	    panelFrecuencia.add(campoFrecuencia);
	    
	    panelEntrada.add(panelRadioButtons);
	    panelEntrada.add(panelFrecuencia);
	    panelEntrada.setBorder(BorderFactory.createTitledBorder("Funcionalidad del Nodo"));
	    
	    JPanel panelAux = new JPanel();
	    panelAux.setLayout(new FlowLayout(FlowLayout.CENTER,30,80));
	    // Añadir cosas
	    panelAux.setBorder(BorderFactory.createTitledBorder("Panel Auxiliar"));
	    
	    panelPropiedades.setLayout(new BorderLayout(10,20));
	    panelPropiedades.add(panelTipo,BorderLayout.NORTH);
	    panelPropiedades.add(panelEntrada,BorderLayout.CENTER);
	    panelPropiedades.add(panelAux,BorderLayout.SOUTH);
	    
	    panelDatos.addTab("Propiedades",null, panelPropiedades, "Propiedades del Nodo");
	    panelDatos.setSelectedIndex(0);
		
	    panelDatos.addTab("Señales", null, panelSeñales,"Señales asociadas al Nodo");
	    panelDatos.addTab("Tramos", null, panelTramos,"Tramos asociados al Nodo");
	}
	
	public void creaPanelBotones(){
		
		JButton botonAceptar;
		JButton botonCancelar;
		
		botonAceptar = new JButton("Aceptar");
		botonCancelar = new JButton("Cancelar");
		
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER,80,28));
		
		panelBotones.add(botonAceptar);
		panelBotones.add(botonCancelar);
		
		panelBotones.setBorder(BorderFactory.createEtchedBorder());
	}
		
}
