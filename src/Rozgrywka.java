public class Rozgrywka {
    // Game parameters
    private int game_rounds;

    // Game attributes
    private Szachownica szachownica;
    private Participant white;
    private Participant black;

    public Rozgrywka(String white_player_type, String black_player_type) {
        szachownica = new Szachownica();

        if (white_player_type.equals("Player"))
            white = new Player("white");
        else
            white  = new Bot("white");

        if (black_player_type.equals("Player"))
            black = new Player("black");
        else
            black = new Bot("black");
    }

    public void set_rounds(int rounds) {
        game_rounds = rounds;
    }

    void start() {
        for (int round = 1; round <= game_rounds; round++) {
            System.out.println("Tura " + round + ":");
            if(white.zmatowany(szachownica)) {
                System.out.println("Mat!");
                System.out.println("Wygrywa Gracz2!");
                return;
            }
            white.pokazSzachownice(szachownica);
            while(!white.wykonajRuch(szachownica));
            if(black.zmatowany(szachownica)) {
                System.out.println("Mat!");
                System.out.println("Wygrywa Gracz1");
                return;
            }
            white.pokazSzachownice(szachownica);
            black.wykonajRuch(szachownica);
        }
        System.out.println("Remis!");
        return;
    }

}
