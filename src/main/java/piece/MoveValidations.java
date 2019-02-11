package piece;

public interface MoveValidations {
    static boolean isAValidStraightMove(int fromX, int fromY, int toX, int toY) {
        /* TODO Add some useful code here. */
        return true;
    }
    static boolean isAValidDiagonalMove(int fromX, int fromY, int toX, int toY) {
        /* TODO Add some useful code here. */
        return true;
    }
    static boolean isAValidKnightMove(int fromX, int fromY, int toX, int toY) {
        /* TODO Add some useful code here. */
        return true;
    }
    static boolean isAValidOneStepMove(int fromX, int fromY, int toX, int toY) {
        /* TODO Add some useful code here. */
        return true;
    }
    static boolean isAValidTwoStepMove(int fromX, int fromY, int toX, int toY) {
        /* TODO Add some useful code here. */
        return true;
    }
    boolean isValidBoardMove(int sourceX, int sourceY, int destX, int destY);
}
