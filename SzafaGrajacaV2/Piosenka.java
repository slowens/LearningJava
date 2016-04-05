class Piosenka implements Comparable<Piosenka>{
	
	String tytul;
	String artysta;
	String ocena;
	String bpm;
	
	public int compareTo(Piosenka o)
	{
		return (tytul.compareTo(o.getTytul())) ;
	}

	Piosenka(String t, String a, String o, String b) {
		
		artysta = a;
		ocena = o;
		bpm = b;
		tytul = t;
	}
	
	public String getTytul() {
		return tytul;
	}

	public String getArtysta() {
		return artysta;
	}

	public String getOcena() {
		return ocena;
	}
	
	public String getBpm() {
		return bpm;
	}
	
	public String toString() {
		return tytul;
	}
	
}