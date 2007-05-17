package is.SimTraffic.Vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import is.SimTraffic.IControlador;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HBuscarElemento;
import is.SimTraffic.Utils.ChequeoInputVentanas;

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
		this.setResizable(false);
		this.setTitle(Messages.getString("PanelBuscar.0")); //$NON-NLS-1$
		
		String[] tipoElemento = { Messages.getString("PanelBuscar.1"), Messages.getString("PanelBuscar.2"), Messages.getString("PanelBuscar.3"), Messages.getString("PanelBuscar.4") }; //Por defecto en Nodo //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		etiqPosicion = new JLabel(Messages.getString("PanelBuscar.5")); //$NON-NLS-1$
		etiqLat = new JLabel(Messages.getString("PanelBuscar.6")); //$NON-NLS-1$
		etiqLon = new JLabel(Messages.getString("PanelBuscar.7"));	 //$NON-NLS-1$
		comboElemento=new JComboBox(tipoElemento);
		etiqNombre = new JLabel(Messages.getString("PanelBuscar.8")); //$NON-NLS-1$
		
		campoNombre = new JTextField(12);
		campoLat = new JTextField(6);	
		campoLon = new JTextField(6);
		
		buscarPos = new JButton(Messages.getString("PanelBuscar.9")); //$NON-NLS-1$
		buscarElem = new JButton(Messages.getString("PanelBuscar.10"));	 //$NON-NLS-1$
		
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
						ChequeoInputVentanas chequeos = new ChequeoInputVentanas();
						if (chequeos.esLatitud(campoLat.getText()) && chequeos.esLongitud(campoLon.getText())){
						 //Situar el mapa en la posicion dada si es correcta.
						 panelMapa.centrarEnPosicion ( Double.parseDouble(campoLat.getText()),
													  Double.parseDouble(campoLon.getText()) );
						 //PanelBuscar.this.setVisible(false);
						 PanelBuscar.this.dispose();
						}
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
