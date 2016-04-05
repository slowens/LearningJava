import java.util.ArrayList;

public class PortalGra{
	
	
		
		private PomocnikGry pomocnik  = new PomocnikGry();
		private ArrayList<Portal> listaPortali = new ArrayList<Portal>();
		private int iloscRuchow = 0;
		
		private void przygotujGre()
		{
			Portal pierwszy = new Portal();
			pierwszy.setNazwa("onet.pl");
			
			Portal drugi = new Portal();
			drugi.setNazwa("interia.pl");
			
			Portal trzeci = new Portal();
			trzeci.setNazwa("wp.pl");
			
			listaPortali.add(pierwszy);
			listaPortali.add(drugi);
			listaPortali.add(trzeci);			
			
			System.out.println("Twoim celem jest zatopienie trzech portali !");
			System.out.println("onet.pl, wp.pl, interia.pl");
			System.out.println("Musisz zatopić je przy jak najmniejszej liczbie ruchów");
			
			for(Portal rozmieszczanyPortal : listaPortali){
				
				ArrayList<String> nowePolozenie = pomocnik.rozmiescPortal(3);
				rozmieszczanyPortal.setPolaPolozenia(nowePolozenie);
				
			}
			
			
		}
	
	private void rozpocznijGre(){
		
		while(!listaPortali.isEmpty()){
			
			String ruchGracza = pomocnik.pobierzDaneWejsciowe("Podaj pole:");
			sprawdzRuchGracza(ruchGracza);
		}
		zakonczGre();
	}
	
	private void sprawdzRuchGracza(String ruch) {
		iloscRuchow++;
		String wynik = "pudło";
		
		for (Portal portalDoSprawdzenia : listaPortali) {
			
			wynik = portalDoSprawdzenia.sprawdz(ruch);
			if (wynik.equals("trafiony")) {
			break;
			}

		if (wynik.equals("zatopiony")) {
		
		listaPortali.remove(portalDoSprawdzenia);
		break;
		}
	} 
	System.out.println(wynik);
} 

private void zakonczGre() {
System.out.println("Wszystkie Portale zostały zatopione! Teraz Twoje informacje nie mają znaczenia.");

if (iloscRuchow <= 18) {
System.out.println("Wykonałeś jedynie " + iloscRuchow + " ruchów.");
System.out.println("Wydostałeś się, zanim Twoje informacje zatoneły.");
} else {
System.out.println("Ale się grzebałeś. Wykonałeś "+ iloscRuchow + " ruchów.");
System.out.println("Teraz rybki pływają pomiędzy Twoimi informacjami.");
}
} 
public static void main (String[] args) {
	
PortalGra gra = new PortalGra();
gra.przygotujGre();
gra.rozpocznijGre();
} // koniec metody
	
}