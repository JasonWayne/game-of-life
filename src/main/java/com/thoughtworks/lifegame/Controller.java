package com.thoughtworks.lifegame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wuwenjie on 6/3/17.
 */
public class Controller {

    @FXML
    Canvas canvas;

    @FXML
    ProgressBar bar;

    @FXML
    Button btn;

    @FXML
    TextField fieldSideLength;

    @FXML
    TextField fieldReproducePeriod;

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

    private double mUpdatePeriod = 500;

    private Timer mCurrentTimer;

    public void initialize() {
        mGraphicsContext = canvas.getGraphicsContext2D();
        gapX = (canvas.getWidth() - BROAD_PADDING * 2) / mSideLength;
        gapY = (canvas.getWidth() - BROAD_PADDING * 2) / mSideLength;
        state = Planet.InitState.LStyle;

        cleanPlanet();

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
                if (matrix[i][j].isOldAlive()) {
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

    public void handleUpdateClicked(ActionEvent actionEvent) {
        createNewTimer();
    }

    public void handleLStyleClicked(ActionEvent actionEvent) {
        mPlanet.init(Planet.InitState.LStyle);
        state = Planet.InitState.LStyle;

        updateCanvas();

    }

    public void handleOneLineClicked(ActionEvent actionEvent) {
        mPlanet.init(Planet.InitState.OneLine);

        state = Planet.InitState.OneLine;
        updateCanvas();
    }

    public void handleUpdateSideLengthClicked(ActionEvent actionEvent) {
        try {
            mSideLength = Integer.valueOf(fieldSideLength.getText());
        } catch (NumberFormatException e){
            fieldSideLength.setText(mSideLength + "");
        }
        gapX = (canvas.getWidth() - BROAD_PADDING * 2) / mSideLength;
        gapY = (canvas.getWidth() - BROAD_PADDING * 2) / mSideLength;
        mPlanet = new Planet(mSideLength);
        mPlanet.init(state);
        updateCanvas();
    }

    public void handleUpdateReproducePeriodClicked(ActionEvent actionEvent) {

        updatePeriod();
    }

    public void handleDynamicUpdateReproducePeriodClicked(ActionEvent actionEvent) {
        updatePeriod();
        createNewTimer();
    }

    public void createNewTimer() {
        mCurrentTimer= new Timer();
        mCurrentTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        mPlanet.updateAllCells();
                        updateCanvas();
                    }
                });
            }
        }, 0, (int) mUpdatePeriod);
    }

    public void updatePeriod() {
        try {
            mUpdatePeriod = Double.valueOf(fieldReproducePeriod.getText());

        } catch (NumberFormatException e) {
            fieldReproducePeriod.setText(mUpdatePeriod + "");
        }
        mPlanet = new Planet(mSideLength);
        mPlanet.init(state);
        updateCanvas();
        mCurrentTimer.cancel();
    }
}
