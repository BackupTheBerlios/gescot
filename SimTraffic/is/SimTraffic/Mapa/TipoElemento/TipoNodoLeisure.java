package is.SimTraffic.Mapa.TipoElemento;

import is.SimTraffic.Messages;

public class TipoNodoLeisure extends TipoElemento implements ITipoElemento {

	/**
	 * Caracter�sticas de Nodos asociadas a  Recreo (Leisure)
	 */
	//private String tipo="leisure";
	
	/**
	 *  Palabras reservadas para los valores concretos de osm (un nodo asociado a un lugar de recreo)
	 *  sports_centre	Polideportivo
	 golf_course	Campo de golf
	 stadium	Estadio (con gradas)
	 marina	Marina para yates y barcos de recreo
	 track	Pista de carreras (ciclismo, galgos, hip�dromo, etc)
	 pitch	Campo de deportes (campo de f�tbol, cancha de baloncesto, pista de p�del, etc)
	 water_park	Parque acu�tico
	 slipway	Rampa para la botadura de barcos
	 fishing	�rea de pesca
	 nature_reserve	Parque natural, reservas ecol�gicas
	 park	Parque
	 garden	Jard�n
	 common	Plazas
	 User Defined	Definido por el usuario
	 */
	//private String valorTipo;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Tabla de 2 columnas utilizada para traducir t�rminos relativos a valores de este tipo de elemento: 
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in ingl�s (en formato osm).
	 */
	//private String[][] tablaTraduccion;
	
	public TipoNodoLeisure(String vTipo) {
		super(vTipo);
		tipo=Messages.getString("TipoNodoLeisure.0"); //$NON-NLS-1$
		//tablaTraduccion=crearTablaTraduccion();
		//String valorOSM =traduciraOSM(vTipo);
		//this.valorTipo = valorOSM;
	}
	
	/**
	 * Columna 0: Palabras en castellano, Columna 1: Palabras in ingl�s (en formato osm).
	 */
	public String[][] crearTablaTraduccion() {
		String[][] tTraduccion = { {Messages.getString("TipoNodoLeisure.1"),Messages.getString("TipoNodoLeisure.2")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoLeisure.3"),Messages.getString("TipoNodoLeisure.4")},  //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoLeisure.5"),Messages.getString("TipoNodoLeisure.6")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoLeisure.7"),Messages.getString("TipoNodoLeisure.8")},	 //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoLeisure.9"),Messages.getString("TipoNodoLeisure.10")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoLeisure.11"),Messages.getString("TipoNodoLeisure.12")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoLeisure.13"),Messages.getString("TipoNodoLeisure.14")}, //$NON-NLS-1$ //$NON-NLS-2$
								   {Messages.getString("TipoNodoLeisure.15"),Messages.getString("TipoNodoLeisure.16")}, //$NON-NLS-1$ //$NON-NLS-2$
									};
		return tTraduccion;
	}
	/*
	/**
	 * M�todo que traduce de las palabras utilizadas por el usuario (castellano) 
	 * a las palabras que utiliza el est�ndar osm (en ingl�s). Si no encuentra la 
	 * traducci�n devuelve la palabra dada.
	 */
	/*
	public String traduciraOSM(String vTipo) {
		String traducido=vTipo;
		
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
	/*
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

	public String getTipo() {
		return tipo;
	}
	
	public String getValorTipo() {
		return valorTipo;
	}
	
	public void setValorTipo(String valorTipo) {
		this.valorTipo=valorTipo;
	}
	
	public String getValorTipoCastellano() {
		String s=traduciraCastellano(valorTipo);
		return s;
	}
	
	public boolean equals(TipoNodoLeisure tipo)
	{
		if (this.tipo!=tipo.tipo) return false;
		if (this.valorTipo!=tipo.valorTipo) return false;
		return true;
		
	}
	
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

	public String getTipoCastellano() {
		return Messages.getString("TipoNodoLeisure.17"); //$NON-NLS-1$
	}

}
