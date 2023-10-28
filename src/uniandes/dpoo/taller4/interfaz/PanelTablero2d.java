package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;



public class PanelTablero2d extends JPanel implements MouseListener {
	
	private boolean[][] tablero;
	private InterfazJuego interfazPrincipal;
	
	public PanelTablero2d(boolean[][] tablero, InterfazJuego Principal) {
		interfazPrincipal = Principal;
		this.tablero = tablero;
		addMouseListener( this );
	}
	
	
	 public void paint(Graphics g)
	    {
	        Graphics2D g2d = (Graphics2D) g;
	        int ancho= getWidth( );
	        int alto= getHeight( );
	        int anchoRect= ancho/tablero.length;
	        int altoRect= alto/tablero.length;
	        for( int i = 0; i < tablero.length; i++ )
	        {
	            for( int j = 0; j < tablero.length; j++ )
	            {
	                Rectangle2D.Double rect= new Rectangle2D.Double( i*anchoRect , j*altoRect, anchoRect, altoRect );
	                if(tablero[i][j]==true)
	                {
	                    g.setColor( Color.YELLOW );
	                }
	                else
	                {
	                    g.setColor( Color.GRAY );
	                }
	                g2d.fill(rect);
	                g.setColor( Color.BLACK );
	                g2d.draw( rect );
	            }
	        }
	    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int click_x = e.getX();
        int click_y = e.getY();
         convertirCoordenadasACasilla(click_x, click_y);
        
        repaint();
		
	}
	
	private void convertirCoordenadasACasilla(int x, int y)
    {
    int ladoTablero = tablero.length;
    int altoPanelTablero = getHeight();
    int anchoPanelTablero = getWidth();
    int altoCasilla = altoPanelTablero / ladoTablero;
    int anchoCasilla = anchoPanelTablero / ladoTablero;
    int fila = (int) (x / anchoCasilla);
    int columna = (int) (y / altoCasilla);
    interfazPrincipal.jugarTablero(fila, columna);
    }
	
	public void actualizar (boolean[][] Tablero) {
		tablero = Tablero;
		repaint();
	}	
		
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
