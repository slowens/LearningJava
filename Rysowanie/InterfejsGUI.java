import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class InterfejsGUI {
	
	JFrame ramka;
	JLabel etykieta;

	public static void main(String[] args) {

		InterfejsGUI apkaGUI = new InterfejsGUI();
		apkaGUI.doDziela();
	}
	
	public void doDziela() {

		ramka = new JFrame();
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton przyciskKolor = new JButton("Zmiana koloru");
		przyciskKolor.addActionListener(new KolorListener());
		
		JButton przyciskEtykieta = new JButton("Zmiana etykiety");
		przyciskEtykieta.addActionListener(new EtykietaListener());
		
		Rysuj panel = new Rysuj();
		etykieta = new JLabel("To jest etykietka");
		
		ramka.getContentPane().add(BorderLayout.SOUTH, przyciskKolor);
		ramka.getContentPane().add(BorderLayout.CENTER, panel);
		ramka.getContentPane().add(BorderLayout.EAST, przyciskEtykieta);
		ramka.getContentPane().add(BorderLayout.WEST, etykieta);
		
		ramka.setSize(800,600);
		ramka.setVisible(true);
	}
	
		class EtykietaListener implements ActionListener {
			public void actionPerformed(ActionEvent zdarzenie) {
				etykieta.setText("Auuuuuu!");
			}
		} // koniec klasy wewnêtrznej
	
	class KolorListener implements ActionListener {
		public void actionPerformed(ActionEvent zdarzenie) {
			ramka.repaint();
		}
	} //  koniec klasy wewnêtrznej
	

	
}