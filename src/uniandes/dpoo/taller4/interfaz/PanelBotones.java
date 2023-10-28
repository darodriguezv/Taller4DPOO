package uniandes.dpoo.taller4.interfaz;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelBotones extends JPanel implements ActionListener {
	
	private InterfazJuego interfazJuego;
	
	public PanelBotones(InterfazJuego interfazJuego) {
		this.interfazJuego = interfazJuego;
		setLayout(new GridLayout(16,1));
		JLabel jlabel1 = new JLabel();
		JLabel jlabel2 = new JLabel();
		JLabel jlabel3 = new JLabel();
		for (int i = 0; i < 4; i++) {
			JLabel jlabel = new JLabel();
			add(jlabel);
		}
		JButton btnNuevo= new JButton( "NUEVO" );
		btnNuevo.setActionCommand("Nuevo");
		
        add(btnNuevo);
        add(jlabel1);
        JButton btnReiniciar= new JButton( "REINICIAR" );
        btnReiniciar.setActionCommand("Reiniciar");
        add(btnReiniciar);
        add(jlabel2);
        JButton btnTop10= new JButton( "TOP-10" );
        btnTop10.setActionCommand("Top-10");
        add(btnTop10);
        add(jlabel3);
        JButton btnCambiarJugador= new JButton( "CAMBIAR JUGADOR" );
        btnCambiarJugador.setActionCommand("Jugador");
        add(btnCambiarJugador);
        
        btnNuevo.addActionListener( this );
        btnReiniciar.addActionListener( this );
        btnTop10.addActionListener( this );
        btnCambiarJugador.addActionListener( this );
        
        for (int i = 0; i < 5; i++) {
			JLabel jlabel = new JLabel();
			add(jlabel);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Nuevo")) {
			interfazJuego.nuevoTablero();
		}else if (e.getActionCommand().equals("Reiniciar")) {
			interfazJuego.reiniciarTablero();
		}else if (e.getActionCommand().equals("Top-10")) {
			interfazJuego.presionarBotonTop10();
		}else if (e.getActionCommand().equals("Jugador")) {
			interfazJuego.cambiarNombreJugador();
			
		}
		
	}
}
