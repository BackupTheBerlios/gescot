package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;

public interface IHerramienta 
{
   
   /**
   @param modelo
   @return int
   @roseuid 45B8B01202CA
    */
   public int hacer(IModelo modelo);
   
   /**
   @param modelo
   @return int
   @roseuid 45B8B01B024D
    */
   public int deshacer(IModelo modelo);
}
