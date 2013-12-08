package empires.gui;

import empires.Barbarians;
import empires.City;
import empires.Empires;
import empires.Road;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Filip Sergot
 */
public class MainPanel extends JPanel {

    public MainPanel() {
        super();
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
        if(!Empires.game.painted) Empires.game.painted = true;
    }
    
    private void drawGame(Graphics2D g) {
        //if(!Empires.game.painted) {
            for(Road r : Empires.game.getRoads())
                r.draw(g);
        //}
        for(City c : Empires.game.getCities())
            c.draw(g);
        
        for(Barbarians b : Empires.game.getBarbarians())
            b.draw(g);
    }
    
}
