import java.util.*;

public abstract class Figura {
    // Znak figury o czarnym kolorze w standardzie unicode.
    protected int unicode;
    // Różnica numeru unicode między białą, a czarną figurą.
    protected int kolor;
    // Pole, na którym figura się znajduje.
    protected Pole pole;

    // Zwraca listę pól na które można stanąć figura na szachownicy.
    public  abstract List <Pole> mozliweRuchy(int x, int y, Szachownica szachownica);

    // Zwraca numer unicode figury.
    int getCode() {
        return unicode + kolor;
    }
}
