package NB19;



public class mainBoardGame {
    public static void main(String[] args) {
        BoardGame game = new BoardGame();

        game.printBoard();
        long time = System.currentTimeMillis();
        System.out.println("Game solved: " + game.solve());
        System.out.println("Time to complete: " + ((System.currentTimeMillis() - time)/(float)1000) + " sek");
    }
}
