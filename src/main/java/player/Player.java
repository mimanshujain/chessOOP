package player;

import history.Path;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Player {
    private String name;
    private final String id;
    private int numberOfWins;
    //Key = Game ID
    //Values = Gamehistory of that game
    private Map<String, List<Path>> gameHistory;

    public Player(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
