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
    private float money = 0;
    
    private ArrayList<Material.types> production = new ArrayList<>(),
                                      purchase   = new ArrayList<>();
    private ArrayList<Material> granary;
    
    private int population, cap = 20;
    
    City() {
        String[] names = new String[10];
        names[0] = "Oklahoma City";
        names[1] = "New Orlean";
        names[2] = "Miami";
        names[3] = "New York";
        names[4] = "San Antonio";
        names[5] = "Houston";
        names[6] = "Los Angeles";
        names[7] = "Cleavland";
        names[8] = "Phoenix";
        names[9] = "Golden State";
        
        name = names[Empires.getR().nextInt(names.length)];
        
        this.granary = new ArrayList<>();
        this.population = Empires.getR().nextInt(100000) + 1000;

        production.add(Material.types.randomType());
        Material.types m = null;
        do {
            m = Material.types.randomType();
        } while(m == production.get(0));
        purchase.add(m);
    }
    
    City(Point p) {
        super();
        this.granary = new ArrayList<>();
        this.setPosition(p);
    }
    
    @Override
    public String toString() {
        return name + getPosition().toString();
    }
    
    /**
     * adds material to granary
     * @param m material to be added
     */
    private void addToGranary(Material m) {
        if(checkCap()) {
            cap--;
            granary.add(m);
        }
    }
    
    /**
     * removes material from granary
     * @param t type of material to remove
     */
    private void removeFromGranary(Material m) {
        cap++;
        granary.remove(m);
    }
    
    /**
     * buys material of given type from a trader
     * @param t type of material to buy
     * @param trader buys from this trader
     */
    public void buyMaterial(Material.types t, Trader trader) {
        Material m = trader.sell(t);
        if(m != null) {
            addToGranary(m);
        }
    }
    
    /**
     * sells material to a trader
     * @param m material to sell
     * @param trader trader who buys material
     */
    public void sellMaterial(Material m, Trader trader) {
        trader.buy(m);
        removeFromGranary(m);
    }
    
    /**
     * trade with traders who is in the city
     * @param t trader in city
     */
    public synchronized void trade(Trader t) {
        for(Object o : granary.toArray()) {
            Material m = (Material) o;
            if(production.contains(m.getType())) {
                sellMaterial(m, t);
            }
        }
        
        for(Material.types type : purchase) {
            buyMaterial(type, t);
        }
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
    public ArrayList<Material.types> getProduction() {
        return production;
    }

    /**
     * @return the purchase
     */
    public ArrayList<Material.types> getPurchase() {
        return purchase;
    }

    /**
     * @param production the production to set
     */
    public void setProduction(ArrayList<Material.types> production) {
        this.production = production;
    }

    /**
     * @param purchase the purchase to set
     */
    public void setPurchase(ArrayList<Material.types> purchase) {
        this.purchase = purchase;
    }
    
    /**
     * checks if there is some cap left
     * @return true if there is some cap left, false otherwise
     */
    public boolean checkCap() {
        return cap > 0;
    }
    
    /**
     * checks if materials are expired
     */
    public void checkMaterials() {
        for(Object o : granary.toArray()) {
            Material m = (Material) o;
            if(!m.checkExpiry())
                if(!m.ifFood()) {
                    removeFromGranary(m);
                    money += 20;
                } else
                    removeFromGranary(m);
        }
    }
    
    /**
     * produce materials
     */
    public void produce() {
        for(Material.types t : production) {
            if(checkCap()) {
                cap--;
                granary.add(new Material(t));
            }
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        if(population <= 0)
            g.setColor(Color.black);
        g.fillOval((int)this.getPosition().getX() - 12, (int)this.getPosition().getY() - 12, 25, 25);
        
        g.setColor(Color.yellow);
    }
}
