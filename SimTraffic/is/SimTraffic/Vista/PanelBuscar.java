package is.SimTraffic.Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HBuscarElemento;
import is.SimTraffic.Vista.PanelesSimulacion.PanelTurismo;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelBuscar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IControlador controlador;
	private PanelMapa panelMapa;
	
	private JLabel etiqPosicion;
	private JLabel etiqLat;
	private JLabel etiqLon;	
	private JComboBox comboElemento;
	private JLabel etiqNombre;
	
	private JTextField campoNombre;
	private JTextField campoLat;	
	private JTextField campoLon;
	
	private JButton buscarPos;
	private JButton buscarElem;

	public PanelBuscar(IControlador controladorParam,PanelMapa panelMap) {
		this.controlador = controladorParam;
		this.panelMapa = panelMap;
		this.setLocation(200,200);
		this.setSize(400,200);
		//this.setResizable(false);
		this.setTitle("Buscar...");
		
		String[] tipoElemento = { "Nodo", "Tramo", "Vía", "Línea de bus" }; //Por defecto en Nodo
		etiqPosicion = new JLabel("Posición");
		etiqLat = new JLabel(" Lat    ");
		etiqLon = new JLabel(" Lon    ");	
		comboElemento=new JComboBox(tipoElemento);
		etiqNombre = new JLabel(" Nombre ");
		
		campoNombre = new JTextField(12);
		campoLat = new JTextField(6);	
		campoLon = new JTextField(6);
		
		buscarPos = new JButton("Buscar");
		buscarElem = new JButton("Buscar");	
		
		this.setLayout(new GridLayout(2,1));
		
		JPanel panelBuscarPos = new JPanel();
		JPanel panelBuscarOtro = new JPanel();
		
		panelBuscarPos.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelBuscarOtro.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panelBuscarPos.add(etiqPosicion);
		panelBuscarPos.add(etiqLat);
		panelBuscarPos.add(campoLat);
		panelBuscarPos.add(etiqLon);
		panelBuscarPos.add(campoLon);
		panelBuscarPos.add(buscarPos);
		
		panelBuscarOtro.add(comboElemento);
		panelBuscarOtro.add(etiqNombre);
		panelBuscarOtro.add(campoNombre);
		panelBuscarOtro.add(buscarElem);
		
		this.add(panelBuscarPos);
		this.add(panelBuscarOtro);	
		
		//Funcionalidad del panel
		
		//Crear oyente externo
		buscarPos.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//Comprobar que los datos son numéricos (el rango ya se comprueba 
						//en el método centrarEnPosicion).
						
						//Situar el mapa en la posicion dada si es correcta.
						panelMapa.centrarEnPosicion ( Double.parseDouble(campoLat.getText()),
													  Double.parseDouble(campoLon.getText()) );
						//PanelBuscar.this.setVisible(false);
						PanelBuscar.this.dispose();
					}
				}
		);
		
		//Crear oyente externo.
		buscarElem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//Buscar el nodo/tramo/via/linea de bus en el mapa (Llama a una herramienta)
						HBuscarElemento herramientaBuscar = new HBuscarElemento(
								((String)(comboElemento.getSelectedItem())),
								campoNombre.getText(),
								panelMapa);
						controlador.herramienta(herramientaBuscar);
						PanelBuscar.this.dispose();
					}
				}
		);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame panelBuscar = new PanelBuscar(null,null);
		panelBuscar.setVisible(true);
	}
}
