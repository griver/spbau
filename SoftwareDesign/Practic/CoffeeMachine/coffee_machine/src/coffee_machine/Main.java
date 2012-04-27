package coffee_machine;

import common.Machine;
import common.MachineBuilder;
import common.exception.BuilderConfigurationException;
import common.exception.InvalidResourceData;
import common.exception.WrongCommandException;
import common.exception.WrongProductNameException;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 28.04.12
 * Time: 2:01
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(System.in);
            Machine vendingMachine= new Machine();
            MachineBuilder builder = new MachineBuilder();
            builder.build(vendingMachine);


            String str;
            while(true) {
                try{
                    str = scanner.next();
                    if(str.equals("i")) {
                        str = scanner.next().trim();
                        vendingMachine.insert(Integer.parseInt(str));
                    } else if(str.equals("s")) {
                        str = scanner.next().trim();
                        vendingMachine.select(str);
                    } else if(str.equals("c")) {
                        vendingMachine.cancel();
                    } else if(str.equals("a")){
                        vendingMachine.addSugar();
                    } else {
                        throw new WrongCommandException();
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid parameter of the insert command.");
                } catch (WrongProductNameException e) {
                    System.out.println("Invalid parameter of the select command. Such product does not exist.");
                } catch (WrongCommandException e) {
                    System.out.println("Wrong Command. Existing commands:\n" +
                            " i - insert\n" +
                            " c - cancel\n" +
                            " s - select \n" +
                            " a - add sugar");
                }

            }
        } catch (BuilderConfigurationException e) {
            System.err.println("Machine building Fails");
        } catch (InvalidResourceData e) {
            System.err.println("Problem somewhere deep in program.");
            e.printStackTrace(System.err);
        }

    }

}

