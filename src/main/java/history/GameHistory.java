package history;

import player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameHistory {

    ArrayList<Path> paths = new ArrayList<Path>();

    public void addPath(Path path) {
        paths.add(path);

    }

    public List<Path> getPaths() {
        return (ArrayList<Path>)paths.clone();
    }

    public List<Path> getPlayerHistory(Player player) {
        return paths.stream()
                                        .filter(p -> p.getPlayer().getId().equals(player.getId()))
                                        .collect(Collectors.toList());
    }
}
