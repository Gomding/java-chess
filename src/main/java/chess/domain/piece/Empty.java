package chess.domain.piece;

import chess.controller.direction.Direction;
import chess.domain.board.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Empty extends Piece{
    private static final List<Direction> DIRECTIONS = new ArrayList<>();
    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    private static final Empty EMPTY = new Empty();

    private Empty() {
        super(Owner.NONE);
    }

    public static Empty getInstance() {
        return EMPTY;
    }

    @Override
    public void validateMove(Position source, Position target, Piece targetPiece) {
        throw new IllegalArgumentException();
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return ".";
    }
}
