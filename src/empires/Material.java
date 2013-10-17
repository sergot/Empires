package empires;

/**
 *
 * @author Filip Sergot
 */

public class Material {
    private int ID;
    private int type;
    private float volume;
    private float weight;

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @return the volume
     */
    public float getVolume() {
        return volume;
    }

    /**
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(float volume) {
        this.volume = volume;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }
    
    public enum TYPES {
        food,
        build
    }
    
    
    
}
