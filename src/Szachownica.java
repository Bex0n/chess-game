import java.util.List;

public class Szachownica {
    private final Figura [][] pola;

    public Szachownica() {
        Figura [][] nowaSzachownica = new Figura [8][8];

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                nowaSzachownica[i][j] = new PustePole(new Pole(i, j));

        for (int i = 0; i < 8; i++) {
            nowaSzachownica[1][i] = new Pionek("czarny", new Pole(1, i));
            nowaSzachownica[6][i] = new Pionek("bialy", new Pole(6, i));
        }

        nowaSzachownica[0][0] = new Wieza("czarny", new Pole(0, 0));
        nowaSzachownica[0][7] = new Wieza("czarny", new Pole(0, 7));
        nowaSzachownica[7][0] = new Wieza("bialy", new Pole(7, 0));
        nowaSzachownica[7][7] = new Wieza("bialy", new Pole(7, 7));

        nowaSzachownica[0][1] = new Skoczek("czarny", new Pole(0, 1));
        nowaSzachownica[0][6] = new Skoczek("czarny", new Pole(0, 6));
        nowaSzachownica[7][1] = new Skoczek("bialy", new Pole(7, 1));
        nowaSzachownica[7][6] = new Skoczek("bialy", new Pole(7, 6));

        nowaSzachownica[0][2] = new Goniec("czarny", new Pole(0, 2));
        nowaSzachownica[0][5] = new Goniec("czarny", new Pole(0, 5));
        nowaSzachownica[7][2] = new Goniec("bialy", new Pole(7, 2));
        nowaSzachownica[7][5] = new Goniec("bialy", new Pole(7, 5));

        nowaSzachownica[0][3] = new Hetman("czarny", new Pole(0, 3));
        nowaSzachownica[7][3] = new Hetman("bialy", new Pole(7, 3));

        nowaSzachownica[0][4] = new Krol("czarny", new Pole(0, 4));
        nowaSzachownica[7][4] = new Krol("bialy", new Pole(7, 4));

        this.pola = nowaSzachownica;
    }

    public boolean wSzachownicy(int x, int y) {
        return (x >= 0 && x < pola.length && y >= 0 && y < pola[0].length);
    }

    public Figura [][] data() {
        Figura [][] kopia = new Figura [pola.length][pola[0].length];
        for (int i = 0; i < pola.length; i++)
            for (int j = 0; j < pola[0].length; j++)
                kopia[i][j] = pola[i][j];
        return kopia;
    }

    public Figura podajPole(int x, int y) {
        return pola[x][y];
    }

    public void ustawPole(int x, int y, Figura figura) {
        pola[x][y] = figura;
        figura.setPole(x, y);
    }

    public int liczbaWierszy() {
        return pola.length;
    }

    public int liczbaKolumn() {
        return pola[0].length;
    }


    public Pole pozycjaWrogiegoKrola(int kolor) {
        String kolorPrzeciwny = "czarny";
        if (kolor == 0)
            kolorPrzeciwny = "bialy";
        Krol wrogiKrol = new Krol(kolorPrzeciwny, new Pole(0, 0));

        for (int i = 0; i < this.liczbaWierszy(); i++)
            for (int j = 0; j < this.liczbaKolumn(); j++)
                if (this.podajPole(i, j).getCode() == wrogiKrol.getCode())
                    return this.podajPole(i, j).getPole();
        return new Pole(-1, -1);
    }

    public boolean szachuje(int kolor) {
        Pole wrogi_krol = pozycjaWrogiegoKrola(kolor);

        for (int i = 0; i < this.liczbaWierszy(); i++)
            for (int j = 0; j < this.liczbaKolumn(); j++)
                if (this.podajPole(i, j).kolor == kolor) {
                    List<Pole> ruchy = this.podajPole(i, j).mozliweRuchy(i, j, this);
                    for (Pole ruch : ruchy)
                        if (ruch.equals(wrogi_krol))
                            return true;
                }
        return false;
    }


}
