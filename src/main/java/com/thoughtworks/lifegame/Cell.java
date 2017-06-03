package com.thoughtworks.lifegame;

/**
 * Created by wuwenjie on 6/3/17.
 */
public class Cell {

    private boolean mIsAlive;

    private int mCellX;
    private int mCellY;

    /**
     *
     * @return total alive cell around
     */
    public int query(Planet planet) { return 0; }

    public void update(Planet planet) {}

    public boolean isAlive() {
        return mIsAlive;
    }

    public void setAlive(boolean alive) {
        mIsAlive = alive;
    }

    public int getCellX() {
        return mCellX;
    }

    public void setCellX(int cellX) {
        mCellX = cellX;
    }

    public int getCellY() {
        return mCellY;
    }

    public void setCellY(int cellY) {
        mCellY = cellY;
    }
}
