package com.thoughtworks.lifegame;

/**
 * Created by wuwenjie on 6/3/17.
 */
public class Cell {

    private boolean mIsAlive;

    private int mCellX;
    private int mCellY;

    public Cell(int x, int y) {
        mCellX = x;
        mCellY = y;
        mIsAlive = false;
    }

    /**
     *
     * @return total alive cell around
     */
    public int query(Planet planet) {
        Cell[][] cellMatrix = planet.getCellMatrix();
        int sideLength = cellMatrix[0].length;

        int baseX = mCellX - 1;
        int baseY = mCellY - 1;

        int aliveCounter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int currentX = baseX + i;
                int currentY = baseY + j;
                if (isValidCell(currentX, currentY, sideLength)) {
                    if (cellMatrix[currentY][currentX].isAlive()) {
                        aliveCounter++;
                    }
                }
            }
        }

        return aliveCounter;
    }

    private boolean isValidCell(int x, int y, int sideLength) {
        return !(x < 0 || y < 0 || x >= sideLength || y >= sideLength);
    }




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
