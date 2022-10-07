package com.example.tetris;

import com.example.tetris.shapes.Shapes;
import com.example.tetris.shapes.TetraminoShape;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class Tetrimino {
    int x, y;
    @FXML
    private Color color;

    private TetraminoShape shape;
    private int[][] shapeTab;

    public Tetrimino(Color color, Shapes s) {
        this.x = 5;
        this.y = 0;
        this.color = color;
        this.shape = new TetraminoShape(s);
        this.shapeTab = shape.getActShape();
    }
    public void rotate(){
        shapeTab = shape.rotate();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void move(int dir, Board b){//for each block in tetramino
        b.activateField(x, y, Color.BLACK);
        boolean result = true;
        if(dir == 0){
            y ++;
        }else{
            if(dir == -1){
                if(x == 0){
                    result = false;
                }
                else if(b.isFilled(x-1, y)){
                    result = false;
                }
            }
            else{
                if(x == 9){
                    result = false;
                }
                else if(b.isFilled(x+1, y)){
                    result = false;
                }
            }
            if(result){
                x += dir;
            }
        }

        b.activateField(x, y, color);//for each block in tetramino
    }
    public boolean canFall(Board b){//for each in shape
        if(y == 19)
        {
            return false;
        }
        boolean result = true;

        result &= !b.isFilled(x, y + 1);
        return result;
    }

    public Color getColor() {
        return color;
    }
}
