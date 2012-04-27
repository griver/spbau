package common;

import common.exception.InvalidResourceData;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 27.04.12
 * Time: 18:21
 * To change this template use File | Settings | File Templates.
 */
public class ResourceData {
    private String name;
    private int cost;
    private int portion;
    private int number;

    public ResourceData(int cost, int portion, int number) throws InvalidResourceData{
        if(cost < 0 || portion < 0 || number < 0) throw new InvalidResourceData();
        this.cost = cost;
        this.portion = portion;
        this.number = number;
    }

    boolean removePortions(int i) throws InvalidResourceData {
        if(i < 0) throw new InvalidResourceData();

        if(number >= portion * i) {
            number-=portion * i;
            return true;
        } else {
            return false;
        }
    }

    int getNumber() {
        return number;
    }

    int getCost() {
        return cost;
    }

    public int getPortion() {
        return portion;
    }

    public int hasPortions() {
        return number/ portion;
    }

}
