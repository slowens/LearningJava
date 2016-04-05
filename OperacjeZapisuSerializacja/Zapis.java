import java.io.*;

	public class Zapis {
		
		public static void main(String[] args) {
			
			Bohater postac1 = new Bohater(50, "Elf", new String[] {"	uk", "miecz", "py	"});
			Bohater postac2 = new Bohater(200, "Trol", new String[] {"pi�ci", "wielki top�r"});
			Bohater postac3 = new Bohater(120, "Magik", new String[] {"czary", "niewidzialno��"});
			// tu wykonujemy jakie� operacje, kt�re mog� zmienia� stan postaci,
			// a nast�pnie zapisujemy te obiekty

			try {
				
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Save\\Gra.txt")); 
				os.writeObject(postac1);
				os.writeObject(postac2);
				os.writeObject(postac3);
				os.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			postac1 = null;
			postac2 = null;
			postac3 = null;

			try {
				
				ObjectInputStream is = new ObjectInputStream(new FileInputStream("Save/Gra.txt"));
				Bohater p1N = (Bohater) is.readObject();
				Bohater p2N = (Bohater) is.readObject();
				Bohater p3N = (Bohater) is.readObject();
				System.out.println("Typ postaci 1.:" + p1N.getTyp());
				System.out.println("Typ postaci 2.:" + p2N.getTyp());
				System.out.println("Typ postaci 3.:" + p3N.getTyp());
				System.out.println("Moc postaci 1.:" + p1N.getMoc());					// moc nie zostanie wypisana poniewa� w kalsie Bohater
				System.out.println("Moc postaci 2.:" + p2N.getMoc());					// zosta�o u�yte s�owo kluczowe transient
				System.out.println("Moc postaci 3.:" + p3N.getMoc());
			} catch (Exception ex) {
				ex.printStackTrace ();
			}
		}
	}