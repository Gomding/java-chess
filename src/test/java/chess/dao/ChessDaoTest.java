package chess.dao;

import chess.controller.dto.NewGameRequestDto;
import chess.controller.dto.PieceResponseDto;
import chess.domain.manager.ChessManager;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ChessDaoTest {

    private ChessDao chessDao;
    private ChessManager chessManager;
    private Long stateId;
    private Long gameId;

    @BeforeEach
    void setUp() {
        chessDao = new ChessDao();
        chessManager = new ChessManager();
    }

    @Test
    void getConnectionTest() {
        Connection connection = chessDao.getConnection();

        assertThat(connection).isInstanceOf(Connection.class);
    }

    @Test
    @Order(1)
    @DisplayName("Game insert 테스트")
    void createGameTest() {
        NewGameRequestDto newGameRequestDto =
                new NewGameRequestDto("white유저임다", "black유저임다", "1:1초보만");
        gameId = chessDao.createNewGame(newGameRequestDto);

        assertThat(gameId).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("State insert 테스트")
    void createStateTest() {
        stateId = chessDao.createState(chessManager, 1L);

        assertThat(stateId).isNotNull();
    }

    @Test
    @Order(3)
    @DisplayName("Piece insert 테스트")
    void createPieceTest() {
        Long pieceID = chessDao.createPieces(1L, "a1", "r");

        assertThat(pieceID).isNotNull();
    }

    @Test
    @DisplayName("Score insert 테스트")
    void createScoreTest() {
        Long scoreId = chessDao.createScore(chessManager.gameStatus(), 1L);

        assertThat(scoreId).isEqualTo(1);
    }
}