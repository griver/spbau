package common;


import common.exception.BuilderConfigurationException;
import common.exception.InvalidNoteException;
import common.exception.InvalidResourceData;
import common.exception.WrongProductNameException;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 27.04.12
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public class MachineBuilder {
    public void build(Machine machine) throws BuilderConfigurationException {
        try {
            machine.setMoneyChecker(buildMoneyChecker());
            ProductMaker standartMaker = new ProductMaker();
            machine.setProductMaker(standartMaker);

            machine.addResource("plastic cup", new ResourceData(0, 1 , 100));
            machine.addResource("sugar", new ResourceData(1, 1, 4));
            machine.addResource("cupuccino powder", new ResourceData(4, 50, 1000));
            machine.addResource("latte powder", new ResourceData(6, 50, 1000));

            ProductData latteData = new ProductData( ProductType.COFFEE);
            latteData.addResource("plastic cup", 1);
            latteData.addResource("latte powder", 1);
            machine.addProduct("latte", latteData);

            ProductData cupuccinoData = new ProductData(ProductType.COFFEE);
            cupuccinoData.addResource("plastic cup", 1);
            cupuccinoData.addResource("cupuccino powder", 1);
            machine.addProduct("cupuccino", cupuccinoData);

        } catch(InvalidNoteException e) {
            System.err.println("Problem with bank note values");
            throw new BuilderConfigurationException();
        } catch (InvalidResourceData e) {
            System.err.println("Problem with resource parameters");
            throw new BuilderConfigurationException();
        }
    }
    
    public MoneyChecker buildMoneyChecker() throws InvalidNoteException{
        MoneyChecker checker = new MoneyChecker();
        checker.addNote(1);
        checker.addNote(2);
        checker.addNote(3);
        checker.addNote(5);
        return checker;
    }
}
