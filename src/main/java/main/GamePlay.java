package main;

import piece.PieceColor;
import player.Player;

/**
 * This class captures any player related property during the game.
 * It can be color, activeness, number of checks etc.
 */
public class GamePlay {
    private Player player;
    private PieceColor color;
    boolean isActive;

    public GamePlay(Player player, PieceColor color) {
        this.player = player;
        this.color = color;
        isActive = false;
    }

    public Player getPlayer() {
        return player;
    }

    public PieceColor getColor() {
        return color;
    }
}
