public class Rozgrywka {

    public Rozgrywka() {
    }

    void start() {
        Szachownica szachownica = new Szachownica();

        Player Player1 = new Player("bialy");
        Bot Player2 = new Bot("czarny");

        for (int i = 1; i <= 40; i++) {
            System.out.println("Tura " + i + ":");
            if(Player1.zmatowany(szachownica)) {
                System.out.println("Mat!");
                System.out.println("Wygrywa Gracz2!");
                return;
            }
            Player1.pokazSzachownice(szachownica);
            while(!Player1.wykonajRuch(szachownica));
            if(Player2.zmatowany(szachownica)) {
                System.out.println("Mat!");
                System.out.println("Wygrywa Gracz1");
                return;
            }
            Player1.pokazSzachownice(szachownica);
            Player2.wykonajRuch(szachownica);
        }
        System.out.println("Remis!");
        return;
    }

}
