package is.SimTraffic.Vista.Representaciones;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Señal;
import is.SimTraffic.Mapa.Tramo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class RepresentacionSimple extends Representacion {
	private int tamaño_carril;

	public RepresentacionSimple() {
		super();
		tamaño_carril = 5;
	}
	
	public void pintar(Graphics2D g, Nodo nodo) 
	{	
		g.setColor(Color.DARK_GRAY);
		g.fillOval(recalculaX(nodo.getPos().getPosX()), recalculaY(nodo
				.getPos().getPosY()), 6, 6);
		g.setColor(Color.RED);
		g.drawOval(recalculaX(nodo.getPos().getPosX()), recalculaY(nodo
				.getPos().getPosY()), 6, 6);
	}
	
	public void pintar(Graphics2D g, Tramo tramo) {
		Posicion posnodo1 = tramo.getNodoInicial().getPos();
		Posicion posnodo2 = tramo.getNodoFinal().getPos();
		int carriles_ida = tramo.getNumCarrilesDir1();
		int carriles_vuelta = tramo.getNumCarrilesDir2();
		double largo = posnodo1.getPosX() - posnodo2.getPosX();
		double alto = posnodo1.getPosY() - posnodo2.getPosY();
		double angulo = Math.atan(alto/largo);
		int x[] = {(int) (3 + posnodo1.getPosX() + tamaño_carril * carriles_ida * Math.sin(angulo)),
				   (int) (3 + posnodo2.getPosX() + tamaño_carril * carriles_vuelta * Math.sin(angulo)),
				   (int) (3 + posnodo2.getPosX() + tamaño_carril * carriles_vuelta * (-Math.sin(angulo))),
				   (int) (3 + posnodo1.getPosX() + tamaño_carril * carriles_ida * (-Math.sin(angulo)))};
		int y[] = {(int) (3 + posnodo1.getPosY() + tamaño_carril * carriles_ida * (-Math.cos(angulo))),
				   (int) (3 + posnodo2.getPosY() + tamaño_carril * carriles_vuelta * (-Math.cos(angulo))),
				   (int) (3 + posnodo2.getPosY() + tamaño_carril * carriles_vuelta * Math.cos(angulo)),
				   (int) (3 + posnodo1.getPosY() + tamaño_carril * carriles_ida * Math.cos(angulo))};
		g.setColor(Color.DARK_GRAY);
		g.fillPolygon(x, y, 4);
		g.setColor(Color.WHITE);
		float array[] = {10,10};
		g.drawLine(recalculaX(posnodo1.getPosX() + 3), recalculaY(posnodo1
				.getPosY() + 3), recalculaX(posnodo2.getPosX() + 3),
				recalculaY(posnodo2.getPosY()) + 3);
		for (int i = 0; i < carriles_ida; i++)
		{
			if (i == carriles_ida - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine(recalculaX((int) (3 + posnodo1.getPosX() + tamaño_carril * (i + 1) * Math.sin(angulo))), 
					   recalculaY((int) (3 + posnodo1.getPosY() + tamaño_carril * (i + 1) * (-Math.cos(angulo)))), 
					   recalculaX((int) (3 + posnodo2.getPosX() + tamaño_carril * (i + 1) * Math.sin(angulo))),
					   recalculaY((int) (3 + posnodo2.getPosY() + tamaño_carril * (i + 1) * (-Math.cos(angulo)))));
		}
		for (int i = 0; i < carriles_vuelta; i++)
		{
			if (i == carriles_vuelta - 1)
				g.setStroke(new BasicStroke(1));
			else
				g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, array, 1));
			g.drawLine(recalculaX((int) (3 + posnodo1.getPosX() + tamaño_carril * -(i + 1) * Math.sin(angulo))), 
					   recalculaY((int) (3 + posnodo1.getPosY() + tamaño_carril * -(i + 1) * (-Math.cos(angulo)))), 
					   recalculaX((int) (3 + posnodo2.getPosX() + tamaño_carril * -(i + 1) * Math.sin(angulo))),
					   recalculaY((int) (3 + posnodo2.getPosY() + tamaño_carril * -(i + 1) * (-Math.cos(angulo)))));
		}}

	@Override
	public void pintar(Graphics2D g, Señal señal) {
		// TODO Auto-generated method stub
		
	}

}
