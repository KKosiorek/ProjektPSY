import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimEventSemaphore;

/**
 * Created by DELL on 2017-01-19.
 */
public class SmoBis extends BasicSimObj {
    private Kolejka kolejka;
    private boolean wolne = true;
    public RozpocznijObslugeBis rozpocznijObsluge;
    public ZakonczObslugeBis zakonczObsluge;
    private MonitoredVar czasSMO;

    SimEventSemaphore semaphore;

    public SmoBis(SimEventSemaphore semaphore) {
        this.kolejka=new Kolejka(true,1,false);
        this.semaphore = semaphore;
        this.czasSMO= new MonitoredVar();
    }

    public Kolejka getKolejka() {
        return kolejka;
    }

    public void setKolejka(Kolejka kolejka) {
        this.kolejka = kolejka;
    }

    public boolean isWolne() {
        return wolne;
    }

    public void setWolne(boolean wolne) {
        this.wolne = wolne;
    }

    public RozpocznijObslugeBis getRozpocznijObsluge() {
        return rozpocznijObsluge;
    }

    public void setRozpocznijObsluge(RozpocznijObslugeBis rozpocznijObsluge) {
        this.rozpocznijObsluge = rozpocznijObsluge;
    }

    public ZakonczObslugeBis getZakonczObsluge() {
        return zakonczObsluge;
    }

    public void setZakonczObsluge(ZakonczObslugeBis zakonczObsluge) {
        this.zakonczObsluge = zakonczObsluge;
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

    public int getLiczZgl(){
        return kolejka.getSize();
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
