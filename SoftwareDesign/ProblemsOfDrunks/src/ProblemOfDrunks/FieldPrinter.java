package ProblemOfDrunks;

import ProblemOfDrunks.exeption.InvalidCoordinateException;
import ProblemOfDrunks.field.ICell;
import ProblemOfDrunks.field.IField;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class FieldPrinter {
    public static void print(IField field) {
       int[] sizes = field.getSizes();
        if(sizes.length != 2) {
            System.out.println("Не могу отображать поля размерности: " + Integer.toString(sizes.length));
            return;
        }
        try{

            ICell cell;
            for(int i=0; i<sizes[0]; i++) {
                for (int j = 0; j<sizes[1]; j++) {
                    cell = field.getCell(i, j);
                    if(cell.isEmpty())
                        System.out.print('');
                    else
                        System.out.print(cell.getFieldObject().getSymbol());
                }
                System.out.println();
            }
        }catch (InvalidCoordinateException e) {
            e.printStackTrace(System.err);
            System.exit(0);
        }
    }
}
