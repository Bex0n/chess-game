import java.util.*;

public class Wieza extends Figura {

    final static int wiezaUnicode = 0x2656;
    final static int zasiegRuchu = 8;

    public Wieza(String kolor, Pole pole) {
        if (kolor == "czarny");
        this.kolor = 0;
        if (kolor == "bialy")
            this.kolor = 6;
        this.pole = pole;
        this.unicode = wiezaUnicode;
    }

    public  List<Pole> mozliweRuchy(int x, int y, Szachownica szachownica) {
        List<Pole> ruchy = new Vector<Pole>();

        // Kierunki w których może poruszać się wieża.
        int [][] zmiana = {{1, 0},
                           {-1, 0},
                           {0, 1},
                           {0, -1}};

        // Dodanie do listy ruchów, które są dostępne dla wieży,
        for (int [] ruch : zmiana)
            for (int zasieg = 1; zasieg <= zasiegRuchu; zasieg++) {
                int docelowy_ruch_x = x + ruch[0] * zasieg;
                int docelowy_ruch_y = y + ruch[1] * zasieg;
                if (szachownica.wSzachownicy(docelowy_ruch_x, docelowy_ruch_y)) {
                    // Sprawdzenie, czy została napotkana sojusznicza figura.
                    if (szachownica.podajPole(docelowy_ruch_x, docelowy_ruch_y).kolor == kolor)
                        break;
                    ruchy.add(new Pole(docelowy_ruch_x, docelowy_ruch_y));
                    // Sprawdzenie, czy została napotkana wroga figura.
                    if (szachownica.podajPole(docelowy_ruch_x, docelowy_ruch_y).kolor != PustePole.podajKolor())
                        break;
                }
            }

        return ruchy;
    }
}
