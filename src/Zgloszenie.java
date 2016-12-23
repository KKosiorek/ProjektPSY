
import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

/**
 * Description: Klasa zgloszenia obsługiwanego w gnieździe obsługi.
 * 
 * @author Dariusz Pierzchala
 */

public class Zgloszenie extends BasicSimObj
{
    double czasOdniesienia;
    static int nr=0;
    private SimGenerator generator;

    int tenNr;
    StartNiecierpliwienia startNiecierpliwienia;
    public KoniecNiecierpliwienia koniecNiecierpliwosci;
    public Smo smo;
	private int priorytet;
    

	public Zgloszenie(double Czas, Smo smo) throws SimControlException
    {
        this.generator=new SimGenerator();
        czasOdniesienia = Czas;
        setTenNr();
        this.smo = smo;
        this.priorytet=generator.uniformInt(1,10);

    }



    @Override
    public String toString() {
        return "Zgloszenie{" +
                "tenNr=" + tenNr +
                ", priorytet=" + priorytet +
                '}';
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

    public void setStartNiecierpliwienia(StartNiecierpliwienia startNiecierpliwienia) {
        this.startNiecierpliwienia = startNiecierpliwienia;
    }

    public void setTenNr() {
		this.tenNr = nr++;
	}

	public int getTenNr() {
		return tenNr;
	}

	public int getPriorytet(){
		return priorytet;
	}

}