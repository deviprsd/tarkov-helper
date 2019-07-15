import res.Ammo;
import res.Logger;
import ui.AmmoPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author Maxx Persin
 *
 */
public class TarkovHelper implements ActionListener {

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

        // res.Ammo Panel
        tabbedPane.addTab("Ammo", null, new AmmoPanel(chartData), null);

        // Maps Panel
        JPanel mapsPane = new JPanel();
        tabbedPane.addTab("Maps", null, mapsPane, null);

        JLabel lblMapSelector = new JLabel("Map Selector");
        lblMapSelector.setBounds(20, 14, 87, 14);

        JComboBox mapComboBox = new JComboBox(new Object[]{});
        mapComboBox.setBounds(129, 11, 87, 20);

        mapsPane.setLayout(null);
        mapsPane.add(lblMapSelector);
        mapsPane.add(mapComboBox);

        JButton btnSelect = new JButton("Select");
        btnSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnSelect.setBounds(226, 10, 90, 23);
        mapsPane.add(btnSelect);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Credits", null, panel_2, null);
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

                if (!values[0].equals("Ammo Type")) {
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

    @Override
    public void actionPerformed(ActionEvent arg0) {

    }
}
