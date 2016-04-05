import java.io.*;

public class PomocnikGry{
	
	public String pobierzDaneWejsciowe(String komunikat){
		
		String wierszWej = null;
		System.out.println(komunikat + " ");
		
		try{
			
			BufferedReader sw = new BufferedReader(new InputStreamReader(System.in));
			wierszWej = sw.readLine();
			
			if(wierszWej.length() == 0) return null;
			
		}catch (IOException e){
			
			System.out.println("IOException: " + e);
		}
		return wierszWej;
	}
	
}