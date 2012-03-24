package ProblemOfDrunks;

import java.util.List;


import java.util.Random;
/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 2:49
 * To change this template use File | Settings | File Templates.
 */
public class Drunk implements IMovingObject {
    private Bottle bottle;
    private ICell cell;
    private IField field;
    private DrunkStates state = DrunkStates.AWAKE;

    static public final Random random = new Random();

    public Drunk() {
    }

    @Override
    public void processColliding(Drunk object) {
        //do nothing
    }

    @Override
    public void processColliding(Policeman object) {
        //do nothing
    }

    @Override
    public void processColliding(Lantern object) {
        //do nothing
    }

    //Не бросает рядом бутылку а мог бы!!
    @Override
    public void processColliding(Bottle object) {
        ICell nextCell = object.getCell();

        this.cell.setFieldObject(null);
        this.setCell(nextCell);

        nextCell.setFieldObject(this);
        object.setCell(null);

        this.setState(DrunkStates.LYING);
    }

    @Override
    public void processColliding(Column object) {
        this.setState(DrunkStates.SLEEP);
    }

    @Override
    public void processColliding(IMovingObject object) {
        object.processColliding(this);
    }

    @Override
    public void makeAction() {

        if(this.state != DrunkStates.AWAKE)
            return;

        List<ICell> neighbors = field.getCellNeighbors(cell);
        int n = random.nextInt(neighbors.size());
        ICell nextCell = neighbors.get(n);

        if(nextCell.isEmpty()) {
            this.cell.setFieldObject(null);

            if(bottle != null) {
                if(random.nextInt(30) == 0)
                    dropBottle();
            }

            this.cell = nextCell;
            this.cell.setFieldObject(this);

        } else {
            IFieldObject obj = nextCell.getFieldObject();
            obj.processColliding(this);
        }

    }

    private void dropBottle() {
        this.cell.setFieldObject(this.bottle);
        bottle.setCell(this.cell);
        this.bottle = null;
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

    public DrunkStates getState() {
        return state;
    }

    public void setState(DrunkStates state) {
        this.state = state;
    }
}
