package com.example.tetris;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field {
    private int x;
    private int y;
    private int size;
    @FXML
    private Color filling;
    @FXML
    private Rectangle skin;

    public Field(int x, int y, int size, Color filling) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.filling = filling;
        skin = new Rectangle(x,y,size,size);
        skin.setFill(filling);
    }

    public void changeColor(Color newColor){
        skin.setFill(newColor);
        filling = newColor;
    }

    public Color getFilling() {
        return filling;
    }

    public Rectangle getSkin() {
        return skin;
    }
}
