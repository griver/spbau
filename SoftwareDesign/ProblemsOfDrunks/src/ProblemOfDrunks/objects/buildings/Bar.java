package ProblemOfDrunks.objects.buildings;

import ProblemOfDrunks.exeption.InvalidCoordinateException;
import ProblemOfDrunks.exeption.NotEmptyCellException;
import ProblemOfDrunks.field.ICell;
import ProblemOfDrunks.field.IField;
import ProblemOfDrunks.IGame;
import ProblemOfDrunks.objects.moving.Drunk;
import ProblemOfDrunks.objects.things.Bottle;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:43
 * To change this template use File | Settings | File Templates.
 */
public class Bar extends AGameObject {
    //===Fields==========================================================
    private IField field;
    private ICell entrance;
    private int counter;
    private IGame game;
    //===/Fields=========================================================

    //===Constructors====================================================
    public Bar(IField field, ICell entrance, IGame game) {
        this.field = field;
        this.entrance = entrance;
        this.game = game;
        counter = 0;
    }
    //===/Constructors===================================================

    //===Methods=========================================================
    @Override
    public void makeAction() {
        ++counter;
        if(counter % 20 == 0) {
            if(entrance.isEmpty())
                releaseDrunk();
        }
    }

    private void releaseDrunk() {
        Drunk newDrunk = new Drunk();

        newDrunk.setBottle(new Bottle());
        try {
            field.addObject(newDrunk, entrance.getCoordinates());
        } catch(InvalidCoordinateException e) {
            System.err.println("Invalid Entrance coordinate in Bar");
            System.exit(0);
        } catch (NotEmptyCellException e) {
            System.err.println("Bar tried to add Drunk in occupied Cell");
            System.exit(0);
        }
        game.registerActiveObject(newDrunk);
    }
    //===/Methods========================================================
}
