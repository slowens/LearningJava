class Zgadywanka{
	
	Gracz g1;
	Gracz g2;
	Gracz g3;
	
	public void rozpocznijGre(){
		
		g1 = new Gracz();
		g2 = new Gracz();
		g3 = new Gracz();		
		
		int typg1 = 0;
		int typg2 = 0;
		int typg3 = 0;
		
		boolean g1zgad = false;
		boolean g2zgad = false;
		boolean g3zgad = false;
		
		int odgadywana = (int)(Math.random() * 10);
		System.out.println("Liczba do wytypowania miesci sie w zakresie od 0 do 9");
		
		while(true){
			
			System.out.println("Liczba do wytypowania: " + odgadywana);
			
			g1.zgaduj();
			g2.zgaduj();
			g3.zgaduj();
			
			typg1 = g1.liczba;
			System.out.println("Gracz nr 1 wytypowa� liczb�: " + typg1);
			
			typg2 = g2.liczba;
			System.out.println("Gracz nr 2 wytypowa� liczb�: " + typg2);
			
			typg3 = g3.liczba;
			System.out.println("Gracz nr 3 wytypowa� liczb�: " + typg3);
			
			
			if (typg1 == odgadywana){
				g1zgad = true;
			}
			
			if (typg2 == odgadywana){
				g2zgad = true;
			}
			
			if (typg3 == odgadywana){
				g3zgad = true;
			}
			
			
			if((g1zgad == true) || (g2zgad == true) || (g3zgad == true)){
				
				System.out.println("Gra sko�czona oto wyniki:");
				System.out.println("Gracz nr 1 wytypowa�: " + typg1);
				System.out.println("Gracz nr 2 wytypowa�: " + typg2);
				System.out.println("Gracz nr 3 wytypowa�: " + typg3);
				System.out.println("Koniec Gry!");
				break;
				
			}else{
				
				System.out.println("Niestety nikt nie odgad� poprawnie.");
				System.out.println("Spr�b�jmy zatem jeszcze raz");
				
			}
			
			
			
		}
		
		
	}
	
	
}