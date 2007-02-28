package is.SimTraffic.Simulacion;

import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;

import java.awt.Dimension;

public class Camion implements IVehiculo 
{
   private int velocidadActual;
   private int velocidadMax;
   private float aceleracionActual;
   private float aceleracionMax;
   private float desaceleracion;
   private int carril;
   private int distanciaSeguridad;
   
   /**
   @roseuid 45B8B3A8000B
    */
   public Camion() 
   {
    
   }
   
   /**
   @return Posicion
   @roseuid 45C1E574030C
    */
   public Posicion getPosicion() 
   {
    return null;
   }
   
   /**
   @return Tramo
   @roseuid 45C1E574033B
    */
   public Tramo getTramo() 
   {
    return null;
   }
   
   /**
   @return awt.Dimension
   @roseuid 45C1E574036A
    */
   public Dimension getDimensiones() 
   {
    return null;
   }
   
   /**
   @return int
   @roseuid 45C1E5740399
    */
   public int getVelActual() 
   {
    return 0;
   }
   
   /**
   @return float
   @roseuid 45C1E57403B8
    */
   public float getAceleracionActual() 
   {
    return 0;
   }
   
   /**
   @return float
   @roseuid 45C1E57403E7
    */
   public float getDesaceleracion() 
   {
    return 0;
   }
   
   /**
   @return int
   @roseuid 45C1E575001E
    */
   public int getDistanciaSeguridad() 
   {
    return 0;
   }
   
   /**
   @return float
   @roseuid 45C1E575004D
    */
   public float getAceleracionMax() 
   {
    return 0;
   }
   
   /**
   @return int
   @roseuid 45C1E575007C
    */
   public int getCarril() 
   {
    return 0;
   }
}
