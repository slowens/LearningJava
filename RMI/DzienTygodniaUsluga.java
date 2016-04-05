import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.text.*;


	public class DzienTygodniaUsluga implements Usluga {

		JLabel etykietaWyniku;
		JComboBox miesiac;
		JTextField dzien;
		JTextField rok;

		public JPanel pobierzPanelGUI() {

			JPanel panel = new JPanel();
			JButton przycisk = new JButton("Wyznacz!");
			przycisk.addActionListener(new WyznaczListener());
			etykietaWyniku = new JLabel("tu jest wyœwietlana data");
			DateFormatSymbols dateStuff = new DateFormatSymbols();
			miesiac = new JComboBox(dateStuff.getMonths());
			dzien = new JTextField(8);
			rok = new JTextField(8);
			JPanel inputPanel = new JPanel(new GridLayout(3,2));

			inputPanel.add(new JLabel("Dzieñ"));
			inputPanel.add(dzien);
			inputPanel.add(new JLabel("Miesi¹c"));
			inputPanel.add(miesiac);
			inputPanel.add(new JLabel("Rok"));
			inputPanel.add(rok);
			panel.add(inputPanel);
			panel.add(przycisk);
			panel.add(etykietaWyniku);
			return panel;
		}
		
		public class WyznaczListener implements ActionListener {
		
			public void actionPerformed(ActionEvent ev) {
				int miesiacNum = miesiac.getSelectedIndex();
				int dzienNum = Integer.parseInt(dzien.getText());
				int rokNum = Integer.parseInt(rok.getText());
				Calendar k = Calendar.getInstance();
				k.set(Calendar.MONTH, miesiacNum);
				k.set(Calendar.DAY_OF_MONTH, dzienNum);
				k.set(Calendar.YEAR, rokNum);
				Date date = k.getTime();
				String dzienTygodnia = (new SimpleDateFormat("EEEE")).format(date);
				etykietaWyniku.setText(dzienTygodnia);
			}
		}
	}