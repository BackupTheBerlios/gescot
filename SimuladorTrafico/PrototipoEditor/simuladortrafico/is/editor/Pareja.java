package PrototipoEditor.simuladortrafico.is.editor;

public class Pareja {
	Nodo nodo1;
	Nodo nodo2;
	
	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pareja(){

	}
	
	public Pareja(Nodo nodo1, Nodo nodo2){
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
	}
	
	public Nodo getComienzo() {
		return nodo1;
	}
	
	public Nodo getFin() {
		return nodo2;
	}
}
