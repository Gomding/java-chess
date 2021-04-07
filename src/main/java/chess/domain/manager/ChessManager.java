package chess.domain.manager;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;

public class ChessManager {
    private static final Owner FIRST_TURN = Owner.WHITE;

    private final Board board;
    private Owner turnOwner = FIRST_TURN;
    private boolean isPlaying = true;
    private int turnNumber = 1;

    public ChessManager() {
        this.board = BoardInitializer.initiateBoard();
    }

    public void move(final Position source, final Position target) {
        validateSourcePiece(source);
        validateTurnOwner(source);
        board.move(source, target);
        increaseTurnNumber();
        changeTurnOwner();
        checkCaughtKing();
    }

    private void validateSourcePiece(final Position source) {
        if (board.pickPiece(source).isEmptyPiece()) {
            throw new IllegalArgumentException("지정한 칸에는 체스말이 존재하지 않습니다.");
        }
    }

    private void validateTurnOwner(final Position source) {
        if (!board.isPositionSameOwner(source, turnOwner)) {
            throw new IllegalArgumentException("현재는 " + turnOwner.name() + "플레이어의 턴입니다.");
        }
    }

    public void changeTurnOwner() {
        turnOwner = turnOwner.reverse();
    }

    private void checkCaughtKing() {
        if (isKingDead()) {
            gameEnd();
        }
    }

    private void increaseTurnNumber() {
        if (this.turnOwner.equals(Owner.BLACK)) {
            this.turnNumber++;
        }
    }

    private boolean isKingDead() {
        return !board.isKingAlive(this.turnOwner);
    }

    public void gameEnd() {
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void resetBoard() {
        turnOwner = FIRST_TURN;
        board.resetBoard();
    }

    public Path movablePath(final Position source) {
        validateSourcePiece(source);
        return board.movablePath(source);
    }

    public GameStatus gameStatus() {
        return GameStatus.statusOfBoard(board);
    }

    public Board getBoard() {
        return board;
    }

    public int turnNumber() {
        return this.turnNumber;
    }

    public Owner turnOwner() {
        return turnOwner;
    }
}
