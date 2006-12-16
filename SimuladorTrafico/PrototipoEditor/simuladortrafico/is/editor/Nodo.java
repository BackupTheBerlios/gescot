package PrototipoEditor.simuladortrafico.is.editor;

public class Nodo {
	//Indican la posición 'x' e 'y' del nodo, recordando que 'y' crece "hacia abajo"
	int x, y;
	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nodo(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void modificaCoord(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getPosX () {
		return x;
	}
	
	public int getPosY () {
		return y;
	}
}
