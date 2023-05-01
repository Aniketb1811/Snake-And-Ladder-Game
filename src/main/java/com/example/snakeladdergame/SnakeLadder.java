package com.example.snakeladdergame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize = 50, width  =10, height = 10;

    public static final int buttonLine = height*tileSize + 50, infoLine = buttonLine - 30;

    private static final Dice dice = new Dice();

    private Player playerOne, playerTwo;

    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize + 100);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        Image image = new Image("C:\\Users\\Aniket\\Desktop\\SnakeLadderGame\\src\\main\\resources\\vectorstock_8154036.jpg");
        ImageView board = new ImageView();
        board.setImage(image);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        //Adding image to button
        Image player1 = new Image("C:\\Users\\Aniket\\Desktop\\SnakeLadderGame\\src\\main\\resources\\Player 1 main.png");
        Image player2 = new Image("C:\\Users\\Aniket\\Desktop\\SnakeLadderGame\\src\\main\\resources\\Player 2 main.png");
        Image startGame = new Image("C:\\Users\\Aniket\\Desktop\\SnakeLadderGame\\src\\main\\resources\\Game Start main.png");
        ImageView player1view = new ImageView(player1);
        ImageView player2view = new ImageView(player2);
        ImageView startGameview = new ImageView(startGame);
        player1view.setFitHeight(15);
        player1view.setFitWidth(80);
        player2view.setFitHeight(15);
        player2view.setFitWidth(80);
        startGameview.setFitHeight(15);
        startGameview.setFitWidth(80);

        //Buttons on Board
        Button playerOneButton = new Button("", player1view);
        Button playerTwoButton = new Button("", player2view);
        Button startButton = new Button("", startGameview);
        playerOneButton.setPrefSize(100, 10);
        playerTwoButton.setPrefSize(100, 10);
        startButton.setPrefSize(100,10);

        playerOneButton.setTranslateX(40);
        playerOneButton.setTranslateY(buttonLine);

        playerTwoButton.setTranslateX(360);
        playerTwoButton.setTranslateY(buttonLine);

        startButton.setTranslateX(210);
        startButton.setTranslateY(buttonLine);
//        playerOneButton.getPrefHeight();
//        playerOneButton.getPrefHeight();

        //Setting Info line
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start The Game");

        playerOneLabel.setTranslateX(60);
        playerOneLabel.setTranslateY(infoLine);

        playerTwoLabel.setTranslateX(380);
        playerTwoLabel.setTranslateY(infoLine);

        diceLabel.setTranslateX(220);
        diceLabel.setTranslateY(infoLine);

        //Adding players
        playerOne = new Player(tileSize- 10, Color.WHITE, "Aniket");
        playerTwo = new Player(tileSize - 20, Color.BLACK, "Yuvraj");

        //Moving Player
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        int diceValue = dice.getRollDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerOne.movePlayer(diceValue);

                        // Winn or Not
                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is " + playerOne.getName());

                            playerOneTurn= false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn=true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted=false;
                        }
                        else {
                            playerOneTurn= false;
                            playerTwoTurn = true;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");
                            playerTwoTurn=true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn :" + playerTwo.getName());
                        }

                    }
                }

            }
        });

        // Start Button Action
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started!");
                startButton.setDisable(true);
                playerOneTurn = true;
                playerOneLabel.setText("Your Turn :" + playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();

                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getRollDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerTwo.movePlayer(diceValue);
                        // Winn or Not
                        if(playerTwo.isWinner()){
                            diceLabel.setText("Winner is " + playerTwo.getName());

                            playerOneTurn= false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn=true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                        }
                        else{
                            playerOneTurn= true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn " + playerTwo.getName());

                            playerTwoTurn=false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                        }

                    }
                }
            }
        });

        root.getChildren().addAll(board,
                playerOneButton, playerTwoButton, startButton,
                playerOneLabel, playerTwoLabel, diceLabel,
                playerOne.getCoin(), playerTwo.getCoin()
        );
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder Game!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}