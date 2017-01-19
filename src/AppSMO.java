/**
 * @author Dariusz Pierzchala
 * 
 * Description: Klasa główna. Tworzy dwa SMO, inicjalizuje je.Startuje symulację. Wyświetla statystyki.
 * 
 * Wersja testowa.
 */

import dissimlab.monitors.Diagram;
import dissimlab.monitors.Statistics;
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
			SimControlEvent stopEvent = new SimControlEvent(1000.0, SimControlStatus.STOPSIMULATION);
			// Uruchomienie symulacji za pośrednictwem metody "startSimulation" 
			model.startSimulation();
			System.out.println(smo.getKolejka().getLiczbaOdrzuconych());
			System.out.println(smo.getLiczbaZgloszonych());
			System.out.println((double)smo.getKolejka().getLiczbaOdrzuconych()/smo.getLiczbaZgloszonych());
			System.out.println("Maksymalny czas obsługi:"+Statistics.max(smo.getCzasSMO()));
			System.out.println("Odchylenie standardowe czasu obslugi "+Statistics.standardDeviation(smo.getCzasSMO()));

			Diagram d1 = new Diagram(Diagram.DiagramType.DISTRIBUTION,
					"Dystyrbuanta czasu obslugi");
			d1.add(smo.getCzasSMO(), java.awt.Color.BLUE);
			d1.show();

			Diagram d3 = new Diagram(Diagram.DiagramType.HISTOGRAM,
					"Histogram dlugosc kolejki ");
			d3.add(smo.getKolejka().getDlKolejki(), java.awt.Color.BLUE);
			d3.show();
		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
