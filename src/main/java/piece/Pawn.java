package piece;

import box.Box;

import java.util.List;

public class Pawn extends Piece {

    private boolean isAtDefaultLocation = true;

    public Pawn(PieceColor color) {
        this.type = PieceType.ROOK;
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

        return MoveValidations.isAValidOneStepMove(sourceX, sourceY, destX, destY);
    }
    //Check if Pawn is moving diagonally. If true then need to check if the diagonal box has a piece of opposite color
    public boolean isMoveDiagonal(int sourceX, int sourceY, int destX, int destY) {
        return MoveValidations.isAValidDiagonalMove(sourceX, sourceY, destX, destY);
    }

    //Check if Pawn is moving two steps. If true then need to check if it is its first move
    public boolean isMoveTwoStepsAhead(int sourceX, int sourceY, int destX, int destY) {
        boolean result = MoveValidations.isAValidTwoStepMove(sourceX, sourceY, destX, destY) && isAtDefaultLocation;
        if (result)
            isAtDefaultLocation = false;
        return result;
    }
}
