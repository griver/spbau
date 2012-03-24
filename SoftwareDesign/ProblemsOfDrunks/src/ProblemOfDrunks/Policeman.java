package ProblemOfDrunks;

import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:28
 * To change this template use File | Settings | File Templates.
 */
public class Policeman implements IMovingObject {
    private ICell target;
    private ICell source;
    private IField field;
    private Drunk drunk;

    public Policeman() {

    }

    @Override
    public void processColliding(Drunk object) {
        if(object.getState() == DrunkStates.LYING) {
            ICell drunkCell = object.getCell();
            drunkCell.setFieldObject(null);

            object.setCell(null);
            drunk = object;
        }
    }

    @Override
    public void processColliding(Policeman object) {
        //do nothing
    }

    @Override
    public void processColliding(Lantern object) {
        //do nothing
    }

    @Override
    public void processColliding(Bottle object) {
        //do nothing
    }

    @Override
    public void processColliding(Column object) {
        //do nothing
    }

    @Override
    public void processColliding(IMovingObject object) {
        this.processColliding(this);
    }

    @Override
    public void makeAction() {
        List<ICell>
        
    }

    public ICell getCell() {
        return cell;
    }

    public void setCell(ICell cell) {
        this.cell = cell;
    }

    public IField getField() {
        return field;
    }

    public void setField(IField field) {
        this.field = field;
    }
}
