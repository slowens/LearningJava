import java.io.Serializable;

class KartaQuizowa implements Serializable{
	
	private String pytanie;
	private String odpowiedz;
	
	
	KartaQuizowa(String pyt, String odp){
		pytanie = pyt;
		odpowiedz =odp;
	}
		
	public String getOdpowiedz(){
		return odpowiedz;
	}
	
	public String getPytanie(){
		return pytanie;
	}
		
	public void setOdpowiedz(String odp){
		odpowiedz = odp;
	}
	
	public void setPytanie(String pyt){
		pytanie = pyt;
	}
	
}