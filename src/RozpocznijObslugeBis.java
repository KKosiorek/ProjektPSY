import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters;

/**
 * Created by DELL on 2017-01-19.
 */
public class RozpocznijObslugeBis extends BasicSimEvent<SmoBis,Zgloszenie> {
    private SmoBis smoParent;
    private SimGenerator generator;

    RozpocznijObslugeBis(SmoBis smoParent,double delay) throws SimControlException {
        super(smoParent,delay);
        generator=new SimGenerator();
        this.smoParent=smoParent;

    }
    RozpocznijObslugeBis(SmoBis smoParent) throws SimControlException {
        super(smoParent);
        generator=new SimGenerator();
        this.smoParent=smoParent;
    }

    @Override
    protected void stateChange() throws SimControlException {
        // Zablokuj gniazdo
        smoParent.setWolne(false);
        // Pobierz zgłoszenie
        Zgloszenie zgl = smoParent.getKolejka().usun();
        System.out.println(simTime()+" - "+simDate(SimParameters.SimDateField.HOUR24)+" - "+simDate(SimParameters.SimDateField.MINUTE)+" - "+simDate(SimParameters.SimDateField.SECOND)+" - "+simDate(SimParameters.SimDateField.MILLISECOND)+": SMOBIS- rozpocznij obsluge zgl. nr: " + zgl.getTenNr());
        if(smoParent.getLiczZgl()==smoParent.getKolejka().getMaxSize()-1){
            smoParent.getSemaphore().open();
        }
        // Wygeneruj czas obsługi
        double czasObslugi = generator.normal(9.0, 1.0);
        //System.out.println(simTime()+" - "+simDate(SimParameters.SimDateField.HOUR24)+" - "+simDate(SimParameters.SimDateField.MINUTE)+" - "+simDate(SimParameters.SimDateField.SECOND)+" - "+simDate(SimParameters.SimDateField.MILLISECOND)+": SMO- Początek obsługi zgl. nr: " + zgl.getTenNr());
        // Zaplanuj koniec obsługi
        smoParent.zakonczObsluge = new ZakonczObslugeBis(smoParent, czasObslugi, zgl);
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
