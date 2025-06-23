import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class GameService {
    private static final Map<String, Supplier<Throw>> STRATEGIES =
        Map.of("PICK_RANDOM", () -> Throw.values()[new Random().nextInt(Throw.values().length)],
            "PICK_ALWAYS_PAPER", () -> Throw.PAPER);

    public void playGame(int roundsNumber) {
        var game = new Game(
            new ComputerPlayer(STRATEGIES.get("PICK_RANDOM")),
            new ComputerPlayer(STRATEGIES.get("PICK_ALWAYS_PAPER")),
            roundsNumber);

        boolean isFinished = game.run();
        if (isFinished)
            game.printResult();
    }
}
