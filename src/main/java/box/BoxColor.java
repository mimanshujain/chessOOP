package box;

import piece.PieceColor;

public enum BoxColor {
    WHITE, BLACK;

    public BoxColor otherColor() {
        if (this == WHITE)
            return BLACK;
        return WHITE;
    }
}
