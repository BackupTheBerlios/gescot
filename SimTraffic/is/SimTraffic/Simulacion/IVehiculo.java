package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;


import java.awt.Dimension;

public interface IVehiculo 
{

	/**Estos metodos se incluyen porque son necesarios para relacionar
	los vehiculos con los nodos de entrada salida y poder mantener el numero
	 de coches que salen de un nodo acorde a las especificaciones*/
   public void setNodoOrigen(Nodo nodo);
   public Nodo getNodoOrigen();
   public void setNodoDestino(Nodo nodo);
   public Nodo getNodoDestino();
   
   /**
   @return Posicion
   @roseuid 45B8AF52028C
    */
   public Posicion getPosicion();
   
   /**
   @return Tramo
   @roseuid 45B8AF5C0059
    */
   public Tramo getTramo();
   
   /**
   @return awt.Dimension
   @roseuid 45B8AF6103A5
    */
   public Dimension getDimensiones();
   
   /**
   @return int
   @roseuid 45B8AF7600C7
    */
   public int getVelActual();
   
   /**
   @return float
   @roseuid 45B8AF8103E4
    */
   public float getAceleracionActual();
   
   /**
   @return float
   @roseuid 45B8AF9B022E
    */
   public float getDesaceleracion();
   
   /**
   @return int
   @roseuid 45B8AFA80069
    */
   public int getDistanciaSeguridad();
   
   /**
   @return float
   @roseuid 45B8AFB20079
    */
   public float getAceleracionMax();
   
   /**
   @return int
   @roseuid 45B8AFBB01A1
    */
   public int getCarril();
}
