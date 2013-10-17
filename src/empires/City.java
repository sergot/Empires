package empires;

/**
 *
 * @author Filip Sergot
 */
public class City {
    private Point position;
    
    private String name;
    private float money;
    private int[] production;
    private int[] purchase;
    private int population;
    private int cap;
    private int capNow;

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
     * @return the capNow
     */
    public int getCapNow() {
        return capNow;
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
     * @param capNow the capNow to set
     */
    public void setCapNow(int capNow) {
        this.capNow = capNow;
    }
    
    
}
