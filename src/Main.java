public class Main {
    public static void main(String[] args) {
        boolean bots = false, players = false;
        for (String arg : args) {
            if (args.equals("-bots"))
                bots = true;
            if (args.equals("-players"))
                players = true;
        }
        Rozgrywka game = new Rozgrywka();
        game.start();
    }
}
