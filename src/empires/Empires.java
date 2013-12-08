package empires;

import empires.gui.MainPanel;
import empires.gui.MenuBar;
import empires.gui.RightMenu;
import empires.gui.StatusBar;
import java.awt.BorderLayout;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Filip Sergot
 */
public class Empires {
    public static Game game;
    public static Random r;
    public static JFrame mainWindow;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mainWindow = new JFrame("Empires");
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainWindow.add(new MenuBar(), BorderLayout.NORTH);
        mainWindow.add(new RightMenu(), BorderLayout.EAST);
        mainWindow.add(new MainPanel(), BorderLayout.CENTER);
        mainWindow.add(new StatusBar(), BorderLayout.SOUTH);
        
        mainWindow.pack();
        mainWindow.setVisible(true);
        
        r = new Random();
        
        game = new Game();
        game.play();
    }
    
}
