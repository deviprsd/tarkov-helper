import util.Logger;
import ui.AmmoPanel;
import ui.CreditPanel;
import ui.MapPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Maxx Persin
 *
 */
public class TarkovHelper {

    private int windowWidth;
    private int windowHeight;
    /**
     *
     */
    public TarkovHelper() {
        Logger.setLogLevel(Logger.ALL);
        drawUI();
    }

    private void drawUI() {
        JFrame mainWindow = new JFrame();
        mainWindow.setTitle("Tarkov Helper");
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        windowHeight = mainWindow.getHeight();
        windowWidth = mainWindow.getWidth();
        mainWindow.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        mainWindow.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addTab("Ammo", null, new AmmoPanel(windowWidth, windowHeight), null);
        tabbedPane.addTab("Maps", null, new MapPanel(), null);
        tabbedPane.addTab("Credits", null, new CreditPanel(), null);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new TarkovHelper();
    }
}
