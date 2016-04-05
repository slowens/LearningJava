class testPies {
	
	public static void main(String[] args){
		
		pies p = new pies();
		
		p.szczekaj();
		p.wielkosc = 20;
		p.imie = "azor";
		p.rasa = "aryjska";
		p.wiek = 2;
		
		System.out.println(p.imie);
		System.out.println(p.rasa);
		System.out.println(p.wielkosc);
		System.out.println(p.wiek);
	}
	
}