	package is.SimTraffic.Vista.Acciones;

	import is.SimTraffic.IControlador;
	import is.SimTraffic.Herramientas.HPausar;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	/**
	 * 
	 * @author Grupo ISTrafico
	 * 
	 */
	public class AccionPausarSimulacion implements ActionListener {

		/**
		 * Interfaz del controlador en el MVC
		 */
		private IControlador controlador;

		
		public AccionPausarSimulacion(IControlador controlador) {
			super();
			this.controlador = controlador;
		}

		public void actionPerformed(ActionEvent arg0) {
			controlador.herramienta(new HPausar());
		}

	}
