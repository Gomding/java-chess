package chess;

import chess.controller.WebChessController;
import chess.controller.dto.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Gson gson = new Gson();
        staticFiles.location("/static");
        WebChessController webChessController = new WebChessController();

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/game", (request, response) -> {
            NewGameRequestDto newGameRequestDto = gson.fromJson(request.body(), NewGameRequestDto.class);
            Long gameID =  webChessController.newGame(newGameRequestDto);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("gameID", gameID);
            return jsonObject;
        }, gson::toJson);

        get("/game/:id", (request, response) -> {
            Long gameID = Long.parseLong(request.params("id"));
            GameResponseDto gameResponseDto = webChessController.findGameByGameId(gameID);
            Map<String, Object> model = new HashMap<>();
            model.put("gameId", gameID);
            model.put("whiteUsername", gameResponseDto.getWhiteUsername());
            model.put("blackUsername", gameResponseDto.getBlackUsername());
            model.put("roomName", gameResponseDto.getRoomName());
            return render(model, "board.html");
        });

        get("/game/:id/load", (request, response) ->  {
            Long gameID = Long.parseLong(request.params("id"));
            return webChessController.findPiecesByGameId(gameID);
        }, gson::toJson);

        get("/game/:id/score", (request, response) -> {
            Long gameID = Long.parseLong(request.params("id"));
            return webChessController.findScoreByGameId(gameID);
        }, gson::toJson);

        get("/game/:id/state", (request, response) -> {
            Long gameID = Long.parseLong(request.params("id"));
            return webChessController.findStateByGameId(gameID);
        }, gson::toJson);

        get("/game/:id/path", (request, response) -> {
            Long gameId = Long.parseLong(request.params("id"));
            String source = request.queryParams("source");
            return webChessController.movablePath(source, gameId).getPath();
        }, gson::toJson);

        post("/game/:id/move", (request, response) -> {
            Long gameID = Long.parseLong(request.params("id"));
            MoveRequestDto moveRequestDto = gson.fromJson(request.body(), MoveRequestDto.class);
            webChessController.move(moveRequestDto, gameID);
            return "";
        }, gson::toJson);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
