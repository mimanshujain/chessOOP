package piece;

import box.Box;

import java.util.List;

public class Queen extends Piece {

    public Queen(PieceColor color) {
        this.type = PieceType.QUEEN;
        this.color = color;
        this.isAliveInGame = true;
    }
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

        return MoveValidations.isAValidStraightMove(sourceX, sourceY, destX, destY)
                || MoveValidations.isAValidDiagonalMove(sourceX, sourceY, destX, destY);
    }
}
