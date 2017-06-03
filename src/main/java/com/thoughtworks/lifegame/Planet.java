package com.thoughtworks.lifegame;

/**
 * Created by wuwenjie on 6/3/17.
 */
public class Planet {

    public enum InitState {
        LStyle, OneLine, TwoLine, Cross, Random
    }

    private int mSideLength;

    private Cell[][] mCellMatrix;

    private int mCurrentEpoch;


    public Planet (int sideLength){
        mSideLength = sideLength;
    }

    public void updateAllCells() {
        for (int i = 0; i < mSideLength; i++) {
            for (int j = 0; j < mSideLength; j++) {
                Cell current = mCellMatrix[i][j];
                if (i == 4 && j == 2) {
                    System.out.println("here");
                }
                current.update(this);
            }
        }
        for (int i = 0; i < mSideLength; i++) {
            for (int j = 0; j < mSideLength; j++) {
                Cell current = mCellMatrix[i][j];
                current.synchronizeState();
            }
        }
    }

    /**
     * initiate predefined planet state
     */
    public void init(InitState state) {
        mCellMatrix = new Cell[mSideLength][mSideLength];
        for (int i = 0; i < mSideLength; i++) {
            for (int j = 0; j < mSideLength; j++) {
                mCellMatrix[i][j] = new Cell(j, i);
            }
        }
        switch (state) {
            case LStyle:
                int[] yLStyle = new int[]{3, 3, 3, 2};
                int[] xLStyle = new int[]{1, 2, 3, 3};
                for (int i = 0; i < yLStyle.length; i++) {
                    mCellMatrix[yLStyle[i]][xLStyle[i]].setOldAlive(true);
                }
                break;
            case OneLine:
                int[] yOneLine = new int[]{2, 2, 2, 2, 2};
                int[] xOneLine = new int[]{0, 1, 2, 3, 4};
                for (int i = 0; i < yOneLine.length; i++) {
                    mCellMatrix[yOneLine[i]][xOneLine[i]].setOldAlive(true);
                }
        }
    }

    protected Cell getCell(int x, int y) {
        return mCellMatrix[y][x];
    }

    public Cell[][] getCellMatrix() {
        return mCellMatrix;
    }

    public void setCellMatrix(Cell[][] cellMatrix) {
        mCellMatrix = cellMatrix;
    }

    public int getCurrentEpoch() {
        return mCurrentEpoch;
    }

    public void setCurrentEpoch(int currentEpoch) {
        mCurrentEpoch = currentEpoch;
    }
}
