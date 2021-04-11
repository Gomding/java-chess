package chess.dao;

import chess.controller.web.dto.game.GameResponseDto;
import chess.domain.game.Game;

import java.sql.*;

public class GameDao {

    public Long saveGame(final Connection connection, final Game game) {
        final String query =
                "INSERT INTO game(room_name, white_username, black_username) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, game.roomName());
            pstmt.setString(2, game.whiteUsername());
            pstmt.setString(3, game.blackUsername());
            pstmt.executeLargeUpdate();
            ResultSet resultSet = pstmt.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                throw new IllegalStateException(sqlException);
            }
            throw new IllegalStateException(e);
        }
    }

    public GameResponseDto findGameById(final Long gameId) {
        final String query =
                "SELECT * from game where id = ?";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);) {
            pstmt.setInt(1, gameId.intValue());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new GameResponseDto(
                        gameId,
                        resultSet.getString("white_username"),
                        resultSet.getString("black_username"),
                        resultSet.getString("room_name"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
