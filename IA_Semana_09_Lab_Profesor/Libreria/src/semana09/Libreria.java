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

public class Libreria extends JFrame implements ActionListener {

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
					Libreria frame = new Libreria();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crea la GUI
	public Libreria() {
		setTitle("Librer\u00EDa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 233);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(10, 11, 47, 14);
		contentPane.add(lblMarca);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 36, 47, 14);
		contentPane.add(lblCantidad);

		cboMarca = new JComboBox<String>();
		cboMarca.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Standford", "Alpha", "Justus", "Loro" }));
		cboMarca.setBounds(67, 8, 100, 20);
		contentPane.add(cboMarca);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(67, 33, 100, 20);
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
		scpScroll.setBounds(10, 61, 414, 123);
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
	int cant,marca,obs;
	double pre,impC,impD,impP;
	
	protected void actionPerformedBtnProcesar(ActionEvent arg0) {
		leerDatos();
		obtenerPrecio();
		ImporteCompra();
		ImporteDescuento();
		ImportePagar();
		lapicero();
		mostrarResultado();
	}
	
	void leerDatos(){		
		cant=Integer.parseInt(txtCantidad.getText());
		marca=cboMarca.getSelectedIndex();
	}
	void obtenerPrecio(){
		if 	   (marca==0){pre=4.85;}
		else if(marca==1){pre=4.35;}
		else if(marca==2){pre=3.50;}
		else             {pre=4.55;}
	}
	void ImporteCompra(){
		impC=pre*cant;
	}
	void ImporteDescuento(){
		
		if     (cant >= 8)			 {impD=0.135*impC;}
		else if(cant >=6 && cant < 8){impD=0.115*impC;}
		else if(cant >=4 && cant < 6){impD=0.095*impC;}
		else                         {impD=0.075*impC;}
	}
	void ImportePagar(){
		impP=impC-impD;
	}
	void lapicero(){
		if      (marca==0){obs=5*cant;}
		else if (marca==1){obs=4*cant;}
		else if (marca==2){obs=3*cant;}
		else              {obs=2*cant;}
	}
	
	void mostrarResultado(){
		// Salida de resultados
		txtS.setText("Importe compra    : S/. " + decimalFormat(impC) +"\n");
		txtS.append ("Importe descuento : S/. " + decimalFormat(impD) +"\n");
		txtS.append ("Importe pagar     : S/. " + decimalFormat(impP) +"\n");
		txtS.append ("Lapiceros         :     " + obs +"\n");
	}
	
	
	public String decimalFormat(double p) {
	return String.format("%.2f",p);
	}
	public void i (String s) {
	txtS.append(s +"\n");
}
}