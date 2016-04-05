import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Animacja {
	
	JFrame ramka;
	Rysuj panel;
	int x = 70;
	int y = 70;

	public static void main(String[] args) {

		Animacja animek = new Animacja();
		animek.doDziela();
	}
	
	public void doDziela() {

		ramka = new JFrame();
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		JButton wlaczAnimacje = new JButton("Wlacz animacje");
		wlaczAnimacje.addActionListener(new AnimacjaListener());
		
		panel = new Rysuj();
			
		ramka.getContentPane().add(BorderLayout.SOUTH, wlaczAnimacje);
		ramka.getContentPane().add(BorderLayout.CENTER, panel);
				
		ramka.setSize(800,600);
		ramka.setVisible(true);
	}
	
		class AnimacjaListener implements ActionListener {
			public void actionPerformed(ActionEvent zdarzenie) {
				for (int i =0; i< 130; i++){
					
					x++;
					y++;
					
					panel.repaint();
					
					try{
						Thread.sleep(5);
					}catch(Exception ex){
						
					}
					
				}
			}
		} // koniec klasy wewnêtrznej
	
		class Rysuj extends JPanel{
	
			public void paintComponent(Graphics g){
				
		
		Graphics2D g2d = (Graphics2D) g;
		/*
				g.fillRect(0,0,this.getWidth(),this.getHeight());
		
				Graphics2D g2d = (Graphics2D) g;
				int czerwony = (int) (Math.random() * 256);
				int zielony = (int) (Math.random() * 256);
				int niebieski = (int) (Math.random() * 256);

				Color kolorPoczatku = new Color(czerwony,zielony,niebieski);
		
				czerwony = (int) (Math.random() * 256);
				zielony = (int) (Math.random() * 256);
				niebieski = (int) (Math.random() * 256);
		
				Color kolorKonca = new Color(czerwony,zielony,niebieski);
		
				GradientPaint gradient = new GradientPaint(150,250,kolorPoczatku, 150,150, kolorKonca);
		*/
				g2d.setColor(Color.green);
				g2d.fillOval(x,y,300,250);
			}
		}
	
	
}