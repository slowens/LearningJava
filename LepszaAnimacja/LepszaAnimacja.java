import javax.swing.*;
import java.awt.*;

public class LepszaAnimacja {

	int x = 70;
	int y = 70;

	public static void main(String[] args) {

		LepszaAnimacja animek = new LepszaAnimacja();
		animek.doDziela();
	}
	
	public void doDziela() {

		JFrame ramka = new JFrame();
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		Rysuj panel = new Rysuj();
			
		ramka.getContentPane().add(panel);
		ramka.setSize(800,600);
		ramka.setVisible(true);
		
		while(1 != 3)
		{
			while ((x != panel.getWidth() -300 ) && (y != panel.getHeight() -250 )) {
			
				x++;
				y++;
			
				panel.repaint();
					
				try{
				Thread.sleep(10);
				}catch(Exception ex){
					
				}
		
			}
		
	
		while ((x != 0 ) && (y != 0 )) {
			
			x--;
			y--;
			
			panel.repaint();
					
			try{
				Thread.sleep(10);
			}catch(Exception ex){
					
				}
		
			}
		}
		}
		class Rysuj extends JPanel{
	
			public void paintComponent(Graphics g){
				
				g.fillRect(10, 10, (this.getWidth() - 20), (this.getHeight() - 20 ));
				g.setColor(Color.green);
				g.fillOval(x,y,300,250);
			}
		}
	
	
}