import java.util.function.Supplier;

public class ComputerPlayer extends Player{
    private final Supplier<Throw> strategy;

    public ComputerPlayer(Supplier<Throw> strategy) {
        this.strategy = strategy;
    }
    @Override
    public Throw chooseThrow() {
        return strategy.get();
    }
}
