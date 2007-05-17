package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.Messages;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * Esta clase es extendida por las barras de herramientas de la apliación.
 * <p>
 * Esta clase esta implementanda con el fin de ser extendida y proveer el método
 * <b>añadirBoton</b> con el fin de minimizar la repetición de código,
 * asegurarnos de que todas las imagenes de los botones se cargan igual y
 * simplificar el código de cada una de las barrras.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Barra extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7566177638043663157L;

	public Barra() {
		super();
	}

	public Barra(int i) {

		super(i);
	}

	/**
	 * Método para crear un boton de herramienta.
	 * <p>
	 * Este método permite no repetir código cada vez que se crea un
	 * nuevo boton de herramienta.
	 * 
	 * @param icono
	 *            Icono pequeño que mostrara el boton
	 * @param iconoGrande
	 *            Icono de mayor tamaño, que aparece en el tooltip
	 * @param tooltip
	 *            Texto del tooltip
	 * @param accion
	 *            Accion asociada al boton
	 * @param toggle
	 * 			  Indica si el boton será de tipo "toggle" o no.
	 * @return El JButton creado
	 */
	protected AbstractButton añadirBoton(String icono, String iconoGrande,
			String tooltip, ActionListener accion, boolean toggle) {

		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon imagen = new ImageIcon(cl
				.getResource(Messages.getString("Barra.0") + icono), tooltip); //$NON-NLS-1$
		AbstractButton boton = null;
		if (toggle)
			boton = new JToggleButton(imagen);
		else
			boton = new JButton(imagen);
		// JButton boton = new JButton(new ImageIcon(
		// "is\\SimTraffic\\Vista\\Imagenes\\" + icono));

		boton.setMargin(new Insets(1, 1, 1, 1));
		// String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\"
		// + iconoGrande;
		URL imageName = cl.getResource(Messages.getString("Barra.1") //$NON-NLS-1$
				+ iconoGrande);
		boton.setToolTipText(Messages.getString("Barra.2") + tooltip + Messages.getString("Barra.3") + imageName //$NON-NLS-1$ //$NON-NLS-2$
				+ Messages.getString("Barra.4")); //$NON-NLS-1$
		boton.addActionListener(accion);
		add(boton);
		return boton;
	}
}
