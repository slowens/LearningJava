import java.rmi.*;

	public interface SerwerUslug extends Remote {
	
		Object[] pobierzListeUslug() throws RemoteException;
		Usluga pobierzUsluge(Object serviceKey) throws RemoteException;
	}