package ProblemOfDrunks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 17.03.12
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class SquareField implements IField {
    private ICell[][] cells;

    public SquareField(int height, int width) {
        cells = new Cell[height][width];
    }

    @Override
    public List<ICell> getCellNeighbors(ICell cell) {

        int[] coordinates = cell.getCoordinates();
        ArrayList<ICell> neighbors  = new ArrayList<ICell>();

        if(coordinates.length != 2)
            //<БРОСИТЬ ЕКСЕПШОН!!
            return neighbors;

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

        return neighbors;
    }

    @Override
    public boolean addObject(int[] coordinates, IFieldObject object) {

        int x = coordinates[0];
        int y = coordinates[1];

        if(cells[x][y].isEmpty() == false)
            return false;

        cells[x][y].setFieldObject(object);
        return true;
    }

    @Override
    public ICell getCell(int[] coordinates) {
        return cells[coordinates[0]][coordinates[1]];
    }

    @Override
    //,бросить исключение по сатане для проверку координат и клетки
    public void setCell(int[] coordinates, ICell cell) {
        cells[coordinates[0]][coordinates[1]] = cell;
    }

    private boolean checkCoordinate(int x, int y) {
        if(0 <= x || x< cells.length) {
            if(0 <= y || y < cells[0].length) {
                return true;
            }
        }
        return false;
    }
}
