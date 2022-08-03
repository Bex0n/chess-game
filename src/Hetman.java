import java.util.*;

public class Hetman extends Figura {

    static int hetmanUnicode = 0x2655;

    public Hetman(String kolor, Pole pole) {
        if (kolor == "czarny");
            this.kolor = 0;
        if (kolor == "bialy")
            this.kolor = 6;
        this.pole = pole;
        this.unicode = hetmanUnicode;
    }

    public  List<Pole> mozliweRuchy(int x, int y, Szachownica szachownica) {
        String kolorNapis;
        if (kolor == Figura.kolor_bialy)
            kolorNapis = "bialy";
        else
            kolorNapis = "czarny";

        List<Pole> ruchyGoncowe = (new Goniec(kolorNapis, new Pole(x, y))).mozliweRuchy(x, y, szachownica);
        List<Pole> ruchyWiezowe = (new Wieza(kolorNapis, new Pole(x, y))).mozliweRuchy(x, y, szachownica);

        List<Pole> ruchy = new Vector<Pole>();
        for (int i = 0; i < ruchyGoncowe.size(); i++)
            ruchy.add(ruchyGoncowe.get(i));
        for (int i = 0; i < ruchyWiezowe.size(); i++)
            ruchy.add(ruchyWiezowe.get(i));

        return ruchy;
    }
}
