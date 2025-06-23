import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class Game {
    private final Player playerOne;
    private final Player playerTwo;
    private final int rounds;
    private final Map<Player, Integer> scores;
    private boolean finished;

    public Game(Player playerOne, Player playerTwo, int rounds) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.rounds = rounds;
        this.scores = new HashMap<>(2);
        this.finished = false;
    }

    public boolean run() {
        IntStream.range(1, rounds + 1)
            .forEach(round -> this.determineWinner(playerOne.chooseThrow(), playerTwo.chooseThrow())
                .ifPresent(player -> scores.merge(player, 1, Integer::sum)));
        this.finished = true;
        return true;
    }

    private Optional<Player> determineWinner(Throw playerOneThrow, Throw playerTwoThrow) {
        if (playerOneThrow == playerTwoThrow)
            return Optional.empty();
        if ((playerOneThrow == Throw.SCISSORS && playerTwoThrow == Throw.PAPER)
            || (playerOneThrow == Throw.PAPER && playerTwoThrow == Throw.ROCK)
            || (playerOneThrow == Throw.ROCK && playerTwoThrow == Throw.SCISSORS))
            return Optional.of(playerOne);
        return Optional.of(playerTwo);
    }

    public void printResult() {
        if (!this.finished) {
            System.out.println("Game is not finished!");
            return;
        }

        System.out.println("END RESULTS");
        System.out.println("Rounds played: " + rounds);
        int draws = rounds - Integer.sum(scores.get(playerOne), scores.get(playerTwo));
        String playerOneEndResult =
            String.format("Player One:\t Won Rounds: %d\t Lost Rounds: %d\t Draws: %d", scores.get(playerOne),
                scores.get(playerTwo), draws);
        String playerTwoEndResult =
            String.format("Player Two:\t Won Rounds: %d\t Lost Rounds: %d\t Draws: %d", scores.get(playerTwo),
                scores.get(playerOne), draws);

        System.out.println(playerOneEndResult);
        System.out.println(playerTwoEndResult);
    }
}
