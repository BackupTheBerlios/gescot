package is.SimTraffic.Vista;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Señales.Semaforo;
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

	private JComboBox comboTipoSeñales;

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

	private JPanel panelSeñales = new JPanel();

	private JPanel panelTramos = new JPanel();
	
	private JPanel panelSemaforos = new JPanel();

	private Nodo nodo;

	private PanelMapa mapa;
	
	/**
	 * Para introducir el tiempo inicial de los nuevos intervalos del semáforo. 
	 */
	private JSpinner tiempoInicialSemaforo;

	/**
	 * Para introducir el tiempo final de los nuevos intervalos del semáforo. 
	 */
	private JSpinner tiempoFinalSemaforo;

	/**
	 * Para elegir el nuevo valor del tiempo total del semáforo. 
	 */
	private JComboBox electorTiempoTotal;
	
	/**
	 * Lista que permitirá acceder a cada componente JSpinner de tiempo final de los intervalos.
	 */	
	private List<JSpinner> listaTiempoInicialIntervalo;
	
	/**
	 * Lista que permitirá acceder a cada componente JSpinner de tiempo final de los intervalos.
	 */
	private List<JSpinner> listaTiempoFinalIntervalo;
	
	/**
	 * Ventana Auxiliar que permitirá modificar los valores de interconexión entre los tramos.
	 */
	private VentanaMatrizDePaso ventanaMatriz;
	
	/**
	 * Lista que almacenará todos los botones que aparecen 
	 */
	private List<JButton> listaBotonesAplicarIntervalo;
	
	public PanelNodo(Nodo nodo) {

	}

	public PanelNodo(Nodo nodo, PanelMapa mapa) {
		this.setLayout(new BorderLayout(2, 2));
		this.nodo = nodo;
		this.mapa = mapa;
		this.setAlwaysOnTop(true);

		panelDatos = new JTabbedPane();
			
		creaPanelDatos();

		panelBotones = new JPanel();
		creaPanelBotones();

		crearAcciones();
		panelDatos.setPreferredSize(new Dimension(250,400));
		this.add(panelDatos, BorderLayout.NORTH);
		//this.add(panelDatos, BorderLayout.CENTER);
		this.add(panelBotones, BorderLayout.SOUTH);
	}

	/**
	 * Diseño de cuadro de dialogo emergente de propiedades de nodo TODO
	 * terminar el diseño de las pestañas
	 */
	public void creaPanelDatos() {
		creaPanelTipo();
		//creaPanelSeñales();
		creaPanelSemaforos();
		// creaPanelTramos();
	}

	/**
	 * Esto correponde a la pestaña de las propiedades de los tramos de un nodo
	 * @deprecated  De momento no se usa y se ha deshabilitado.
	 */
	public void creaPanelTramos() {
		panelDatos.addTab(Messages.getString("PanelNodo.0"), null, panelTramos, //$NON-NLS-1$
				Messages.getString("PanelNodo.1")); //$NON-NLS-1$
	}

	
	/**
	 * Hace que la pestaña que aparece resaltada por defecto sea la de los semáforos. 
	 */
	public void daFocoAPanelSemaforo(){
		panelDatos.setSelectedIndex(1);
	}
	
	public void creaPanelSemaforos(){
		panelSemaforos.removeAll();
		panelDatos.addTab(Messages.getString("PanelNodo.2"), null, panelSemaforos,Messages.getString("PanelNodo.3")); //$NON-NLS-1$ //$NON-NLS-2$
		
		//Si no se ha insertado todavía un semáforo.
		if (nodo.getSeñal() == null){
			JLabel etiquetaNuevo = new JLabel(Messages.getString("PanelNodo.4")); //$NON-NLS-1$
			JButton botonCrearSemaforo = new JButton(Messages.getString("PanelNodo.5")); //$NON-NLS-1$
			AccionCrearSemaforo oyenteBotonSemaforo = new AccionCrearSemaforo(nodo,this);
			botonCrearSemaforo.addActionListener(oyenteBotonSemaforo);
			panelSemaforos.add(etiquetaNuevo);
			panelSemaforos.add(botonCrearSemaforo);
		//Si ya existe un semáforo en el nodo
		} else if(nodo.getSeñal().getNombre().equals(Messages.getString("PanelNodo.6"))){ //$NON-NLS-1$
			JPanel panelInterior = new JPanel();
			String[] valoresTiempoTotal = {Messages.getString("PanelNodo.7"),Messages.getString("PanelNodo.8"),Messages.getString("PanelNodo.9"),Messages.getString("PanelNodo.10")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			electorTiempoTotal = new JComboBox(valoresTiempoTotal);
			electorTiempoTotal.setSelectedItem((String.valueOf(((Semaforo)nodo.getSeñal()).getTiempoTotal())));
			
			JLabel etiqElector = new JLabel(Messages.getString("PanelNodo.11")); //$NON-NLS-1$
			JButton botonAplicar = new JButton(Messages.getString("PanelNodo.12")); //$NON-NLS-1$
			JButton botonBorrarSemaforo = new JButton(Messages.getString("PanelNodo.13")); //$NON-NLS-1$
			
			AccionEliminarSemaforo accionEliminarSem = new AccionEliminarSemaforo(nodo,this);
			botonBorrarSemaforo.addActionListener(accionEliminarSem);
			
			botonAplicar.setToolTipText(Messages.getString("PanelNodo.14")); //$NON-NLS-1$
			AccionCambiarTiempoTotalSem oyenteBotonAplicar = new AccionCambiarTiempoTotalSem(nodo,this);
			botonAplicar.addActionListener(oyenteBotonAplicar);
			panelSemaforos.add(etiqElector);
			panelSemaforos.add(electorTiempoTotal);
			panelSemaforos.add(botonAplicar);
			panelSemaforos.add(botonBorrarSemaforo);
			
			JScrollPane panelScroll = new JScrollPane(panelInterior);
			panelScroll.setPreferredSize(new Dimension(450,250));
			panelSemaforos.add(panelScroll);
			int valorScrollVertical = ((Semaforo)nodo.getSeñal()).getListaIntervalos().size()*70;
			panelInterior.setPreferredSize(new Dimension(430,valorScrollVertical));
			creaPanelesIntervalos(panelInterior);
			
			JButton añadirIntervalo = new JButton(Messages.getString("PanelNodo.15")); //$NON-NLS-1$
			panelSemaforos.add(añadirIntervalo);
			JLabel etiqDe = new JLabel(Messages.getString("PanelNodo.16")); //$NON-NLS-1$
			panelSemaforos.add(etiqDe);
			tiempoInicialSemaforo = new JSpinner(new SpinnerNumberModel(0, 0,((Semaforo)nodo.getSeñal()).getTiempoTotal(), 5));
			panelSemaforos.add(tiempoInicialSemaforo);
			JLabel etiqA = new JLabel(Messages.getString("PanelNodo.17")); //$NON-NLS-1$
			panelSemaforos.add(etiqA);
			tiempoFinalSemaforo = new JSpinner(new SpinnerNumberModel(((Semaforo)nodo.getSeñal()).getTiempoTotal(), 0,((Semaforo)nodo.getSeñal()).getTiempoTotal(), 5));
			panelSemaforos.add(tiempoFinalSemaforo);
			AccionInsertarIntervalo oyenteBotonAñadirIntervalo = new AccionInsertarIntervalo(nodo,this);
			añadirIntervalo.addActionListener(oyenteBotonAñadirIntervalo);	
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
		
		this.listaBotonesAplicarIntervalo = new ArrayList<JButton>();
		
		listaTiempoInicialIntervalo = new ArrayList<JSpinner>();
		listaTiempoFinalIntervalo = new ArrayList<JSpinner>();
		
		ClassLoader cl = this.getClass().getClassLoader();

		ImageIcon imagen = new ImageIcon(cl
				.getResource(Messages.getString("PanelNodo.18")), Messages.getString("PanelNodo.19")); //$NON-NLS-1$ //$NON-NLS-2$
			
		for (int i=0;i<((Semaforo)nodo.getSeñal()).getListaIntervalos().size();i++) {
			JLabel etiqDe = new JLabel(Messages.getString("PanelNodo.20")); //$NON-NLS-1$
			String valor1 = String.valueOf(((Semaforo)nodo.getSeñal()).getListaIntervalos().get(i).getTiempoInicial());
			String valor2 = String.valueOf(((Semaforo)nodo.getSeñal()).getListaIntervalos().get(i).getTiempoFinal());
			JSpinner tiempoDe = new JSpinner(new SpinnerNumberModel(Integer.parseInt(valor1), 0, Integer.parseInt(valor2), 5));
			EscuchaCambioSpinner oyenteCambioSpinner = new EscuchaCambioSpinner(i,this);
			tiempoDe.addChangeListener(oyenteCambioSpinner);
			listaTiempoInicialIntervalo.add(tiempoDe);
			JLabel etiqA = new JLabel(Messages.getString("PanelNodo.21")); //$NON-NLS-1$
			JSpinner tiempoA = new JSpinner(new SpinnerNumberModel(Integer.parseInt(valor2), 0,((Semaforo)nodo.getSeñal()).getTiempoTotal(), 5));
			tiempoA.addChangeListener(oyenteCambioSpinner);
			listaTiempoFinalIntervalo.add(tiempoA);
			JButton mostrarM = new JButton(Messages.getString("PanelNodo.22")); //$NON-NLS-1$
			AccionAbrirMatrizDePaso oyenteBotonMatriz = new AccionAbrirMatrizDePaso(this);
			JButton aplicarC = new JButton(Messages.getString("PanelNodo.23")); //$NON-NLS-1$
			aplicarC.setEnabled(false);
			JButton botonBorrarIntervalo = new JButton(imagen);
			botonBorrarIntervalo.setPreferredSize(new Dimension(30,25));
			AccionEliminarIntervalo  eliminarIntervalo = new AccionEliminarIntervalo(nodo, this, i);
			botonBorrarIntervalo.addActionListener(eliminarIntervalo);
			listaBotonesAplicarIntervalo.add(aplicarC);
			AccionModificarIntervalo oyenteBotonAplicar = new AccionModificarIntervalo(nodo,this);
			//Asignamos un nombre al botón para poder diferenciar en el oyente que intervalo estamos modificando.
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
			intervaloI.add(botonBorrarIntervalo);
			intervaloI.setBorder(BorderFactory.createTitledBorder(Messages.getString("PanelNodo.24") + i)); //$NON-NLS-1$
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
	 * Esto correponde a la pestaña de las propiedades de las señales de un nodo
	 * falta mucho por hacer TODO casi todo
	 */
	public void creaPanelSeñales() {

		panelSeñales.setLayout(new BorderLayout());
		JPanel panelTipoSeñal = new JPanel();
		JPanel panelDetallesSeñal = new JPanel();
		this.panelSeñales.add(panelTipoSeñal, BorderLayout.NORTH);
		this.panelSeñales.add(panelDetallesSeñal, BorderLayout.CENTER);

		panelTipoSeñal.setLayout(new FlowLayout());
		panelDetallesSeñal.setLayout(new FlowLayout());

		panelTipoSeñal.setBorder(BorderFactory
				.createTitledBorder(Messages.getString("PanelNodo.25"))); //$NON-NLS-1$
		panelDetallesSeñal.setBorder(BorderFactory
				.createTitledBorder(Messages.getString("PanelNodo.26"))); //$NON-NLS-1$

		JLabel etiquetaTipoSeñal = new JLabel(Messages.getString("PanelNodo.27")); //$NON-NLS-1$
		String[] tiposSeñales = { Messages.getString("PanelNodo.28"), Messages.getString("PanelNodo.29"), Messages.getString("PanelNodo.30"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("PanelNodo.31") }; //$NON-NLS-1$
		comboTipoSeñales = new JComboBox(tiposSeñales);

		panelTipoSeñal.add(etiquetaTipoSeñal);
		panelTipoSeñal.add(comboTipoSeñales);

		JLabel etiquetaDetallesSeñal1 = new JLabel(Messages.getString("PanelNodo.32")); //$NON-NLS-1$
		String[] tiposSemaforos = { Messages.getString("PanelNodo.33"), Messages.getString("PanelNodo.34"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("PanelNodo.35") }; //$NON-NLS-1$
		comboTipoSemaforos = new JComboBox();
		comboTipoSemaforos = new JComboBox(tiposSemaforos);
		JLabel etiquetaDetallesSeñal2 = new JLabel(Messages.getString("PanelNodo.36")); //$NON-NLS-1$
		campoTiempoCicloSemaforo = new JTextField(Messages.getString("PanelNodo.37")); //$NON-NLS-1$
		campoTiempoCicloSemaforo.setPreferredSize(new Dimension(30, 20));

		panelDetallesSeñal.add(etiquetaDetallesSeñal1);
		panelDetallesSeñal.add(comboTipoSemaforos);
		panelDetallesSeñal.add(etiquetaDetallesSeñal2);
		panelDetallesSeñal.add(campoTiempoCicloSemaforo);

		panelDatos.addTab(Messages.getString("PanelNodo.38"), null, panelSeñales, //$NON-NLS-1$
				Messages.getString("PanelNodo.39")); //$NON-NLS-1$

		// TODO Cargar los datos del nodo al formulario

	}

	/**
	 * 
	 * 
	 */
	public void creaPanelTipo() {

		JPanel panelTipo = new JPanel();
		panelTipo.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		JLabel etiquetaTipo = new JLabel(Messages.getString("PanelNodo.40")); //$NON-NLS-1$

		// Modificando
		String[] tiposNodos = { Messages.getString("PanelNodo.41"), Messages.getString("PanelNodo.42"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("PanelNodo.43"), Messages.getString("PanelNodo.44"), Messages.getString("PanelNodo.45")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		comboTipo = new JComboBox(tiposNodos);

		JLabel etiquetaValor = new JLabel(Messages.getString("PanelNodo.46")); //$NON-NLS-1$

		String[] valorNodos = { Messages.getString("PanelNodo.47") }; //$NON-NLS-1$

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
		panelTipo.setBorder(BorderFactory.createTitledBorder(Messages.getString("PanelNodo.48"))); //$NON-NLS-1$

		JPanel panelEntrada = crearSeccionEntradaSalida();

		panelAuxiliar = new JPanel();
		panelAuxiliar.setLayout(new BorderLayout());
		JPanel panelNombre = new JPanel();
		panelNombre.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		JLabel etiquetaNombre = new JLabel(Messages.getString("PanelNodo.49")); //$NON-NLS-1$
		campoNombre = new JTextField(14);
		if (nodo.getNombre() != null)
			campoNombre.setText(nodo.getNombre());
		panelNombre.add(etiquetaNombre);
		panelNombre.add(campoNombre);
		JPanel panelPosicion = new JPanel();
		panelPosicion.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel etiquetaPosicion = new JLabel(Messages.getString("PanelNodo.50")); //$NON-NLS-1$
		JLabel etiquetaposicionX = new JLabel(Messages.getString("PanelNodo.51")); //$NON-NLS-1$
		JLabel etiquetaposicionY = new JLabel(Messages.getString("PanelNodo.52")); //$NON-NLS-1$
		JLabel posicionX = new JLabel((Messages.getString("PanelNodo.53") + Representacion.pasarAGrados(nodo //$NON-NLS-1$
				.getPos().getLat())));
		JLabel posicionY = new JLabel((Messages.getString("PanelNodo.54") + Representacion.pasarAGrados(nodo //$NON-NLS-1$
				.getPos().getLon())));
		panelPosicion.add(etiquetaPosicion);
		panelPosicion.add(etiquetaposicionX);
		panelPosicion.add(posicionX);
		panelPosicion.add(etiquetaposicionY);
		panelPosicion.add(posicionY);

		panelAuxiliar.add(panelNombre, BorderLayout.NORTH);
		panelAuxiliar.add(panelPosicion, BorderLayout.SOUTH);
		panelAuxiliar.setBorder(BorderFactory
				.createTitledBorder(Messages.getString("PanelNodo.55"))); //$NON-NLS-1$

		panelPropiedades.setLayout(new BorderLayout(10, 20));
		panelPropiedades.add(panelTipo, BorderLayout.NORTH);
		panelPropiedades.add(panelEntrada, BorderLayout.CENTER);
		panelPropiedades.add(panelAuxiliar, BorderLayout.SOUTH);

		panelDatos.addTab(Messages.getString("PanelNodo.56"), null, panelPropiedades, //$NON-NLS-1$
				Messages.getString("PanelNodo.57")); //$NON-NLS-1$
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
				campoTiempoCicloSemaforo, comboTipoSeñales, mapa);
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

		botonAceptar = new JButton(Messages.getString("PanelNodo.58")); //$NON-NLS-1$
		botonCancelar = new JButton(Messages.getString("PanelNodo.59")); //$NON-NLS-1$

		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 28));

		panelBotones.add(botonAceptar);
		panelBotones.add(botonCancelar);

		panelBotones.setBorder(BorderFactory.createEtchedBorder());
	}

	/**
	 * Redundante (método que ya existía en un oyente peor necesario aquí,
	 * rediseñar más adelante).
	 */
	public void configurarValoresSeñal() {
		if (comboTipoSeñales.getSelectedItem().equals(Messages.getString("PanelNodo.60"))) { //$NON-NLS-1$
			String[] tiposSemaforos = { Messages.getString("PanelNodo.61"), Messages.getString("PanelNodo.62"), //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("PanelNodo.63") }; //$NON-NLS-1$
			JComboBox comboTipoSemaforos = new JComboBox(tiposSemaforos);
			panelSeñales.add(comboTipoSemaforos);
		} else {
			comboValor.removeAllItems();
			comboValor.addItem(Messages.getString("PanelNodo.64")); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 * 
	 */
	public void configurarValoresNodo() {
		if (comboTipo.getSelectedItem().equals(Messages.getString("PanelNodo.65"))) { //$NON-NLS-1$
			String[] s1 = { Messages.getString("PanelNodo.66"), Messages.getString("PanelNodo.67"), Messages.getString("PanelNodo.68"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("PanelNodo.69"), Messages.getString("PanelNodo.70"), Messages.getString("PanelNodo.71"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("PanelNodo.72") }; //$NON-NLS-1$
			comboValor.removeAllItems();
			for (int i = 0; i < s1.length; i++)
				comboValor.addItem(s1[i]);
		}

		else if (comboTipo.getSelectedItem().equals(Messages.getString("PanelNodo.73"))) { //$NON-NLS-1$
			String[] s2 = { Messages.getString("PanelNodo.74"), Messages.getString("PanelNodo.75"), Messages.getString("PanelNodo.76"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("PanelNodo.77"), Messages.getString("PanelNodo.78"), Messages.getString("PanelNodo.79"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					Messages.getString("PanelNodo.80"), Messages.getString("PanelNodo.81") }; //$NON-NLS-1$ //$NON-NLS-2$
			comboValor.removeAllItems();
			for (int i = 0; i < s2.length; i++)
				comboValor.addItem(s2[i]);
		}

		else if (comboTipo.getSelectedItem().equals(Messages.getString("PanelNodo.82"))) { //$NON-NLS-1$
			String[] s3 = { Messages.getString("PanelNodo.83"), Messages.getString("PanelNodo.84"), //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("PanelNodo.85"), Messages.getString("PanelNodo.86"), Messages.getString("PanelNodo.87") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			comboValor.removeAllItems();
			for (int i = 0; i < s3.length; i++)
				comboValor.addItem(s3[i]);
		}

		else if (comboTipo.getSelectedItem().equals(Messages.getString("PanelNodo.88"))) { //$NON-NLS-1$
			TipoElemento inf = new TipoNodoAmenity(Messages.getString("PanelNodo.89")); //$NON-NLS-1$
			String[] s4 = inf.devolverListaValores();
			comboValor.removeAllItems();
			for (int i = 0; i < s4.length; i++)
				comboValor.addItem(s4[i]);
		}

		else {
			comboValor.removeAllItems();
			comboValor.addItem(Messages.getString("PanelNodo.90")); //$NON-NLS-1$
		}
	}

	private JPanel crearSeccionEntradaSalida() {
		JPanel panelEntrada = new JPanel();
		panelEntrada.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 13));

		panel.add(new JLabel(Messages.getString("PanelNodo.91"))); //$NON-NLS-1$
		panel.add(new JLabel(Messages.getString("PanelNodo.92"))); //$NON-NLS-1$
		panel.add(new JLabel(Messages.getString("PanelNodo.93"))); //$NON-NLS-1$
		panel.add(new JLabel(Messages.getString("PanelNodo.94"))); //$NON-NLS-1$

		es = nodo.getEs();

		panel.add(new JLabel(Messages.getString("PanelNodo.95"))); //$NON-NLS-1$
		for (int i = 0; i < 3; i++) {
			entrada[i] = new JTextField(3);
			if (es != null)
				entrada[i].setText(Messages.getString("PanelNodo.96") + es.getPorcentajesEntrada()[i]); //$NON-NLS-1$
			panel.add(entrada[i]);
		}

		panel.add(new JLabel(Messages.getString("PanelNodo.97"))); //$NON-NLS-1$
		for (int i = 0; i < 3; i++) {
			salida[i] = new JTextField(3);
			if (es != null)
				salida[i].setText(Messages.getString("PanelNodo.98") + es.getPorcentajesSalida()[i]); //$NON-NLS-1$
			panel.add(salida[i]);
		}

		// JPanel panel2 = new JPanel();
		// panel2.setLayout(new FlowLayout());
		// panel2.add(new JLabel("Estos valores indican cuantos coches entran
		// por este"));

		// panelEntrada.add(panel2);
		panelEntrada.add(panel, BorderLayout.CENTER);
		panelEntrada.setBorder(BorderFactory
				.createTitledBorder(Messages.getString("PanelNodo.99"))); //$NON-NLS-1$

		return panelEntrada;
	}

	/**
	 * Método que lee los valores ingresados por el usario sobre frecuencias y
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
				//System.out.print(entradas[i]);
			}
			//System.out.println();
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
