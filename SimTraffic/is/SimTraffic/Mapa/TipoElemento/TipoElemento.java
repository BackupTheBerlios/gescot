package is.SimTraffic.Mapa.TipoElemento;

import java.io.Serializable;

public abstract class TipoElemento implements ITipoElemento, Serializable{

	/**
	 * Categor�a del tipo de elemento (nodo, tramo, v�a)
	 */
	protected String tipo;
	
	/**
	 * Descripci�n cocnreta de la categor�a asignada
	 */
	protected String valorTipo;
	
	/**
	 * Tabla de 2 columnas utilizada para traducir t�rminos relativos a valores de este tipo de elemento: 
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in ingl�s (en formato osm).
	 */
	private String[][] tablaTraduccion;
	
	public TipoElemento(String valorTipo) {
		tablaTraduccion=crearTablaTraduccion();
		String valorOSM =traduciraOSM(valorTipo);
		this.valorTipo = valorOSM;
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in ingl�s (en formato osm).
	 */
	public abstract String[][] crearTablaTraduccion();
	
	/**
	 * M�todo que traduce de las palabras utilizadas por el usuario (castellano) 
	 * a las palabras que utiliza el est�ndar osm (en ingl�s). Si no encuentra la 
	 * traducci�n devuelve la palabra dada.
	 */
	public String traduciraOSM(String vTipo) {
		String traducido=vTipo;
		if (vTipo == null) return "";
		
		if (vTipo.equals("")) return "";
		
		boolean encontrado=false;
		for (int i=0; (i<tablaTraduccion.length) && (!encontrado); i++) {
			if (tablaTraduccion[i][0].equals(vTipo)) {
				traducido=tablaTraduccion[i][1];
				encontrado=true;
			}
		}
		
		return traducido;
	}
	
	/**
	 * M�todo que traduce de las palabras utilizadas por el est�ndar osm (en ingl�s) 
	 * a las palabras que utiliza el usuario (en castellano).
	 */
	public String traduciraCastellano(String vTipo) {
		
		String traducido=vTipo;
		
		if (vTipo.equals("")) return "";
		
		boolean encontrado=false;
		for (int i=0; (i<tablaTraduccion.length) && (!encontrado); i++) {
			if (tablaTraduccion[i][1].equals(vTipo)) {
				traducido=tablaTraduccion[i][0];
				encontrado=true;
			}
		}
		
		return traducido;	
	}
	
	public String[] devolverListaValores() {
		String[] s = new String[tablaTraduccion.length];
		for (int i=0;i<tablaTraduccion.length;i++) {
			s[i] = tablaTraduccion[i][0];
		}
		return s;
	}

	public String getTipo() {
		return tipo;
	}
	
	public String getValorTipo() {
		return valorTipo;
	}
	
	/**
	 * 
	 */
	public void setValorTipo(String valorTipo) {
		this.valorTipo=valorTipo;
	}
	
	public boolean equals(Object objeto)
	{
		if (objeto == null)
			return false;
		if (objeto.getClass() != this.getClass())
			return false;
		TipoElemento tipo = (TipoElemento) objeto;
		if (this.tipo!=tipo.tipo) return false;
		if (this.valorTipo!=tipo.valorTipo) return false;
		return true;
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getValorTipoCastellano() {
		String s=traduciraCastellano(valorTipo);
		return s;
	}

	public abstract String getTipoCastellano();

}
