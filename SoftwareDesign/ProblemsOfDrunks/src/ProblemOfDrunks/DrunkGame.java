package ProblemOfDrunks;

import ProblemOfDrunks.field.IField;
import ProblemOfDrunks.objects.IGameObject;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public class DrunkGame implements IGame {
    //===Fields==========================================================
    private IField field;
    private int steep;
    private HashSet<IGameObject> activeObjects = new HashSet<IGameObject>();
    private LinkedList<IGameObject> addedObjects = new LinkedList<IGameObject>();
    private LinkedList<IGameObject> removedObjects = new LinkedList<IGameObject>();
    //===/Fields=========================================================

    //===Constructors====================================================
    DrunkGame() {
        steep = 0;
    }
    //===/Constructors===================================================

    //===Methods=========================================================
    @Override
    public void registerActiveObject(IGameObject object) {
        addedObjects.addLast(object);
    }

    @Override
    public void removeActiveObject(IGameObject object) {
        removedObjects.addLast(object);
    }

    @Override
    public boolean nextSteep() {
        while(removedObjects.isEmpty() == false) {
            activeObjects.remove(removedObjects.removeFirst());
        }

        while(addedObjects.isEmpty() == false) {
            activeObjects.add(addedObjects.removeFirst());
        }

        if(activeObjects.isEmpty())
            return false;

        for(IGameObject object : activeObjects) {
            object.makeAction();
        }
        return true;
    }
    //===/Methods========================================================

    //==Setters and getters==============================================
    public IField getField() {
        return field;
    }

    public void setField(IField field) {
        this.field = field;
    }
    //==/Setters and getters=============================================
}
