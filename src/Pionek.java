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
//        int [] kolumnaChange = {1, -1, 0, 0};
//        int [] wierszChange;
//        if(kolor == 6)
//            wierszChange = new int [] {-1, -1, -1, -2};
//        else
//            wierszChange = new int [] {1, 1, 1, 2};

        int [][] docelowe_pola = {{1, 1},
                                  {1, -1},
                                  {1, 0},
                                  {2, 0}};
        if (kolor == Figura.kolor_bialy)
            for(int [] ruch : docelowe_pola)
                    ruch[0] *= (-1);
        for (int [] ruch : docelowe_pola) {
            ruch[0] += x;
            ruch[1] += y;
        }

        // Sprawdzenie bicia na ukos - sprawdzenie czy pole jest zajęte przez figurę o innym kolorze.
        if(szachownica.wSzachownicy(docelowe_pola[0][0], docelowe_pola[0][1])) {
            Figura docelowe_pole = szachownica.podajPole(docelowe_pola[0][0], docelowe_pola[0][1]);
            if(docelowe_pole.kolor != kolor)
                if(docelowe_pole.kolor != PustePole.podajKolor())
                    ruchy.add(new Pole(docelowe_pola[0][0], docelowe_pola[0][1]));
        }
        if(szachownica.wSzachownicy(docelowe_pola[1][0], docelowe_pola[1][1])) {
            Figura docelowe_pole = szachownica.podajPole(docelowe_pola[1][0], docelowe_pola[1][1]);
            if(docelowe_pole.kolor != kolor)
                if(docelowe_pole.kolor != PustePole.podajKolor())
                    ruchy.add(new Pole(docelowe_pola[1][0], docelowe_pola[1][1]));
        }

        // Obranie linii startowej pionka dla danego koloru.
        int linia_startowa;
        if(kolor == Figura.kolor_bialy)
            linia_startowa = 6;
        else
            linia_startowa= 1;

        // Sprawdzenie ruchów naprzód - sprawdzenie czy pola są puste.
        if(szachownica.wSzachownicy(docelowe_pola[2][0], docelowe_pola[2][1])) {
            Figura ruch_w_przod = szachownica.podajPole(docelowe_pola[2][0], docelowe_pola[2][1]);
                if(ruch_w_przod.kolor == PustePole.podajKolor()) {
                    ruchy.add(new Pole(docelowe_pola[2][0], docelowe_pola[2][1]));
                    if(x == linia_startowa && szachownica.wSzachownicy(docelowe_pola[3][0], docelowe_pola[3][1])) {
                        Figura ruch_dwa_w_przod = szachownica.podajPole(docelowe_pola[3][0], docelowe_pola[3][1]);
                        if(ruch_dwa_w_przod.kolor == PustePole.podajKolor())
                            ruchy.add(new Pole(docelowe_pola[3][0], docelowe_pola[3][1]));
                    }
                }
        }

        return ruchy;
    }
}
