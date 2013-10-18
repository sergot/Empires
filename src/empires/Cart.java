package empires;

import java.util.ArrayList;

/**
 *
 * @author Filip Sergot
 */
public class Cart {
    private int cap;
    private float speed;
    private ArrayList<Material> materials = new ArrayList<>();

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
}
