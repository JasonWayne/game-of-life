package com.thoughtworks.lifegame;

/**
 * Created by wuwenjie on 6/3/17.
 */
public class Cell {

    private boolean mIsNewAlive;


    private boolean mIsOldAlive;

    private int mCellX;
    private int mCellY;

    public Cell(int x, int y) {
        mCellX = x;
        mCellY = y;
        mIsOldAlive = false;
        mIsNewAlive = false;
    }

    /**
     *
     * @return total alive cell around
     */
    public int query(Planet planet) {
        Cell[][] cellMatrix = planet.getCellMatrix();

        int sideLength = cellMatrix.length;

        int baseX = mCellX - 1;
        int baseY = mCellY - 1;

        int aliveCounter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int currentX = baseX + j;
                int currentY = baseY + i;
                if (isValidCell(currentX, currentY, sideLength)) {
                    if (cellMatrix[currentY][currentX].isOldAlive()) {
                        aliveCounter++;
                    }
                }
            }
        }
        if (mIsOldAlive) {
            aliveCounter--;
        }

        return aliveCounter;
    }

    private boolean isValidCell(int x, int y, int sideLength) {
        return !(x < 0 || y < 0 || x >= sideLength || y >= sideLength);
    }

    public boolean update(Planet planet) {
        switch (query(planet)) {
            case 3:
                mIsNewAlive = true;
                break;
            case 2:
                mIsNewAlive = mIsOldAlive;
                break;
            default:
                mIsNewAlive = false;
        }
        return mIsNewAlive;
    }

    public boolean isNewAlive() {
        return mIsNewAlive;
    }

    public void setNewAlive(boolean newAlive) {
        mIsNewAlive = newAlive;
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


    public boolean isOldAlive() {
        return mIsOldAlive;
    }

    public void setOldAlive(boolean oldAlive) {
        mIsOldAlive = oldAlive;
    }

    public void synchronizeState() {
        mIsOldAlive = mIsNewAlive;
    }
}
