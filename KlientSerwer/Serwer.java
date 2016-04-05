import java.io.*;
import java.net.*;

	public class Serwer {

		String[] listaPorad = {"U�ywaj mniejszych bit�w", "Chod� w dopasowanych spodniach. Nie, te nie sprawiaj�, �e wygl�dasz grubo.", "Jedno s�owo: nieodpowiednie", "Tylko dzi� - b�d� uczciwy - powiedz swojemu szefowi, co *naprawd�*czujesz", "Mo�e chcesz zastanowi� si� nad swoj� fryzur�."};

		public void doDziela() {

			try {
	
				ServerSocket gniazdoSrw = new ServerSocket(4242);
				
				while(true) {
				
					Socket gniazdo = gniazdoSrw.accept();
					
					PrintWriter pisarz = new PrintWriter(gniazdo.getOutputStream());
					String porada = wybierzPorade();
					pisarz.println(porada);
					pisarz.close();
					System.out.println(porada);
				}
			
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		} // koniec metody

		public static void main(String[] args) {
			
			Serwer serwer = new Serwer();
			serwer.doDziela();
		}

		private String wybierzPorade() {
		
			int random = (int) (Math.random() * listaPorad.length);
			return listaPorad[random];
		}
	}