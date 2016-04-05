import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;


public class QuizGra {

	private JTextArea pytanie;
	private JTextArea odpowiedz;
	private ArrayList<KartaQuizowa> listaKart;
	private KartaQuizowa biezacaKarta;
	private int indeksBiezacejKarty;
	private JFrame ramka;
	private JButton przyciskNastepnaKarta;
	private boolean czyPrezentowanaOdpowiedz;
	
	public static void main (String[] args) {
		QuizGra gra = new QuizGra();
		gra.doDziela();
	}
	
	public void doDziela() {
	// tworzymy graficzny interfejs u¿ytkownika
		
		ramka = new JFrame("Quiz");
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelGlowny = new JPanel();
		
		Font czcionkaDuza = new Font("sanserif", Font.BOLD, 24);
		
		pytanie = new JTextArea(10,20);
		pytanie.setFont(czcionkaDuza);
		pytanie.setLineWrap(true);
		pytanie.setEditable(false);
		
		JScrollPane przewijanieP = new JScrollPane(pytanie);
		przewijanieP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		przewijanieP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		przyciskNastepnaKarta = new JButton("Poka¿pytanie");
		panelGlowny.add(przewijanieP);
		panelGlowny.add(przyciskNastepnaKarta);
		przyciskNastepnaKarta.addActionListener(new NastepnaKartaListener());
		
		JMenuBar pasekMenu = new JMenuBar();
		JMenu menuPlik = new JMenu("Plik");
		JMenuItem opcjaOtworz = new JMenuItem("Otwórz zbiór kart");
		opcjaOtworz.addActionListener(new OtworzMenuListener());
		menuPlik.add(opcjaOtworz);
		pasekMenu.add(menuPlik);
		ramka.setJMenuBar(pasekMenu);
		ramka.getContentPane().add(BorderLayout.CENTER, panelGlowny);
		ramka.setSize(640,500);
		ramka.setVisible(true);
	} // koniec metody

	public class NastepnaKartaListener implements ActionListener {
		
		public void actionPerformed(ActionEvent zd) {
		
			if (czyPrezentowanaOdpowiedz) {
				// poka¿ odpowied, bo u¿ytkownik ju¿ widzia³ pytanie

				pytanie.setText(biezacaKarta.getOdpowiedz());
				przyciskNastepnaKarta.setText("Nastêpna karta");
				czyPrezentowanaOdpowiedz = false;
			} else {
				// poka¿nastêpne pytanie
				
				if (indeksBiezacejKarty < listaKart.size()) {
					pokazNastepnaKarte();
				} else {
					// nie ma wiêcej kart
					pytanie.setText("To by³a ostatnia karta");
					przyciskNastepnaKarta.setEnabled(false);
				}	
			}
		}
	}
	

	public class OtworzMenuListener implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
		
			JFileChooser dialogFile = new JFileChooser();
			dialogFile.showOpenDialog(ramka);
			wczytajPlik(dialogFile.getSelectedFile());
		}
	}

	private void wczytajPlik(File file) {
		
		listaKart = new ArrayList<KartaQuizowa>();
		try {
			
			BufferedReader czytelnik = new BufferedReader(new FileReader(file));
			String wiersz = null;
			while ((wiersz = czytelnik.readLine()) != null) {
				tworzKarte(wiersz);
			}
			czytelnik.close();
		} catch(Exception ex) {
			System.out.println("Nie mo¿na odczytaæ z pliku z kartami!");
			ex.printStackTrace();
		}
		
		// czas zaczynaæ
		pokazNastepnaKarte();
	}
	
	private void tworzKarte(String wierszDanych) {
		
		String[] wyniki = wierszDanych.split("/");
		KartaQuizowa card = new KartaQuizowa(wyniki[0], wyniki[1]);
		listaKart.add(card);
		System.out.println("utworzno kartê");
	}
	
	private void pokazNastepnaKarte() {
		
		biezacaKarta = listaKart.get(indeksBiezacejKarty);
		indeksBiezacejKarty++;
		pytanie.setText(biezacaKarta.getPytanie());
		przyciskNastepnaKarta.setText("Poka¿ odpowiedŸ");
		
		czyPrezentowanaOdpowiedz = true;
	}

} // koniec klasy