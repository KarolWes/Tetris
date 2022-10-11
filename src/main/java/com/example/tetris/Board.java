package com.example.tetris;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Board {
    @FXML
    private Pane mainWindow;
    private int x = 10;
    private int y = 20;
    private int fieldSize = 30;
    private int gap = 3;
    private boolean[][] boardMath;
    private Field[][] boardFields;
    private int[] sums;
    private List<Integer> toDestroy;
    @FXML
    public List<Color> fillings;
    public int x_start, y_start;

    public Board(int x_start, int y_start, Pane window) {
        toDestroy = new ArrayList<>();
        this.x_start = x_start;
        this.y_start = y_start;
        this.mainWindow = window;
        allColors();
        boardMath = new boolean[x][y];
        boardFields = new Field[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                boardMath[i][j] = false;
                boardFields[i][j] = new Field(x_start+i*(fieldSize + gap), y_start+j*(fieldSize + gap), fieldSize, fillings.get(0));
                mainWindow.getChildren().add(boardFields[i][j].getSkin());
            }
        }
        sums = new int[y];
        for(int i = 0; i < y; i++){
            sums[i] = 0;
        }

    }

    private void allColors() {
        fillings = new ArrayList<>();
        fillings.add(Color.BLACK);
        fillings.add(Color.WHITE);
        fillings.add(Color.RED);
        fillings.add(Color.BLUE);
        fillings.add(Color.YELLOW);
        fillings.add(Color.GREEN);
        fillings.add(Color.DEEPPINK);
    }

    public List<Color> getFillings() {
        return fillings;
    }
    public void activateField(int x, int y, Color c){
        boardFields[x][y].changeColor(c);
        boardMath[x][y] ^= true;
    }
    public boolean isFilled(int x, int y){
        return boardMath[x][y];
    }
    private void countSums(){
        for(int i = 0; i < y; i++){
            int tmp = 0;
            for(int j = 0; j < x; j++){
                if(boardMath[j][i]){
                    tmp++;
                }
            }
            sums[i] = tmp;
        }
    }
    private int destroyLines() throws InterruptedException {
        int destroyed = 0;
        for(int i = 0; i < y; i++){
            if(sums[i] == x){
                toDestroy.add(i);
                TimeUnit.MILLISECONDS.sleep(500);
                for(int j = 0; j < x; j++){
                    boardMath[j][i] = false;
                }
                sums[i] = 0;
                destroyed++;
            }
        }
        return destroyed;
    }
    private void moveDown(){
        int destroyed = 0;
        Collections.reverse(toDestroy);
        for(int i: toDestroy){
            for(int m = i+destroyed; m > 0; m--){
                for(int j = 0; j < x; j++){
                    boardMath[j][m] = boardMath[j][m-1];
                    boardFields[j][m].changeColor(boardFields[j][m-1].getFilling());
                }
            }
            destroyed ++;
        }
        toDestroy.clear();
    }
    public int endCheck() throws InterruptedException {
        countSums();
        int points = destroyLines();
        moveDown();
        return points;
    }
}
