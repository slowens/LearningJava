import java.awt.*;
import javax.swing.*;
import java.rmi.*;
import java.awt.event.*;

	public class PrzegladarkaUslug {

		JPanel panelGlowny;
		JComboBox listaUslug;
		SerwerUslug serwer;
		
		public void tworzGUI() {
			JFrame ramka = new JFrame("Przegl�darka RMI");
			panelGlowny = new JPanel();
			ramka.getContentPane().add(BorderLayout.CENTER, panelGlowny);
			Object[] uslugi = pobierzListeUslug();
			listaUslug = new JComboBox(uslugi);
			ramka.getContentPane().add(BorderLayout.NORTH, listaUslug);
			listaUslug.addActionListener(new ListaUslugListener());
			ramka.setSize(500,500);
			ramka.setVisible(true);
		}
		
		void wczytajUsluge(Object wybranaUsl) {
			try {
				Usluga usl = serwer.pobierzUsluge(wybranaUsl);
				panelGlowny.removeAll();
				panelGlowny.add(usl.pobierzPanelGUI());
				panelGlowny.validate();
				panelGlowny.repaint();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		Object[] pobierzListeUslug() {
			Object obj = null;
			Object[] uslugi = null;
			try {
				obj = Naming.lookup("rmi://127.0.0.1/SerwerUslug");
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			serwer = (SerwerUslug) obj;
			
			try {
				uslugi = serwer.pobierzListeUslug();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			return uslugi;
		}

		class ListaUslugListener implements ActionListener {
			public void actionPerformed(ActionEvent ev) {
				// pobranie wybranej us�ugi
				Object wybor = listaUslug.getSelectedItem();
				wczytajUsluge(wybor);
			}	
		}
		
		public static void main(String[] args) {
			new PrzegladarkaUslug().tworzGUI();
		}
	}