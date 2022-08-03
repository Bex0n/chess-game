import java.util.*;

public class Wieza extends Figura {

    static int wiezaUnicode = 0x2656;

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

        PustePole pusty = new PustePole(new Pole(0, 0));

        // Kierunki, w których może poruszać się wieża.
        int [] xChange = {1, -1, 0 ,0};
        int [] yChange = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++)
            for (int j = 1; j <= 8; j++) {
                int sprawdzane_pole_x = x + xChange[i] * j;
                int sprawdzane_pole_y = y + yChange[i] * j;
                if (szachownica.wSzachownicy(sprawdzane_pole_x, sprawdzane_pole_y)) {
                    if (szachownica.podajPole(sprawdzane_pole_x, sprawdzane_pole_y).kolor == kolor)
                        break;
                    ruchy.add(new Pole(sprawdzane_pole_x, sprawdzane_pole_y));
                    if (szachownica.podajPole(sprawdzane_pole_x, sprawdzane_pole_y).kolor != pusty.kolor)
                        break;
                }
            }

        return ruchy;
    }
}
