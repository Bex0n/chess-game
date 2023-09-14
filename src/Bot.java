import java.util.*;

public class Bot extends Participant {
    public Bot(String kolor) {
        if (kolor == "white")
            this.kolor = 6;
        if (kolor == "black")
            this.kolor = 0;
    }

    public boolean wykonajRuch(Szachownica szachownica) {
        // Szachowanie.
        int kolor_przeciwny = 0;
        if (kolor == 0)
            kolor_przeciwny = 6;

        if(this.zmatowany(szachownica))
            return true;

        znajdzOrazRuszFigure(szachownica, kolor_przeciwny);
        return true;
    }

    public void znajdzOrazRuszFigure(Szachownica szachownica, int kolor_przeciwny) {
        Random rand = new Random();
        int x = 0;
        int y = 0;

        while(true) {
            boolean found = false;
            x = rand.nextInt(8);
            y = rand.nextInt(8);
            if(szachownica.podajPole(x,y).kolor == kolor) {
                List<Pole> dostepneRuchy = szachownica.podajPole(x, y).mozliweRuchy(x, y, szachownica);
                if (dostepneRuchy.size() > 0) {
                    for (int k = 0; k < dostepneRuchy.size(); k++) {
                        Figura oldPoleFirst = szachownica.podajPole(x, y);
                        Figura oldPoleSecond = szachownica.podajPole(dostepneRuchy.get(k).getWiersz(), dostepneRuchy.get(k).getKolumna());
                        szachownica.podajPole(x, y).pole = new Pole(dostepneRuchy.get(k).getWiersz(), dostepneRuchy.get(k).getKolumna());
                        szachownica.ustawPole(dostepneRuchy.get(k).getWiersz(), dostepneRuchy.get(k).getKolumna(), szachownica.podajPole(x, y));
                        szachownica.ustawPole(x, y, new PustePole(new Pole(x, y)));
                        if (!szachownica.szachuje(kolor_przeciwny))
                            found = true;
                        szachownica.ustawPole(dostepneRuchy.get(k).getWiersz(), dostepneRuchy.get(k).getKolumna(), oldPoleSecond);
                        szachownica.ustawPole(x, y, oldPoleFirst);
                        szachownica.podajPole(x, y).pole = new Pole(x, y);
                    }
                }
            }
            if (found)
                break;
        }
        ruszFigure(szachownica, x, y, szachownica.podajPole(x, y).mozliweRuchy(x, y, szachownica), kolor_przeciwny);
        return;
    }

    public boolean ruszFigure(Szachownica szachownica, int x, int y, List<Pole> dostepneRuchy, int kolor_przeciwny) {
        Random rand = new Random();
        while(true) {
            int n = rand.nextInt(dostepneRuchy.size());

            Figura oldPoleFirst = szachownica.podajPole(x, y);
            Figura oldPoleSecond = szachownica.podajPole(dostepneRuchy.get(n).getWiersz(), dostepneRuchy.get(n).getKolumna());
            szachownica.podajPole(x, y).pole = new Pole(dostepneRuchy.get(n).getWiersz(), dostepneRuchy.get(n).getKolumna());
            szachownica.ustawPole(dostepneRuchy.get(n).getWiersz(), dostepneRuchy.get(n).getKolumna(), szachownica.podajPole(x, y));
            szachownica.ustawPole(x, y, new PustePole(new Pole(x, y)));
            if (!szachownica.szachuje(kolor_przeciwny)) {
                char literaPoczatkowa = (char) ((int) ('A') + y);
                char liczbaPoczatkowa = (char) ((int) ('0') + szachownica.liczbaWierszy() - x);
                char literaKoncowa = (char) ((int) ('A') + dostepneRuchy.get(n).getKolumna());
                char liczbaKoncowa = (char) ((int) ('0') + szachownica.liczbaWierszy() - dostepneRuchy.get(n).getWiersz());

                System.out.println(literaPoczatkowa + "" + liczbaPoczatkowa + " " + literaKoncowa + "" + liczbaKoncowa);

                if (szachownica.szachuje(kolor))
                    System.out.println("Szach");

                return true;
            }
            else {
                szachownica.ustawPole(x, y, oldPoleFirst);
                szachownica.ustawPole(dostepneRuchy.get(n).getWiersz(), dostepneRuchy.get(n).getKolumna(), oldPoleSecond);
                szachownica.podajPole(x, y).pole = new Pole(x, y);
            }
        }
    }


}
