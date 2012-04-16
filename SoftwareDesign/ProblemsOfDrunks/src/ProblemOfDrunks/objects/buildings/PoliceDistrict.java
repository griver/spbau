package ProblemOfDrunks.objects.buildings;

import ProblemOfDrunks.exeption.InvalidCoordinateException;
import ProblemOfDrunks.exeption.NotEmptyCellException;
import ProblemOfDrunks.field.ICell;
import ProblemOfDrunks.field.IField;
import ProblemOfDrunks.IGame;

import ProblemOfDrunks.objects.moving.Drunk;
import ProblemOfDrunks.objects.moving.DrunkStates;
import ProblemOfDrunks.objects.moving.Policeman;
import ProblemOfDrunks.objects.things.Lantern;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:45
 * To change this template use File | Settings | File Templates.
 */
public class PoliceDistrict extends AGameObject {
    //===Fields==========================================================
    private IField field;
    private Lantern lantern;
    private ICell entrance;
    private IGame game;
    private LinkedList<Policeman> policemans = new LinkedList<Policeman>();
    //===/Fields=========================================================

    //===Constructors====================================================
    public PoliceDistrict(IField field, ICell entrance, IGame game) {
        this.field = field;
        this.entrance = entrance;
        this.game = game;
    }
    //===/Constructors===================================================

    //===Methods=========================================================
    @Override
    public void makeAction() {
        if(entrance.isEmpty() == false) {
            return;
        }
        HashSet<ICell> visited = new HashSet<ICell>();
        LinkedList<ICell> queue = new LinkedList<ICell>();

        visited.add(lantern.getCell());
        queue.add(lantern.getCell());

        while (!queue.isEmpty() && getPolicemenNumber() != 0) {
            ICell v = queue.removeFirst();

            if(!v.isEmpty()) {
                v.getFieldObject().processViewing(this);
            }

            try {
                for(ICell u : field.getCellNeighbors(v)) {
                    if(lantern.isLighted(u) &&  visited.contains(u) == false) {
                        queue.addLast(u);
                        visited.add(u);
                    }
                }
            } catch (InvalidCoordinateException e) {
                e.printStackTrace(System.err);
                System.exit(0);
            }
        }
    }

    // Если найдет пьяницу выпустит полицейского
    public void viewFieldObject(Drunk drunk) {
        if(drunk.getState() != DrunkStates.LYING)
            return;
        if(entrance.isEmpty() == false)
            return;

        Policeman policeman = policemans.removeFirst();
        policeman.setTarget(drunk.getCell());
        try {
            field.addObject(policeman, entrance.getCoordinates());
            game.registerActiveObject(policeman);
        } catch (InvalidCoordinateException e) {
            System.err.println("Invalid entance coordinate");
            e.printStackTrace(System.err);
            System.exit(0);
        } catch (NotEmptyCellException e) {
            System.err.println("Entance cell is already occupied");
            e.printStackTrace(System.err);
            System.exit(0);
        }

    }

    public void admitPoliceman(Policeman policeman) {
        policeman.setCell(null);
        policeman.setDrunk(null);
        game.removeActiveObject(policeman);
        policemans.addLast(policeman);
    }

    public void addPoliceman(Policeman policeman) {
        policeman.setField(this.field);
        policeman.setDistrict(this);
        policeman.setDrunk(null);
        policeman.setCell(null);
        policemans.addLast(policeman);
    }
    //===/Methods========================================================

    //==Setters and getters==============================================
    public int getPolicemenNumber() {
        return policemans.size();
    }

    public void  setLatern(Lantern lantern) {
        this.lantern = lantern;
    }

    public ICell getEntrance() {
        return entrance;
    }

    public void setEntrance(ICell entrance) {
        this.entrance = entrance;
    }
    //==/Setters and getters=============================================
}
