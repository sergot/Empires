package empires.gui;

import empires.Empires;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Empires.getMainWindow().dispose();
                System.exit(0);
            }
        });
        firstMenu.add(exitItem);
        
        this.add(firstMenu);
    }
            
}
