package is.SimTraffic.Vista;

import is.SimTraffic.IModelo;

public class Vista implements IVista 
{
	public IModelo theIModelo;
	
	public Ventana ventana;
	
	/**
	 * @roseuid 45B8B3A90088
	 */
	public Vista(IModelo modelo) 
	{
		setModelo(modelo);
		ventana = new Ventana(modelo);
	}

	/**
	 * @roseuid 45C1DF84033B
	 */
	public void mostrar() 
	{
		ventana.setVisible(true);
	}

	public void actualizar() 
	{
		ventana.repaint();
	}

	/**
	 * @param modelo
	 * @roseuid 45C1E04500CA
	 */
	public void setModelo(IModelo modelo) 
	{
		theIModelo = modelo;
	}
}
