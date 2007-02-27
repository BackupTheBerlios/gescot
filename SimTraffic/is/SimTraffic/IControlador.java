package is.SimTraffic;


public interface IControlador 
{
   
   /**
   @roseuid 45B8A7B103D4
    */
   public void crear();
   
   /**
   @param herramienta
   @return int
   @roseuid 45B8A7B80386
    */
   public int herramienta(IHerramienta herramienta);
   
   /**
   @param tipoError
   @return String
   @roseuid 45B8A814000B
    */
   public String obtenerMensajeError(int tipoError);
   
   /**
   @param ayuda
   @return String
   @roseuid 45B8A85C03B5
    */
   public String obtenerVinculoAyuda(int ayuda);
}
