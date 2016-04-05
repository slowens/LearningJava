import java.util.*;
import java.io.*;


	public class SzafaGrajaca2 {

	
		ArrayList<Piosenka> listaPiosenek = new ArrayList<Piosenka>();

		public static void main(String[] args) {

			SzafaGrajaca2 szafka = new SzafaGrajaca2();
			szafka.doDziela();
		}

		public void doDziela() {

			pobierzPiosenki();
			Collections.sort(listaPiosenek);  // sortowanie piosenek alfabetycznie
			for(Piosenka piosenka : listaPiosenek){
			System.out.println(piosenka);
			}
		}

		void pobierzPiosenki() {

			try {

				File plik = new File("ListaPiosenek.txt");
				BufferedReader reader = new BufferedReader(new FileReader(plik));
				String wiersz = null;
				while ((wiersz= reader.readLine()) != null) {
					dodajPiosenke(wiersz);
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		void dodajPiosenke(String wierszDoAnalizy) {

			String[] elementy = wierszDoAnalizy.split("/");
			Piosenka nowaPiosenka = new Piosenka(elementy[0], elementy[1], elementy[2], elementy[3]);
			listaPiosenek.add(nowaPiosenka);
		}
	}