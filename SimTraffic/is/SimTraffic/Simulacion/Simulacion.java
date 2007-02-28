package is.SimTraffic.Simulacion;

import java.util.List;
import java.util.ArrayList;

public class Simulacion 
{
   private List<IVehiculo> vehiculos;
   private Parametros theParametros;
   
   /**
   @roseuid 45B8B3A7025D
    */
   public Simulacion() 
   {
	   vehiculos = new ArrayList<IVehiculo>();
   }
   
   /**
   @return Parametros
   @roseuid 45B8AAB50192
    */
   public Parametros getParametros() 
   {
    return null;
   }
   
   /**
   @param parametros
   @return int
   @roseuid 45B8AAD3022E
    */
   public int modificaParametros(Parametros parametros) 
   {
    return 0;
   }
   
   /**
   @return int
   @roseuid 45B8AAEE029B
    */
   public int comenzar() 
   {
    return 0;
   }
   
   /**
   @return int
   @roseuid 45B8AAFC0328
    */
   public int detener() 
   {
    return 0;
   }
   
   /**
   @return int
   @roseuid 45B8AB0401D0
    */
   public int pausar() 
   {
    return 0;
   }
   
   public List<IVehiculo> getVehiculos() {
	   return vehiculos;
   }
}
