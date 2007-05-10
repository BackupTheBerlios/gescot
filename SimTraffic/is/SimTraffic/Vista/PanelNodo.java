package is.SimTraffic.Vista;

import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Se�ales.Semaforo;
import is.SimTraffic.Mapa.TipoElemento.TipoElemento;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;
import is.SimTraffic.Vista.Acciones.PanelNodo.*;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaCambioSpinner;
import is.SimTraffic.Vista.Representaciones.Representacion;
import is.SimTraffic.Vista.VentanaMatrizPaso.OyenteVentanaMatrizDePaso;
import is.SimTraffic.Vista.VentanaMatrizPaso.VentanaMatrizDePaso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class PanelNodo extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTabbedPane panelDatos;

	private JPanel panelBotones;

	private JPanel panelAuxiliar;

	private JComboBox comboTipoSe�ales;

	private JComboBox comboTipoSemaforos;

	private JTextField campoTiempoCicloSemaforo;

	private JComboBox comboTipo;

	private JComboBox comboValor;

	private EntradaSalida es;

	private JTextField[] entrada = new JTextField[3];

	private JTextField[] salida = new JTextField[3];

	private JTextField campoNombre;

	private JButton botonAceptar;

	private JButton botonCancelar;

	private JPanel panelPropiedades = new JPanel();

	private JPanel panelSe�ales = new JPanel();

	private JPanel panelTramos = new JPanel();
	
	private JPanel panelSemaforos = new JPanel();

	private Nodo nodo;

	private PanelMapa mapa;
	
	/**
	 * Para introducir el tiempo inicial de los nuevos intervalos del sem�foro. 
	 */
	private JSpinner tiempoInicialSemaforo;

	/**
	 * Para introducir el tiempo final de los nuevos intervalos del sem�foro. 
	 */
	private JSpinner tiempoFinalSemaforo;

	/**
	 * Para elegir el nuevo valor del tiempo total del sem�foro. 
	 */
	private JComboBox electorTiempoTotal;
	
	/**
	 * Lista que permitir� acceder a cada componente JSpinner de tiempo final de los intervalos.
	 */	
	private List<JSpinner> listaTiempoInicialIntervalo;
	
	/**
	 * Lista que permitir� acceder a cada componente JSpinner de tiempo final de los intervalos.
	 */
	private List<JSpinner> listaTiempoFinalIntervalo;
	
	/**
	 * Ventana Auxiliar que permitir� modificar los valores de interconexi�n entre los tramos.
	 */
	private VentanaMatrizDePaso ventanaMatriz;
	
	/**
	 * Lista que almacenar� todos los botones que aparecen 
	 */
	private List<JButton> listaBotonesAplicarIntervalo;
	
	public PanelNodo(Nodo nodo) {

	}

	public PanelNodo(Nodo nodo, PanelMapa mapa) {
		this.setLayout(new BorderLayout(2, 2));
		this.nodo = nodo;
		this.mapa = mapa;

		panelDatos = new JTabbedPane();
			
		creaPanelDatos();

		panelBotones = new JPanel();
		creaPanelBotones();

		crearAcciones();
		this.add(panelDatos, BorderLayout.NORTH);
		this.add(panelBotones, BorderLayout.SOUTH);
	}

	/**
	 * Dise�o de cuadro de dialogo emergente de propiedades de nodo TODO
	 * terminar el dise�o de las pesta�as
	 */
	public void creaPanelDatos() {
		creaPanelTipo();
		//creaPanelSe�ales();
		creaPanelSemaforos();
		// creaPanelTramos();
	}

	/**
	 * Esto correponde a la pesta�a de las propiedades de los tramos de un nodo
	 * @deprecated  De momento no se usa y se ha deshabilitado.
	 */
	public void creaPanelTramos() {
		panelDatos.addTab("Tramos", null, panelTramos,
				"Tramos asociados al Nodo");
	}

	
	/**
	 * Hace que la pesta�a que aparece resaltada por defecto sea la de los sem�foros. 
	 */
	public void daFocoAPanelSemaforo(){
		panelDatos.setSelectedIndex(1);
	}
	
	public void creaPanelSemaforos(){
		panelSemaforos.removeAll();
		panelDatos.addTab("Sem�foros", null, panelSemaforos,"Sem�foros del tramo");
		
		//Si no se ha insertado todav�a un sem�foro.
		if (nodo.getSe�al() == null){
			JLabel etiquetaNuevo = new JLabel("No existe un sem�foro en el nodo");
			JButton botonCrearSemaforo = new JButton("Crear Sem�foro");
			AccionCrearSemaforo oyenteBotonSemaforo = new AccionCrearSemaforo(nodo,this);
			botonCrearSemaforo.addActionListener(oyenteBotonSemaforo);
			panelSemaforos.add(etiquetaNuevo);
			panelSemaforos.add(botonCrearSemaforo);
		//Si ya existe un sem�foro en el nodo
		} else if(nodo.getSe�al().getNombre().equals("Semaforo")){
			JPanel panelInterior = new JPanel();
			String[] valoresTiempoTotal = {"30","60","120","240"};
			electorTiempoTotal = new JComboBox(valoresTiempoTotal);
			electorTiempoTotal.setSelectedItem((String.valueOf(((Semaforo)nodo.getSe�al()).getTiempoTotal())));
			
			JLabel etiqElector = new JLabel("Elija el tiempo total de ciclo del sem�foro");
			JButton botonAplicar = new JButton("Aplicar Cambios");
			botonAplicar.setToolTipText("�Aviso! Se borrar�n todos los intervalos creados");
			AccionCambiarTiempoTotalSem oyenteBotonAplicar = new AccionCambiarTiempoTotalSem(nodo,this);
			botonAplicar.addActionListener(oyenteBotonAplicar);
			panelSemaforos.add(etiqElector);
			panelSemaforos.add(electorTiempoTotal);
			panelSemaforos.add(botonAplicar);
			
			JScrollPane panelScroll = new JScrollPane(panelInterior);
			panelScroll.setPreferredSize(new Dimension(450,250));
			panelSemaforos.add(panelScroll);
			int valorScrollVertical = ((Semaforo)nodo.getSe�al()).getListaIntervalos().size()*70;
			panelInterior.setPreferredSize(new Dimension(430,valorScrollVertical));
			creaPanelesIntervalos(panelInterior);
			
			JButton a�adirIntervalo = new JButton("Haga Click para a�adir intervalo");
			panelSemaforos.add(a�adirIntervalo);
			JLabel etiqDe = new JLabel("De");
			panelSemaforos.add(etiqDe);
			tiempoInicialSemaforo = new JSpinner(new SpinnerNumberModel(0, 0,((Semaforo)nodo.getSe�al()).getTiempoTotal(), 5));
			panelSemaforos.add(tiempoInicialSemaforo);
			JLabel etiqA = new JLabel("a");
			panelSemaforos.add(etiqA);
			tiempoFinalSemaforo = new JSpinner(new SpinnerNumberModel(((Semaforo)nodo.getSe�al()).getTiempoTotal(), 0,((Semaforo)nodo.getSe�al()).getTiempoTotal(), 5));
			panelSemaforos.add(tiempoFinalSemaforo);
			AccionInsertarIntervalo oyenteBotonA�adirIntervalo = new AccionInsertarIntervalo(nodo,this);
			a�adirIntervalo.addActionListener(oyenteBotonA�adirIntervalo);	
		}		
	}
	
	public int dameTiempoInicialSemaforo(){
		return (Integer)this.tiempoInicialSemaforo.getValue();
	}

	public int dameTiempoFinalSemaforo(){
		return (Integer)this.tiempoFinalSemaforo.getValue();
	}
	
	public int dameTiempoInicialIntervalo(int i){
		int valorSpinner = (Integer)listaTiempoInicialIntervalo.get(i).getValue();
		return valorSpinner;
	}
	
	public int dameTiempoFinalIntervalo(int i){
		int valorSpinner = (Integer)listaTiempoFinalIntervalo.get(i).getValue();
		return valorSpinner;
	}
	
	public int dameTiempoTotal(){
		int nuevoTiempoTotal = Integer.parseInt((String)this.electorTiempoTotal.getSelectedItem());
		return nuevoTiempoTotal;
	}
	
	private void creaPanelesIntervalos(JPanel panelInterno){
		panelInterno.removeAll();
		
		this.listaBotonesAplicarIntervalo = new ArrayList();
		
		listaTiempoInicialIntervalo = new ArrayList<JSpinner>();
		listaTiempoFinalIntervalo = new ArrayList<JSpinner>();
		
		for (int i=0;i<((Semaforo)nodo.getSe�al()).getListaIntervalos().size();i++) {
			JLabel etiqDe = new JLabel("De");
			String valor1 = String.valueOf(((Semaforo)nodo.getSe�al()).getListaIntervalos().get(i).getTiempoInicial());
			String valor2 = String.valueOf(((Semaforo)nodo.getSe�al()).getListaIntervalos().get(i).getTiempoFinal());
			JSpinner tiempoDe = new JSpinner(new SpinnerNumberModel(Integer.parseInt(valor1), 0, Integer.parseInt(valor2), 5));
			EscuchaCambioSpinner oyenteCambioSpinner = new EscuchaCambioSpinner(i,this);
			tiempoDe.addChangeListener(oyenteCambioSpinner);
			listaTiempoInicialIntervalo.add(tiempoDe);
			JLabel etiqA = new JLabel("a");
			JSpinner tiempoA = new JSpinner(new SpinnerNumberModel(Integer.parseInt(valor2), 0,((Semaforo)nodo.getSe�al()).getTiempoTotal(), 5));
			tiempoA.addChangeListener(oyenteCambioSpinner);
			listaTiempoFinalIntervalo.add(tiempoA);
			JButton mostrarM = new JButton("MostrarMatriz");
			AccionAbrirMatrizDePaso oyenteBotonMatriz = new AccionAbrirMatrizDePaso(this);
			JButton aplicarC = new JButton("AplicarCambios");
			aplicarC.setEnabled(false);
			listaBotonesAplicarIntervalo.add(aplicarC);
			AccionModificarIntervalo oyenteBotonAplicar = new AccionModificarIntervalo(nodo,this);
			//Asignamos un nombre al bot�n para poder diferenciar en el oyente que intervalo estamos modificando.
			mostrarM.addActionListener(oyenteBotonMatriz);
			mostrarM.setName(String.valueOf(i));
			aplicarC.addActionListener(oyenteBotonAplicar);
			aplicarC.setName(String.valueOf(i));
			JPanel intervaloI = new JPanel();
			intervaloI.add(etiqDe);
			intervaloI.add(tiempoDe);
			intervaloI.add(etiqA);
			intervaloI.add(tiempoA);
			intervaloI.add(mostrarM);
			intervaloI.add(aplicarC);
			intervaloI.setBorder(BorderFactory.createTitledBorder("Intervalo " + i));
			panelInterno.add(intervaloI);
		}
		
	}
	
	public void abrirVentanaMatrizDePaso(int i){
		this.ventanaMatriz = new VentanaMatrizDePaso(nodo,this,i);
		ventanaMatriz.validate();
		OyenteVentanaMatrizDePaso oyenteMatrizPaso = new OyenteVentanaMatrizDePaso(this);
		this.ventanaMatriz.addWindowListener(oyenteMatrizPaso);
		this.setVisible(false);
	}
	
	/**
	 * Esto correponde a la pesta�a de las propiedades de las se�ales de un nodo
	 * falta mucho por hacer TODO casi todo
	 */
	public void creaPanelSe�ales() {

		panelSe�ales.setLayout(new BorderLayout());
		JPanel panelTipoSe�al = new JPanel();
		JPanel panelDetallesSe�al = new JPanel();
		this.panelSe�ales.add(panelTipoSe�al, BorderLayout.NORTH);
		this.panelSe�ales.add(panelDetallesSe�al, BorderLayout.CENTER);

		panelTipoSe�al.setLayout(new FlowLayout());
		panelDetallesSe�al.setLayout(new FlowLayout());

		panelTipoSe�al.setBorder(BorderFactory
				.createTitledBorder("Tipo de Se�al"));
		panelDetallesSe�al.setBorder(BorderFactory
				.createTitledBorder("Detalles"));

		JLabel etiquetaTipoSe�al = new JLabel("Tipo");
		String[] tiposSe�ales = { "                  ", "STOP", "Ceda el Paso",
				"Sem�foros" };
		comboTipoSe�ales = new JComboBox(tiposSe�ales);

		panelTipoSe�al.add(etiquetaTipoSe�al);
		panelTipoSe�al.add(comboTipoSe�ales);

		JLabel etiquetaDetallesSe�al1 = new JLabel("Tipo de Semaforo");
		String[] tiposSemaforos = { "                  ", "Circular",
				"Perpendicular" };
		comboTipoSemaforos = new JComboBox();
		comboTipoSemaforos = new JComboBox(tiposSemaforos);
		JLabel etiquetaDetallesSe�al2 = new JLabel("Tiempo de ciclo (segundos)");
		campoTiempoCicloSemaforo = new JTextField("");
		campoTiempoCicloSemaforo.setPreferredSize(new Dimension(30, 20));

		panelDetallesSe�al.add(etiquetaDetallesSe�al1);
		panelDetallesSe�al.add(comboTipoSemaforos);
		panelDetallesSe�al.add(etiquetaDetallesSe�al2);
		panelDetallesSe�al.add(campoTiempoCicloSemaforo);

		panelDatos.addTab("Se�ales", null, panelSe�ales,
				"Se�ales asociadas al Nodo");

		// TODO Cargar los datos del nodo al formulario

	}

	/**
	 * 
	 * 
	 */
	public void creaPanelTipo() {

		JPanel panelTipo = new JPanel();
		panelTipo.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		JLabel etiquetaTipo = new JLabel("Tipo");

		// Modificando
		String[] tiposNodos = { "(no definido)", "Carretera",
				"Tiempo Libre", "Construcci�n", "Infraestructura"};

		comboTipo = new JComboBox(tiposNodos);

		JLabel etiquetaValor = new JLabel("Valor");

		String[] valorNodos = { "                  " };

		comboValor = new JComboBox(valorNodos);

		// Por depurar
		boolean encontrado = false;
		if (nodo.getTipo() != null) {
			for (int i = 0; (i < comboTipo.getItemCount()) && (!encontrado); i++) {
				if (comboTipo.getItemAt(i).equals(
						nodo.getTipo().getTipoCastellano())) {
					// comboTipo.setSelectedItem(i);
					comboTipo.setSelectedItem(nodo.getTipo()
							.getTipoCastellano());
					configurarValoresNodo();
					encontrado = true;
					for (int j = 0; j < comboValor.getItemCount(); j++) {
						if (comboValor.getItemAt(j).equals(
								nodo.getTipo().getValorTipoCastellano()))
							comboValor.setSelectedItem(nodo.getTipo()
									.getValorTipoCastellano());
					}
				}
			}
		}

		panelTipo.add(etiquetaTipo);
		panelTipo.add(comboTipo);
		panelTipo.add(etiquetaValor);
		panelTipo.add(comboValor);
		panelTipo.setBorder(BorderFactory.createTitledBorder("Tipo de Nodo"));

		JPanel panelEntrada = crearSeccionEntradaSalida();

		panelAuxiliar = new JPanel();
		panelAuxiliar.setLayout(new BorderLayout());
		JPanel panelNombre = new JPanel();
		panelNombre.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		JLabel etiquetaNombre = new JLabel("Nombre");
		campoNombre = new JTextField(14);
		if (nodo.getNombre() != null)
			campoNombre.setText(nodo.getNombre());
		panelNombre.add(etiquetaNombre);
		panelNombre.add(campoNombre);
		JPanel panelPosicion = new JPanel();
		panelPosicion.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel etiquetaPosicion = new JLabel("Posici�n=  ");
		JLabel etiquetaposicionX = new JLabel("Lat:");
		JLabel etiquetaposicionY = new JLabel("Lon:");
		JLabel posicionX = new JLabel(("" + Representacion.pasarAGrados(nodo
				.getPos().getLat())));
		JLabel posicionY = new JLabel(("" + Representacion.pasarAGrados(nodo
				.getPos().getLon())));
		panelPosicion.add(etiquetaPosicion);
		panelPosicion.add(etiquetaposicionX);
		panelPosicion.add(posicionX);
		panelPosicion.add(etiquetaposicionY);
		panelPosicion.add(posicionY);

		panelAuxiliar.add(panelNombre, BorderLayout.NORTH);
		panelAuxiliar.add(panelPosicion, BorderLayout.SOUTH);
		panelAuxiliar.setBorder(BorderFactory
				.createTitledBorder("Informaci�n del Nodo"));

		panelPropiedades.setLayout(new BorderLayout(10, 20));
		panelPropiedades.add(panelTipo, BorderLayout.NORTH);
		panelPropiedades.add(panelEntrada, BorderLayout.CENTER);
		panelPropiedades.add(panelAuxiliar, BorderLayout.SOUTH);

		panelDatos.addTab("Propiedades", null, panelPropiedades,
				"Propiedades del Nodo");
		panelDatos.setSelectedIndex(0);

	}

	/**
	 * 
	 * 
	 */
	public void crearAcciones() {
		ActionListener accionSeleccionarTipo = new AccionSeleccionarTipo(
				comboTipo, comboValor);
		comboTipo.addActionListener(accionSeleccionarTipo);

		// Oyentes para los botones aceptar y cancelar
		ActionListener accionAceptar = new AccionAceptar(nodo, comboTipo,
				comboValor, es, campoNombre, this, comboTipoSemaforos,
				campoTiempoCicloSemaforo, comboTipoSe�ales, mapa);
		botonAceptar.addActionListener(accionAceptar);
		final PanelNodo panelPpal = this;
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.dispose();
			}
		});
	}

	/**
	 * 
	 * 
	 */
	public void creaPanelBotones() {

		botonAceptar = new JButton("Aceptar");
		botonCancelar = new JButton("Cancelar");

		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 28));

		panelBotones.add(botonAceptar);
		panelBotones.add(botonCancelar);

		panelBotones.setBorder(BorderFactory.createEtchedBorder());
	}

	/**
	 * Redundante (m�todo que ya exist�a en un oyente peor necesario aqu�,
	 * redise�ar m�s adelante).
	 */
	public void configurarValoresSe�al() {
		if (comboTipoSe�ales.getSelectedItem().equals("Semaforo")) {
			String[] tiposSemaforos = { "                  ", "Circular",
					"Perpendicular (�?)" };
			JComboBox comboTipoSemaforos = new JComboBox(tiposSemaforos);
			panelSe�ales.add(comboTipoSemaforos);
		} else {
			comboValor.removeAllItems();
			comboValor.addItem("No definido");
		}
	}

	/**
	 * 
	 * 
	 */
	public void configurarValoresNodo() {
		if (comboTipo.getSelectedItem().equals("Carretera")) {
			String[] s1 = { "Mini-rotonda", "Stop", "Cruce",
					"Port�n para veh�culos", "Cambio De Rasante", "Puente",
					"Viaducto" };
			comboValor.removeAllItems();
			for (int i = 0; i < s1.length; i++)
				comboValor.addItem(s1[i]);
		}

		else if (comboTipo.getSelectedItem().equals("Tiempo Libre")) {
			String[] s2 = { "Campo de golf", "Estadio", "Marina",
					"Pista de carreras", "Campo de deporte", "Parque acu�tico",
					"Parque", "Jard�n" };
			comboValor.removeAllItems();
			for (int i = 0; i < s2.length; i++)
				comboValor.addItem(s2[i]);
		}

		else if (comboTipo.getSelectedItem().equals("Construcci�n")) {
			String[] s3 = { "Planta e�lica", "Planta Hidroel�ctrica",
					"Central Hidroel�ctrica", "Central nuclear", "Faro" };
			comboValor.removeAllItems();
			for (int i = 0; i < s3.length; i++)
				comboValor.addItem(s3[i]);
		}

		else if (comboTipo.getSelectedItem().equals("Infraestructura")) {
			TipoElemento inf = new TipoNodoAmenity("Pub");
			String[] s4 = inf.devolverListaValores();
			comboValor.removeAllItems();
			for (int i = 0; i < s4.length; i++)
				comboValor.addItem(s4[i]);
		}

		else {
			comboValor.removeAllItems();
			comboValor.addItem("No definido");
		}
	}

	private JPanel crearSeccionEntradaSalida() {
		JPanel panelEntrada = new JPanel();
		panelEntrada.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 13));

		panel.add(new JLabel("Int\\Hr"));
		panel.add(new JLabel("Ma�ana"));
		panel.add(new JLabel("Tarde"));
		panel.add(new JLabel("Noche"));

		es = nodo.getEs();

		panel.add(new JLabel("Entran"));
		for (int i = 0; i < 3; i++) {
			entrada[i] = new JTextField(3);
			if (es != null)
				entrada[i].setText("" + es.getPorcentajesEntrada()[i]);
			panel.add(entrada[i]);
		}

		panel.add(new JLabel("Salen"));
		for (int i = 0; i < 3; i++) {
			salida[i] = new JTextField(3);
			if (es != null)
				salida[i].setText("" + es.getPorcentajesSalida()[i]);
			panel.add(salida[i]);
		}

		// JPanel panel2 = new JPanel();
		// panel2.setLayout(new FlowLayout());
		// panel2.add(new JLabel("Estos valores indican cuantos coches entran
		// por este"));

		// panelEntrada.add(panel2);
		panelEntrada.add(panel, BorderLayout.CENTER);
		panelEntrada.setBorder(BorderFactory
				.createTitledBorder("Entradas y salidas por el nodo"));

		return panelEntrada;
	}

	/**
	 * M�todo que lee los valores ingresados por el usario sobre frecuencias y
	 * genera el atributo es del tipo EntradaSalida
	 */
	public EntradaSalida generarEs() {
		int[] entradas = new int[3];
		int[] salidas = new int[3];
		try {
			for (int i = 0; i < 3; i++) {
				try {
					entradas[i] = Integer.parseInt(this.entrada[i].getText());
				} catch (Exception e) {
					entradas[i] = 0;
				}
				System.out.print(entradas[i]);
			}
			System.out.println();
			for (int i = 0; i < 3; i++) {
				try {
					salidas[i] = Integer.parseInt(this.salida[i].getText());
				} catch (Exception e) {
					salidas[i] = 0;
				}
				System.out.print(salidas[i]);
			}
			es = new EntradaSalida(entradas, salidas);
		} catch (Exception e) {
			es = null;
		}
		return es;
	}

	public PanelMapa getMapa() {
		return mapa;
	}

	public void setMapa(PanelMapa mapa) {
		this.mapa = mapa;
	}

	public Nodo getNodo() {
		return nodo;
	}

	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}

	public List<JButton> getListaBotonesAplicarIntervalo() {
		return listaBotonesAplicarIntervalo;
	}

	public void setListaBotonesAplicarIntervalo(
			List<JButton> listaBotonesAplicarIntervalo) {
		this.listaBotonesAplicarIntervalo = listaBotonesAplicarIntervalo;
	}
}
