package empires;

/**
 *
 * @author Filip Sergot
 */
public class Cart {
    private int cap;
    private float speed;
    private Material[] materials;

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
     * @return the materials
     */
    public Material[] getMaterials() {
        return materials;
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
     * @param materials the materials to set
     */
    public void setMaterials(Material[] materials) {
        this.materials = materials;
    }
    
    
}
