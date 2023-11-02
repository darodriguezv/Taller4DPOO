package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

import java.util.ArrayList;
import java.util.Random;


public class InterfazJuego extends JFrame implements WindowListener{
	//Ventana principal
	private PanelJugadas panelSur;
	private PanelBotones panelEste;
	private PanelOpcionesSuperiores panelNorte;
	private PanelTablero2d panelCentro;
	private Tablero tablero;
	private int tamanio = 5;
	private Top10  top10 ;
	//Ventana Top 10
	private JFrame jFrameTop10;
	private JPanel panelTop10 ;
	private JList<RegistroTop10> JlistTop10;
	private JScrollPane scrollPaneTop10;
	
	public InterfazJuego()
	{
		// Top 10
		top10 = new Top10();
		top10.cargarRecords( new File("./data/top10.csv"));
		JlistTop10 = new JList<RegistroTop10>(recorrerTop10());	
		panelTop10 =  new JPanel();
		
		JlistTop10.setPreferredSize(new Dimension(300,500));
		JlistTop10.setCellRenderer(new CustomListCellRenderer());
		scrollPaneTop10 = new JScrollPane(JlistTop10);
		
		JTextField nombreJugadorTop10 = new JTextField("# Jugador");
		nombreJugadorTop10.setPreferredSize(new Dimension(100,200));
		nombreJugadorTop10.setEditable(false);
		panelTop10.add(scrollPaneTop10);
		panelTop10.setBackground(Color.WHITE);
		jFrameTop10 = new JFrame("Lista top 10");
		jFrameTop10.setLayout(new GridLayout( 2, 1 ));
		jFrameTop10.add(nombreJugadorTop10);
		jFrameTop10.getContentPane().add(panelTop10);
		jFrameTop10.setSize(335,400);
		jFrameTop10.setResizable(false);
		
		
		
		
		// Interfaz
		setSize( 1920, 1000 );
		setLayout( new BorderLayout( ) );
		panelSur = new PanelJugadas(this);
		add(panelSur, BorderLayout.SOUTH);
		panelEste = new PanelBotones(this);
		add(panelEste,BorderLayout.EAST);
		panelNorte = new PanelOpcionesSuperiores(this);
		add(panelNorte ,BorderLayout.NORTH);
		
		tablero = new Tablero( 5 );
		tablero.desordenar(2);
		
		panelCentro = new PanelTablero2d(tablero.darTablero(),this);
		add(panelCentro);
		addWindowListener(this);
		
		
			
	}
	
	
	
	
	
	public void jugarTablero(int fila , int columna) {
		
		tablero.jugar(fila, columna);
		panelSur.actualizarPuntaje(tablero.darJugadas());
		if(tablero.tableroIluminado()) {
			Top10();
			System.out.println("Nose");
		}
	}
	
	public void nuevoTablero() {
		
		tablero = new Tablero( tamanio );
		Random randomNumber = new Random() ;
		tablero.desordenar(randomNumber.nextInt(tamanio*tamanio));
		panelCentro.actualizar(tablero.darTablero());
		panelSur.actualizarPuntaje(tablero.darJugadas());
	}
	public void reiniciarTablero() {
		tablero.reiniciar();
		panelCentro.actualizar(tablero.darTablero());
		panelSur.actualizarPuntaje(tablero.darJugadas());
	}
	
	public void cambiarDificultad (String dificultad) {
		if(dificultad.equals("facil")) {
			tablero  = new Tablero(5);
			tablero.desordenar(1);
			
		}
		else if (dificultad.equals("medio")) {
			cambiarTamanioTablero(9);
		}
		else {
			cambiarTamanioTablero(11);
		}
		panelCentro.actualizar(tablero.darTablero());
		panelSur.actualizarPuntaje(tablero.darJugadas()); 
	}
	
	
	public void cambiarTamanioTablero(int size) {
		tablero = new Tablero( size );
		
		Random randomNumber = new Random() ;
		tablero.desordenar(randomNumber.nextInt(tamanio*tamanio));
		
		panelCentro.actualizar(tablero.darTablero());
		
		tamanio = size;
		panelSur.actualizarPuntaje(tablero.darJugadas());
	}
	
	public void cambiarNombreJugador() {
		String nombreJugador= JOptionPane.showInputDialog( this, "Ingrese el nombre:", 
                "Cambiar nombre de jugador", JOptionPane.QUESTION_MESSAGE );
		panelSur.actualizarNombre(nombreJugador);
	}
	
	
	public void Top10() {
		int puntaje = tablero.calcularPuntaje();
		
		String nombreJugador = panelSur.getNombrePlayer();
		RegistroTop10 registroJugador = new RegistroTop10(nombreJugador, puntaje);
		
		if(top10.esTop10(puntaje)) {
			top10.agregarRegistro(registroJugador.darNombre(), puntaje);
			jFrameTop10.remove(panelTop10);
			panelTop10 =  new JPanel();
			JlistTop10 = new JList<RegistroTop10>(recorrerTop10());
			
			scrollPaneTop10 = new JScrollPane(JlistTop10);
			panelTop10.add(scrollPaneTop10);
			
			jFrameTop10.getContentPane().add(panelTop10);
			jFrameTop10.pack();
			
		}
		
		
	
		
	}
	
	public void pressBotonTop10() {
		 jFrameTop10.setVisible(true);
		 
		 jFrameTop10.setLocationRelativeTo(this);
	}
	
	public DefaultListModel<RegistroTop10> recorrerTop10() {
		DefaultListModel<RegistroTop10> registros = new DefaultListModel<>();
		
		for (RegistroTop10 registro : top10.darRegistros()) {
			registros.addElement(registro);
			
		}
		return registros;
		
	}
	
	
	
	
	
	public static void main(String[] args)
    {
        InterfazJuego ventana = new InterfazJuego( );
        ventana.setLocationRelativeTo( null );
        ventana.setVisible( true );
    }





	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		try {
			top10.salvarRecords(new File("./data/top10.csv") );
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}





	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	static class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (index % 2 == 0) {
                setForeground(Color.BLACK); // Color para las posiciones pares
            } else {
                setForeground(Color.red); // Color para las posiciones impares
            }
            // Personaliza la apariencia de los elementos y agrega la numeraci�n
            setText((index + 1) + ". " + value);

            return this;
        }
    }
}
