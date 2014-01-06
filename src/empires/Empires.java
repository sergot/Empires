package empires;

import empires.gui.MainPanel;
import empires.gui.MenuBar;
import empires.gui.RightMenu;
import empires.gui.StatusBar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Filip Sergot
 */
public class Empires {
    private static Game game;
    private static Random r;
    private static JFrame mainWindow;
    private static StatusBar statusBar;
    private static RightMenu rightMenu;
    public static int id = 0;
    public static JTextField name;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mainWindow = new JFrame("Empires");
        getMainWindow().setLayout(new BorderLayout());
        getMainWindow().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getMainWindow().add(new MenuBar(), BorderLayout.NORTH);
        
        rightMenu = new RightMenu();
        getMainWindow().add(getRightMenu(), BorderLayout.EAST);
        getMainWindow().add(new MainPanel(), BorderLayout.CENTER);
        
        statusBar = new StatusBar();
        getMainWindow().add(getStatusBar(), BorderLayout.SOUTH);
        
        getMainWindow().pack();
        getMainWindow().setVisible(true);
        
        r = new Random();
        
        game = new Game();
        getGame().play();
        getGame().stop();
        
        // game ends
        JFrame over = new JFrame("GAME OVER");
        JPanel gameOver = new JPanel(new GridLayout(1, 2));
        
        name = new JTextField();
        gameOver.add(name);
        
        JButton submitScore = new JButton("Submit");
        submitScore.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                getGame().setName(name.getText());
                saveScore(getGame());
                System.exit(0);
            }
        });
        gameOver.add(submitScore);
        over.add(gameOver);
        
        over.pack();
        over.setVisible(true);
        
    }
    
    /**
     * saves current score
     * @param gejm current game
     */
    private static void saveScore(Game gejm) {
        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("scores.xml")));
        } catch (FileNotFoundException ex) {
            File f = new File("scores.xml");
            try {
                f.createNewFile();
            } catch (IOException ex1) {
            }
        }
        
        ArrayList<Game> games = new ArrayList<>();
        if(decoder != null) {
            games = (ArrayList<Game>) decoder.readObject();
        }
        
        XMLEncoder encoder = null;
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("scores.xml")));
            
        } catch (FileNotFoundException ex) {
        }
        if(encoder != null) {
            games.add(gejm);
            
            Collections.sort(games, new CustomComparator());
            Collections.reverse(games);
            
            if(games.size() > 5)
                games.remove(5);
            
            encoder.writeObject(games);
            encoder.close();
        }
    }

    /**
     * @return the r
     */
    public static Random getR() {
        return r;
    }

    /**
     * @return the game
     */
    public static Game getGame() {
        return game;
    }

    /**
     * @return the mainWindow
     */
    public static JFrame getMainWindow() {
        return mainWindow;
    }

    /**
     * @return the statusBar
     */
    public static StatusBar getStatusBar() {
        return statusBar;
    }

    /**
     * @return the rightMenu
     */
    public static RightMenu getRightMenu() {
        return rightMenu;
    }
}


class CustomComparator implements Comparator<Game> {
    @Override
    public int compare(Game g1, Game g2) {
        return g1.getTime().compareTo(g2.getTime());
    }
}