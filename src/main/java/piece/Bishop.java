package piece;

import box.Box;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor color) {
        this.type = PieceType.BISHOP;
        this.color = color;
        this.isAliveInGame = true;
    }

    /**
     * Player can get a hint of possible moves
     * @return
     */
    public List<Box> generatePossibleMoves() {
        /* TODO Add some useful code here. */
        return null;
    }

    @Override
    public boolean isValidBoardMove(int sourceX, int sourceY, int destX, int destY) {
        if (!isAliveInGame)
            return false;
        if (!super.isValidBoardMove(sourceX, sourceY, destX, destY))
            return false;

        return MoveValidations.isAValidDiagonalMove(sourceX, sourceY, destX, destY);
    }
}
