package com.example.tetris;

import com.example.tetris.shapes.Shapes;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Controller {
    private Board b;
    private boolean left = false, right = false, rotate = false;
    private  int frames = 0;
    private final int frameLimit = 20;
    private Tetrimino fallingBlock;
    public Random rand;
    private static final List<Shapes> val = List.of(Shapes.values());

    @FXML
    private Pane mainWindow;
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
        }
        return  true;
    }

    private Tetrimino generateNewBlock() {
        Color c = b.fillings.get(rand.nextInt(b.fillings.size()-2)+2);
        Shapes s = val.get(rand.nextInt(val.size()));
        Tetrimino t = new Tetrimino(c, s);
        t.changeColor(b, t.getColor());
        return t;
    }

    private void gravity() {
        fallingBlock.move(0, b);
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