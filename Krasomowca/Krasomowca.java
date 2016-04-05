public class Krasomowca {
public static void main (String[] args) {

String[] listaSlow1 = {"architektura wielowarstwowa", "30000 metrów", "rozwiązanie B-do-B", "aplikacja kliencka", "interfejs internetowy","inteligentna karta", "rozwiązaniedynamiczne", "sześć sigma", "przenikliwość"};

String[] listaSlow2 = {"zwiększa możliwości", "poprawia atrakcyjność",
"pomnaża wartość", "opracowana dla", "bazująca na", "rozproszona",
"sieciowa", "skoncentrowana na", "podniesiona na wyższy poziom", "skierowanej", "udostępniona"};

String[] listaSlow3 = {"procesu", "punktu wpisywania", "rozwiązania","strategii", "paradygmatu", "portalu", "witryny", "wersji", "misji"};

int lista1D = listaSlow1.length;
int lista2D = listaSlow2.length;
int lista3D = listaSlow3.length;

int rand1 = (int) (Math.random() *lista1D);
int rand2 = (int) (Math.random() *lista2D);
int rand3 = (int) (Math.random() *lista3D);

String zdanie = listaSlow1[rand1] + " " + listaSlow2[rand2] + " " + listaSlow3[rand3];


System.out.println("Generowane zdanie: ");
System.out.println(zdanie);

}
}

