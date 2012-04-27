package vending_machine;

import common.exception.BuilderConfigurationException;
import common.exception.InvalidNoteException;
import common.exception.InvalidResourceData;
import common.*;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 28.04.12
 * Time: 0:47
 * To change this template use File | Settings | File Templates.
 */
public class VendingMachineBuilder extends MachineBuilder {
    @Override
    public void build(Machine machine) throws BuilderConfigurationException {
        
        super.build(machine);
        try {
            machine.addResource("bonaqua bottle", new ResourceData(10, 1, 50));
            machine.addResource("perrier bottle", new ResourceData(30, 1, 50));

            ProductData bonaquaData = new ProductData( ProductType.WATER);
            bonaquaData.addResource("bonaqua bottle", 1);
            machine.addProduct("bonaqua", bonaquaData);

            ProductData perrierData = new ProductData( ProductType.WATER);
            perrierData.addResource("perrier bottle", 1);
            machine.addProduct("perrier", perrierData);

        }catch (InvalidResourceData e) {
            System.err.println("Problem with resource parameters");
            throw new BuilderConfigurationException();
        }
    }

    @Override
    public MoneyChecker buildMoneyChecker() throws InvalidNoteException{
        MoneyChecker checker = super.buildMoneyChecker();
        checker.addNote(10);
        checker.addNote(50);
        return checker;
    }
}
