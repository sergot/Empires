package empires;

import java.util.ArrayList;

/**
 *
 * @author Filip Sergot
 */
public class Cart {
    private int cap;
    private float speed;
    private boolean broken;
    
    private ArrayList<Material> materials = new ArrayList<>();

    public Cart() {
        cap = 10;
        broken = false;
    }
    
    /**
     * checks if cart contains material of given type
     * @param type type of material to check
     * @return contains or not
     */
    public boolean has(Material.types type) {
        for(Material m : materials)
            if(type == m.getType())
                return true;
        return false;
    }
    
    /**
     * adds material to the cart
     * @param m material to add
     */
    public void add(Material m) {
        cap--;
        materials.add(m);
    }
    
    /**
     * gets material from the cart
     * @param t type of material to get
     * @return requested material
     */
    public Material get(Material.types t) {
        Material ret = null;
        
        for(Material m : materials) {
            if(m.getType() == t) {
                cap++;
                materials.remove(m);
                ret = m;
                break;
            }
        }
        
        return ret;
    }
    
    /**
     * gets random material from the cart
     * @return random Material from cart
     */
    public Material getRandomMaterial() {
        if(materials.size() == 0) return null;
        return materials.get(Empires.getR().nextInt(materials.size()));
    }
    
    /**
     * checks if cart is broken
     * @return true if broken; false otherwise
     */
    public boolean getBroken() {
        return broken;
    }
    
    /**
     * @return the cap
     */
    public int getCap() {
        return cap;
    }

    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param cap the cap to set
     */
    public void setCap(int cap) {
        this.cap = cap;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * @return the materials
     */
    public ArrayList<Material> getMaterials() {
        return materials;
    }

    /**
     * @param materials the materials to set
     */
    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }

    /**
     * @param broken the broken to set
     */
    public void setBroken(boolean broken) {
        this.broken = broken;
    }
}
