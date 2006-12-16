/**
 * 
 */
package Multithreads.simuladortrafico.is.multithreads;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Multithreads.simuladortrafico.is.multithreads.mapa.*;
import Multithreads.simuladortrafico.is.multithreads.parser.CargadorMapa;

/**
 * @author Grupo IS Tráfico
 * 
 * Clase principal, donde se crean todas las instancias de los objetos y en
 * particular la ventana. Dispone de una clase main, que se ha de ejecutar para
 * ver la simulación.
 * 
 */
public class Principal {

	static Ventana ventana;

	private static List<Nodo> nodos;

	private static List<Tramo> tramos;

	private static List<Vehiculo> vehiculos;

	static boolean conpanel = true;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Object[] options = { "JFrame", "JPanel" };
		int temp = JOptionPane.showOptionDialog(null,
				"Por favor, elija una opción", "Forma de dibujar en pantalla",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		if (temp == 0)
			conpanel = false;
		else
			conpanel = true;

		int n;
		String temp2;
		Integer integer;

		// inicializa los coches a una cantidad.
		int coches;
		try {
			temp2 = JOptionPane.showInputDialog("Ingrese el numero de coches");
			integer = new Integer(temp2);
			coches = integer.intValue();
		} catch (Exception e) {
			coches = 10;
			JOptionPane.showMessageDialog(null,
					"Valor no valido, usando el valor por defecto");
		}

		int largoX = 200;
		int largoY = 160;

		// crea las distintas listas, que almacenarán nodos, tramos y vehiculos.
		nodos = new ArrayList<Nodo>();
		tramos = new ArrayList<Tramo>();
		vehiculos = new ArrayList<Vehiculo>();

		Random gen = new Random();
		Semaforo paso = new Semaforo(false);

		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(ventana);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			// This is where a real application would open the file.
			Mapa mapa = CargadorMapa.cargar(file.getAbsolutePath());
			nodos = mapa.getNodos();
			tramos = mapa.getTramos();
		} else {
			try {
				temp2 = JOptionPane
						.showInputDialog("Ingrese el numero de filas y columnas");
				integer = new Integer(temp2);
				n = integer.intValue();
			} catch (Exception e) {
				n = 4;
				JOptionPane.showMessageDialog(null,
						"Valor no valido, usando el valor por defecto");
			}

			// crea los n*n nodos
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					nodos.add(new Nodo(40 + largoX * i, 40 + largoY * j, paso));
				}
			}

			// crea los tramos que unen los nodos, creando una cuadricula. Los
			// tramos se crean
			// con cualquier número de carriles entre 0 y 4 en cada uno de los
			// sentidos.
			for (int i = 0; i < n - 1; i++) {
				for (int j = 0; j < n; j++) {
					tramos.add(new Tramo(nodos.get(i + n * j), nodos.get(i + 1
							+ n * j), nodos.get(i + n * j).distancia(
							nodos.get(i + 1 + n * j)), gen.nextInt(3) + 1, gen
							.nextInt(3) + 1, true));
					tramos.add(new Tramo(nodos.get(j + n * i), nodos.get(j + n
							* (i + 1)), nodos.get(j + n * i).distancia(
							nodos.get(j + n * (i + 1))), gen.nextInt(3) + 1,
							gen.nextInt(3) + 1, false));
				}
			}
			tramos.add(new Tramo(nodos.get(0), nodos.get(n + 1), nodos.get(1)
					.distancia(nodos.get(n + 1)), 3, 3, true));
			tramos.add(new Tramo(nodos.get(n - 1), nodos.get(2 * n - 2), nodos
					.get(n - 1).distancia(nodos.get(2 * n - 2)), 3, 3, true));
		}

		if (!conpanel) {
			// crea una nueva ventana, que se encargara de aparecer en pantalla.
			ventana = new Ventana();

			// relaciona las listas con la ventana, para que ésta pueda
			// representar los contenidos.
			ventana.setNodos(nodos);
			ventana.setTramos(tramos);
			ventana.setVehiculos(vehiculos);
		} else
			ventana = new Ventana(nodos, tramos, vehiculos);

		// Crea una instancia del controlador, encargado de regular ciertos
		// aspectos de
		// sincronización de los vehiculos
		Controlador control = new Controlador(coches, ventana, paso);

		// crea los coches, que reparte homogéneamente entre los nodos.
		int numero;
		for (int i = 0; i < coches; i++) {
			numero = gen.nextInt(nodos.size());
			vehiculos.add(new Coche(nodos.get(numero), control));
		}

		// crea un nuevo hilo para cada vehiculo
		Iterator<Vehiculo> it = vehiculos.iterator();
		while (it.hasNext()) {
			new Thread(it.next()).start();
		}

	}

}
