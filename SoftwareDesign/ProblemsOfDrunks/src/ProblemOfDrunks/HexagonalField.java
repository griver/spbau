package ProblemOfDrunks;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:48
 * To change this template use File | Settings | File Templates.
 */
public class HexagonalField implements IField{
    public HexagonalField() {
    }

    @Override
    public List<ICell> getCellNeighbors(ICell cell) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean addObject(int[] coordinates, IFieldObject object) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ICell getCell(int[] coordinates) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCell(int[] coordinates, ICell cell) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
