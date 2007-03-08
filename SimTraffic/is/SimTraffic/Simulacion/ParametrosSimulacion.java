package is.SimTraffic.Simulacion;

import java.util.ArrayList;

public class ParametrosSimulacion 
{
   private int clima;
   private int numVehiculos;
   private float hora;
   private int estadoCoches;
   /**
    * porcentaje de coches que hay de cada tipo en la simulacion actual
    */
   private ArrayList porcentajeTipo;
   
   /**
   @roseuid 45B8B3A70347
    */
   public ParametrosSimulacion() 
   {
    
   }
   
   /**
   @return int
   @roseuid 45B8AE8B00D6
    */
   public int getClima() 
   {
    return this.clima;
   }
   
   /**
   @return int
   @roseuid 45B8AE94001B
    */
   public int getNumVehiculos() 
   {
    return this.numVehiculos;
   }
   
   /**
   @return float
   @roseuid 45B8AE9C03A5
    */
   public float getHora() 
   {
    return this.hora;
   }
   
   /**
   @return int
   @roseuid 45B8AEA80079
    */
   public int getEstadoCoches() 
   {
    return this.estadoCoches;
   }
   
   /**
   @return java.util.ArrayList
   @roseuid 45B8AEB203D4
    */
   public ArrayList getPorcentajeTipo() 
   {
    return this.porcentajeTipo;
   }
}
