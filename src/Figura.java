import java.util.*;

public abstract class Figura {
    // Znak figury o czarnym kolorze w standardzie unicode.
    protected int unicode;
    // Różnica numeru unicode między białą, a czarną figurą.
    protected int kolor;
    // Pole, na którym figura się znajduje.
    protected Pole pole;
    public static int kolor_bialy = 6;
    public static int kolor_czarny = 0;

    // Zwraca listę pól na które można stanąć figura na szachownicy.
    public  abstract List <Pole> mozliweRuchy(int x, int y, Szachownica szachownica);

    // Zwraca numer unicode figury.
    public int getCode() {
        return unicode + kolor;
    }

    public Pole getPole() {
        return pole;
    }

    public void setPole(int x, int y) {
        pole = new Pole(x, y);
    }
}
