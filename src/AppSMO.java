/**
 * @author Dariusz Pierzchala
 * 
 * Description: Klasa główna. Tworzy dwa SMO, inicjalizuje je.Startuje symulację. Wyświetla statystyki.
 * 
 * Wersja testowa.
 */

import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;

import java.util.Scanner;

public class AppSMO {
	public static void main(String[] args) {
		try {
			SimManager model = SimManager.getInstance();
			// Powołanie Smo 
			Smo smo = new Smo();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Podaj p-stwo powrotu zgłoszenia z obslugi: ");
			smo.setPrawdopodobienstwo(scanner.nextDouble());

			// Utworzenie otoczenia
			Otoczenie generatorZgl = new Otoczenie(smo);
			// Dwa sposoby zaplanowanego końca symulacji
			//model.setEndSimTime(10000);
			// lub
			SimControlEvent stopEvent = new SimControlEvent(100.0, SimControlStatus.STOPSIMULATION);
			// Uruchomienie symulacji za pośrednictwem metody "startSimulation" 
			model.startSimulation();
			System.out.println(smo.getKolejka().getLiczbaOdrzuconych());
			System.out.println(smo.getLiczbaZgloszonych());
			System.out.println((double)smo.getKolejka().getLiczbaOdrzuconych()/smo.getLiczbaZgloszonych());
		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
