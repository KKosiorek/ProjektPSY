/**
 * @author Dariusz Pierzchala
 * 
 * Description: Description: Klasa gniazda obsługi obiektów - zgłoszeń 
 */

import java.util.LinkedList;


import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimEventSemaphore;
import dissimlab.simcore.SimControlException;



public class Smo extends BasicSimObj
{
    private Kolejka kolejka;
    private static int liczbaZgloszonych=0;
    private double prawdopodobienstwo;
	private boolean wolne = true;
    public RozpocznijObsluge rozpocznijObsluge;
    public ZakonczObsluge zakonczObsluge;
    private MonitoredVar czasSMO;
    private SimEventSemaphore semaphore;
    private SmoBis smoBis;
	
    /** Creates a new instance of Smo 
     * @throws SimControlException */
    public Smo(SmoBis smoBis,SimEventSemaphore semaphore) throws SimControlException
    {
        // Utworzenie wewnętrznej listy w kolejce
        this.smoBis=smoBis;
        kolejka = new Kolejka(true,10,false);
        this.czasSMO=new MonitoredVar();
        this.semaphore=semaphore;
    }

    public SmoBis getSmoBis() {
        return smoBis;
    }

    public void setSmoBis(SmoBis smoBis) {
        this.smoBis = smoBis;
    }

    public int liczbaZgl()
    {
        return kolejka.getSize();
    }

	public boolean isWolne() {
		return wolne;
	}

	public void setWolne(boolean wolne) {
		this.wolne = wolne;
	}

    public Kolejka getKolejka() {
        return kolejka;
    }

    @Override
	public void reflect(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean filter(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

    public static int getLiczbaZgloszonych() {
        return liczbaZgloszonych;
    }

    public static void setLiczbaZgloszonych(int liczbaZgloszonych) {
        Smo.liczbaZgloszonych = liczbaZgloszonych;
    }

    public double getPrawdopodobienstwo() {
        return prawdopodobienstwo;
    }

    public void setPrawdopodobienstwo(double prawdopodobienstwo) {
        this.prawdopodobienstwo = prawdopodobienstwo;
    }

    public MonitoredVar getCzasSMO() {
        return czasSMO;
    }

    public void setCzasSMO(MonitoredVar czasSMO) {
        this.czasSMO = czasSMO;
    }

    public SimEventSemaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(SimEventSemaphore semaphore) {
        this.semaphore = semaphore;
    }
}