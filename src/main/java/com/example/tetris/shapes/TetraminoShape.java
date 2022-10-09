package com.example.tetris.shapes;

import java.util.ArrayList;
import java.util.List;

public class TetraminoShape {
    private List<int[][]> rotations;
    private Shapes s;
    int actRot;

    public TetraminoShape(Shapes s) {
        this.s = s;
        actRot = 0;
        rotations = new ArrayList<>();
        rotations.add(getTypeShape(s));
        getRemindigRotations(s);
    }

    private void getRemindigRotations(Shapes s) {
        int[][] shape = new int[4][2];
        switch (s){
            case I -> {

                shape[0][0] = 0; shape[0][1] = 0;
                shape[1][0] = 1; shape[1][1] = 0;
                shape[2][0] = 2; shape[2][1] = 0;
                shape[3][0] = 3; shape[3][1] = 0;
                rotations.add(shape);
            }
            case L -> {
                shape[0][0] = 0; shape[0][1] = 0;
                shape[1][0] = 1; shape[1][1] = 0;
                shape[2][0] = 2; shape[2][1] = 0;
                shape[3][0] = 0; shape[3][1] = 1;
                rotations.add(shape);
                shape = new int[4][2];
                shape[0][0] = 0; shape[0][1] = 0;
                shape[1][0] = 1; shape[1][1] = 0;
                shape[2][0] = 1; shape[2][1] = 1;
                shape[3][0] = 1; shape[3][1] = 2;
                rotations.add(shape);
                shape = new int[4][2];
                shape[0][0] = 2; shape[0][1] = 0;
                shape[1][0] = 0; shape[1][1] = 1;
                shape[2][0] = 1; shape[2][1] = 1;
                shape[3][0] = 2; shape[3][1] = 1;
                rotations.add(shape);
            }
            case J -> {
                shape[0][0] = 0; shape[0][1] = 0;
                shape[1][0] = 1; shape[1][1] = 0;
                shape[2][0] = 2; shape[2][1] = 0;
                shape[3][0] = 2; shape[3][1] = 1;
                rotations.add(shape);
                shape = new int[4][2];
                shape[0][0] = 0; shape[0][1] = 0;
                shape[1][0] = 1; shape[1][1] = 0;
                shape[2][0] = 1; shape[2][1] = 1;
                shape[3][0] = 1; shape[3][1] = 2;
                rotations.add(shape);
                shape = new int[4][2];
                shape[0][0] = 0; shape[0][1] = 0;
                shape[1][0] = 0; shape[1][1] = 1;
                shape[2][0] = 1; shape[2][1] = 1;
                shape[3][0] = 2; shape[3][1] = 1;
                rotations.add(shape);
            }
            case S -> {
                shape[0][0] = 1; shape[0][1] = 0;
                shape[1][0] = 2; shape[1][1] = 0;
                shape[2][0] = 0; shape[2][1] = 1;
                shape[3][0] = 1; shape[3][1] = 1;
                rotations.add(shape);
            }
            case Z -> {
                shape[0][0] = 1; shape[0][1] = 0;
                shape[1][0] = 0; shape[1][1] = 1;
                shape[2][0] = 1; shape[2][1] = 1;
                shape[3][0] = 0; shape[3][1] = 2;
                rotations.add(shape);
            }
            case T -> {
                shape[0][0] = 1; shape[0][1] = 0;
                shape[1][0] = 0; shape[1][1] = 1;
                shape[2][0] = 1; shape[2][1] = 1;
                shape[3][0] = 1; shape[3][1] = 2;
                rotations.add(shape);
                shape = new int[4][2];
                shape[0][0] = 1; shape[0][1] = 0;
                shape[1][0] = 0; shape[1][1] = 1;
                shape[2][0] = 1; shape[2][1] = 1;
                shape[3][0] = 2; shape[3][1] = 1;
                rotations.add(shape);
                shape = new int[4][2];
                shape[0][0] = 0; shape[0][1] = 0;
                shape[1][0] = 0; shape[1][1] = 1;
                shape[2][0] = 1; shape[2][1] = 1;
                shape[3][0] = 0; shape[3][1] = 2;
                rotations.add(shape);
            }
            case O ->{}
        }

    }

    private int[][] getTypeShape(Shapes type){
        int[][] shape = new int[4][2]; // x, y
        shape[0][0] = 0; shape[0][1] = 0; // pivot
        switch(type){

            case I -> {
                shape[1][0] = 0; shape[1][1] = 1;
                shape[2][0] = 0; shape[2][1] = 2;
                shape[3][0] = 0; shape[3][1] = 3;
            }
            case L -> {
                shape[1][0] = 0; shape[1][1] = 1;
                shape[2][0] = 0; shape[2][1] = 2;
                shape[3][0] = 1; shape[3][1] = 2;
            }
            case J -> {
                shape[1][0] = 1; shape[1][1] = 0;
                shape[2][0] = 0; shape[2][1] = 1;
                shape[3][0] = 0; shape[3][1] = 2;
            }
            case O -> {
                shape[1][0] = 0; shape[1][1] = 1;
                shape[2][0] = 1; shape[2][1] = 0;
                shape[3][0] = 1; shape[3][1] = 1;
            }
            case S -> {
                shape[1][0] = 1; shape[1][1] = 0;
                shape[2][0] = 1; shape[2][1] = 1;
                shape[3][0] = 2; shape[3][1] = 1;
            }
            case Z -> {
                shape[1][0] = 0; shape[1][1] = 1;
                shape[2][0] = 1; shape[2][1] = 1;
                shape[3][0] = 1; shape[3][1] = 2;
            }
            case T -> {
                shape[1][0] = 1; shape[1][1] = 0;
                shape[2][0] = 2; shape[2][1] = 0;
                shape[3][0] = 1; shape[3][1] = 1;
            }
        }
        return shape;
    }
    public int [][] getActShape(){
        return rotations.get(actRot);
    }
    public int [][] rotate(){
        actRot = (actRot+1)%rotations.size();
        return rotations.get(actRot);
    }
    public void reverseRotation(){
        actRot = (actRot-1)%rotations.size();
    }
}
