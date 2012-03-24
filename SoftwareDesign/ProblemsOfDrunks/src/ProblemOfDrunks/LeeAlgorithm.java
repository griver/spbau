package ProblemOfDrunks;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 19.03.12
 * Time: 3:15
 * To change this template use File | Settings | File Templates.
 */
public class LeeAlgorithm implements IPathAlgorithm {
    ICell[] queue;
    IField  field;

    public LeeAlgorithm(IField field) {
        this.setField(field);
    }

    @Override
    public List<ICell> findPath(ICell startCell, ICell endCell) {
        HashMap<ICell, ICell> visited = new HashMap<ICell, ICell>();
        
        int head = 0;
        int tail = 1;

        queue[head] = startCell;
        visited.put(startCell, null);

        while(head != tail) {

            for(ICell  cell : field.getCellNeighbors(queue[head])) {
                if(visited.containsKey(cell) == false) {
                    visited.put(cell, queue[head]);
                    queue[tail++] = cell;
                }
            }
            ++head;

        }

        //path building
        LinkedList<ICell> path = new LinkedList<ICell>();

        if(visited.get(endCell) == null ) {
            return null;
        }

        ICell parent = visited.get(endCell);

        while(parent != null) {
            path.addFirst(parent);
            parent = visited.get(parent);
        }

        return path;
    }


    public IField getField() {
        return field;
    }

    public void setField(IField field) {
        this.field = field;
        this.queue = new ICell[field.getHeight() * field.getHeight()];
    }

}
