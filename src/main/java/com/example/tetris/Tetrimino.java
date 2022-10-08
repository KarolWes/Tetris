package com.example.tetris;

import com.example.tetris.shapes.Shapes;
import com.example.tetris.shapes.TetraminoShape;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tetrimino {
    int x, y;
    @FXML
    private Color color;

    private TetraminoShape shape;
    private List<int[]> shapeTab;

    public Tetrimino(Color color, Shapes s) {
        this.x = 5;
        this.y = 0;
        this.color = color;
        this.shape = new TetraminoShape(s);
        this.shapeTab = Arrays.asList(shape.getActShape());
    }
    public void rotate(){
        shapeTab = Arrays.asList(shape.rotate());
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void move(int dir, Board b){

        changeColor(b, Color.BLACK);
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

        changeColor(b, color);
    }
    public boolean canFall(Board b){
        boolean result = true;//for each in shape
        int lowest = -1;
        for(int[] pos: shapeTab){
            lowest = Math.max(lowest, pos[1]);
        }
        if(y+lowest == 19){
            return false;
        }
        for(int[] pos: shapeTab){
            int[] tmp = new int[2];
            tmp[0] = pos[0]; tmp[1] = pos[1]+1;
            boolean contains = false;
            for (int[] test: shapeTab) {
                if(Arrays.equals(test, tmp)){
                    contains = true;
                }
            }
            if(!contains){
                result &= !b.isFilled(x+pos[0], y+pos[1] + 1);
            }
        }

        return result;
    }


    public Color getColor() {
        return color;
    }
    public void changeColor(Board b, Color newColor){
        for(int[] pos: shapeTab){
            b.activateField(x+pos[0], y+pos[1], newColor);
        }
    }
}
