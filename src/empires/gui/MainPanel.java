package empires.gui;

import empires.Barbarians;
import empires.City;
import empires.Empires;
import empires.Legion;
import empires.Road;
import empires.Trader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Filip Sergot
 */
public class MainPanel extends JPanel implements MouseListener {

    public MainPanel() {
        super();
        
        addMouseListener(this);
        
        this.setPreferredSize(new Dimension(800, 500));
        this.setBackground(Color.white);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.black);
        g2d.setColor(Color.yellow);
        
        this.drawGame(g2d);
        if(!Empires.getGame().isPainted()) Empires.getGame().setPainted(true);
    }
    
    /**
     * draws all objects on the screen
     * @param g Graphics2D
     */
    private void drawGame(Graphics2D g) {
        for(Road r : Empires.getGame().getRoads())
            r.draw(g);
        
        for(Road r : Empires.getGame().getRoadsC())
            r.draw(g);
        
        for(City c : Empires.getGame().getCities())
            c.draw(g);
        
        for(Barbarians b : Empires.getGame().getBarbarians())
            b.draw(g);
        
        for(Legion l : Empires.getGame().getLegions())
            l.draw(g);
        
        for(Trader t : Empires.getGame().getTraders())
            t.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX(), y = me.getY();
                
        for(City c : Empires.getGame().getCities()) {
            if(c.closeTo(x, y)) {
                Empires.getGame().setSelected(c);
                Empires.getRightMenu().beingPanel.removeAll();
                Empires.getRightMenu().beingPanel.add(new JLabel(c.getName()));
                Empires.getRightMenu().beingPanel.add(new JLabel("money: " + c.getMoney()));
                Empires.getRightMenu().beingPanel.add(new JLabel("population: " + c.getPopulation()));
                Empires.getRightMenu().beingPanel.add(new JLabel("cap: " + c.getCap()));
                Empires.getRightMenu().beingPanel.add(new JLabel("granary: " + c.getGranary()));
                Empires.getRightMenu().beingPanel.add(new JLabel("buys: " + c.getPurchase()));
                Empires.getRightMenu().beingPanel.add(new JLabel("sells: " + c.getProduction()));
                
                JButton produce = new JButton("Produce");
                produce.setActionCommand("produce");
                produce.addActionListener(Empires.getRightMenu());
                Empires.getRightMenu().beingPanel.add(produce);
            }
        }
        
        for(Barbarians b : Empires.getGame().getBarbarians()) {
            if(b.closeTo(x, y)) {
                Empires.getRightMenu().beingPanel.removeAll();
                Empires.getRightMenu().beingPanel.add(new JLabel(b.getName()));
                Empires.getRightMenu().beingPanel.add(new JLabel("in: " + b.getIn()));
            }
        }
        
        for(Legion l : Empires.getGame().getLegions()) {
            if(l.closeTo(x, y)) {
                Empires.getRightMenu().beingPanel.removeAll();
                Empires.getRightMenu().beingPanel.add(new JLabel("position: " + l.getPosition()));
            }
        }
        
        for(Trader t : Empires.getGame().getTraders()) {
            if(t.closeTo(x, y)) {
                Empires.getGame().setSelected(t);
                Empires.getRightMenu().beingPanel.removeAll();
                Empires.getRightMenu().beingPanel.add(new JLabel(t.getFname() + " " + t.getLname()));
                Empires.getRightMenu().beingPanel.add(new JLabel("to: " + t.getTo()));
                Empires.getRightMenu().beingPanel.add(new JLabel("cart: " + t.getCart().getMaterials()));
                
                JButton br = new JButton("Break");
                br.setActionCommand("break");
                br.addActionListener(Empires.getRightMenu());
                Empires.getRightMenu().beingPanel.add(br);
                
                JButton rp = new JButton("Repair");
                rp.setActionCommand("repair");
                rp.addActionListener(Empires.getRightMenu());
                Empires.getRightMenu().beingPanel.add(rp);
                
                JButton rm = new JButton("Remove");
                rm.setActionCommand("removetr");
                rm.addActionListener(Empires.getRightMenu());
                Empires.getRightMenu().beingPanel.add(rm);
                
                Empires.getRightMenu().newCity = new JTextField(20);
                Empires.getRightMenu().newCity.setText("write number of city");
                Empires.getRightMenu().newCity.selectAll();
                Empires.getRightMenu().beingPanel.add(Empires.getRightMenu().newCity);
                
                JButton changeCity = new JButton("Change city");
                changeCity.setActionCommand("changecity");
                changeCity.addActionListener(Empires.getRightMenu());
                Empires.getRightMenu().beingPanel.add(changeCity);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
