package empires;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Filip Sergot
 */

public class Material {
    public enum types {
        food,
        wood,
        corn,
        copper,
        weapon,
        armor,
        brick;
        
        private static final List<types> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static types randomType()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
    
    private int ID;
    private types type;
    private float volume;
    private float weight;
    private int expiry = -1;
    
    /**
     *
     * @param t type of material
     */
    public Material(types t) {
        type = t;
        
        if(t == types.corn)
            expiry = 50000;
        else if(t == types.food)
            expiry = 20000;
        else
            expiry = 100000;
        
        ID = Empires.id++;
    }
    
    @Override
    public String toString() {
        return "" + type;
    }
    
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the type
     */
    public types getType() {
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
    public void setType(types type) {
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
    
    /**
     * checks if materials is expired
     * @return true if it's not expired; false otherwise
     */
    public boolean checkExpiry() {
        return expiry-- > 0;
    }
    
    /**
     * check if this material is a food
     * @return true if it's food; false otherwise
     */
    public boolean ifFood() {
        return type == types.food || type == types.corn;
    }
}
