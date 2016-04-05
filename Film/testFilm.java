public class testFilm{
	
	public static void main(String[] args){
		
		film p = new film();
		film o = new film();
		film s = new film();
		
		p.ocena = 5;
		p.tytul = "Fajny Film";
		p.rokprod = 1999;
		p.gatunek = "komedia";
		
		
		s.ocena = 3;
		s.tytul = "Stary Film";
		s.rokprod = 1978;
		s.gatunek = "horror";
		
		o.ocena = 9;
		o.tytul = "Film3";
		o.rokprod = 2007;
		o.gatunek = "thriller";
		
		System.out.println("Pierwszy film to: " + p.tytul + " ocena: "  + p.ocena + " gatunek: " + p.gatunek + " rok produkcji: " + p.rokprod);
		
		System.out.println("Drugi film to: " + s.tytul + " ocena: "  + s.ocena + " gatunek: " + s.gatunek + " rok produkcji: " + s.rokprod);
		
		System.out.println("Trzeci film to: " + o.tytul + " ocena: "  + o.ocena + " gatunek: " + o.gatunek + " rok produkcji: " + o.rokprod);
		
		System.out.println("");
		
		p.podajtytul();
		s.podajtytul();
		o.podajtytul();
		
	}
	
}