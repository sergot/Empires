package empires.gui;

import empires.City;
import empires.Trader;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import empires.Empires;
import empires.Legion;
import javax.swing.JTextField;

/**
 *
 * @author Filip Sergot
 */
public class RightMenu extends JPanel implements ActionListener {
    public JPanel panel;
    public JPanel beingPanel;
    public JTextField newCity;

    public RightMenu() {
        super(new BorderLayout());
        this.setPreferredSize(new Dimension(250, 0));
        this.setBorder(BorderFactory.createTitledBorder("Control"));
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        
        JButton newLegion = new JButton("New legion");
        newLegion.setActionCommand("newlegion");
        newLegion.addActionListener(this);
        
        JButton newTrader = new JButton("New trader");
        newTrader.setActionCommand("newtrader");
        newTrader.addActionListener(this);
        
        panel.add(newLegion); panel.add(newTrader);
        
        beingPanel = new JPanel(new GridLayout(8, 1));
        
        add(panel, BorderLayout.NORTH);
        add(beingPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String comm = ae.getActionCommand();
        if(comm.equals("newtrader")) {
            if(Empires.getGame().getTraders().size() < 3) {
                Trader t = new Trader();
                Empires.getGame().getTraders().add(t);
                Empires.getGame().getBeings().add(t);
                new Thread(t).start();
            } else {
                System.err.println("You can have only 3 traders.");
            }
        } else if(comm.equals("newlegion")) {
            if(Empires.getGame().barbariansInCity()) {
                Legion l = new Legion();
                Empires.getGame().getLegions().add(l);
                Empires.getGame().getBeings().add(l);
                new Thread(l).start();
            }
        } else if(comm.equals("repair")) {
            if(Empires.getGame().getSelected() != null) {
                Trader t = (Trader) Empires.getGame().getSelected();
                t.getCart().setBroken(false);
            }
        } else if(comm.equals("break")) {
            if(Empires.getGame().getSelected() != null) {
                Trader t = (Trader) Empires.getGame().getSelected();
                t.getCart().setBroken(true);
            }
        } else if(comm.equals("produce")) {
            if(Empires.getGame().getSelected() != null) {
                City c = (City) Empires.getGame().getSelected();
                c.produce();
            }
        } else if(comm.equals("removetr")) {
            if(Empires.getGame().getSelected() != null) {
                Trader t = (Trader) Empires.getGame().getSelected();
                t.die();
            }
        } else if(comm.equals("changecity")) {
            if(Empires.getGame().getSelected() != null) {
                Trader t = (Trader) Empires.getGame().getSelected();
                if(!newCity.getText().isEmpty()) {
                    try {
                        t.setNext(Empires.getGame().getCities().get(Integer.parseInt(newCity.getText())));
                        t.setChanged(true);
                    } catch(NumberFormatException e) {
                        System.err.println("THIS IS NOT A NUMBER");
                    }
                }
            }
        }
    }
    
}
