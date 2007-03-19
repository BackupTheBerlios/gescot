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
		try
		{
			if (nodo.getTipo() != null)
			{	
				String aux = nodo.getTipo().getTipoCastellano();
				BufferedImage buffer = null;
				if (aux.equalsIgnoreCase("Carretera"))
				{
					aux = nodo.getTipo().getValorTipoCastellano();
					if (aux.equalsIgnoreCase("Mini-rotonda"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Rotonda.png"));
					}
					else if (aux.equalsIgnoreCase("Stop"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Stop.png"));
					}
					else if (aux.equalsIgnoreCase("Cruce"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Port�n para veh�culos"))
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
				else if (aux.equalsIgnoreCase("Tiempo Libre"))
				{
					aux = nodo.getTipo().getValorTipoCastellano();
					if (aux.equalsIgnoreCase("Campo de golf"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Campo_Golf.png"));
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
					else if (aux.equalsIgnoreCase("Parque acu�tico"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Parque"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Jard�n"))
					{
						
					}
				} 
				else if (aux.equalsIgnoreCase("Construcci�n"))
				{
					aux = nodo.getTipo().getValorTipoCastellano();
					if (aux.equalsIgnoreCase("Planta e�lica"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Torre_de_electricidad.PNG"));
					}
					else if (aux.equalsIgnoreCase("Planta Hidroel�ctrica"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Central Hidroel�ctrica"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Central nuclear"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Faro"))
					{
						
					}
				} else if (aux.equalsIgnoreCase("Infraestructura"))
				{
					aux = nodo.getTipo().getValorTipoCastellano();
					if (aux.equalsIgnoreCase("Pub"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\puff.png"));
					}
					else if (aux.equalsIgnoreCase("Parking"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Parking.png"));
					}
					else if (aux.equalsIgnoreCase("Gasolinera"))
					{
						buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Gasolinera.png"));
					}
					else if (aux.equalsIgnoreCase("Cabina de telefono"))
					{
						//buffer = ImageIO.read(new File("is\\SimTraffic\\Vista\\Imagenes\\Representacion Avanzada\\Cabinadetelefono.png"));
					}
					else if (aux.equalsIgnoreCase("Aseos Publicos"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Edificio Publico"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Iglesia"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Cementerio"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Oficina de Correos"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Oficina de Correos"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Buzon de Correos"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Colegio"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Supermercado"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Hospital"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Libreria"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Comisaria"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Parque de bomberos"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Terrazas"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Restaurante"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Cadena de comida rapida"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Estacion de autobus"))
					{
						
					}
					else if (aux.equalsIgnoreCase("Teatro"))
					{
						
					}
				}
				g.drawImage(buffer, x_MapaARep(nodo.getPos().getLon())-9, y_MapaARep(nodo.getPos().getLat())-9, null);
			}
		}
		catch (IOException e) {e.printStackTrace();}
	}
	
}
