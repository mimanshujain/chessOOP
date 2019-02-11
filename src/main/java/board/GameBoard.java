package board;

import box.Box;
import box.BoxColor;
import history.GameHistory;
import history.Path;
import piece.*;
import player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This is Board class which has boxes and handles piece movements. Also stores game history.
 */
public class GameBoard {
    private Box[][] boxes;
    private Player currentPlayer;
    private Player winner = null;

    private GameHistory history;
    ArrayList<Piece> killList;

    public GameBoard() {
        this.boxes = new Box[8][8];
        this.killList = new ArrayList<>();
        this.history = new GameHistory();
        this.setDefaultBoxes();
    }

    /**
     * Setting up Board
     */
    private void setDefaultBoxes() {
        //Set White Royals
        this.boxes[0][0] = new Box(0,0, BoxColor.WHITE, new Rook(PieceColor.WHITE));
        this.boxes[0][1] = new Box(0,1, BoxColor.BLACK, new Knight(PieceColor.WHITE));
        this.boxes[0][2] = new Box(0,2, BoxColor.WHITE, new Bishop(PieceColor.WHITE));
        this.boxes[0][3] = new Box(0,3, BoxColor.BLACK, new Queen(PieceColor.WHITE));
        this.boxes[0][4] = new Box(0,4, BoxColor.WHITE, new King(PieceColor.WHITE));
        this.boxes[0][5] = new Box(0,5, BoxColor.BLACK, new Bishop(PieceColor.WHITE));
        this.boxes[0][6] = new Box(0,6, BoxColor.WHITE, new Knight(PieceColor.WHITE));
        this.boxes[0][7] = new Box(0,7, BoxColor.BLACK, new Rook(PieceColor.WHITE));
        //Set Black Royals
        this.boxes[7][0] = new Box(7,0, BoxColor.BLACK, new Rook(PieceColor.BLACK));
        this.boxes[7][1] = new Box(7,1, BoxColor.WHITE, new Knight(PieceColor.BLACK));
        this.boxes[7][2] = new Box(7,2, BoxColor.BLACK, new Bishop(PieceColor.BLACK));
        this.boxes[7][3] = new Box(7,3, BoxColor.WHITE, new Queen(PieceColor.BLACK));
        this.boxes[7][4] = new Box(7,4, BoxColor.BLACK, new King(PieceColor.BLACK));
        this.boxes[7][5] = new Box(7,5, BoxColor.WHITE, new Bishop(PieceColor.BLACK));
        this.boxes[7][6] = new Box(7,6, BoxColor.BLACK, new Knight(PieceColor.BLACK));
        this.boxes[7][7] = new Box(7,7, BoxColor.WHITE, new Rook(PieceColor.BLACK));

        BoxColor boxColor = BoxColor.BLACK;
        //Set Pawns
        for (int j = 0; j < 8; j++) {
            this.boxes[1][j] = new Box(1,j, boxColor, new Pawn(PieceColor.WHITE));
            boxColor = boxColor.otherColor();
            this.boxes[6][j] = new Box(6,j, boxColor, new Pawn(PieceColor.BLACK));
        }

        //Set Empty Boxes
        boxColor = boxColor.otherColor();
        for (int i = 2; i <=5; i++) {
            for (int j = 0; j < 8; j++) {
                this.boxes[i][j] = new Box(i,j, boxColor);
                boxColor = boxColor.otherColor();
            }
        }
    }

    /**
     * Method to make the move on the chess board
     * @param player
     * @param sourceX
     * @param sourceY
     * @param destX
     * @param destY
     * @return
     */
    public boolean moveThePiece(Player player, int sourceX, int sourceY, int destX, int destY) {
        //Same player not allowed to move again
        if (null != currentPlayer && currentPlayer.getId().equals(player.getId())){
            return false;
        }

        //Getting source and destination boxes
        Box sourceBox = this.boxes[sourceX][sourceY];
        Box destBox = this.boxes[destX][destY];

        //Checking if sourcebox is not empty and destination box is a valid box to make a move.
        //Ex: if the destination box already has a piece of the same color, then game cannot make this move.
        if (sourceBox != null && !sourceBox.isEmpty() && destBox != null && destBox.isPieceAllowedToMove(sourceBox.getPiece())) {
            Piece sourcePiece = sourceBox.getPiece();
            Piece destPiece = destBox.getPiece();

            boolean isValidMove = isMoveValid(sourceX, sourceY, destX, destY, sourcePiece, destPiece);

            if (isValidMove) {
                sourceBox.removeCurrentPiece();

                destBox.movePiece(sourcePiece);
                if (destPiece != null) {
                    destPiece.setAliveInGame(false);
                    killList.add(destPiece);
                }
                if (isCheckMate())
                    winner = player;

                history.addPath(new Path(sourceX, sourceY, destX, destY, player, sourcePiece));

                currentPlayer = player;
                return true;
            }
        }
        return false;
    }

    /**
     * This is to Deal with possible illegal moves. It checks the validity of the move.
     * @param sourceX
     * @param sourceY
     * @param destX
     * @param destY
     * @param sourcePiece
     * @param destPiece
     * @return
     */
    private boolean isMoveValid(int sourceX, int sourceY, int destX, int destY, Piece sourcePiece, Piece destPiece) {
        boolean isValidMove;

        //If piece is pawn and move is diagonal, then it can move only if there is a piece of opposite color present in destination box
        if ((sourcePiece.getType() == PieceType.PAWN)
                && ((Pawn) sourcePiece).isMoveDiagonal(sourceX, sourceY, destX, destY) && destPiece != null) {

            isValidMove = true;
        }
        //Check if Pawn is moving two steps. If true then need to check if it is its first move
        else if ((sourcePiece.getType() == PieceType.PAWN)
                && ((Pawn) sourcePiece).isMoveTwoStepsAhead(sourceX, sourceY, destX, destY) && destPiece != null) {

            isValidMove = true;
        }
        else {
            isValidMove = sourcePiece.isValidBoardMove(sourceX, sourceY, destX, destY);
        }
        return isValidMove;
    }

    /**
     * Generate hints in terms of boxes destination boxes it can go.
     * @param sourceX
     * @param sourceY
     * @return
     */
    public List<Box> getHints(int sourceX, int sourceY) {
        Box box = this.boxes[sourceX][sourceY];
        List<Box> moves = new ArrayList<>();
        if (box != null && !box.isEmpty()) {
            Piece piece = box.getPiece();
            moves = piece.generatePossibleMoves();
        }
        return  moves;
    }

    private boolean isPlayerInCheck(Player player) {
        /* TODO Add some useful code here. */
        return false;
    }
    private boolean isCheckMate() {
        /* TODO Add some useful code here. */
        return false;
    }

    public Player getWinner() {
        return winner;
    }

    public List<Path> getCompleteGameHistory() {
        return history.getPaths();
    }
    public List<Path> getGameHistoryForPlayer(Player player) {
        return history.getPlayerHistory(player);
    }
    public List<Piece> getKillList() {
        return (ArrayList<Piece>)killList.clone();
    }
}
