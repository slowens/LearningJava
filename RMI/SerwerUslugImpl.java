import java.rmi.*;
import java.util.*;
import java.rmi.server.*;


	public class SerwerUslugImpl extends UnicastRemoteObject implements SerwerUslug {

		HashMap listaUslug;
		
		public SerwerUslugImpl() throws RemoteException {
			konfigurujUslugi();

		}

		private void konfigurujUslugi() {

			listaUslug = new HashMap();
			listaUslug.put("Us³uga gry w koœci", new GraWKosciUsluga());
			listaUslug.put("Us³uga dnia tygodnia", new DzienTygodniaUsluga());
			listaUslug.put("Us³uga wizualizacji muzyki", new MiniMuzykaUsluga());
		}

		public Object[] pobierzListeUslug() {
			System.out.println("zdalne");
			return listaUslug.keySet().toArray();
		}
		
		public Usluga pobierzUsluge(Object kluczUsl) throws RemoteException {
			Usluga usluga = (Usluga) listaUslug.get(kluczUsl);
			return usluga;
		}
		
		public static void main (String[] args) {
			try {
				Naming.rebind("SerwerUslug", new SerwerUslugImpl());
			} catch(Exception ex) { }
				System.out.println("Zdalna us³uga uruchomiona");
		}	
		
	}		