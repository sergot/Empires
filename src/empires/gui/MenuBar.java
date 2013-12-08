package empires.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/**
 *
 * @author Filip Sergot
 */
public class MenuBar extends JMenuBar {
    private JMenu firstMenu;
    private JMenuItem firstMenuFirstItem;
    private JMenuItem exitItem;

    public MenuBar() {
        firstMenu = new JMenu("File");
        
        firstMenuFirstItem = new JMenuItem("New Game");
        firstMenu.add(firstMenuFirstItem);
        
        firstMenu.add(new JSeparator());
        
        exitItem = new JMenuItem("Exit");
        firstMenu.add(exitItem);
        
        this.add(firstMenu);
    }
            
}
