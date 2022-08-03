import java.util.*;

public class Player extends Participant {
    public Player(String kolor) {
        if (kolor == "czarny")
            this.kolor = 0;
        if (kolor == "bialy")
            this.kolor = 6;
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

        Scanner scanner = new Scanner(System.in);
        String move = scanner.nextLine();
        if (move.length() != 5) {
            System.out.println("Ruch nie spelnia oczekiwanej dlugosci");
            return false;
        }
        int xPoczatkowa =  (int) move.charAt(0) - (int) ('A');
        int yPoczatkowa = szachownica.liczbaWierszy() - ((int) move.charAt(1) - (int) ('0'));
        if (move.charAt(2) != ' ') {
            System.out.println("Ruchy nie sa oddzielone spacja");
            return false;
        }
        int xKoncowa = (int) move.charAt(3) - (int) ('A');
        int yKoncowa = szachownica.liczbaWierszy() - ((int) move.charAt(4) - (int) ('0'));

        if(!szachownica.wSzachownicy(yPoczatkowa, xPoczatkowa) || !szachownica.wSzachownicy(yKoncowa, xKoncowa)) {
            System.out.println("Nieprawidlowy ruch!");
            return false;
        }

        //System.out.println("Tak " + xPoczatkowa + " " + yPoczatkowa + " " + xKoncowa + " " + yKoncowa);

        if(szachownica.podajPole(yPoczatkowa, xPoczatkowa).kolor != this.kolor) {
            PustePole puste = new PustePole(new Pole(0, 0));
            if(szachownica.podajPole(yPoczatkowa, xPoczatkowa).kolor == puste.kolor)
                System.out.println("To puste pole!");
            else
                System.out.println("To nie twoja figura!");
            return false;
        }



        List<Pole> dostepneRuchy = szachownica.podajPole(yPoczatkowa, xPoczatkowa).mozliweRuchy(yPoczatkowa, xPoczatkowa, szachownica);
        for (int i = 0; i < dostepneRuchy.size(); i++) {
            //System.out.println("Mozliwe pole " + dostepneRuchy.get(i).getWiersz() + " " + dostepneRuchy.get(i).getKolumna());
            if (dostepneRuchy.get(i).getWiersz() == yKoncowa && dostepneRuchy.get(i).getKolumna() == xKoncowa) {
                Figura oldPoleFirst = szachownica.podajPole(yPoczatkowa, xPoczatkowa);
                Figura oldPoleSecond = szachownica.podajPole(yKoncowa, xKoncowa);
                szachownica.podajPole(yPoczatkowa, xPoczatkowa).pole = new Pole(yKoncowa, xKoncowa);
                szachownica.ustawPole(yKoncowa, xKoncowa, szachownica.podajPole(yPoczatkowa, xPoczatkowa));
                szachownica.ustawPole(yPoczatkowa, xPoczatkowa, new PustePole(new Pole(yPoczatkowa, xPoczatkowa)));
                if(szachownica.szachuje(kolorPrzeciwny)){
                    szachownica.ustawPole(yKoncowa, xKoncowa, oldPoleSecond);
                    szachownica.ustawPole(yPoczatkowa, xPoczatkowa, oldPoleFirst);
                    szachownica.podajPole(yPoczatkowa, xPoczatkowa).pole = new Pole(yPoczatkowa, yPoczatkowa);
                    if(szached)
                        System.out.println("Masz szacha");
                    else
                        System.out.println("Ruch przegrywa gre");
                    return false;
                }

                if (szachownica.szachuje(kolor))
                    System.out.println("Szach");

                return true;
            }
        }


        System.out.println("Nieprawidlowy ruch");
        return false;
    }

}
