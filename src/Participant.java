import java.util.List;

public abstract class Participant {
    protected int kolor;
    public Participant() {
    }

    public abstract boolean wykonajRuch(Szachownica szachownica);

    public void pokazSzachownice(Szachownica szachownica) {
        Figura [][] stanSzachownicy = szachownica.data();
        for (int i = 0; i < stanSzachownicy.length; i++) {
            System.out.print(stanSzachownicy.length - i + " ");
            for (int j = 0; j < stanSzachownicy[0].length; j++)
                System.out.print((char) (stanSzachownicy[i][j].getCode()) + " ");
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0; i < stanSzachownicy[0].length; i++) {
            char numer_wiersza = (char) ('A' + i);
            System.out.print(numer_wiersza + " ");
        }
        System.out.println();
    }

    public boolean zmatowany(Szachownica szachownica) {

        int kolorPrzeciwny = 0;
        if (kolor == 0)
            kolorPrzeciwny = 6;

        boolean szached = szachownica.szachuje(kolorPrzeciwny);
        if(szached) {
            int ratujaceRuchy = 0;
            for (int i = 0; i < szachownica.liczbaWierszy(); i++)
                for (int j = 0; j < szachownica.liczbaKolumn(); j++)
                    if (szachownica.podajPole(i, j).kolor == kolor) {
                        List<Pole> dostepneRuchy = szachownica.podajPole(i, j).mozliweRuchy(i, j, szachownica);
                        for (int k = 0; k < dostepneRuchy.size(); k++) {
                            Figura oldPoleFirst = szachownica.podajPole(i, j);
                            Figura oldPoleSecond = szachownica.podajPole(dostepneRuchy.get(k).getWiersz(), dostepneRuchy.get(k).getKolumna());
                            szachownica.ustawPole(dostepneRuchy.get(k).getWiersz(), dostepneRuchy.get(k).getKolumna(), szachownica.podajPole(i, j));
                            szachownica.ustawPole(i, j, new PustePole(new Pole(i, j)));
                            if (!szachownica.szachuje(kolorPrzeciwny))
                                ratujaceRuchy++;
                            szachownica.ustawPole(dostepneRuchy.get(k).getWiersz(), dostepneRuchy.get(k).getKolumna(), oldPoleSecond);
                            szachownica.ustawPole(i, j, oldPoleFirst);
                        }
                    }
            return ratujaceRuchy == 0;
        }
        return false;
    }

    public boolean zszachowany(Szachownica szachownica) {
        return szachownica.szachuje(this.kolorPrzeciwny());
    }

    public int kolorPrzeciwny() {
        if(kolor == Figura.kolor_bialy)
            return Figura.kolor_czarny;
        return Figura.kolor_bialy;
    }
}
