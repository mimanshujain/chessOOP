package box;

import piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Box {
    private final int x;
    private final int y;
    private final BoxColor boxColor;
    Piece piece = null;

    private final ArrayList<Piece> pieceHistory = new ArrayList<Piece>();

    public Box(int x, int y, BoxColor boxColor) {
        this.x = x;
        this.y = y;
        this.boxColor = boxColor;
    }
    public Box(int x, int y, BoxColor boxColor, Piece piece) {
        this.x = x;
        this.y = y;
        this.boxColor = boxColor;
        this.movePiece(piece);
        piece.addBoxVisited(this);
    }
    public ArrayList<Piece> getPieceHistory() {
        return (ArrayList<Piece>)this.pieceHistory.clone();
    }

    public void movePiece(Piece piece) {
        this.piece = piece;
        pieceHistory.add(piece);
    }

    //Check if the box is occupied or not
    public boolean isEmpty() {
        return this.piece == null;
    }

    //Check if the target box has its own color player
    public boolean isPieceAllowedToMove(Piece piece) {
        return isEmpty() || (null != piece && this.piece.getColor() != piece.getColor());
    }

    //Remove the piece currently residing at this box
    public void removeCurrentPiece() {
        if (null != piece) {
            piece = null;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return "Box{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
