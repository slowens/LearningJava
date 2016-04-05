import javax.swing.*;
import java.awt.*;


public class Guziki{
	
	
	public static void main(String[] args){
		
		Guziki okno = new Guziki();
		okno.doRoboty();
	}
	
	
	public void doRoboty() {
		
		JFrame ramka = new JFrame();
		JPanel panel = new JPanel();
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.setBackground(Color.darkGray);
		
		JButton przycisk = new JButton("To szok!");
		JButton przycisk2 = new JButton("Trach!");
		JButton przycisk3 = new JButton("Och?!");

		panel.add(przycisk);
		panel.add(przycisk2);
		panel.add(przycisk3);
		
		ramka.getContentPane().add(BorderLayout.EAST, panel);
		
		
		ramka.setSize(200,200);
		ramka.setVisible(true);
}
	
	
}

