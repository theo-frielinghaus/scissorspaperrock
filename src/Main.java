
public class Main {
    public static void main(String[] args) {
        final var ROUNDS_NO = 100;

        System.out.println("Lets play Scissors, Paper, Rock!\n");

        GameService gameService = new GameService();
        gameService.playGame(ROUNDS_NO);

    }
}