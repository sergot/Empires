package empires;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Filip Sergot
 */
public class City extends Being {    
    private String name;
    private float money;
    private ArrayList<Integer> production, purchase;
    private ArrayList<Material> granary = new ArrayList<>();
    private int population, cap;

    City() {
       this.population = Empires.r.nextInt(100000);
    }
    
    City(Point p) {
        super();
        this.setPosition(p);
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
     * @return the production
     */
    public ArrayList<Integer> getProduction() {
        return production;
    }

    /**
     * @return the purchase
     */
    public ArrayList<Integer> getPurchase() {
        return purchase;
    }

    /**
     * @param production the production to set
     */
    public void setProduction(ArrayList<Integer> production) {
        this.production = production;
    }

    /**
     * @param purchase the purchase to set
     */
    public void setPurchase(ArrayList<Integer> purchase) {
        this.purchase = purchase;
    }
    
    /**
     * checks if there is some cap left
     * @return true if there is some cap left, false otherwise
     */
    public boolean checkCap() {
        // TODO
        return true;
    }
    
    public void buy(Trader t) {
        // TODO
    }
    
    public void sell(Trader t) {
        // TODO
    }
    
    public void produce(int type) {
        // TODO
    }
    
    @Override
    public void draw(Graphics2D g) {
        if(population <= 0)
            g.setColor(Color.black);
        g.fillOval((int)this.getPosition().getX() - 12, (int)this.getPosition().getY() - 12, 25, 25);
        
        g.setColor(Color.yellow);
    }
}
