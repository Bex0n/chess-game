import java.util.*;

public class Player extends Participant {
    public Player(String kolor) {
        if (kolor == "czarny")
            this.kolor = 0;
        if (kolor == "bialy")
            this.kolor = 6;
    }

    public boolean wykonajRuch(Szachownica szachownica) {
        if(zmatowany(szachownica))
            return true;

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
            if (dostepneRuchy.get(i).getWiersz() == yKoncowa && dostepneRuchy.get(i).getKolumna() == xKoncowa) {
                Figura oldPoleFirst = szachownica.podajPole(yPoczatkowa, xPoczatkowa);
                Figura oldPoleSecond = szachownica.podajPole(yKoncowa, xKoncowa);
                szachownica.podajPole(yPoczatkowa, xPoczatkowa).pole = new Pole(yKoncowa, xKoncowa);
                szachownica.ustawPole(yKoncowa, xKoncowa, szachownica.podajPole(yPoczatkowa, xPoczatkowa));
                szachownica.ustawPole(yPoczatkowa, xPoczatkowa, new PustePole(new Pole(yPoczatkowa, xPoczatkowa)));
                if(zszachowany(szachownica)){
                    szachownica.ustawPole(yKoncowa, xKoncowa, oldPoleSecond);
                    szachownica.ustawPole(yPoczatkowa, xPoczatkowa, oldPoleFirst);
                    szachownica.podajPole(yPoczatkowa, xPoczatkowa).pole = new Pole(yPoczatkowa, yPoczatkowa);
                    if(zszachowany(szachownica))
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
