package empires;

/**
 *
 * @author Filip Sergot
 */
public class Trader {
    private Point position;
    private String fname;
    
    private String lname;
    private Cart cart;

    /**
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @return the cart
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @param cart the cart to set
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    /**
     * adds item to the cart
     * @param m a item to add
     */
    public void buy(Material m) {
        // TODO
    }
    
    /**
     * removes item from the cart
     * @param m a item do remove
     */
    public void sell(Material m) {
        // TODO
    }
}
