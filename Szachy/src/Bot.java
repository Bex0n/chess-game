import java.util.*;

public class Bot extends Participant {
    public Bot(String kolor) {
        if (kolor == "bialy")
            this.kolor = 6;
        if (kolor == "czarny")
            this.kolor = 0;
    }

    public boolean wykonajRuch(Szachownica szachownica) {

        // Szachowanie
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
                            szachownica.podajPole(i, j).pole = new Pole(dostepneRuchy.get(k).getWiersz(), dostepneRuchy.get(k).getKolumna());
                            szachownica.ustawPole(dostepneRuchy.get(k).getWiersz(), dostepneRuchy.get(k).getKolumna(), szachownica.podajPole(i, j));
                            szachownica.ustawPole(i, j, new PustePole(new Pole(i, j)));
                            if(!szachownica.szachuje(kolorPrzeciwny))
                                ratujaceRuchy++;
                            szachownica.ustawPole(dostepneRuchy.get(k).getWiersz(), dostepneRuchy.get(k).getKolumna(), oldPoleSecond);
                            szachownica.ustawPole(i, j, oldPoleFirst);
                            szachownica.podajPole(i, j).pole = new Pole(i, j);
                        }
                    }
            if(ratujaceRuchy == 0) {
                return true;
            }
        }
        // Koniec szachowania

        Random rand = new Random();
        int x = 0;
        int y = 0;
        int counter = 0;

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
                        if (!szachownica.szachuje(kolorPrzeciwny))
                            found = true;
                        szachownica.ustawPole(dostepneRuchy.get(k).getWiersz(), dostepneRuchy.get(k).getKolumna(), oldPoleSecond);
                        szachownica.ustawPole(x, y, oldPoleFirst);
                        szachownica.podajPole(x, y).pole = new Pole(x, y);
                    }
                }
            }
            if (found)
                break;
            if (counter > 100000)
                return true;
            counter++;
        }
        List<Pole> dostepneRuchy = szachownica.podajPole(x, y).mozliweRuchy(x, y, szachownica);

        while(true) {
            int n = rand.nextInt(dostepneRuchy.size());

            Figura oldPoleFirst = szachownica.podajPole(x, y);
            Figura oldPoleSecond = szachownica.podajPole(dostepneRuchy.get(n).getWiersz(), dostepneRuchy.get(n).getKolumna());
            szachownica.podajPole(x, y).pole = new Pole(dostepneRuchy.get(n).getWiersz(), dostepneRuchy.get(n).getKolumna());
            szachownica.ustawPole(dostepneRuchy.get(n).getWiersz(), dostepneRuchy.get(n).getKolumna(), szachownica.podajPole(x, y));
            szachownica.ustawPole(x, y, new PustePole(new Pole(x, y)));
            if (!szachownica.szachuje(kolorPrzeciwny)) {
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
