package empires.gui;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Filip Sergot
 */
public class StatusBar extends JPanel {
    private static JLabel statusLabel;

    /**
     * @return the statusLabel
     */
    public static String getStatusLabel() {
        return statusLabel.getText();
    }

    /**
     * @param text a String to print in status label
     */
    public static void setStatusLabel(String text) {
        statusLabel.setText(text);
    }
    
    public StatusBar() {
        super(new GridLayout());
        
        statusLabel = new JLabel("Status: playing");
        
        this.add(statusLabel);
    }
    
    
    
}
