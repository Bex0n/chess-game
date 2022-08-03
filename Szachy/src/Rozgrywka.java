public class Rozgrywka {

    public Rozgrywka() {
    }

    void start() {
        Szachownica szachownica = new Szachownica();

        Player Player1 = new Player("bialy");
        Participant Bot1 = new Bot("czarny");

        for (int i = 1; i <= 40; i++) {
            System.out.println("Tura " + i + ":");
            if(Player1.zmatowany(szachownica)) {
                System.out.println("Mat!");
                System.out.println("Wygrywa bot!");
                return;
            }
            Player1.pokazSzachownice(szachownica);
            while(!Player1.wykonajRuch(szachownica));
            if(Bot1.zmatowany(szachownica)) {
                System.out.println("Mat!");
                System.out.println("Wygrywa gracz");
                return;
            }
            Player1.pokazSzachownice(szachownica);
            Bot1.wykonajRuch(szachownica);
        }
        System.out.println("Remis!");
        return;
    }

}
