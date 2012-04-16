package ProblemOfDrunks.field;

import ProblemOfDrunks.exeption.InvalidCoordinateException;
import ProblemOfDrunks.exeption.NotEmptyCellException;
import ProblemOfDrunks.objects.IFieldObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:48
 * To change this template use File | Settings | File Templates.
 */
public class HexagonalField implements IField {

    //===Fields==========================================================
    private ICell[][] cells;
    //===/Fields=========================================================

    //===Constructors====================================================
    public HexagonalField(int height, int width) {
        cells = new Cell[height][width];

        for(int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }
    //===/Constructors===================================================

    //===Methods=========================================================
    @Override
    public void addObject(IFieldObject object, int... coordinates) throws InvalidCoordinateException, NotEmptyCellException {
        if(checkCoordinate(coordinates) == false) {
            throw new InvalidCoordinateException();
        }

        int x = coordinates[0];
        int y = coordinates[1];

        if(cells[x][y].isEmpty() == false)
            throw new NotEmptyCellException();

        object.setField(this);
        cells[x][y].setFieldObject(object);
        object.setCell(cells[x][y]);
    }

    @Override
    public List<ICell> getCellNeighbors(ICell cell) throws InvalidCoordinateException {
        int[] coordinates = cell.getCoordinates();
        if(checkCoordinate(coordinates) == false)
            throw new InvalidCoordinateException();

        ArrayList<ICell> neighbors  = new ArrayList<ICell>();

        int x = coordinates[0];
        int y = coordinates[1];

        if(this.checkCoordinate(x - 1, y)) {
            neighbors.add(cells[x - 1][y]);
        }

        if(this.checkCoordinate(x, y - 1)) {
            neighbors.add(cells[x][y - 1]);
        }

        if(this.checkCoordinate(x + 1, y)) {
            neighbors.add(cells[x + 1][y]);
        }

        if(this.checkCoordinate(x, y + 1)) {
            neighbors.add(cells[x][y + 1]);
        }

        if(this.checkCoordinate(x - 1, y - 1)) {
            neighbors.add(cells[x - 1][y - 1]);
        }

        if(this.checkCoordinate(x + 1, y + 1)) {
            neighbors.add(cells[x + 1][y + 1]);
        }

        return neighbors;
    }

    @Override
    public int distance(ICell a, ICell b) throws InvalidCoordinateException {
        if(!checkCoordinate(a.getCoordinates()) || !checkCoordinate(b.getCoordinates()))
            throw new InvalidCoordinateException();

        int aX = a.getCoordinates()[0];
        int aY = a.getCoordinates()[1];
        int bX = b.getCoordinates()[0];
        int bY = b.getCoordinates()[1];
        int squareDistance = Math.abs(aX-bX) + Math.abs(aY -bY);

        if((aX- bX)*(aY - bY) > 0) {
            //using (-1, -1) and (+1,+1) neighbors sometimes we can get to b faster
            int hexPlus = Math.max(Math.abs(aX-bX),Math.abs(aY - bY));
            return Math.min(hexPlus, squareDistance);
        } else {
            return squareDistance;
        }
    }

    public boolean checkCoordinate(int... coordinates) {
        if(coordinates.length != 2) {
            return false;
        }

        int x = coordinates[0];
        int y = coordinates[1];

        if(0 <= x && x< cells.length) {
            if(0 <= y && y < cells[0].length) {
                return true;
            }
        }
        return false;
    }
    //===/Methods========================================================

    //==Setters and getters==============================================
    @Override
    public ICell getCell(int... coordinates) throws InvalidCoordinateException {
        if(this.checkCoordinate(coordinates)) {
            return cells[coordinates[0]][coordinates[1]];
        } else {
            throw new InvalidCoordinateException();
        }
    }

    @Override
    public void setCell(ICell cell, int... coordinates) throws InvalidCoordinateException{
        if(checkCoordinate(coordinates)) {
            cells[coordinates[0]][coordinates[1]] = cell;
        } else {
            throw new InvalidCoordinateException();
        }
    }

    public int getHeight() {
        return cells.length;
    }

    public int getWight() {
        if(cells.length > 0) {
            return cells[0].length;
        } else {
            return 0;
        }
    }

    public int getNumberOfCells(){
        return getHeight()*getWight();
    }

    public int[] getSizes() {
        return new int[]{getHeight(), getWight() };
    }
    //==/Setters and getters=============================================
}



