package com.thoughtworks.lifegame;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;

/**
 * Created by wuwenjie on 6/3/17.
 */
public class Controller {

    @FXML
    Canvas canvas;

//    @FXML
//    ChoiceBox initPanelStateChoiceBox;

    private GraphicsContext mGraphicsContext;

    private Color colorChessBoard = Color.valueOf("#FBE39B");
    private Color colorLine = Color.valueOf("#884B09");
    private Color aliveColor = Color.valueOf("#888888");
    private Color deadColor = Color.valueOf("#FBE39B");

    private static final double BROAD_PADDING = 20;
    private double gapX, gapY;

    private Planet.InitState state;
    private Planet mPlanet;
    private int mSideLength = 5;

    public void initialize() {
        mGraphicsContext = canvas.getGraphicsContext2D();
        gapX = (canvas.getWidth() - BROAD_PADDING * 2) / mSideLength;
        System.out.println(gapX);

        gapY = (canvas.getWidth() - BROAD_PADDING * 2) / mSideLength;

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "LStyle", "OneLine")
        );
        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                switch(newValue.intValue()) {
                    case 0:
                        state = Planet.InitState.LStyle;
                        break;
                    case 1:
                        state = Planet.InitState.OneLine;
                        break;
                    default:
                        state = Planet.InitState.LStyle;
                        break;
                }

            }
        });

        cleanPlanet();


//        for (int i = 0; i < 2; i++) {
//        while(true) {
            mPlanet.updateAllCells();
        Cell[][] matrix = mPlanet.getCellMatrix();
            updateCanvas();
//            try {
//                Thread.sleep(1000);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

    private void cleanPlanet() {
        mGraphicsContext.setFill(colorChessBoard);
        mGraphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        mGraphicsContext.setStroke(colorLine);

        mPlanet = new Planet(mSideLength);
        mPlanet.init(Planet.InitState.LStyle);

        updateCanvas();


    }

    private void updateCanvas() {
        for (int i = 0; i < mSideLength; i++) {
            for (int j = 0; j < mSideLength; j++) {
                Cell[][] matrix = mPlanet.getCellMatrix();
                if (matrix[i][j].isNewAlive()) {
                    mGraphicsContext.setFill(aliveColor);
                    mGraphicsContext.fillRect(BROAD_PADDING + gapX * j, BROAD_PADDING + gapY * i, gapX, gapY);
                } else {
                    mGraphicsContext.setFill(deadColor);
                    mGraphicsContext.fillRect(BROAD_PADDING + gapX * j, BROAD_PADDING + gapY * i, gapX, gapY);
                }

            }
        }

        for (int i = 0; i <= mSideLength; i++) {
            mGraphicsContext.strokeLine(i * gapX + BROAD_PADDING, BROAD_PADDING, i * gapX + BROAD_PADDING, canvas.getHeight() - BROAD_PADDING);
            mGraphicsContext.strokeLine(BROAD_PADDING, i * gapY + BROAD_PADDING, canvas.getWidth() - BROAD_PADDING, i * gapY + BROAD_PADDING);
        }
    }

}
