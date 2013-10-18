package empires;

import java.util.ArrayList;

/**
 *
 * @author Filip Sergot
 */
public class City {
    private Point position;
    
    private String name;
    private float money;
    private int[] production, purchase;
    private int population, cap;
    private ArrayList<Material> granary = new ArrayList<>();

    /**
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the money
     */
    public float getMoney() {
        return money;
    }

    /**
     * @return the production
     */
    public int[] getProduction() {
        return production;
    }

    /**
     * @return the purchase
     */
    public int[] getPurchase() {
        return purchase;
    }

    /**
     * @return the population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @return the cap
     */
    public int getCap() {
        return cap;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(float money) {
        this.money = money;
    }

    /**
     * @param production the production to set
     */
    public void setProduction(int[] production) {
        this.production = production;
    }

    /**
     * @param purchase the purchase to set
     */
    public void setPurchase(int[] purchase) {
        this.purchase = purchase;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * @param cap the cap to set
     */
    public void setCap(int cap) {
        this.cap = cap;
    }

    /**
     * @return the granary
     */
    public ArrayList<Material> getGranary() {
        return granary;
    }

    /**
     * @param granary the granary to set
     */
    public void setGranary(ArrayList<Material> granary) {
        this.granary = granary;
    }
    
    /**
     * checks if there is some cap left
     */
    public void checkCap() {
        // TODO
    }
    
    public void buy(Trader t) {
        // TODO
    }
    
    public void sell(Trader t) {
        // TODO
    }
}
