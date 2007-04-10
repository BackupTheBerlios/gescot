package is.SimTraffic.Vista.BarrasHerramientas;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Esta clase es extendida por las barras de herramientas de la apliaci�n.
 * <p>
 * Esta clase esta implementanda con el fin de ser extendida y proveer el m�todo
 * <b>a�adirBoton</b> con el fin de minimizar la repetici�n de c�digo,
 * asegurarnos de que todas las imagenes de los botones se cargan igual y
 * simplificar el c�digo de cada una de las barrras.
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
	 * M�todo para crear un boton de herramienta.
	 * <p>
	 * Este m�todo perminte repetir no repetir c�digo cada vez que se crea un
	 * nuevo boton de herramienta.
	 * 
	 * @param icono
	 *            Icono peque�o que mostrara el boton
	 * @param iconoGrande
	 *            Icono de mayor tama�o, que aparece en el tooltip
	 * @param tooltip
	 *            Texto del tooltip
	 * @param accion
	 *            Accion asociada al boton
	 * @return El JButton creado
	 */
	protected JButton a�adirBoton(String icono, String iconoGrande,
			String tooltip, ActionListener accion) {

		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon imagen = new ImageIcon(cl
				.getResource("is/SimTraffic/Vista/Imagenes/" + icono), tooltip);
		JButton boton = new JButton(imagen);
		// JButton boton = new JButton(new ImageIcon(
		// "is\\SimTraffic\\Vista\\Imagenes\\" + icono));

		boton.setMargin(new Insets(1, 1, 1, 1));
		// String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\"
		// + iconoGrande;
		URL imageName = cl.getResource("is/SimTraffic/Vista/Imagenes/"
				+ iconoGrande);
		boton.setToolTipText("<html>" + tooltip + " <img src=" + imageName
				+ "></html>");
		boton.addActionListener(accion);
		add(boton);
		return boton;
	}
}
