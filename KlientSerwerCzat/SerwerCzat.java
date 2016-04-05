import java.io.*;
import java.net.*;
import java.util.*;

	public class SerwerCzat {

		ArrayList strumienieWyjsciowe;

		
		public class ObslugaKlienta implements Runnable {
			
			BufferedReader czytelnik;
			Socket gniazdo;
			
			public ObslugaKlienta(Socket clientSocket) {
				
				try {
					
					gniazdo = clientSocket;
					InputStreamReader isReader = new InputStreamReader(gniazdo.getInputStream());
					czytelnik = new BufferedReader(isReader);
				} catch(Exception ex) {ex.printStackTrace();}
			} // koniec konstruktora
		
		
		public void run() {
			
			String wiadomosc;
				
				try {
					while ((wiadomosc = czytelnik.readLine()) != null) {
						
						System.out.println("Odczytano: " + wiadomosc);
						rozeslijDoWszystkich(wiadomosc);
					} // koniec pêtli
				} catch(Exception ex) {ex.printStackTrace();}
			} // koniec metody
		}
		
		public void doDziela() {

			strumienieWyjsciowe = new ArrayList();
		
			try {
	
				ServerSocket gniazdoSrw = new ServerSocket(5000);
				
				while(true) {
				
					Socket gniazdoKlienta = gniazdoSrw.accept();
					
					PrintWriter pisarz = new PrintWriter(gniazdoKlienta.getOutputStream());
					strumienieWyjsciowe.add(pisarz);
					
					Thread t = new Thread(new ObslugaKlienta(gniazdoKlienta));
					t.start();
					System.out.println("mamy po³¹czenie");
					
				}
			
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		} // koniec metody
		
		
		
		public void rozeslijDoWszystkich(String message) {
			
			Iterator it = strumienieWyjsciowe.iterator();
				
			while(it.hasNext()) {
					try {
						
						PrintWriter pisarz = (PrintWriter) it.next();
						pisarz.println(message);
						pisarz.flush();
					} catch(Exception ex) {
						ex.printStackTrace();
					}
				} // koniec pêtli
			} // koniec metody

		public static void main(String[] args) {
			
			SerwerCzat serwer = new SerwerCzat();
			serwer.doDziela();
		}

	}