package is.SimTraffic.Herramientas.DescargarMapa;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


import is.SimTraffic.Herramientas.DescargarMapa.GBC;


public class PleaseWaitDialog extends JDialog {

	private final JProgressBar progressBar = new JProgressBar();

	public final JLabel currentAction = new JLabel("Contacting the OSM server...");
	public final BoundedRangeModel progress = progressBar.getModel();
	public final JButton cancel = new JButton("Cancel");

	public PleaseWaitDialog(String text) {
		super(JOptionPane.getFrameForComponent(new JFrame()), true);
		
		setLayout(new GridBagLayout());
		JPanel pane = new JPanel(new GridBagLayout());
		pane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		pane.add(currentAction, GBC.eol().fill(GBC.HORIZONTAL));
		pane.add(progressBar, GBC.eop().fill(GBC.HORIZONTAL));
		pane.add(cancel, GBC.eol().anchor(GBC.CENTER));
		this.currentAction.setText(text);
		setContentPane(pane);
		setSize(350,100);

	}
}
