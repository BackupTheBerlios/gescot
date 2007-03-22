package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaTeclado;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarElementos;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarNodos;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarTramos;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class BarraSeleccionar extends JToolBar 
{
	private static final long serialVersionUID = 1L;

	public BarraSeleccionar(Ventana ventana, MLSeleccionarTramos escuchaSeleccionTramos, MLSeleccionarElementos escuchaSeleccionNodosYTramos, MLSeleccionarNodos escuchaSeleccion, EscuchaTeclado escuchaTeclado)
	{
		JButton seleccion_nodo = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodos.png"));
		seleccion_nodo.setMargin(new Insets(1, 1, 1, 1));
		String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodos-2.png"; 
		seleccion_nodo.setToolTipText("<html>Seleccionar nodos <img src="+imageName+"></html>");
		seleccion_nodo.addActionListener(new AccionSobreMapa(
				escuchaSeleccion, ventana, escuchaTeclado));
		seleccion_nodo.addKeyListener(escuchaTeclado);
		
		JButton seleccion_tramo = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\seleccionar-tramos.png"));
		seleccion_tramo.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\seleccionar-tramos-2.png"; 
		seleccion_tramo.setToolTipText("<html>Seleccionar tramos <img src="+imageName+"></html>");
		seleccion_tramo.addActionListener(new AccionSobreMapa(
				escuchaSeleccionTramos, ventana, escuchaTeclado));
		seleccion_tramo.addKeyListener(escuchaTeclado);
		
		JButton seleccion_area =  new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodosytramos.png"));
		seleccion_area.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodosytramos-2.png"; 
		seleccion_area.setToolTipText("<html>Seleccionar componentes dentro de área <img src="+imageName+"></html>");
		seleccion_area.addActionListener(new AccionSobreMapa(
				escuchaSeleccionNodosYTramos, ventana, escuchaTeclado));
		seleccion_area.addKeyListener(escuchaTeclado);
		
		add(seleccion_nodo);
		add(seleccion_tramo);
		add(seleccion_area);
	}
}
