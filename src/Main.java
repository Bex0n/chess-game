public class Main {
    public static void main(String[] args) {
        String white = "Player", black = "Bot";
        
        for (String arg : args) {
            if (args.equals("-bots")) {
                white = "Bot"; 
                black = "Bot";
            }
            if (args.equals("-players")) {
                white = "Player"; 
                black = "Player";
            }
            if (args.equals("-black")) {
                white = "Bot";
                black = "Player";
            }
        }

        Rozgrywka game = new Rozgrywka(white, black);
        game.set_rounds(40);
        game.start();
    }
}
