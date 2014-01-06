package empires;

import empires.gui.BeingGUI;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.PathIterator;

/**
 *
 * @author Filip Sergot
 */

public class Trader extends Being implements Movable, Runnable {
    private BeingGUI being;
    private Road currentRoad;
    
    private City from, to, in, next = null;
    
    private String fname;
    private String lname;
    
    private Cart cart;
    
    private boolean changed = false;

    public Trader() {
        String[] fnames = new String[10];
        String[] snames = new String[10];
        
        fnames[0] = "Hakeem";
        fnames[1] = "James";
        fnames[2] = "Michael";
        fnames[3] = "LeBron";
        fnames[4] = "Dirk";
        fnames[5] = "Dwyane";
        fnames[6] = "Stephen";
        fnames[7] = "Chris";
        fnames[8] = "Steve";
        fnames[9] = "Dwight";
        
        snames[0] = "Olajuvon";
        snames[1] = "Harden";
        snames[2] = "Jordan";
        snames[3] = "James";
        snames[4] = "Nowitzky";
        snames[5] = "Wade";
        snames[6] = "Curry";
        snames[7] = "Bosh";
        snames[8] = "Nash";
        snames[9] = "Howard";
        
        
        cart = new Cart();
        
        being = new BeingGUI(BeingGUI.type.TRADER, this);
        this.setRadius(being.getRadius());
        
        in = Empires.getGame().getCities().get(Empires.getR().nextInt(Empires.getGame().getCities().size()));
        
        fname = fnames[Empires.getR().nextInt(fnames.length)] + "'" + Empires.id++ + "'";
        lname = snames[Empires.getR().nextInt(snames.length)];
    }
    
    /**
     * @return the first name
     */
    public String getFname() {
        return fname;
    }

    /**
     * @return the last name
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
     * @param fname the first name to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @param lname the last name to set
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
        if(cart.getCap() > 0) {
            being.setSpeed(being.getSpeed() - 0.005);
            cart.add(m);
        }
    }
    
    /**
     * removes item from the cart
     * @param t
     * @return true if sold; false otherwise
     */
    public Material sell(Material.types t) {
        if(cart.has(t)) {
            being.setSpeed(being.getSpeed() + 0.005);
            return cart.get(t);
        }
        return null;
    }

    @Override
    public void move() {
        double[] point = new double[2];
        
        PathIterator pi = being.getPath().getPathIterator(null);
        
        while(!pi.isDone()) {
            int type = pi.currentSegment(point);
            
            if(type == PathIterator.SEG_MOVETO) {
                double x = point[0] - being.getRadius() / 2,
                       y = point[1] - being.getRadius() / 2;
                being.setFrame(x, y, being.getRadius(), being.getRadius());
                setPosition(new Point(x, y));
            } else if(type == PathIterator.SEG_LINETO) {
                double ox = being.getCenterX();
                double oy = being.getCenterY();
                double nx = point[0];
                double ny = point[1];
                
                double distance = Math.sqrt((ox-nx)*(ox-nx) + (oy-ny)*(oy-ny));
                
                int count = (int)((distance / being.getSpeed()) / being.getDelay());
                
                double deltaX = (nx - ox) / count;
                double deltaY = (ny - oy) / count;
                
                for (int i = 0; i < count; i++) {
                    if(this.dead())
                        break;
                    
                    if(cart.getBroken()) {
                        i--;
                        continue;
                    }
                    
                    boolean closeTo = false;
                    for(Object o : Empires.getGame().getTraders().toArray()) {
                        Trader t = (Trader) o;
                        if(t != null && !t.equals(this) && t.closeTo((int)this.getPosition().getX(), (int)this.getPosition().getY()) && t.getTo().equals(this.getTo())
                                && this.getPosition().distance(getTo().getPosition()) > t.getPosition().distance(t.getTo().getPosition())) {
                            closeTo = true;
                        }
                    }
                    
                    if(closeTo) {
                        i--;
                        continue;
                    }
                    
                    ox += deltaX;
                    oy += deltaY;
                    
                    double new_x = ox - being.getRadius() / 2,
                           new_y = oy - being.getRadius() / 2;
                    setPosition(new Point(new_x, new_y));
                    
                    being.setFrame(new_x, new_y, being.getRadius(), being.getRadius());
                    
                    try { Thread.sleep(being.getDelay()); }
                    catch (InterruptedException e) { }
                }
            }
            pi.next();
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.setColor(being.getColor());
        g.draw(being);
        
        g.setColor(Color.yellow);
    }

    @Override
    public void run() {
        while(!dead()) {
            if(!cart.getBroken()) {
                from = in;
                
                Material randMaterial = cart.getRandomMaterial();
                Material.types what = null;
                if(randMaterial != null)
                    what = randMaterial.getType();

                if(what != null) {
                    setTo(Empires.getGame().getCityWhichBuys(what));
                    if(currentRoad != null &&currentRoad.getEnd().equals(Empires.getGame().getCenter())) {
                        currentRoad = Empires.getGame().specificRoad(getTo());
                    } else {
                        currentRoad = Empires.getGame().specificRoad(from, getTo());
                    }
                } else {
                    currentRoad = null;
                }
                
                if(currentRoad == null || next != null) {
                    if(next != null) {
                        setTo(next);
                        next = null;
                    } else
                        if(currentRoad == null)
                            setTo(Empires.getGame().getCities().get(Empires.getR().nextInt(Empires.getGame().getCities().size())));
                    if(in == null) {
                        currentRoad = Empires.getGame().specificRoad(getTo());
                    } else {
                        currentRoad = Empires.getGame().specificRoad(from, getTo());
                    }
                }
                
                if(currentRoad != null) {
                    being.setPath(currentRoad.getPath());

                    City oldTo = to;
                    synchronized(Empires.getGame().getCrossLock()) {
                        Empires.getGame().setOnCross(false);
                    }
                    move();
                    changed = !oldTo.equals(to);
                    
                    if(currentRoad.getEnd().equals(Empires.getGame().getCenter())) {
                        in = null;
                        synchronized(Empires.getGame().getCrossLock()) {
                            Empires.getGame().setOnCross(true);
                        }
                    } else {
                        in = oldTo;
                        if(in.getPopulation() > 0)
                            if(!changed)
                                in.trade(this);
                    }
                    
                    try {
                        if(in != null && in.getPopulation() > 0 && !changed) Thread.sleep(5000);
                    } catch (InterruptedException ex) {}
                }
            }
        }
        Empires.getGame().getTraders().remove(this);
    }

    /**
     * @return the to
     */
    public City getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(City to) {
        this.to = to;
    }

    /**
     * @param changed the changed to set
     */
    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    /**
     * @param next the next to set
     */
    public void setNext(City next) {
        this.next = next;
    }
}
