import java.io.*;
		
	public class ZapisTekstowy {

		public static void main(String[] args) {
		
			FileWriter pisarz;
			
			try {
			
				pisarz = new FileWriter("Save/test.txt");
				String zmienna = "Witamy w pliku tekstowym!";
				pisarz.write(zmienna);
				pisarz.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}finally{
				;
			}
}
}
	