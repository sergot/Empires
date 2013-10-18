package empires;

/**
 *
 * @author Filip Sergot
 */
public class Road {
    private City start;
    private City end;
    private boolean oneWay = false;

    /**
     * @return the start
     */
    public City getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public City getEnd() {
        return end;
    }

    /**
     * @return the oneWay
     */
    public boolean isOneWay() {
        return oneWay;
    }

    /**
     * @param start the start to set
     */
    public void setStart(City start) {
        this.start = start;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(City end) {
        this.end = end;
    }

    /**
     * @param oneWay the oneWay to set
     */
    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }
    
    
}
