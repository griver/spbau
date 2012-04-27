package common;

import common.exception.InvalidNoteException;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 27.04.12
 * Time: 22:52
 * To change this template use File | Settings | File Templates.
 */
public class MoneyChecker {
    private HashSet<Integer> notes = new HashSet<Integer>();

    public void addNote(int note) throws InvalidNoteException {
        if(note > 0) notes.add(note);
        else throw  new InvalidNoteException();
    }

    public boolean check(int note) {
        return notes.contains(note);
    }
}