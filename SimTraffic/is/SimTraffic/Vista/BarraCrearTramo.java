package is.SimTraffic.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class BarraCrearTramo extends JToolBar 
{
	private JComboBox opcionesTipoTramo;

	private JComboBox opcionesSentidoTramo;
	
	private JSpinner numCarrDir1;
	
	private JSpinner numCarrDir2;
	
	private JSpinner velMax;

	
	
	public BarraCrearTramo()
	{
		JLabel tipoTramo = new JLabel(" Tipo de Tramo ");
		String[] opcionesTipo = {"Bidireccional", "Unidireccional"};
		opcionesTipoTramo = new JComboBox(opcionesTipo);
		
		final JLabel sentidoLabel = new JLabel(" Sentido ");
		String[] opcionesSentido = {"Sentido 1", "Sentido 2"};
		opcionesSentidoTramo = new JComboBox(opcionesSentido);
		
		final JLabel numCarrDir1Label = new JLabel(" Nº Carriles Dir1 ");
		numCarrDir1 = new JSpinner(new SpinnerNumberModel(2,1,4,1));
		
		final JLabel numCarrDir2Label = new JLabel(" Nº Carriles Dir2 ");
		numCarrDir2 = new JSpinner(new SpinnerNumberModel(2,1,4,1));
		
		JLabel velMaxLabel = new JLabel(" Velocidad Máxima ");
		velMax = new JSpinner(new SpinnerNumberModel(60,20,120,10));
		
		add(tipoTramo);
		add(opcionesTipoTramo);
		add(sentidoLabel);
		add(opcionesSentidoTramo);
		add(numCarrDir1Label);
		add(numCarrDir1);
		add(numCarrDir2Label);
		add(numCarrDir2);
		add(velMaxLabel);
		add(velMax);
		
		//Inicializamos:
		sentidoLabel.setEnabled(false);
		opcionesSentidoTramo.setEnabled(false);
		numCarrDir1Label.setEnabled(true);
		numCarrDir1.setEnabled(true);
		numCarrDir2Label.setEnabled(true);
		numCarrDir2.setEnabled(true);
		
		
		//Oyentes:
		opcionesTipoTramo.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						if (opcionesTipoTramo.getSelectedIndex() == 1) //Unidireccional.
						{
							sentidoLabel.setEnabled(true);
							opcionesSentidoTramo.setEnabled(true);
							opcionesSentidoTramo.setSelectedIndex(0); //Por defecto, sentido 1.
							numCarrDir1Label.setEnabled(true);
							numCarrDir1.setEnabled(true);
							if (((Integer)numCarrDir1.getValue()).intValue() == 0)
								numCarrDir1.setValue(1);
							numCarrDir2Label.setEnabled(false);
							numCarrDir2.setEnabled(false);
							numCarrDir2.setValue(0);
						}
						else //Bidireccional.
						{
							sentidoLabel.setEnabled(false);
							opcionesSentidoTramo.setEnabled(false);
							numCarrDir1Label.setEnabled(true);
							numCarrDir1.setEnabled(true);
							numCarrDir2Label.setEnabled(true);
							numCarrDir2.setEnabled(true);
							if (((Integer)numCarrDir1.getValue()).intValue() == 0)
								numCarrDir1.setValue(1);
							if (((Integer)numCarrDir2.getValue()).intValue() == 0)
								numCarrDir2.setValue(1);
						}
						
					}
				});
		
		opcionesSentidoTramo.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						if (opcionesSentidoTramo.getSelectedIndex() == 0) //Sentido 1
						{
							numCarrDir1Label.setEnabled(true);
							numCarrDir1.setEnabled(true);
							if (((Integer)numCarrDir1.getValue()).intValue() == 0)
								numCarrDir1.setValue(1);
							numCarrDir2Label.setEnabled(false);
							numCarrDir2.setEnabled(false);
							numCarrDir2.setValue(0);
						}
						else //Sentido 2
						{
							numCarrDir1Label.setEnabled(false);
							numCarrDir1.setEnabled(false);
							numCarrDir1.setValue(0);
							numCarrDir2Label.setEnabled(true);
							numCarrDir2.setEnabled(true);
							if (((Integer)numCarrDir2.getValue()).intValue() == 0)
								numCarrDir2.setValue(1);
						}
						
					}
				});
	}


	public Tramo prepararTramo(Nodo i, Nodo f) 
	{

		Tramo tramo = new Tramo(i,f);
		int carrilesDir1 =((Integer)numCarrDir1.getValue()).intValue();
		int carrilesDir2 = ((Integer)numCarrDir2.getValue()).intValue();
		tramo.setNumCarrilesDir1(carrilesDir1);
		tramo.setNumCarrilesDir2(carrilesDir2);
		tramo.setVelMax(((Integer)velMax.getValue()).floatValue());
		return tramo;
	}
}
