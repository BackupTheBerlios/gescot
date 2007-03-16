package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Mapa.Nodo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RepresentacionAvanzada extends RepresentacionSimple 
{
	public void pintar(Graphics2D g, Nodo nodo)
	{
		super.pintar(g,nodo);
		if (nodo.getTipo() != null)
		{	
			String aux = nodo.getTipo().getTipoCastellano();
			BufferedImage buffer = null;
			if (aux.equalsIgnoreCase("Carretera"))
			{
				aux = nodo.getTipo().getValorTipoCastellano();
				if (aux.equalsIgnoreCase("Mini-rotonda"))
				{
					//ImageIcon icono = new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\Re.png");
					try {
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Rotonda.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (aux.equalsIgnoreCase("Stop"))
				{
					
				}
				else if (aux.equalsIgnoreCase("Cruce"))
				{
					
				}
				else if (aux.equalsIgnoreCase("Portón para vehículos"))
				{
					
				}
				else if (aux.equalsIgnoreCase("Cambio De Rasante"))
				{
					
				}
				else if (aux.equalsIgnoreCase("Puente"))
				{
					
				}
				else if (aux.equalsIgnoreCase("Viaducto"))
				{
					
				}
			}
			else if (aux.equalsIgnoreCase("Campo de golf"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Estadio"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Pista de carreras"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Campo de deporte"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Parque acuático"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Parque"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Jardín"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Planta eólica"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Planta eólica"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Planta Hidroeléctrica"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Central Hidroeléctrica"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Central nuclear"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Faro"))
			{
				
			}
			else if (aux.equalsIgnoreCase("Pub"))
			{
				
			}
			g.drawImage(buffer, x_MapaARep(nodo.getPos().getLon()), y_MapaARep(nodo.getPos().getLat()), null);
		}
	}
}
