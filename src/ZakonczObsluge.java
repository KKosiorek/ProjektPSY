/**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventSemaphore;
import dissimlab.simcore.SimParameters.SimDateField;

public class ZakonczObsluge extends BasicSimEvent<Smo, Zgloszenie>
{
    private Smo smoParent;
	private SimGenerator generator;

    public ZakonczObsluge(Smo parent, double delay, Zgloszenie zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
        this.smoParent = parent;
		generator=new SimGenerator();
    }

    public ZakonczObsluge(Smo parent, SimEventSemaphore semafor, Zgloszenie zgl) throws SimControlException
    {
    	super(parent, semafor, zgl);
        this.smoParent = parent;
		generator=new SimGenerator();
    }
    
	@Override
	protected void onInterruption() throws SimControlException {
		// TODO
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {

        System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": SMO- Koniec obsługi zgl. nr: " + transitionParams.getTenNr());
        	if((!generator.probability(smoParent.getPrawdopodobienstwo()))&&transitionParams.isObsluzony()==false){
				smoParent.setWolne(true);
				System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": SMO- Powrot zgl. nr: " + transitionParams.getTenNr());
        		if(smoParent.getKolejka().dodaj(transitionParams)){
					System.out.println(simTime() + " - " + simDate(SimDateField.HOUR24) + " - " + simDate(SimDateField.MINUTE) + " - " + simDate(SimDateField.SECOND) + " - " + simDate(SimDateField.MILLISECOND) + ": Powrot do kolejki- Dodano nowe zgl. nr: " + transitionParams.getTenNr());
				}else{
					System.out.println(simTime() + " - " + simDate(SimDateField.HOUR24) + " - " + simDate(SimDateField.MINUTE) + " - " + simDate(SimDateField.SECOND) + " - " + simDate(SimDateField.MILLISECOND) + ": Powrot do kolejki- Orzucono zgl. nr: " + transitionParams.getTenNr());
					smoParent.getKolejka().setLiczbaOdrzuconych(smoParent.getKolejka().getLiczbaOdrzuconych()+1);
				}
				// Zaplanuj dalsza obsługe w tym gnieździe
				if (smoParent.liczbaZgl() > 0)
				{
					smoParent.rozpocznijObsluge = new RozpocznijObsluge(smoParent);
				}
			}else{
				if(smoParent.getSmoBis().getKolejka().dodaj(transitionParams)){
					smoParent.getCzasSMO().setValue(
				 			simTime() - transitionParams.getCzasOdniesienia());
					System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": SMOBis-trafilo do kolejki  zgl. nr:" + transitionParams.getTenNr());
					smoParent.setWolne(true);
					if(smoParent.getSmoBis().getLiczZgl()==1&& smoParent.getSmoBis().isWolne()==true){
						smoParent.getSmoBis().rozpocznijObsluge=new RozpocznijObslugeBis(smoParent.getSmoBis());
					}

					// Zaplanuj dalsza obsługe w tym gnieździe
					if (smoParent.liczbaZgl() > 0)
					{
						smoParent.rozpocznijObsluge = new RozpocznijObsluge(smoParent);
					}
				}else{
					System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": SMOBis- czeka w semaforze zgl. " + transitionParams.getTenNr());
					transitionParams.setObsluzony(true);
					smoParent.zakonczObsluge=new ZakonczObsluge(smoParent,smoParent.getSemaphore(),transitionParams);
				}
			}

	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}