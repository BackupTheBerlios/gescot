package is.SimTraffic.Herramientas;

import is.SimTraffic.*;
import is.SimTraffic.Mapa.*;

public class HEliminarNodo implements IHerramienta 
{
	private Nodo nodo;
   
   /**
   @roseuid 45C3154D02B7
    */
   public HEliminarNodo() 
   {
    
   }
   
   public HEliminarNodo(Nodo nodo){
	   this.nodo = nodo;
   }

   
   
   /**
   @param modelo
   @return int
   @roseuid 45C315C50259
    */
   public int hacer(IModelo modelo) 
   {
	   modelo.getMapa().eliminar(nodo);
	   return 0;
   }
   
   /**
   @param modelo
   @return int
   @roseuid 45C315C50288
    */
   public int deshacer(IModelo modelo) 
   {
	   modelo.getMapa().insertar(nodo);
	   return 0;
   }
}
