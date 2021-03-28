package chess.view;

import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Piece;
import chess.manager.Status;

public class OutputView {

    private OutputView() {
    }

    public static void printGuideStartGame() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작은 start, 재시작은 restart, 종료는 end 명령을 입력하세요.");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(final Board board) {
        for (final Horizontal horizontal : Horizontal.values()) {
            for (final Vertical vertical : Vertical.values()) {
                final Piece piece = board.of(Position.of(vertical, horizontal));
                System.out.print(piece.getSymbol());
            }
            System.out.println();
        }
    }

    public static void printRestartGame(Board board) {
        System.out.println("게임을 재시작합니다.");
        printBoard(board);
    }

    public static void printAbleToMove(final Board board, final Path ableToMove) {
        for (final Horizontal horizontal : Horizontal.values()) {
            for (final Vertical vertical : Vertical.values()) {
                final Position position = Position.of(vertical, horizontal);
                printPieceOrAbleToMoveSymbol(board, position, ableToMove);
            }
            System.out.println();
        }
    }

    private static void printPieceOrAbleToMoveSymbol(final Board board, final Position position, final Path ableToMove) {
        if (ableToMove.contains(position)) {
            System.out.print("*");
            return;
        }

        final Piece piece = board.of(position);
        System.out.print(piece.getSymbol());
    }


    public static void printStatus(final Status status) {
        System.out.println("White score : " + status.whiteScore());
        System.out.println("Black score : " + status.blackScore());
    }

    public static void printGameResult(final Status status) {
        printStatus(status);

        System.out.println("=== 게임 결과 ===");

        if (status.whiteScore() > status.blackScore()) {
            System.out.println("화이트의 승리입니다.");
        }

        if (status.whiteScore() < status.blackScore()) {
            System.out.println("블랙의 승리입니다.");
        }

        if (status.whiteScore() == status.blackScore()) {
            System.out.println("무승부입니다.");
        }
    }

    public static void printEndGame() {
        System.out.println("게임을 종료합니다.");
    }

    public static void printError(RuntimeException runtimeException) {
        System.out.println(runtimeException.getMessage());
    }
}
