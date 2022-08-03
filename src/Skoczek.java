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
        List<Pole> mozliwe_ruchy = new Vector<Pole>();

        // Możliwe ruchy skoczka.
        int [][] zmiana = {{2, 1},
                           {2, -1},
                           {-2, 1},
                           {-2, -1},
                           {1, 2},
                           {1, -2},
                           {-1, 2},
                           {-1, -2}};

        // Dodanie do listy ruchów, jeśli na danym polu nie stoi sojusznicza figura.
        for(int [] ruch : zmiana) {
            int docelowy_ruch_x = x + ruch[0];
            int docelowy_ruch_y = y + ruch[1];
            if (szachownica.wSzachownicy(docelowy_ruch_x, docelowy_ruch_y))
                if (szachownica.podajPole(docelowy_ruch_x, docelowy_ruch_y).kolor != kolor)
                    mozliwe_ruchy.add(new Pole(docelowy_ruch_x, docelowy_ruch_y));
        }

        return mozliwe_ruchy;
    }
}
