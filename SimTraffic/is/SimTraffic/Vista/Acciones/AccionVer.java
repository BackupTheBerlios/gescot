package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.ElementoMapa;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.BarrasHerramientas.Barra;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public abstract class AccionVer implements ActionListener {

	IControlador controlador;
	IModelo modelo;	
	JPanel ventanaVer;
	PanelMapa panel_mapa;
	List elementos;
	String direccionIcono;
	String tituloVer;
	String nombreElemento;

	public AccionVer(IControlador controlador, IModelo modelo,JFrame ventana,PanelMapa panel_mapa, 
			String direccionIcono, String tituloVer, String nombreElemento) {
		super();
		this.controlador = controlador;
		this.modelo = modelo;
		this.panel_mapa=panel_mapa;
		this.tituloVer = tituloVer;
		this.direccionIcono = direccionIcono;
		this.nombreElemento = nombreElemento;
	}

	public void actionPerformed(ActionEvent arg0) {
		
		 poneElementosEnLista();
		 if (ventanaVer==null) {
		 ventanaVer = new JPanel(new BorderLayout());
		 
		ClassLoader cl = this.getClass().getClassLoader();

		 JLabel iconoBus = new JLabel((new ImageIcon(cl
					.getResource("is/SimTraffic/Vista/Imagenes/" + direccionIcono)))); //$NON-NLS-1$
		 JPanel imagenBus =new JPanel();
		 imagenBus.setLayout(new BorderLayout());
		 imagenBus.add(iconoBus);
		 ventanaVer.add(imagenBus,BorderLayout.WEST); 
		 JScrollPane panel_lista =new JScrollPane(elementos);
		 panel_lista.setBorder(BorderFactory.createEmptyBorder(20, 10, 150, 20));
		 JPanel titulo =new JPanel();
		 JLabel label=new JLabel(tituloVer);
		 label.setFont(new Font(Messages.getString("AccionVer.0"), Font.BOLD, 15)); //$NON-NLS-1$
		 titulo.add(label);
		 ventanaVer.add(titulo,BorderLayout.NORTH);
		 ventanaVer.add(panel_lista,BorderLayout.CENTER);
		 ventanaVer.setBorder(BorderFactory.createRaisedBevelBorder());
		 JButton boton_salir= new JButton(Messages.getString("AccionVer.1")); //$NON-NLS-1$
		 boton_salir.addActionListener(new accionSalir());
		 JButton boton_buscar=new JButton(Messages.getString("AccionVer.2")+nombreElemento); //$NON-NLS-1$
		 estableceOyenteBuscar(boton_buscar);
		 JPanel botones=new JPanel(new BorderLayout());
		 botones.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		 botones.add(boton_buscar,BorderLayout.NORTH);
		 botones.add(boton_salir,BorderLayout.SOUTH);
		 ventanaVer.add(botones,BorderLayout.SOUTH);
		 panel_mapa.add(ventanaVer,BorderLayout.WEST);
		 panel_mapa.repaint();
		 }
	}
	
	abstract protected void estableceOyenteBuscar(JButton buscar) ;
	
	protected void poneElementosEnLista(){
		elementos = new List(); 
		
		//Esta es la línea que cambia en cada AccionVer
		Iterator it = modelo.getMapa().getLineasAutobuses().iterator();
		
		while (it.hasNext()){
		 elementos.add(((ElementoMapa)it.next()).getNombre());
		}
	}
	
	private class accionSalir implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			panel_mapa.remove(ventanaVer);
			ventanaVer=null;
			modelo.getMapa().limpiaSeleccion();
			panel_mapa.setLinea(null);
			panel_mapa.repaint();
		}
	}

}
