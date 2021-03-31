package chess.controller.dto;

import chess.domain.board.Board;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.stream.Collectors;

public class BoardResponseDto {
    private final List<String> pieces;

    private BoardResponseDto(final List<String> pieces) {
        this.pieces = pieces;
    }

    public static BoardResponseDto toBoard(final Board board) {
        return new BoardResponseDto(board.pieces().stream()
                .map(Piece::getSymbol)
                .collect(Collectors.toList()));
    }

    public List<String> getPieces() {
        return pieces;
    }
}
