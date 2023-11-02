package uniandes.dpoo.taller4.interfaz;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelJugadas extends JPanel {
	
	
	private JTextField nJugadas;
	private JTextField nombreJugador;
	
	public PanelJugadas(InterfazJuego interfazJuego) {
		setLayout(new GridLayout( 1, 4 ));
		JTextField jugadas = new JTextField("Jugadas:");
		jugadas.setEditable(false);
		
		add(jugadas);
		nJugadas = new JTextField("0");
		nJugadas.setEditable(false);
		add(nJugadas);
		
		JTextField jugador = new JTextField("Jugador");
		jugador.setEditable(false);
		add(jugador);
		nombreJugador = new JTextField("Jugador1");
		nombreJugador.setEditable(false);
		
		add(nombreJugador);
		
		
	}
	public void actualizarPuntaje(int puntaje) {
		 
		nJugadas.setText( Integer.toString(puntaje) );
		
	}
	public void actualizarNombre(String nombrePlayer) {
		nombreJugador.setText(nombrePlayer);
	}
	
	public String getNombrePlayer() {
		return nombreJugador.getText();
	}
}

