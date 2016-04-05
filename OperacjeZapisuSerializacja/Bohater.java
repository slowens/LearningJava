import java.io.*;

	public class Bohater implements Serializable {			// implementacja interfejsu s�u�acego do serializacji
		
		transient int moc; 			// �ycie s�owa kluczowego transient powoduje �e sk�adowa jest pomijana
		String typ;						// podczas procesu serializacji , zapisu i odczytu
		String[] bronie;
		
		public Bohater(int m, String t, String[] b) {
			moc = m;
			typ = t;
			bronie = b;
		}
		
		public int getMoc() {
			return moc;
		}
		
		public String getTyp() {
			return typ;
		}
		
		public String getBronie() {
			String listaBroni = "";
			for (int i = 0; i < bronie.length; i++) {
				listaBroni += bronie[i] + " ";
			}
			return listaBroni;
		}
}