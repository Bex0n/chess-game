import java.util.*;

public class Skoczek extends Figura {

    static int skoczekUnicode = 0x2658;

    public Skoczek(String kolor, Pole pole) {
        if (kolor == "czarny");
            this.kolor = 0;
        if (kolor == "bialy")
            this.kolor = 6;
        this.pole = pole;
        this.unicode = skoczekUnicode;
    }

    public  List<Pole> mozliweRuchy(int x, int y, Szachownica szachownica) {
        List<Pole> ruchy = new Vector<Pole>();

        // Możliwe ruchy skoczka.
        int [] xChange = {2, 2, -2, -2, 1, 1, -1, -1};
        int [] yChange = {1, -1, 1, -1, 2, -2, 2, -2};

        for(int i = 0; i < xChange.length; i++)
            if(szachownica.wSzachownicy(x+xChange[i], y+yChange[i]))
                if(szachownica.podajPole(x+xChange[i], y+yChange[i]).kolor != kolor)
                    ruchy.add(new Pole(x+xChange[i], y + yChange[i]));
        return ruchy;
    }
}
