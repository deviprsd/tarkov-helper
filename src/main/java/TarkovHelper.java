import res.*;
import ui.*;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author Maxx Persin
 *
 */
public class TarkovHelper {

    // Disregard all of row 1 and all of column 2 when parsing csv file

    private LinkedList<LinkedList<Ammo>> chartData;

    /**
     *
     */
    public TarkovHelper() {
        chartData = new LinkedList<>();
        readCSV();
        drawUI();
    }

    private void drawUI() {
        JFrame mainWindow = new JFrame();
        mainWindow.setTitle("Tarkov Helper");
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(1341, 541);
        mainWindow.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        mainWindow.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addTab("Ammo", null, new AmmoPanel(chartData), null);
        tabbedPane.addTab("Maps", null, new MapPanel(), null);
        tabbedPane.addTab("Credits", null, new CreditPanel(), null);
    }

    private void readCSV() {
        try {
            File csvFile = new File(
                    Objects.requireNonNull(getClass().getClassLoader().getResource("tarkov_ammo_chart.csv")).getFile()
            );
            BufferedReader csvReader = new BufferedReader(new FileReader(csvFile));
            int idx1 = 0, idx2 = 0;
            String line, prevCaliber = "";

            while ((line = csvReader.readLine()) != null) {
                String[] values = line.split(",");

                if (!values[0].equalsIgnoreCase("Caliber")) {
                    if (!values[0].equals(prevCaliber)) {
                        idx1++;
                        idx2 = 0;
                        prevCaliber = values[0];
                        chartData.add(new LinkedList<>());
                    }

                    if (prevCaliber.equals("")) {
                        prevCaliber = values[0];
                    }

                    Logger.info(prevCaliber);

                    int[] temp = {
                            Integer.parseInt(values[6]), Integer.parseInt(values[7]),
                            Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]),
                            Integer.parseInt(values[11])
                    };

                    Ammo a = new Ammo(
                            values[0], values[1], Integer.parseInt(values[2]), Integer.parseInt(values[3]),
                            Integer.parseInt(values[4]),
                            Integer.parseInt(values[5].substring(0, values[5].length() - 1)), temp
                    );

                    chartData.getLast().add(a);
                    idx2++;
                }
            }
        } catch (Exception e) {
            Logger.setLogLevel(Logger.ALL);
            Logger.err(e);
            JOptionPane.showMessageDialog(null, "Error reading ammo chart file");
            System.exit(-1);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new TarkovHelper();
    }
}
