import java.util.*;

public class Krol extends Figura {

    static int krolUnicode = 0x2654;

    public Krol(String kolor, Pole pole) {
        if (kolor == "czarny");
        this.kolor = 0;
        if (kolor == "bialy")
            this.kolor = 6;
        this.pole = pole;
        this.unicode = krolUnicode;
    }

    public  List<Pole> mozliweRuchy(int x, int y, Szachownica szachownica) {
        List<Pole> ruchy = new Vector<Pole>();

        int [] xChange = {-1, -1, -1, 0, 0, 1, 1, 1};
        int [] yChange = {-1, 0, 1, -1, 1, -1, 0, 1};

        PustePole pusty = new PustePole(new Pole(0, 0));

        for(int i = 0; i < 8; i++)
            if(szachownica.wSzachownicy(x + xChange[i], y + yChange[i]))
                if(szachownica.podajPole(x + xChange[i], y + yChange[i]).kolor  != kolor)
                    if(szachownica.podajPole(x + xChange[i], y + yChange[i]).kolor  == pusty.kolor)
                        ruchy.add(new Pole(x + xChange[i], y + yChange[i]));
                    else
                        ruchy.add(new Pole(x + xChange[i], y + yChange[i]));

        return ruchy;
    }
}
