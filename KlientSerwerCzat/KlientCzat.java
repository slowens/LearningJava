import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class KlientCzat{

		JTextField wiadomosc;
		JTextArea odebraneWiadomosci;
		PrintWriter pisarz;
		BufferedReader czytelnik;
		Socket gniazdo;
		
		public void doDziela() {

			JFrame ramka = new JFrame("Prymitywny Klient Czatu");
			JPanel panelGlowny = new JPanel();
			
			odebraneWiadomosci = new JTextArea(15,50);
			odebraneWiadomosci.setLineWrap(true);
			odebraneWiadomosci.setWrapStyleWord(true);
			odebraneWiadomosci.setEditable(false);
			
			JScrollPane przewijanie = new JScrollPane(odebraneWiadomosci);
			przewijanie.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			przewijanie.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			wiadomosc = new JTextField(20);
			JButton przyciskWyslij = new JButton("Wyœlij");
			przyciskWyslij.addActionListener(new PrzyciskWyslijListener());
			
			panelGlowny.add(przewijanie);
			panelGlowny.add(wiadomosc);
			panelGlowny.add(przyciskWyslij);
			konfigurujKomunikacje();
			
			Thread watekOdbiorcy = new Thread(new OdbiorcaKomunikatow());
			watekOdbiorcy.start();
			
			ramka.getContentPane().add(BorderLayout.CENTER, panelGlowny);
			ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ramka.setSize(600,350);
			ramka.setVisible(true);

		} // koniec metody

		private void konfigurujKomunikacje() {

			try {

				gniazdo = new Socket("127.0.0.1", 5000);
				InputStreamReader czytelnikStrm = new InputStreamReader(gniazdo.getInputStream());
				czytelnik = new BufferedReader(czytelnikStrm);
				pisarz = new PrintWriter(gniazdo.getOutputStream());
				System.out.println("obs³uga sieci gotowa do u¿ycia");
			
			} catch(IOException ex) {
				ex.printStackTrace();
			}
			
		} // koniec metody

		public class PrzyciskWyslijListener implements ActionListener {

			public void actionPerformed(ActionEvent ev) {

				try {

					pisarz.println(wiadomosc.getText());
					pisarz.flush();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
				wiadomosc.setText("");
				wiadomosc.requestFocus();
			}

		} // koniec klasy wewnêtrznej
	
	public static void main(String[] args) {

		new KlientCzat().doDziela();
	}

	public class OdbiorcaKomunikatow implements Runnable {
			
		public void run() {
			
			String wiadom;
				
				try {
					
					while (true) {
						if((wiadom = czytelnik.readLine()) != null){
							System.out.println("Odczytano: " + wiadom);
							odebraneWiadomosci.append(wiadom + "\n");
						}
					} // koniec while

				} catch(Exception ex) {ex.printStackTrace();}

			} // koniec metody run()

		} // koniec klasy wewnêtrznej
	
	
	
} // koniec klasy zewnêtrznej