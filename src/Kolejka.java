import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Kolejka {
    private List<Zgloszenie> kolejka;
    private boolean fifo;
    private int maxSize; //<=0 - inf
    private boolean priorytet;
    static int liczbaOdrzuconych=0;


    public Kolejka(boolean fifo, int maxSize, boolean priorytet){
        kolejka = new LinkedList<>();
        this.fifo=fifo;
        this.maxSize=maxSize;
        this.priorytet=priorytet;
    }

    //rozmiar
    public int getSize(){
        return kolejka.size();
    }

    public List<Zgloszenie> getKolejka() {
        return kolejka;
    }

    //dodaj
    public boolean dodaj(Zgloszenie zgloszenie){
        if(maxSize>0 && kolejka.size()>=maxSize ){
            return false;
        }
        else{
            if(kolejka.isEmpty()){
                kolejka.add(zgloszenie);
                return true;
            }
            else if(priorytet && fifo){
                for(Zgloszenie zgl: kolejka){
                    if(zgloszenie.getPriorytet()>zgl.getPriorytet()){
                        kolejka.add(kolejka.indexOf(zgl),zgloszenie);
                        return true;
                    }
                    else if(kolejka.indexOf(zgl)== kolejka.size()-1){
                        kolejka.add(zgloszenie);
                        return true;
                    }
                }
            }
            else if(priorytet && !fifo){ //lifo
                for(Zgloszenie zgl: kolejka){
                    if(zgloszenie.getPriorytet()== zgl.getPriorytet() || zgloszenie.getPriorytet() > zgl.getPriorytet()){
                        kolejka.add(kolejka.indexOf(zgl),zgloszenie);
                        return true;
                    }
                    else if(kolejka.indexOf(zgl)== kolejka.size()-1){
                        kolejka.add(zgloszenie);
                        return true;
                    }
                }
            }
            else if(!priorytet && fifo){
                kolejka.add(zgloszenie);
                return true;
            }
            else{ //bez prio lifo
                kolejka.add(0,zgloszenie);
                return true;
            }
        }
        return false;
    }
    //usun(pobierz)
    public Zgloszenie usun(){
        return kolejka.remove(0);
    }

    //usun wskazany
    public boolean usunWskazany(Zgloszenie z){
        return kolejka.remove(z);
    }

    public static void setLiczbaOdrzuconych(int liczbaOdrzuconych) {
        Kolejka.liczbaOdrzuconych = liczbaOdrzuconych;
    }

    public static int getLiczbaOdrzuconych() {
        return liczbaOdrzuconych;
    }



}
