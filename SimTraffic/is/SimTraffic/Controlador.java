package is.SimTraffic;

import is.SimTraffic.Herramientas.IHerramienta;
import is.SimTraffic.Vista.IVista;


public class Controlador implements IControlador 
{
   public IVista theIVista;
   public IModelo theIModelo;
   
   /**
   @roseuid 45B8B3A90134
    */
   public Controlador() 
   {
    
   }
   
   /**
   @roseuid 45C1E08103C8
    */
   public void crear() 
   {
    
   }
   
   /**
   @param herramienta
   @return int
   @roseuid 45C1E08103E7
    */
   public int herramienta(IHerramienta herramienta) 
   {
    return 0;
   }
   
   /**
   @param tipoError
   @return String
   @roseuid 45C1E082004D
    */
   public String obtenerMensajeError(int tipoError) 
   {
    return null;
   }
   
   /**
   @param ayuda
   @return String
   @roseuid 45C1E082009B
    */
   public String obtenerVinculoAyuda(int ayuda) 
   {
    return null;
   }
}
