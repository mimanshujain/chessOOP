package history;

import piece.Piece;
import player.Player;

public class Path {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    private Player player;
    private Piece piece;

    public Path(int fromX, int fromY, int toX, int toY, Player player, Piece piece) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.player = player;
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "Path{" +
                "fromX=" + fromX +
                ", fromY=" + fromY +
                ", toX=" + toX +
                ", toY=" + toY +
                ", player=" + player.toString() +
                ", piece=" + piece +
                '}';
    }

    public Player getPlayer() {
        return player;
    }
}
