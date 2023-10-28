package uniandes.dpoo.taller4.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PanelOpcionesSuperiores extends JPanel implements ActionListener {
	private JTextField jTextFieldTamanio;
	private JComboBox<String> jComboBox;
	private JTextField jTextFieldDificultad;
	private JRadioButton radioButtonFacil;
	private JRadioButton radioButtonMedio;
	private JRadioButton radioButtonDificil;
	private InterfazJuego interfazPrincipal;
	private String[] size = { "5x5", "7x7", "9x9", "11x11", "13x13" };
	
	public PanelOpcionesSuperiores( InterfazJuego intefazPrincipal) {
		this.interfazPrincipal = intefazPrincipal;
		setLayout(new GridLayout(1,6));
		jTextFieldTamanio = new JTextField("Tamaño:");
		jTextFieldTamanio.setEditable(false);
		add(jTextFieldTamanio);
		jComboBox =new JComboBox(size);
		jComboBox.addActionListener(this);
		add(jComboBox);
		jTextFieldDificultad = new JTextField("Dificultad:");
		jTextFieldDificultad.setEditable(false);
		add(jTextFieldDificultad);
		radioButtonFacil = new JRadioButton("Facil");
		radioButtonMedio = new JRadioButton("Medio");
		radioButtonDificil = new JRadioButton("Dificil");
		radioButtonFacil.addActionListener(this);
		radioButtonMedio.addActionListener(this);
		radioButtonDificil.addActionListener(this);
		add(radioButtonFacil);
		add(radioButtonMedio);
		add(radioButtonDificil);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==jComboBox) {
			if(jComboBox.getSelectedIndex()==0) {
				interfazPrincipal.cambiarTamanioTablero(5);
			}else if(jComboBox.getSelectedIndex()==1) {
				interfazPrincipal.cambiarTamanioTablero(7);
			}else if (jComboBox.getSelectedIndex()==2) {
				interfazPrincipal.cambiarTamanioTablero(9);
			} else if (jComboBox.getSelectedIndex()==3) {
				interfazPrincipal.cambiarTamanioTablero(11);
			}else {
				interfazPrincipal.cambiarTamanioTablero(13);
			}
		}else if (e.getSource()==radioButtonFacil && radioButtonFacil.isSelected()) {
			interfazPrincipal.cambiarDificultad("ez");
		}else if (e.getSource()==radioButtonMedio && radioButtonMedio.isSelected()) {
			interfazPrincipal.cambiarDificultad("medium");
		}else if(e.getSource()==radioButtonDificil && radioButtonDificil.isSelected()) {
			interfazPrincipal.cambiarDificultad("DIFFICULT");
		}
		
	}
}
