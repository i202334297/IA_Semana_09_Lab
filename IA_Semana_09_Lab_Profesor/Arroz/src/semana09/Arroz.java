package semana09;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;

public class Arroz extends JFrame implements ActionListener {

	// Declaraci�n de variables
	private static final long serialVersionUID = 9206324162700448001L;
	private JPanel contentPane;
	private JLabel lblMarca;
	private JLabel lblCantidad;
	private JComboBox<String> cboMarca;
	private JTextField txtCantidad;
	private JButton btnProcesar;
	private JButton btnBorrar;
	private JScrollPane scpScroll;
	private JTextArea txtS;

	// Lanza la aplicaci�n
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Arroz frame = new Arroz();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crea la GUI
	public Arroz() {
		setTitle("Arroz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMarca = new JLabel("Marca de arroz");
		lblMarca.setBounds(10, 11, 91, 14);
		contentPane.add(lblMarca);

		lblCantidad = new JLabel("Cantidad de bolsas");
		lblCantidad.setBounds(10, 36, 91, 14);
		contentPane.add(lblCantidad);

		cboMarca = new JComboBox<String>();
		cboMarca.setModel(new DefaultComboBoxModel<String>(new String[] {"Coste\u00F1o", "Paisana del Norte", "Tropical Superior", "Norte\u00F1o Superior"}));
		cboMarca.setBounds(111, 8, 100, 20);
		contentPane.add(cboMarca);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(111, 33, 100, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);

		btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(this);
		btnProcesar.setBounds(335, 7, 89, 23);
		contentPane.add(btnProcesar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(this);
		btnBorrar.setBounds(335, 32, 89, 23);
		contentPane.add(btnBorrar);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 61, 414, 190);
		contentPane.add(scpScroll);

		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtS);
	}


	// Direcciona eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBorrar) {
			actionPerformedBtnBorrar(arg0);
		}
		if (arg0.getSource() == btnProcesar) {
			actionPerformedBtnProcesar(arg0);
		}
	}

	// Procesa la pulsaci�n del bot�n Borrar
	protected void actionPerformedBtnBorrar(ActionEvent arg0) {
		txtCantidad.setText("");
		txtS.setText("");
		cboMarca.setSelectedIndex(0);
		txtCantidad.requestFocus();
	}

	// Procesa la pulsaci�n del bot�n Procesar
	int cant,Marroz,obsC,gomi;
	double pre,impC,impD,impP;
	protected void actionPerformedBtnProcesar(ActionEvent arg0) {
		leerDatos();
		obtenerPrecio();
		ImporteCompra();
		ImporteDescuento();
		ImportePagar();
		Obsequios();
		Gomitas();
		mostrarResultado();
	}
	void leerDatos(){
		Marroz=cboMarca.getSelectedIndex();
		cant=Integer.parseInt(txtCantidad.getText());	
	}
	void obtenerPrecio(){
		if (Marroz==0){pre=14.5;}
		else if (Marroz==1){pre=12.6;}
		else if (Marroz==2){pre=10.0;}
		else {pre=12.8;}
	}
	void ImporteCompra(){
		impC=pre*cant;
	}
	void ImporteDescuento(){
		if(cant	< 4){impD=impC*0.09;}
		else if(cant >= 4 && cant < 7){impD=impC*0.11;}
		else if (cant>=7 && cant <10){impD=impC*0.13;}
		else {impD=impC*0.15;}
	}
	void ImportePagar(){
		impP=impC-impD;
	}
	void Obsequios(){
		if(Marroz==0){obsC=3*cant;}
		else if (Marroz==1){obsC=2*cant;}
		else if (Marroz==2){obsC=2*cant;}
		else {obsC=1;}
	}
	void Gomitas(){
		if(impP<50){gomi=5;}
		else if (impP>=50 && impP<100){gomi=10;}
		else if (impP>=100 && impP<150){gomi=15;}
		else{gomi=20;}
	}
	
	void mostrarResultado(){
		// Salida de resultados
		txtS.setText("Importe compra    : S/ " +impC+ "\n");
		txtS.append ("Importe descuento : S/ " +impD+"\n");
		txtS.append ("Importe pagar     : S/ " +impP+"\n");
		txtS.append ("Caramelos         : "    +obsC+"\n");
		txtS.append ("Gomitas           : "    +gomi+"\n");
	}
	
	
}