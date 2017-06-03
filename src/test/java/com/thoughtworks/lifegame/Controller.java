package com.thoughtworks.lifegame;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by wuwenjie on 6/3/17.
 */
public class Controller {

    @FXML
    Canvas canvas;

    private GraphicsContext mGraphicsContext;

    private Color colorChessBoard = Color.valueOf("#FBE39B");
    private Color colorLine = Color.valueOf("#884B09");
    private Color testColor = Color.valueOf("#888888");

    private static final double BROAD_PADDING = 20;
    private double gapX, gapY;

    public void initialize() {
        mGraphicsContext = canvas.getGraphicsContext2D();
        gapX = (canvas.getWidth() - BROAD_PADDING * 2) / 20;
        System.out.println(gapX);

        gapY = (canvas.getWidth() - BROAD_PADDING * 2) / 20;

        cleanPlanet();
    }

    private void cleanPlanet() {
        mGraphicsContext.setFill(colorChessBoard);
        mGraphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        mGraphicsContext.setStroke(colorLine);

        mGraphicsContext.setFill(testColor);
        mGraphicsContext.fillRect(BROAD_PADDING, BROAD_PADDING, gapX, gapX);

        for (int i = 0; i <= 20; i++) {
            mGraphicsContext.strokeLine(i * gapX + BROAD_PADDING, BROAD_PADDING, i * gapX + BROAD_PADDING, canvas.getHeight() - BROAD_PADDING);
            mGraphicsContext.strokeLine(BROAD_PADDING, i * gapY + BROAD_PADDING, canvas.getWidth() - BROAD_PADDING, i * gapY + BROAD_PADDING);
        }
    }

}
