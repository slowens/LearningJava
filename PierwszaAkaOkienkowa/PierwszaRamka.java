import javax.swing.*;
import java.awt.event.*;


public class PierwszaRamka implements ActionListener{
	
	JButton guzik;
	boolean n = false;
	
	public static void main(String[] args){
		PierwszaRamka pRamka = new PierwszaRamka();
		pRamka.doDziela();
	}
	
	public void doDziela(){
		JFrame ramka = new JFrame();
		guzik = new JButton("Nie naciskaj mnie !!!");
		
		guzik.addActionListener(this);
		
		ramka.getContentPane().add(guzik);
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		ramka.setSize(300, 300);
		ramka.setVisible(true);
		}
		
		public void actionPerformed(ActionEvent zdarzenie){
			
			if(n == false){
				guzik.setText("BUUUUUUUUMMMMM !!!");
				n = true;
			}else{
				guzik.setText("Nie naciskaj mnie !!!");
				n = false;
			}
			
		}
		
	}
	
	
