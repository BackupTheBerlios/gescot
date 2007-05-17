package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Utils.Tiempo;

public class HCrearNuevoMapa implements IHerramienta {

	//Mapa mapa;
	
	public HCrearNuevoMapa(){
		
		
	}
	
	
	public int hacer(IModelo modelo) {		
		//mapa=modelo.getMapa();
		modelo.setMapa(new Mapa());
		return 0;
	}

	public int deshacer(IModelo modelo) {
		//Consideramos que esta herramienta no debe implementar un método deshacer. 
		//Si se considera oportuno incluirlo, descomentar lo comentado.
		//modelo.setMapa(mapa);
		return 0;
	}
	public String toString(){
		return Tiempo.Hora()+Messages.getString("HCrearNuevoMapa.0")+Messages.getString("HCrearNuevoMapa.1"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
