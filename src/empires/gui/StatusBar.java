package empires.gui;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Filip Sergot
 */
public class StatusBar extends JPanel {
    private JLabel statusLabel;

    /**
     * @return the statusLabel
     */
    public String getStatusLabel() {
        return statusLabel.getText();
    }

    /**
     * @param text a String to print in status label
     */
    public void setStatusLabel(String text) {
        statusLabel.setText(text);
    }
    
    public StatusBar() {
        super(new GridLayout());
        
        statusLabel = new JLabel("Status: playing");
        
        this.add(statusLabel);
    }
    
    
    
}
