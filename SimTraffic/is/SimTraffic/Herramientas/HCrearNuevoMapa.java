package is.SimTraffic.Herramientas;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Mapa;

public class HCrearNuevoMapa implements IHerramienta {

	//Mapa mapa;
	
	public HCrearNuevoMapa(){
		
		
	}
	
	
	public int hacer(IModelo modelo) {
		// TODO Auto-generated method stub
		//mapa=modelo.getMapa();
		modelo.setMapa(new Mapa());
		return 0;
	}

	public int deshacer(IModelo modelo) {
		//Consideramos que esta herramienta no debe implementar un método deshacer. 
		//Si se considera oportuno incluirlo, descomentar lo comentado.
		//modelo.setMapa(mapa);
		// TODO Auto-generated method stub
		return 0;
	}

}
