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

    public final int windowWidth = 1341;
    public final int windowHeight = 541;
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
        mainWindow.setSize(windowWidth, windowHeight);
        mainWindow.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        mainWindow.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addTab("Ammo", null, new AmmoPanel(), null);
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
