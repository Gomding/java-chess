package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;

import java.util.List;

public abstract class Pawn extends Piece{
    private static final int ABLE_DISTANCE_TO_MOVE = 2;

    private static final Pawn BLACK_PAWN = new Pawn(Owner.BLACK, Direction.blackPawnDirections()){
        @Override
        public boolean isFirstLine(Horizontal horizontal) {
            return Horizontal.SEVEN.equals(horizontal);
        }
    };

    private static final Pawn WHITE_PAWN = new Pawn(Owner.WHITE, Direction.whitePawnDirections()) {
        @Override
        public boolean isFirstLine(Horizontal horizontal) {
            return Horizontal.TWO.equals(horizontal);
        }
    };

    private Pawn(Owner owner, List<Direction> directions) {
        super(owner, directions);
    }

    public static Pawn getInstanceOf(Owner owner){
        if (owner.equals(Owner.BLACK)){
            return BLACK_PAWN;
        }

        if (owner.equals(Owner.WHITE)){
            return WHITE_PAWN;
        }

        throw new IllegalArgumentException("Invalid pawn");
    }

    public abstract boolean isFirstLine(Horizontal horizontal);

    public boolean validateMove(Position source, Position target, Piece targetPiece) {
        return isValidStraightMove(source, target) || isValidDiagonalMove(source, target, isEnemy(targetPiece));
    }

    private boolean isValidStraightMove(Position source, Position target) {
        return source.isStraight(target) && (isValidBasicMove(source, target) || isValidSpecialMove(source, target));
    }

    private boolean isValidBasicMove(Position source, Position target) {
        return source.getDistance(target) == 1;
    }

    private boolean isValidSpecialMove(Position source, Position target){
        return isFirstLine(source.getHorizontal())
                && source.getDistance(target) == ABLE_DISTANCE_TO_MOVE;
    }

    private boolean isValidDiagonalMove(Position source, Position target, boolean isEnemy){
        if(!source.isDiagonal(target)){
            return false;
        }
        return source.getDistance(target) == 1 && isEnemy;
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}