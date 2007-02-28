package is.SimTraffic.Mapa;


public class Tramo 
{
   private float velocidadMax;
   private int numCarrilesDir1;
   private int numCarrilesDir2;
   private int tipo;
   private Nodo nodoInicial;
   private Nodo nodoFinal;
   
   /**
    * Constructor de la clase Tramo con nodos inicial y final.<p>
    * Este constructuro crea un nuevo tramo a partir de dos nodos, y le da el
    * resto de los valores por defecto. Esto será, un carril en cada sentido,
    * una velocidad máxima de 40 y un tipo 0.
   @roseuid 45B8B3A800B7
    */
   public Tramo(Nodo nodoInicial, Nodo nodoFinal) 
   {
	   this.nodoInicial = nodoInicial;
	   this.nodoFinal = nodoFinal;
	   numCarrilesDir1 = 1;
	   numCarrilesDir2 = 1;
	   velocidadMax = 40;
	   tipo = 0; //???
   }
   
   /**
   @return float
   @roseuid 45B8AD5501C1
    */
   public float getVelMax() 
   {
    return velocidadMax;
   }
   
   /**
   @return int
   @roseuid 45B8AD6403B5
    */
   public int getNumCarrilesDir1() 
   {
    return numCarrilesDir1;
   }
   
   /**
   @return int
   @roseuid 45B8AD8B0338
    */
   public int getNumCarrilesDir2() 
   {
    return numCarrilesDir2;
   }
   
   /**
   @return int
   @roseuid 45B8AD9A0309
    */
   public int getTipo() 
   {
    return tipo;
   }
   
   public boolean tieneNodo(Nodo nodo) {
	   if (nodo == nodoInicial)
		   return true;
	   if (nodo == nodoFinal)
		   return true;
	   return false;
   }
   
   public boolean equals(Object objeto) {
	   if (objeto == null) return false;
	   if (objeto.getClass() != this.getClass()) return false;
	   Tramo nodo = (Tramo) objeto;
	   if (nodo.nodoInicial != this.nodoInicial) return false;
	   if (nodo.nodoFinal != this.nodoFinal) return false;
	   return true;
   }
}
