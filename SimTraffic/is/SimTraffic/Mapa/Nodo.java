package is.SimTraffic.Mapa;

import java.util.ArrayList;

public class Nodo 
{
   private float entrada;
   private float salida;
   private boolean parada;
   public Señal theSeñal;
   private Posicion posicion;
   public Tramo theTramo[];
   
   /**
   @roseuid 45B8B3A80192
    */
   public Nodo() 
   {
    
   }
   
   /**
   @return Posicion
   @roseuid 45B8ABD203D4
    */
   public Posicion getPos() 
   {
    return null;
   }
   
   /**
   @return ArrayList
   @roseuid 45B8AC4903C4
    */
   public ArrayList getTramos() 
   {
    return null;
   }
   
   /**
   @return float
   @roseuid 45B8AC990105
    */
   public float getEntrada() 
   {
    return 0;
   }
   
   /**
   @return float
   @roseuid 45B8ACB301F0
    */
   public float getSalida() 
   {
    return 0;
   }
   
   /**
   @return boolean
   @roseuid 45B8ACBE0105
    */
   public boolean getParada() 
   {
    return true;
   }
   
   public boolean equals(Object objeto) {
	   if (objeto == null) return false;
	   if (objeto.getClass() != this.getClass()) return false;
	   Nodo nodo = (Nodo) objeto;
	   if (nodo.posicion != this.posicion) return false;
	   return true;
   }
}
