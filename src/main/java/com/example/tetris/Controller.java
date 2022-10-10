package com.example.tetris;

import com.example.tetris.shapes.Shapes;
import com.example.tetris.shapes.TetraminoShape;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.*;

public class Controller {
    private Board b;
    private boolean left = false, right = false, rotate = false;
    private  int frames = 0;
    private final int frameLimit = 1;
    private Tetrimino fallingBlock;
    public Random rand;
    private Color lastColor = Color.BLACK;
    private static final List<Shapes> val = List.of(Shapes.values());

    @FXML
    private Pane mainWindow;
    @FXML
    private Text endGameBaner;
    public void initialize(){
        rand = new Random();
        b = new Board(50, 50, mainWindow);
        fallingBlock = generateNewBlock();
        timer.start();
    }

    public AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            frames++;
            movement();
            if (frames == frameLimit) {

                frames = 0;
                try {
                    check();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                gravity();
            }
        }
    };

    private boolean check() throws InterruptedException {
        if(!fallingBlock.canFall(b)){
            b.endCheck();
            fallingBlock = generateNewBlock();
            if(Objects.isNull(fallingBlock)){
                endGame();
            }

        }
        return  true;
    }

    private void endGame() {
        timer.stop();
        endGameBaner = new Text("Game Over");
        endGameBaner.setX(100);
        endGameBaner.setY(200);
        endGameBaner.setFill(Color.AZURE);
        endGameBaner.setFont(Font.font(24));
        mainWindow.getChildren().add(endGameBaner);
    }

    private Tetrimino generateNewBlock() {
        Color c = b.fillings.get(rand.nextInt(b.fillings.size()-2)+2);
        while(c == lastColor){
            c = b.fillings.get(rand.nextInt(b.fillings.size()-2)+2);
        }
        lastColor = c;
        Shapes s = val.get(rand.nextInt(val.size()));
        Tetrimino t = new Tetrimino(c, s);
        if(t.testEnd(b)){
            return null;
        }
        t.changeColor(b, t.getColor());
        return t;
    }

    private void gravity() {
        if(fallingBlock != null){
            fallingBlock.move(0, b);
        }

    }
    private void movement(){
        if(left){
            fallingBlock.move(-1, b);
            left = false;
        }
        if(right){
            fallingBlock.move(1, b);
            right = false;
        }
        if(rotate){
            rotate = false;
            fallingBlock.rotate(b);
        }
    }

    public EventHandler<KeyEvent> handleKeyReleased = new EventHandler<>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            switch (keyEvent.getCode()) {
                case A, LEFT:
                    left = false;
                    break;
                case D, RIGHT:
                    right = false;
                    break;
                case W, UP, SPACE:
                    rotate = false;
            }
        }
    };


    public EventHandler<KeyEvent> handleKeyPressed = new EventHandler<>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            switch (keyEvent.getCode()) {
                case A, LEFT:
                    left = true;
                    right = false;
                    break;
                case D, RIGHT:
                    right = true;
                    left = false;
                    break;
                case W, UP, SPACE:
                    rotate = true;
                    break;
                default:
                    break;
            }
        }
    };


}