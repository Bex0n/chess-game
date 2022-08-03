import java.util.List;
import java.util.Vector;

public class PustePole extends Figura {
    static int pustePoleUnicode = 0x2B1C + 1;
    //0x25A1
    public PustePole(Pole pole) {
        this.kolor = -1;
        this.pole = pole;
        this.unicode = pustePoleUnicode;
    }

    public List<Pole> mozliweRuchy(int x, int y, Szachownica szachownica) {
        List<Pole> ruchy = new Vector<Pole>(0);
        return ruchy;
    }
}
