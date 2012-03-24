package ProblemOfDrunks;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 2:12
 * To change this template use File | Settings | File Templates.
 */
public class Cell implements ICell{
    IFieldObject object;
    int[] coordinates = null;
    Cell(int[] values) {
        this.setCoordinates(values);
    }
    @Override
    public boolean isEmpty() {
        return (object == null);
    }

    @Override
    public IFieldObject getFieldObject() {
        return this.object;
    }

    @Override
    public void setFieldObject(IFieldObject object) {
        this.object =  object;
    }

    @Override
    public void setCoordinates(int[] values) {
        coordinates = values.clone();
    }

    @Override
    public int[] getCoordinates() {
        return this.coordinates;
    }
}
