package ui;

import beans.Map;
import com.opencsv.bean.CsvToBeanBuilder;
import util.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;
import java.awt.Image;

public class MapPanel extends JPanel implements ActionListener {
    private HashMap<String, Map> chartData;
    private String[] mapTypes;

    private int windowHeight;
    private int windowWidth;

    private JButton btnSelect;
    private JComboBox<String> mapComboBox;
    private JLabel mapLabel;
    private JScrollPane mapScrollPane;

    public MapPanel(int windowHeight, int windowWidth) {
        super();

        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;

        chartData = new HashMap<>();
        readMapCSV();

        Set<String> dynamicAmmoTypes = chartData.keySet();
        mapTypes = chartData.keySet().toArray(new String[dynamicAmmoTypes.size()]);

        this.setupUI();
    }

    private void readMapCSV() {
        try {
            List<Map> maps = new CsvToBeanBuilder<Map>(new InputStreamReader(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tarkov_map_chart.csv"))))
                            .withType(Map.class).build().parse();

            for (Map map : maps) {
                chartData.put(map.getName(), map);
            }
        } catch (Exception e) {
            Logger.err(e);
            JOptionPane.showMessageDialog(null, "Error reading map files");
            System.exit(-1);
        }
    }

    private void setupUI() {
        JLabel lblMapSelector = new JLabel("Map Selector");
        lblMapSelector.setBounds(20, 14, 87, 14);

        mapComboBox = new JComboBox<>(mapTypes);
        mapComboBox.setEditable(false);
        mapComboBox.setBounds(129, 11, 87, 20);

        btnSelect = new JButton("Select");
        btnSelect.setBounds(226, 10, 90, 23);
        btnSelect.addActionListener(this);

        ImageIcon currentMap = new ImageIcon();
        mapLabel = new JLabel(currentMap);
        // mapLabel.setSize(200, 200);
        // mapLabel.setLocation(116, 65);
        mapScrollPane = new JScrollPane(mapLabel);
        mapScrollPane.setLocation(50, 50);

        this.setLayout(null);
        this.add(lblMapSelector);
        this.add(mapComboBox);
        this.add(btnSelect);
        this.add(mapScrollPane);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSelect) {
            displaySelectedMap();
        }
    }

    private void displaySelectedMap() {
        ImageIcon currentMap = new ImageIcon(Objects
                .requireNonNull(chartData.get(mapComboBox.getItemAt(mapComboBox.getSelectedIndex())).getFileAsImage()));
        mapLabel.setIcon(currentMap);
        mapScrollPane.setSize((int)(windowWidth * 1.66), (windowHeight / 2) - 50);
       // mapScrollPane.setViewport
        if (currentMap.getIconWidth() >= windowWidth) {

        } else {

        }
        
        //mapLabel.setHorizontalAlignment(JLabel.CENTER);
        //mapLabel.setVerticalAlignment(JLabel.CENTER);

        this.repaint();
    }
}
