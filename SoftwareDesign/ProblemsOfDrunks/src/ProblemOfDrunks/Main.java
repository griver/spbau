package ProblemOfDrunks;

import ProblemOfDrunks.field.SquareField;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 06.03.12
 * Time: 0:29
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    static public void main(String[] args) {
        IGameBuilder builder = new StandartBuilder(new SquareField(15, 15), new DrunkGame());
        //IGameBuilder builder = new StandartBuilder(new HexagonalField(15, 15), new DrunkGame());
        IGame game = builder.buildGame();
        for(int i = 1; i<= 800; i++) {
            game.nextSteep();
            System.out.println("Ход номер: "+ Integer.toString(i));
            FieldPrinter.print(game.getField());
        }
    }
}
