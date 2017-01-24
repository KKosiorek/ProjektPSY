import com.sun.nio.zipfs.ZipFileAttributes;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters;

/**
 * Created by DELL on 2017-01-19.
 */
public class ZakonczObslugeBis extends BasicSimEvent<SmoBis,Zgloszenie> {
    private SmoBis smoParent;
    private SimGenerator generator;

    public ZakonczObslugeBis(SmoBis smoParent,double delay,Zgloszenie zgl) throws SimControlException {
        super(smoParent,delay,zgl);
        this.smoParent=smoParent;
    }
    @Override
    protected void stateChange() throws SimControlException {
        smoParent.setWolne(true);
        smoParent.getCzasSMO().setValue(simTime()-transitionParams.getCzasOdniesienia());
        System.out.println(simTime()+" - "+simDate(SimParameters.SimDateField.HOUR24)+" - "+simDate(SimParameters.SimDateField.MINUTE)+" - "+simDate(SimParameters.SimDateField.SECOND)+" - "+simDate(SimParameters.SimDateField.MILLISECOND)+": SMOBis- Koniec obslugi zgl. nr: " + transitionParams.getTenNr());
        if(smoParent.getLiczZgl()>0){
            smoParent.rozpocznijObsluge = new RozpocznijObslugeBis(smoParent);
        }
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
