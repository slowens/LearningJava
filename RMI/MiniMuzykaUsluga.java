import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


	public class MiniMuzykaUsluga implements Usluga {
		
		MojPanelRysunkowy mojPanel;

		public JPanel pobierzPanelGUI() {

			JPanel panelGlowny = new JPanel();
			mojPanel = new MojPanelRysunkowy();
			JButton przyciskZagraj = new JButton("Zagraj!");
			przyciskZagraj.addActionListener(new ZagrajListener());
			panelGlowny.add(mojPanel);
			panelGlowny.add(przyciskZagraj);
			return panelGlowny;
		}

		public class ZagrajListener implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
				try {
					Sequencer sekwenser = MidiSystem.getSequencer();
					sekwenser.open();
					sekwenser.addControllerEventListener(mojPanel, new int[] {127});
					Sequence sekwencja = new Sequence(Sequence.PPQ, 4);
					Track track = sekwencja.createTrack();
						
						for (int i = 0; i < 100; i+= 4) {
							int rNum = (int) ((Math.random() * 50) + 1);
					
							/*
							if (rNum < 38) { // dalsze czynnoœci wykonujemy, je¿eli num <38 (75% czasu)
								track.add(makeEvent(144,1,rNum,100,i));
								track.add(makeEvent(176,1,127,0,i));
								track.add(makeEvent(128,1,rNum,100,i + 2));
							}
							*/
						} // koniec pêtli

						sekwenser.setSequence(sekwencja);
						sekwenser.start();
						sekwenser.setTempoInBPM(220);
				} catch (Exception ex) {ex.printStackTrace();}
			} // koniec metody actionPerformed
		} // koniec klasy wewnêtrznej

		public MidiEvent twozZdarzenie(int plc, int kanal, int jeden, int dwa, int takt) {

			MidiEvent zdarzenie = null;
			try {
				ShortMessage a = new ShortMessage();
				a.setMessage(plc, kanal, jeden, dwa);
				zdarzenie = new MidiEvent(a, takt);
			}catch(Exception e) { }
			return zdarzenie;
		}

		class MojPanelRysunkowy extends JPanel implements ControllerEventListener {
		// rysujemy tylko wtedy, je¿li odebraliœmy zdarzenie
			
			boolean kmk = false;
			
			public void controlChange(ShortMessage event) {
				kmk = true;
				repaint();
			}
	
			public Dimension getPreferredSize() {
				return new Dimension(300,300);
			}

			public void paintComponent(Graphics g) {
				if (kmk) {
					Graphics2D g2 = (Graphics2D) g;
					int r = (int) (Math.random() * 250);
					int gr = (int) (Math.random() * 250);
					int b = (int) (Math.random() * 250);
					g.setColor(new Color(r,gr,b));
					int wys = (int) ((Math.random() * 120) + 10);
					int szer = (int) ((Math.random() * 120) + 10);
					int x = (int) ((Math.random() * 40) + 10);
					int y = (int) ((Math.random() * 40) + 10);
					g.fillRect(x,y,wys, szer);
					kmk = false;
				} // koniec if
			} // koniec metody
		} // koniec klasy wewnêtrznej
	} // koniec klasy