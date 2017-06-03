package com.thoughtworks.lifegame;

import java.util.List;

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
    }

    public void updateAllCells() {}

    /**
     * initiate predefined planet state
     */
    public void init(InitState state) {
        mCellMatrix = new Cell[mSideLength][mSideLength];
        switch (state) {
            case LStyle:
                int[] y = new int[]{3, 3, 3, 2};
                int[] x = new int[]{1, 2, 3, 3};
                for (int i = 0; i < y.length; i++) {
                    mCellMatrix[y[i]][x[i]].setAlive(true);
                }
        }
    }

    protected Cell getCell(int x, int y) {
        return mCellMatrix[x][y];
    }
}
