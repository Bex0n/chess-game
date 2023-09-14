public class Pole {
    private int wiersz;
    private int kolumna;

    public Pole(int x, int y) {
        this.wiersz = x;
        this.kolumna = y;
    }

    public int getWiersz() {
        return wiersz;
    }

    public int getKolumna() {
        return kolumna;
    }

    public void setWiersz(int x) {
        this.wiersz = x;
    }

    public void setKolumna(int x) {
        this.kolumna = x;
    }

    public boolean equals(Pole other) {
        return wiersz == other.wiersz && kolumna == other.kolumna;
    }
}
