package empires.gui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Filip Sergot
 */
public class RightMenu extends JPanel {

    public RightMenu() {
        super(new GridBagLayout());
        this.setPreferredSize(new Dimension(250, 0));
        this.setBorder(BorderFactory.createTitledBorder("Control"));
    }
    
}
