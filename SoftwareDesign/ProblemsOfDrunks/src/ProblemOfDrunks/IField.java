package ProblemOfDrunks;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 14.03.12
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
public interface IField {
    List<ICell> getCellNeighbors(ICell cell);
    boolean addObject(int[] coordinates, IFieldObject object);
    ICell getCell(int[] coordinates);
    void setCell(int[] coordinates, ICell cell);
    int getHeight();
    int getWight();
}
