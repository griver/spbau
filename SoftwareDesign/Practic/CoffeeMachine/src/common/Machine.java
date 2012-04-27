package common;

import common.exception.InvalidResourceData;
import common.exception.WrongProductNameException;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 28.04.12
 * Time: 2:41
 * To change this template use File | Settings | File Templates.
 */

public class Machine {
    private HashMap<String, ResourceData> resources = new HashMap<String, ResourceData>();
    private HashMap<String, ProductData> products = new HashMap<String, ProductData>();
    private ProductMaker maker;
    private MoneyChecker moneyChecker;

    private int moneyInside;
    private int sugarQuantity;

    public void setMoneyChecker(MoneyChecker checker) {
        this.moneyChecker = checker;
    }

    public void setProductMaker(ProductMaker maker) {
        this.maker = maker;
        this.maker.setResources(resources);
    }

    public void addResource(String name, ResourceData data) {
        resources.put(name, data);
    }

    public void addProduct(String name, ProductData data) {
        products.put(name, data);
    }

    public void insert( int note) {
        if(moneyChecker.check(note)) {
            moneyInside += note;
        } else {
            returnMoney(note);
        }

    }
    //exception
    public void select(String productName) throws WrongProductNameException, InvalidResourceData {
        if(!products.containsKey(productName)) {
            cancel();
            throw new WrongProductNameException();
        }

        ProductData data = products.get(productName);
        if(maker.canMake(data))
            if(maker.getCost(data) <= moneyInside) {
                moneyInside-= maker.getCost(data);
                Product product = maker.make(productName, data);
                tryToAddSugar(product, data);
                returnProduct(product);
            }
        cancel();
    }

    public void addSugar() {
        ++sugarQuantity;
    }

    public void cancel() {
        returnMoney(moneyInside);
        moneyInside = 0;
        sugarQuantity = 0;
    }


    private void tryToAddSugar(Product product, ProductData data) throws InvalidResourceData {
        if(data.getType() == ProductType.COFFEE) {
            ResourceData sugarData = resources.get("sugar");
            int addedSugar = Math.min(sugarData.hasPortions(), sugarQuantity);
            addedSugar = Math.min(addedSugar, moneyInside/sugarData.getCost());
            moneyInside -= addedSugar* sugarData.getCost();
            sugarData.removePortions(addedSugar);
            if(addedSugar > 0)
                product.addText(" with "+ Integer.toString(addedSugar) + " lumps of sugar");
        }
    }

    private void returnProduct( Product product) {
        System.out.println("Machine returns: " + product.getText());
    }

    private void returnMoney(int notes) {
        System.out.println("Machine returns: " + Integer.toString(notes) +  " notes");
    }
}

