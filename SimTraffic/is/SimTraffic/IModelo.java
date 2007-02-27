package is.SimTraffic;

import java.util.ArrayList;

public interface IModelo 
{
   
   /**
   @return Mapa
   @roseuid 45B8A8B60376
    */
   public Mapa getMapa();
   
   /**
   @return java.util.ArrayList
   @roseuid 45B8A8C60357
    */
   public ArrayList getVehiculos();
   
   /**
   @return Simulacion
   @roseuid 45B8A94C01C1
    */
   public Simulacion getSimulacion();
   
   /**
   @param mapa
   @roseuid 45B8A95A0347
    */
   public void setMapa(Mapa mapa);
}
