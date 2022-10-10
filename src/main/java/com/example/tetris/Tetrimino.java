package com.example.tetris;

import com.example.tetris.shapes.Shapes;
import com.example.tetris.shapes.TetraminoShape;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

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
    public void rotate(Board b){
        changeColor(b, Color.BLACK);
        var tmp = Arrays.asList(shape.rotate());
        boolean result = true;
        for(var pos: tmp){
            if(x+pos[0] < 10){
                result &= ! b.isFilled(x+pos[0], y+pos[1]);
            }
            else{
                result = false;
            }

        }
        if(result){
            shapeTab = tmp;
        }
        else{
            shape.reverseRotation();
        }
        changeColor(b, color);
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
                else if(!canMove(b, -1, 0)){
                    result = false;
                }
            }
            else{
                int furthest = -1;
                for(int[] pos: shapeTab){
                    furthest = Math.max(furthest, pos[0]);
                }
                if(x+furthest == 9){
                    result = false;
                }
                else if(!canMove(b, 1, 0)){
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
        int lowest = -1;
        for(int[] pos: shapeTab){
            lowest = Math.max(lowest, pos[1]);
        }
        if(y+lowest == 19){
            return false;
        }
        return canMove(b, 0, 1);


    }

    private boolean canMove(Board b, int dir_x, int dir_y) {
        boolean result = true;
        for(int[] pos: shapeTab){
            int[] tmp = new int[2];
            tmp[0] = pos[0]+dir_x; tmp[1] = pos[1]+dir_y;
            boolean contains = false;
            for (int[] test: shapeTab) {
                if(Arrays.equals(test, tmp)){
                    contains = true;
                }
            }
            if(!contains){
                result &= !b.isFilled(x+pos[0] +dir_x, y+pos[1] + dir_y);
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
    public TetraminoShape getShape(){
        return shape;
    }

    public boolean testEnd(Board b) {
        boolean result = false;
        for(var pos : shapeTab){
            result |= b.isFilled(x+pos[0], y+pos[1]);
        }
        return result;
    }
}
