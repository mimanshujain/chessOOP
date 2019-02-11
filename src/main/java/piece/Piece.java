package piece;

import box.Box;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class to define different types of pieces in the game
 */
public abstract class Piece implements MoveValidations{
    PieceType type;
    PieceColor color;
    boolean isAliveInGame;
    private List<Box> boxHistory = new ArrayList<>();

    /**
     * Method to return possible box movements give a location of piece
     * @return
     */
    public abstract List<Box> generatePossibleMoves();

    /**
     * Default validation function which checks the basic corner condition for a move to happen
     * @param sourceX
     * @param sourceY
     * @param destX
     * @param destY
     * @return
     */
    public boolean isValidBoardMove(int sourceX, int sourceY, int destX, int destY) {
        //No movement, hence not valid
        if (sourceX == destX && sourceY == destY) {
            return  false;
        }
        return (this.isInBoardRange(sourceX) && this.isInBoardRange(sourceY) &&
                this.isInBoardRange(destX) && this.isInBoardRange(destY));
    }

    /**
     * If the coordinate is in the range of board
     * @param num
     * @return
     */
    private boolean isInBoardRange(int num) {
        return (0 <= num && num <= 7);
    }

    /**
     * Given the piece, what all boxes it has traversed throughout the game
     * @return
     */
    public List<Box> getBoxHistory() {
        return boxHistory;
    }

    public void addBoxVisited(Box box) {
        boxHistory.add(box);
    }

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }

    public boolean isAliveInGame() {
        return isAliveInGame;
    }

    public void setAliveInGame(boolean aliveInGame) {
        isAliveInGame = aliveInGame;
    }
}
