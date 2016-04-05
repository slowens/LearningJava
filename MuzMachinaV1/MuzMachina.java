import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;


	public class MuzMachina implements MetaEventListener {
		JPanel panelGlowny;
		ArrayList<JCheckBox> listaPolWyboru;
		Sequencer sekwenser;
		Sequence sekwencja;
		Track sciezka;
		JFrame ramkaGlowna;
		String[] nazwyInstrumentow = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat","Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom","High Agogo", "Open Hi Conga"};
		int[] instrumenty = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	
	public static void main (String[] args) {
		new MuzMachina().tworzGUI();
	}

	public void meta(MetaMessage meta){
		;
	}
	
	public void tworzGUI() {
		ramkaGlowna = new JFrame("MuzMachina");
		ramkaGlowna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BorderLayout uklad = new BorderLayout();
		
		JPanel panelTla = new JPanel(uklad);
		panelTla.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		listaPolWyboru = new ArrayList<JCheckBox>();
		
		Box lewyObszarGlowny = new Box(BoxLayout.Y_AXIS);
		Box obszarPrzyciskow = new Box(BoxLayout.Y_AXIS);
		
		lewyObszarGlowny.add(obszarPrzyciskow);
		JButton start = new JButton("Start");
		start.addActionListener(new MojStartListener());
		obszarPrzyciskow.add(start);
		JButton stop = new JButton("Stop");
		stop.addActionListener(new MojStopListener());
		obszarPrzyciskow.add(stop);
		JButton tempoG = new JButton("Szybciej");
		tempoG.addActionListener(new MojTempoGListener());
		obszarPrzyciskow.add(tempoG);
		JButton tempoD = new JButton("Wolniej");
		tempoD.addActionListener(new MojTempoDListener());
		obszarPrzyciskow.add(tempoD);
		JButton zapisz = new JButton("Zapisz");
		zapisz.addActionListener(new ZapiszListener());
		obszarPrzyciskow.add(zapisz);
		JButton wczytaj = new JButton("Wczytaj");
		wczytaj.addActionListener(new OdtworzListener());
		obszarPrzyciskow.add(wczytaj);
		
		Box obszarNazw = new Box(BoxLayout.Y_AXIS);
		
		for (int i = 0; i < 16; i++) {
			obszarNazw.add(new Label(nazwyInstrumentow[i]));
		}
		
		panelTla.add(BorderLayout.EAST, obszarPrzyciskow);
		panelTla.add(BorderLayout.WEST, obszarNazw);
		ramkaGlowna.getContentPane().add(panelTla);
		GridLayout siatkaPolWyboru = new GridLayout(16,16);
		siatkaPolWyboru.setVgap(1);
		siatkaPolWyboru.setHgap(2);
		panelGlowny = new JPanel(siatkaPolWyboru);
		panelTla.add(BorderLayout.CENTER, panelGlowny);

		for (int i = 0; i < 256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			listaPolWyboru.add(c);
			panelGlowny.add(c);
		} // koniec pêtli
		
		konfigurujMidi();
		ramkaGlowna.setBounds(50,50,300,300);
		ramkaGlowna.pack();
		ramkaGlowna.setVisible(true);
	} // koniec metody
	

	public void konfigurujMidi() {

		try {
			sekwenser = MidiSystem.getSequencer();
			sekwenser.open();
			sekwencja = new Sequence(Sequence.PPQ,4);
			sciezka = sekwencja.createTrack();
			sekwenser.setTempoInBPM(120);
		} catch(Exception e) {e.printStackTrace();}
	} // koniec metody
	
	
	public void utworzSciezkeIOdtworz() {
		
		int[] listaSciezki = null;
		sekwencja.deleteTrack(sciezka);
		sciezka = sekwencja.createTrack();
		
		for (int i = 0; i < 16; i++) {
			listaSciezki = new int[16];
			int klucz = instrumenty[i];
			
			for (int j = 0; j < 16; j++ ) {
				JCheckBox jc = (JCheckBox) listaPolWyboru.get(j + (16*i));
				
				if ( jc.isSelected()) {
					
					listaSciezki[j] = klucz;
				} else {
					listaSciezki[j] = 0;
				}
				
			} // koniec pêtli wewnêtrznej

			utworzSciezke(listaSciezki);
			sciezka.add(tworzZdarzenie(176,1,127,0,16));
		} // koniec pêtli zewnêtrznej
		
		sciezka.add(tworzZdarzenie(192,9,1,0,15));
			
			try {
				sekwenser.setSequence(sekwencja);
				sekwenser.setLoopCount(sekwenser.LOOP_CONTINUOUSLY);
				sekwenser.start();
				sekwenser.setTempoInBPM(120);
			} catch(Exception e) {e.printStackTrace();}
	} // koniec metody

	public class MojStartListener implements ActionListener {
		
		public void actionPerformed(ActionEvent a) {
			utworzSciezkeIOdtworz();
		}
	} // koniec klasy wewnêtrznej
	
	
	public class MojStopListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			sekwenser.stop();
		}
	} // koniec klasy wewnêtrznej

	public class MojTempoGListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float wspTempa = sekwenser.getTempoFactor();
			double wspt = (double)wspTempa * 1.03;
			sekwenser.setTempoFactor((float) wspt);
		}
	} // koniec klasy wewêntrznej

	public class MojTempoDListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float wspTempa = sekwenser.getTempoFactor();
			double wspt = (double)wspTempa * 0.97;
			sekwenser.setTempoFactor((float)wspt);
		}
	} // koniec klasy wewnêtrznej

	public void utworzSciezke(int[] lista) {
		
		for (int i = 0; i < 16; i++) {
		int klucz = lista[i];
			if (klucz != 0) {
				sciezka.add(tworzZdarzenie(144,9,klucz, 100, i));
				sciezka.add(tworzZdarzenie(128,9,klucz, 100, i+1));
			}
		}
	}
	
	public static MidiEvent tworzZdarzenie(int plc, int kanal, int jeden, int dwa, int takt) {
		
		MidiEvent zdarzenie = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(plc, kanal, jeden, dwa);
			zdarzenie = new MidiEvent(a, takt);
		} catch(Exception e) { e.printStackTrace(); }
			return zdarzenie;
		} // koniec metody

		
	public class ZapiszListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// tworzymy tablicê ze stranami pól wyboru

			boolean[] stanyPol = new boolean[256];
			for (int i = 0; i < 256; i++) {
				JCheckBox pole = (JCheckBox) listaPolWyboru.get(i);
				if (pole.isSelected()) {
				stanyPol[i] = true;
				}
			}
			try {
				
				JFileChooser plikDanych = new JFileChooser();
				plikDanych.showSaveDialog(ramkaGlowna);
				
				FileOutputStream strumienPlk = new FileOutputStream(plikDanych.getSelectedFile());
				ObjectOutputStream os = new ObjectOutputStream(strumienPlk);
				os.writeObject(stanyPol);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		} // koniec metody
	} //koniec klasy		
		
		
	public class OdtworzListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			
			boolean[] stanyPol = null;
			try {
				
				JFileChooser plikDanych = new JFileChooser();
				plikDanych.showSaveDialog(ramkaGlowna);
			
				FileInputStream strumienPlk = new FileInputStream(plikDanych.getSelectedFile());
				ObjectInputStream is = new ObjectInputStream(strumienPlk);
				stanyPol = (boolean[]) is.readObject();
			} catch(Exception ex) {ex.printStackTrace();}
			for (int i = 0; i < 256; i++) {
				JCheckBox pole = (JCheckBox) listaPolWyboru.get(i);
				if (stanyPol[i]) {
					pole.setSelected(true);
				} else {
					pole.setSelected(false);
				}
			}
			sekwenser.stop();
			utworzSciezkeIOdtworz();
		} // koniec metody
	} // koniec klasy wewnêtrznej
		
		
} // koniec klasy
	
	
	
	
	
	
	
	
	