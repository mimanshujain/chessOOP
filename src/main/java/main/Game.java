package main;

import board.GameBoard;

//import com.sun.org.slf4j.internal.LoggerFactory;
//import org.slf4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import piece.PieceColor;
import player.Player;

/**
 * This is the main Game class. The game starts from here.
 * A game consists of a board and 2 players to start with
 */
public class Game {
    private static Logger logger = LoggerFactory.getLogger(Game.class);

    private GameBoard board;
    private GamePlay play1;
    private GamePlay play2;

    public Game(Player player1, Player player2) {
        //Define the Gameplay for each player which will have all the properties related to Player.
        this.play1 = new GamePlay(player1, PieceColor.WHITE);
        this.play2 = new GamePlay(player2, PieceColor.BLACK);
        board = new GameBoard();
        //Player 1 will move first
        this.play1.isActive = true;
    }

    public void startTheGame() {
        Player activePlayer = getActivePlayer();
        this.playTheChance(activePlayer);
    }
    //Method to play back and forth
    public void playTheChance(Player player) {

        //Assuming we will get coordinates from here through GUI selection or user input
        int[] coordinates = this.getMovementCoordinates();

        if (coordinates.length == 4) {
            if (board.moveThePiece(player, coordinates[0], coordinates[1], coordinates[2], coordinates[3])) {
                logger.info("Moved piece");
                if (board.getWinner() != null) {
                    this.declareTheWinner();
                } else {
                    this.changeTheActivePlayer();
                    this.playTheChance(this.getActivePlayer());
                }
            }
            else {
                logger.info("Try Again");
                this.playTheChance(player);
            }
        }
    }
    private int[] getMovementCoordinates() {
        /* TODO Add some useful code here.
        *  The input can be coming from a GUI. We need to capture 4 coordinates*/
        return new int[4];
    }

    private Player getActivePlayer() {
        return this.play1.isActive ? this.play1.getPlayer() : this.play2.getPlayer();
    }

    private void changeTheActivePlayer() {
        if (this.play1.isActive && !this.play2.isActive) {
            this.play2.isActive = true;
            this.play1.isActive = false;
        }
        else if (this.play2.isActive && !this.play1.isActive) {
            this.play1.isActive = true;
            this.play2.isActive = false;
        }
        else {
            throw new RuntimeException("More than 1 active players. Game not set up properly");
        }
    }

    public void declareTheWinner() {
        if (null == board)
            logger.info("No Game being played");

        if (board.getWinner() != null) {
            Player player = board.getWinner();
            logger.info(player.toString() + " won the game");
            return;
        }

        logger.info("Game is in play currently");
    }

    public static void main(String[] args) {
        try {
            Player p1 = new Player("Test1");
            Player p2 = new Player("Test2");

            Game game = new Game(p1, p2);
            game.startTheGame();
        }
        catch(Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}
