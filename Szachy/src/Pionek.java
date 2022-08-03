import java.util.*;

public class Pionek extends Figura {

    static int pionekUnicode = 0x2659;

    public Pionek(String kolor, Pole pole) {
        if (kolor == "czarny");
            this.kolor = 0;
        if (kolor == "bialy")
            this.kolor = 6;
        this.pole = pole;
        this.unicode = pionekUnicode;
    }

    public  List<Pole> mozliweRuchy(int x, int y, Szachownica szachownica) {
        List<Pole> ruchy = new Vector<Pole>();
        int [] kolumnaChange = {1, -1, 0, 0};
        int [] wierszChange;
        if(kolor == 6)
            wierszChange = new int [] {-1, -1, -1, -2};
        else
            wierszChange = new int [] {1, 1, 1, 2};

        int pusty = new PustePole(new Pole(0, 0)).kolor;

        for (int i = 0; i < 2; i++)
        if(szachownica.wSzachownicy(x+wierszChange[i], y+kolumnaChange[i]))
            if(szachownica.podajPole(x+wierszChange[i], y+kolumnaChange[i]).kolor != kolor)
                if(szachownica.podajPole(x+wierszChange[i], y+kolumnaChange[i]).kolor != pusty)
                    ruchy.add(new Pole(x+wierszChange[i], y+kolumnaChange[i]));

        if(szachownica.wSzachownicy(x+wierszChange[2], y+kolumnaChange[2]))
            if(szachownica.podajPole(x+wierszChange[2], y+kolumnaChange[2]).kolor != kolor)
                if(szachownica.podajPole(x+wierszChange[2], y+kolumnaChange[2]).kolor == pusty)
                    ruchy.add(new Pole(x+wierszChange[2], y+kolumnaChange[2]));

        int startowalinia = 0;
        if(kolor == 6)
            startowalinia = 6;
        if(kolor == 0)
            startowalinia = 1;

        if(x == startowalinia)
            if(szachownica.wSzachownicy(x+wierszChange[2], y+kolumnaChange[2]))
                if(szachownica.podajPole(x+wierszChange[2], y+kolumnaChange[2]).kolor != kolor)
                    if(szachownica.podajPole(x+wierszChange[2], y+kolumnaChange[2]).kolor == pusty)
                        if(szachownica.wSzachownicy(x+wierszChange[3], y+kolumnaChange[3]))
                            if(szachownica.podajPole(x+wierszChange[3], y+kolumnaChange[3]).kolor != kolor)
                                if(szachownica.podajPole(x+wierszChange[3], y+kolumnaChange[3]).kolor == pusty)
                                    ruchy.add(new Pole(x+wierszChange[3], y + kolumnaChange[3]));

        return ruchy;
    }
}
