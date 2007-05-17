package is.SimTraffic.Vista.PanelesSimulacion;
import is.SimTraffic.Messages;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 */

/**
 * @author usuario_local
 *
 */
public class PanelTurismo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PanelTurismo(){
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
		JLabel iconoTurismo = new JLabel(new ImageIcon(Messages.getString("PanelTurismo.0"))); //$NON-NLS-1$
		JLabel etiquetaTitulo = new JLabel(Messages.getString("PanelTurismo.1")); //$NON-NLS-1$
		etiquetaTitulo.setFont(new Font(null,Font.BOLD,15));
		panelTitulo.add(iconoTurismo);
		panelTitulo.add(etiquetaTitulo);
		
		GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);

        gbc.gridx = 0; 
		gbc.gridy = 0; 
		gbc.gridwidth = 1; 
		gbc.gridheight = 1; 
		//gbc.weighty = 0.1; 
		this.add (panelTitulo, gbc);
	}

}
