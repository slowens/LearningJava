import java.io.*;
import java.net.*;

	public class Serwer {

		String[] listaPorad = {"U¿ywaj mniejszych bitów", "ChodŸ w dopasowanych spodniach. Nie, te nie sprawiaj¹, ¿e wygl¹dasz grubo.", "Jedno s³owo: nieodpowiednie", "Tylko dziœ - b¹dŸ uczciwy - powiedz swojemu szefowi, co *naprawdê*czujesz", "Mo¿e chcesz zastanowiæ siê nad swoj¹ fryzur¹."};

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