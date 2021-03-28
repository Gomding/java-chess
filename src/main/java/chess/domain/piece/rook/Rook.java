package chess.domain.piece.rook;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.MaxDistance;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class Rook extends Piece {

    private Rook(final Owner owner, final Score score, final List<Direction> directions, MaxDistance maxDistance) {
        super(owner, score, directions, maxDistance);
    }

    private Rook(final Owner owner, final Score score, final List<Direction> directions) {
        this(owner, score, directions, MaxDistance.ROOK);
    }

    protected Rook(final Owner owner) {
        this(owner, new Score(5.0d), Direction.straightDirections());
    }

    public static Rook getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BlackRook.getInstance();
        }

        if (owner.equals(Owner.WHITE)) {
            return WhiteRook.getInstance();
        }

        throw new IllegalArgumentException("Invalid Rook");
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }
}
